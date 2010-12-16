
class ConfigurationHolder {
	static def config = null
	
	static def getConfig() {
        if (!config) {
            config = new ConfigSlurper().parse(new File("WEB-INF/config.groovy").text)
        }
		return config
	}
}