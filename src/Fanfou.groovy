import org.scribe.builder.*
import org.scribe.builder.api.*
import org.scribe.model.*
import org.scribe.oauth.*
import groovyx.gaelyk.logging.GroovyLogger

class Fanfou {
    static final GroovyLogger log = new GroovyLogger('Fanfou')
	
    String API_ROOT = "http://api.fanfou.com"

    OAuthService service
    Token token

    Fanfou() {
        def config = ConfigurationHolder.config
		service = new ServiceBuilder().provider(FanfouApi.class).apiKey(config.fanfou.oauth.consumer_key).apiSecret(config.fanfou.oauth.consumer_secret).build()
        token = new Token(config.fanfou.oauth.token, config.fanfou.oauth.token_secret)
    }

    def Post(url, service, token, message) {
		try {
            def request = new OAuthRequest(Verb.POST, url)
            request.addBodyParameter("status", message)
            request.addBodyParameter("source", "ratte")
			service.signRequest(token, request)
			def response = request.send()
            
			if (response.wasSuccessful()) {
				log.info "Fanfou Post success: ${response.code}"
			}
			else { 
                log.warning "Fanfou Post fail: ${response.code} : ${response.body}"
            }
		}
		catch (any) {
			log.warning "Fanfou Post ${url} error: ${any.message}"
		}
	}

	def updateStatus(message) {
		Post("$API_ROOT/statuses/update.xml", service, token, message)
	}
}
