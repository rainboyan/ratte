<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="zh-CN">
<head profile="http://gmpg.org/xfn/11">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="generator" content="groovy" />
	<title> <%= request.user?.name ?: 'Rain'%>&#039;s Site </title>
	<link rel="shortcut icon" href="/images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="/css/main.css" media="screen" />
</head>
<body>
	<div id="header">
		<a name="top"></a>
		<div id="nameContainer">
			<a id="siteName" href="/"><%= request.user?.name ?: 'Rain'%>&#039;s Site</a><br />
			 Twitter @ Google App Engine
		</div>
		<div id="navWrapper">
			<ul id="nav">
				<li class="page_item"><a href="/" title="Home">Home</a></li>
				<li class="page_item"><a href="/blog" title="Home">Blog</a></li>
				<li class="page_item page-item-2"><a href="/about" title="关于">About</a></li>
			</ul>
		</div>
	</div>
	<div id="contentWrapper">
		<div id="content">
			<div id="wrapper">

