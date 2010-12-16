				<div id="sidebar" class="right">
					<ul>
						<li class="panel"></li>
						<li class="panel divider">
							<div id="miniabout">
								<img class="thumbpic" src="${request.user?.profile_image_url_bigger ?: 'images/rain_avatar.jpg'}" alt="Rain's Avatar" />
								<div class="clear"></div>
								<ul>
									<li><strong>Name: </strong>${request.user?.name ?: 'Rain'}</li>
									<li><strong>Location: </strong>${request.user?.location ?: ''}</li>
									<li><strong>Web: </strong><a href="${request.user?.url ?: 'http://rainboyan.com'}">${request.user?.url ?: 'http://rainboyan.com'}</a></li>
									<li><strong>Bio: </strong>${request.user?.description ?: ''}</li>
									<li></li>
									<li><strong>Tweets: </strong>${request.user?.statuses_count ?: ''}</li>
								</ul>
								<ul class="socialmedia">
									<li><a href="http://twitter.com/rainboyan"><img src="/images/icons/twitter_16.png" alt="Rain's Twitter" /></a></li>
									<li><a href="http://facebook.com/rainboyan"><img src="/images/icons/facebook_16.png" alt="Rain's Facebook" /></a></li>
									<li><a href="http://cn.last.fm/user/rainboyan"><img src="/images/icons/lastfm_16.png" alt="Rain's LastFM" /></a></li>
									<li><a href="http://flickr.com/people/rainboyan"><img src="/images/icons/flickr_16.png" alt="Rain's Flickr" /></a></li>
									<li><a href="http://skype.com/rainboyan"><img src="/images/icons/skype_16.png" alt="Rain's Skype" /></a></li>
									<li><a href="/?feed=rss2"><img src="/images/icons/rss_16.png" alt="Rain's Feed" /></a></li>
									<li><a href="mailto:rainboyan@gmail.com"><img src="/images/icons/email_16.png" alt="Rain's Email" /></a></li>
								</ul>
							</div>
						</li>
						<li class="panel divider"><h2 class="panelTitle">Friends</h2>
							<ul class='xoxo blogroll'>
								<% request.friends.each { friend -> %>
								<a href="http://twitter.com/${friend.screen_name}"><img class="thumbpic" width="32" height="32" src="${friend?.profile_image_url}" alt="${friend.name}'s Avatar" /></a>
								<% } %>
							</ul>
						</li>
						<li class="panel divider"><h2 class="panelTitle">Links</h2>
							<ul class='xoxo blogroll'>
								<li><a href="http://rainboyan.com/">Rain's Blog</a></li>
								<li><a href="http://code.google.com/p/ratte/">Ratte</a></li>
								<li><a href="http://twitter.com/">Twitter</a></li>
								<li><a href="http://code.google.com/appengine">Google App Engine</a></li>
								<li><a href="http://gaelyk.appspot.com/">Gaelyk</a></li>
							</ul>
						</li>
					</div>
					<div class="clear"></div>
				</div>