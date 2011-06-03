import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import groovyx.gaelyk.logging.GroovyLogger

class TwitterAsync {
    static final GroovyLogger log = new GroovyLogger('TwitterAsync')
    
    def init = false
    def config

    String username
    Twitter twitter


    TwitterAsync() {
        if (!init) {
            config = ConfigurationHolder.config
            username = config.twitter.username
            twitter = new Twitter()
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
                if (config.sina) {
                	def sina = new Sina()
                    sina.updateStatus(tweet.text)
                }
                if (config.qq) {
                	def qq = new Qq()
                    qq.updateStatus(tweet.text)
                }
                if (config.douban) {
                	def douban = new Douban()
                    douban.saying(tweet.text)
                }
                if (config.t163) {
                	def t163 = new T163()
                    t163.updateStatus(tweet.text)
                }
                if (config.sohu) {
                	def sohu = new Sohu()
                    sohu.updateStatus(tweet.text)
                }
                if (config.fanfou) {
                	def fanfou = new Fanfou()
                    fanfou.updateStatus(tweet.text)
                }
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
