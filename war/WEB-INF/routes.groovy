// routes 
get "/", forward: "/index.groovy", cache: 1.hours
get "/about", forward: "/about.groovy", cache: 1.hours
get "/blog", forward: "/blog.groovy", cache: 1.hours
get "/cron/twittersync", forward: "/cron/twittersync.groovy"
get "/favicon.ico", forward: "/images/favicon.ico"