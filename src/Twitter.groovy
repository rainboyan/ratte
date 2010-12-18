import java.text.DateFormat
import java.text.SimpleDateFormat
import org.scribe.builder.*
import org.scribe.builder.api.*
import org.scribe.model.*
import org.scribe.oauth.*
import groovy.util.slurpersupport.GPathResult
import groovyx.gaelyk.logging.GroovyLogger

class Twitter {
    static final GroovyLogger log = new GroovyLogger('Twitter')

	static final DateFormat twitterFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
	
    String API_ROOT = "http://twitter.com"
	
    String username

    OAuthService service
    Token token

    XmlSlurper slurper = new XmlSlurper()

	Twitter() {
		def config = ConfigurationHolder.config
        username = config.twitter.username
		service = new ServiceBuilder().provider(TwitterApi.class).apiKey(config.twitter.oauth.consumer_key).apiSecret(config.twitter.oauth.consumer_secret).build()
        token = new Token(config.twitter.oauth.token, config.twitter.oauth.token_secret)
	}

	Map parseUser(GPathResult root)
	{
	    def user = [:]
	    root.children().each {
	        user[it.name()] = it as String
	    }
	
	    def status = [:]
	    root.status.children().each {
	        status[it.name()] = it as String
	    }
	    user.profile_image_url_bigger = user?.profile_image_url[0..-11] + 'bigger' + user?.profile_image_url[-4..-1]
	    user.status = status
	    return user
	}
    
    def Get(url, service, token, closure) {
		try {
            def request = new OAuthRequest(Verb.GET, url)
			service.signRequest(token, request)
			def response = request.send()
			if (response.wasSuccessful()) {
				closure.call(response.body)
			}
			else { }
		}
		catch (any) {
			log.warning "HTTP Get ${url} error: ${any.message}"
		}
	}

    Map getUser() {
		Get("$API_ROOT/users/show/${username}.xml", service, token) { data ->
			def user = slurper.parseText(data)
			return parseUser(user)
		}
    }
    
    List<Map> getFollowers() {
    	def followers = []
        Get("$API_ROOT/statuses/followers/${username}.xml", service, token) { data ->
        	def users = slurper.parseText(data)
			users.children().each { user ->
			    followers << parseUser(user)
			}
        }
        return followers
    }
    
    List<Map> getFriends() {
    	def friends = []
        Get("$API_ROOT/statuses/friends/${username}.xml", service, token) { data ->
        	def users = slurper.parseText(data)
			users.children().each { user ->
			    friends << parseUser(user)
			}
        }
        return friends
    }
    
    List<Map> getTweets() {
    	getTweets(-1)
    }

    List<Map> getTweets(long sinceId) {
    	def tweets = []
        def url = "$API_ROOT/statuses/user_timeline/${username}.xml"
        if (sinceId > 0) {
            url = url + "?since_id=" + sinceId
        }
        else if (sinceId == 0) {
            url = url + "?count=5"
        }
        Get(url, service, token) { data ->
        	def statuses = slurper.parseText(data)
			statuses.children().each { status ->
			    def tweet = [:]
			    status.children().each {
			        tweet[it.name()] = it as String
			    }
			    tweet.created_at_local = DateUtils.formatDate(DateUtils.getLocalDate(twitterFormat.parse(status.created_at.toString())))
			    tweets << tweet
			}
        }
        return tweets
    }
}