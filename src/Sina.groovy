import groovyx.gaelyk.logging.GroovyLogger
import weibo4j.Status
import weibo4j.Weibo
import weibo4j.WeiboException

class Sina {
    static final GroovyLogger log = new GroovyLogger('Sina')

    Weibo weibo

    Sina() {
        def config = ConfigurationHolder.config
        weibo = new Weibo()
        weibo.setOAuthConsumer(config.sina.consumer.key as String, config.sina.consumer.secret as String)
        weibo.setToken(config.sina.oauth.token as String, config.sina.oauth.token_secret as String)
    }

	def updateStatus(message) {
		try {
            Status status = weibo.updateStatus(message)
		}
		catch (any) {
			log.warning "Sina updateStatus: ${any.message}"
		}
	}
}
