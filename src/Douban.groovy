import org.scribe.builder.*
import org.scribe.builder.api.*
import org.scribe.model.*
import org.scribe.oauth.*
import groovyx.gaelyk.logging.GroovyLogger

class Douban {
    static final GroovyLogger log = new GroovyLogger('Douban')
	
    String API_ROOT = "http://api.douban.com"

    OAuthService service
    Token token

	Douban() {
		def config = ConfigurationHolder.config
		service = new ServiceBuilder().provider(DoubanApi.class).apiKey(config.douban.oauth.consumer_key).apiSecret(config.douban.oauth.consumer_secret).callback(config.douban.oauth.callback).build()
        token = new Token(config.douban.oauth.token, config.douban.oauth.token_secret)
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
			log.warning "Douban Get ${url} error: ${any.message}"
		}
	}

    def Post(url, service, token, message) {
		try {
            def request = new OAuthRequest(Verb.POST, url)
            request.addPayload(message)
			service.signRequest(token, request)
            request.addHeader("Content-Type", "application/atom+xml");
			def response = request.send()
			if (response.wasSuccessful()) {
				log.info "Douban Post success: ${response.code}"
			}
			else { 
                log.warning "Douban Post fail: ${response.code} : ${response.body}"
            }
		}
		catch (any) {
			log.warning "Douban Post ${url} error: ${any.message}"
		}
	}
    
    def saying(message) {
        Post("$API_ROOT/miniblog/saying", service, token, DoubanEntryBuilder.buildSaying(message))
    }

}

class DoubanEntryBuilder {
    public static String buildSaying(String message) {
		StringBuilder entry = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>")
		entry.append("<entry xmlns:ns0=\"http://www.w3.org/2005/Atom\" ")
		entry.append("xmlns:db=\"http://www.douban.com/xmlns/\">")
		entry.append(String.format("<content>%s</content>", message))
		entry.append("</entry>")
		return entry.toString()
    }
}