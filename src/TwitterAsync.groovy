import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import groovyx.gaelyk.logging.GroovyLogger

class TwitterAsync {
    static final GroovyLogger log = new GroovyLogger('TwitterAsync')
    
    def init = false

    String username
    Twitter twitter
    Sina sina

    TwitterAsync() {
        if (!init) {
            def config = ConfigurationHolder.config
            username = config.twitter.username
            twitter = new Twitter()
            sina = new Sina()
        }
    }

    def getLatest() {
        def datastore = DatastoreServiceFactory.datastoreService
        def query = new Query("Twitter")
        query.addSort("dateCreated", Query.SortDirection.DESCENDING)
        PreparedQuery preparedQuery = datastore.prepare(query)
        def entities = preparedQuery.asList(withLimit(10))
        if (entities) {
            return entities[0].sinceId?.toLong()
        }
        else {
            return 0
        }
    }

	def execute() {
		try {
            def tweets = twitter.getTweets(getLatest())
            tweets.reverse().each { tweet ->
                sina.updateStatus(tweet.text)
                
                def entity = new Entity("Twitter")
                entity.sinceId = tweet.id
                entity.dateCreated = new Date()
                entity.save()
            }
		}
		catch (any) {
			log.warning "TwitterAsync execute: ${any.message}"
		}
	}
}
