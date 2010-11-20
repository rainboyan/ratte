// routes 
get "/", forward: "/index.groovy", cache: 24.hours
get "/about", forward: "/about.groovy", cache: 24.hours
get "/favicon.ico", forward: "/images/favicon.ico"