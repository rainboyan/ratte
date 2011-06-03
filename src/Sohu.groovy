import org.scribe.builder.*
import org.scribe.builder.api.*
import org.scribe.model.*
import org.scribe.oauth.*
import groovyx.gaelyk.logging.GroovyLogger

class Sohu {
    static final GroovyLogger log = new GroovyLogger('Sohu')
	
    String API_ROOT = "http://api.t.sohu.com"

    OAuthService service
    Token token

    Sohu() {
        def config = ConfigurationHolder.config
		service = new ServiceBuilder().provider(SohuApi.class).apiKey(config.sohu.oauth.consumer_key).apiSecret(config.sohu.oauth.consumer_secret).build()
        token = new Token(config.sohu.oauth.token, config.sohu.oauth.token_secret)
    }

    def Post(url, service, token, message) {
		try {
            def request = new OAuthRequest(Verb.POST, url)
            request.addBodyParameter("status", message)
			service.signRequest(token, request)
			def response = request.send()
            
			if (response.wasSuccessful()) {
				log.info "Sohu Post success: ${response.code}"
			}
			else { 
                log.warning "Sohu Post fail: ${response.code} : ${response.body}"
            }
		}
		catch (any) {
			log.warning "Sohu Post ${url} error: ${any.message}"
		}
	}

	def updateStatus(message) {
		Post("$API_ROOT/statuses/update.xml", service, token, message)
	}
}
