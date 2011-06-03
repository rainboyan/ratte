import org.scribe.builder.*
import org.scribe.builder.api.*
import org.scribe.model.*
import org.scribe.oauth.*
import groovyx.gaelyk.logging.GroovyLogger

class T163 {
    static final GroovyLogger log = new GroovyLogger('T163')
	
    String API_ROOT = "http://api.t.163.com"

    OAuthService service
    Token token

    T163() {
        def config = ConfigurationHolder.config
		service = new ServiceBuilder().provider(T163Api.class).apiKey(config.t163.oauth.consumer_key).apiSecret(config.t163.oauth.consumer_secret).build()
        token = new Token(config.t163.oauth.token, config.t163.oauth.token_secret)
    }

    def Post(url, service, token, message) {
		try {
            def request = new OAuthRequest(Verb.POST, url)
            request.addBodyParameter("status", message)
			service.signRequest(token, request)
			def response = request.send()
            
			if (response.wasSuccessful()) {
				log.info "T163 Post success: ${response.code}"
			}
			else { 
                log.warning "T163 Post fail: ${response.code} : ${response.body}"
            }
		}
		catch (any) {
			log.warning "T163 Post ${url} error: ${any.message}"
		}
	}

	def updateStatus(message) {
		Post("$API_ROOT/statuses/update.json", service, token, message)
	}
}
