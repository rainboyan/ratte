
def twitter = new Twitter()

request.user = twitter.user
request.friends = twitter.friends
request.tweets = twitter.tweets

forward 'index.gtpl'