binding {
    config = new ConfigSlurper().parse(new File("WEB-INF/config.groovy").text)
}