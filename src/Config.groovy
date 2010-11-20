
class Config {
	def config = null
	
	Config() {
		def configFile = new File("WEB-INF/config.groovy")
		config = new ConfigSlurper().parse(configFile.text)
	}
	
	def getTwitter() {
		return config.twitter
	}
}