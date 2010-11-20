def twitter = new Twitter()

request.user = twitter.user
request.friends = twitter.friends

forward 'about.gtpl'