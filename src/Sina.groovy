import org.scribe.builder.*
import org.scribe.builder.api.*
import org.scribe.model.*
import org.scribe.oauth.*
import groovyx.gaelyk.logging.GroovyLogger

class Sina {
    static final GroovyLogger log = new GroovyLogger('Sina')
	
    String API_ROOT = "http://api.t.sina.com.cn"

    OAuthService service
    Token token

    Sina() {
        def config = ConfigurationHolder.config
		service = new ServiceBuilder().provider(SinaApi.class).apiKey(config.sina.oauth.consumer_key).apiSecret(config.sina.oauth.consumer_secret).build()
        token = new Token(config.sina.oauth.token, config.sina.oauth.token_secret)
    }

    def Post(url, service, token, message) {
		try {
            def request = new OAuthRequest(Verb.POST, url)
            request.addBodyParameter("status", message)
			service.signRequest(token, request)
			def response = request.send()
            
			if (response.wasSuccessful()) {
				log.info "Sina Post success: ${response.code}"
			}
			else { 
                log.info "Sina Post fail: ${response.code}"
            }
		}
		catch (any) {
			log.warning "Sina Post ${url} error: ${any.message}"
		}
	}

	def updateStatus(message) {
		Post("$API_ROOT/statuses/update.xml", service, token, message)
	}
}
