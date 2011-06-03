import org.scribe.builder.*
import org.scribe.builder.api.*
import org.scribe.model.*
import org.scribe.oauth.*
import groovyx.gaelyk.logging.GroovyLogger

class Qq {
    static final GroovyLogger log = new GroovyLogger('Qq')
	
    String API_ROOT = "http://open.t.qq.com"

    OAuthService service
    Token token

    Qq() {
        def config = ConfigurationHolder.config
		service = new ServiceBuilder().provider(QqApi.class).apiKey(config.qq.oauth.consumer_key).apiSecret(config.qq.oauth.consumer_secret).signatureType(SignatureType.QueryString).build()
        token = new Token(config.qq.oauth.token, config.qq.oauth.token_secret)
    }

    def Post(url, service, token, message) {
		try {
            def request = new OAuthRequest(Verb.POST, url)
            request.addBodyParameter("format", "xml")
            request.addBodyParameter("content", message)
            request.addBodyParameter("clientip", "202.96.133.134")
			service.signRequest(token, request)
			def response = request.send()
            
			if (response.wasSuccessful()) {
				log.info "Qq Post success: ${response.code}"
			}
			else { 
                log.warning "Qq Post fail: ${response.code} : ${response.body}"
            }
		}
		catch (any) {
			log.warning "Qq Post ${url} error: ${any.message}"
		}
	}

	def updateStatus(message) {
		Post("$API_ROOT/api/t/add", service, token, message)
	}
}
