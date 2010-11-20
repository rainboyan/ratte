<% include '/WEB-INF/includes/header.gtpl' %>

<div id="main" class="left">
	<ol id="timeline" class="statuses">
		<% request.tweets.eachWithIndex { tweet, i -> %>
		<% if (i == 0) { %>
		<li class="hentry u-rainboyan status latest-status" id="status_${tweet.id}">
		<% } else { %>
		<li class="hentry u-rainboyan status" id="status_${tweet.id}">
		<% } %>
		    <span class="status-body">
		    <span class="status-content">
		        <span class="entry-content">${tweet.text}</span>
			</span><br/>
		    <span class="meta entry-meta" data="{}">
		  	<a class="entry-date" rel="bookmark" href="http://twitter.com/rainboyan/status/${tweet.id}">
		    <span class="published timestamp" data="{time:'${tweet.created_at}'}">${tweet.created_at_local}</span></a>
		  	<span>via ${tweet.source}</span>
		  	</span>
		    <ul class="meta-data clearfix"></ul>
		  	</span>
		</li>
		<% } %>
	</ol>
	<div id="pagination">
    	<a href="/" class="round more" id="more" rel="next">more</a>
	</div>
</div>

<% include '/WEB-INF/includes/footer.gtpl' %>

