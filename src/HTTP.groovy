class HTTP {
	def static Get(url, auth, closure) {
		try {
			def conn = new URL(url).openConnection()
			conn.setRequestProperty("Authorization", "Basic ${auth}")
			conn.setRequestMethod("GET")
			if (conn.responseCode == 200) {
				closure.call(conn.content.text)
			}
			else { }
		}
		catch (Exception e) {
			//
		}
	}
}
