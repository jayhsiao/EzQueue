<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html lang="en">
<head>
<title>排排</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="<c:url value="/css/ezqueue.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/fb_other.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<form id="submitForm" style="font-family: 微軟正黑體;">
<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand">排排</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse"
			aria-expanded="false" style="height: 1px;">
			<ul class="nav navbar-nav">
				<li>
					<a id="a_myQueue"><img src="http://graph.facebook.com/<c:out value="${RESPONSE_MAP.user.fbId}"/>/picture?width=12&height=14">&nbsp;<c:out value="${RESPONSE_MAP.user.name}"/><span style="display: none;"><i class="fa fa-spinner fa-spin"></i></span></a>
				</li>
				<li>
					<a id="a_promotions"><i class="fa fa-thumbs-up"></i>強力推薦<span style="display: none;"><i class="fa fa-spinner fa-spin"></i></span></a>
				</li>
				<li>
					<a id="a_favorite"><i class="fa fa-heart"></i>我的最愛<span style="display: none;"><i class="fa fa-spinner fa-spin"></i></span></a>
				</li>
				<li>
					<a id="a_queueing"><i class="fa fa-clock-o"></i>正在排隊<span style="display: none;"><i class="fa fa-spinner fa-spin"></i></span></a>
				</li>
				<li>
					<a id="a_create"><i class="fa fa-play"></i>建立排隊<span style="display: none;"><i class="fa fa-spinner fa-spin"></i></span></a>
				</li>
				<li>
					<a id="a_setting"><i class="fa fa-cog"></i>系統設定<span style="display: none;"><i class="fa fa-spinner fa-spin"></i></span></a>
				</li>
			</ul>
		</div>
	</div>
</nav>

<div class="container">
	<div id="div_main"></div>
</div>

</form>

<div id="fb-root"></div>

<input type="hidden" id="name"   name="name"   value="<c:out value="${RESPONSE_MAP.user.name}"/>">
<input type="hidden" id="userId" name="userId" value="<c:out value="${RESPONSE_MAP.user.userId}"/>">

</body>
</html>