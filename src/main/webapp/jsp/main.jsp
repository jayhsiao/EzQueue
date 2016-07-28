<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="shortcut icon" href="<c:url value="/img/favicon.ico"/>"/>
<link rel="bookmark"      href="<c:url value="/img/favicon.ico"/>"/>
<link rel="stylesheet" href="<c:url value="/css/ezqueue.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/fb_home.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/create.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue_list.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue_detail.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ezqueue_star.js"/>"></script>
</head>
<body style="font-family: 微軟正黑體;">
<form id="submitForm" class="form-horizontal">
<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button id="btn_navbar" type="button" class="navbar-toggle collapsed"
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
					<a id="a_myQueue" style="cursor: pointer;">
						<%@include file="/jsp/facebook_user.jsp" %>
					</a>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">
						<i class="fa fa-globe"></i>&nbsp;分類&nbsp;<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="ul_all">
						<c:forEach items="${RESPONSE_MAP.queueTypes}" var="queueType">
							<li>
								<a id="<c:out value="${queueType.queueTypeId }"/>">
									<i class="fa <c:out value="${queueType.iconClass }"/>"></i>&nbsp;<c:out value="${queueType.dscr }"/>
								</a>
							</li>
						</c:forEach>
					</ul>
				</li>
				<li>
					<a id="a_promotion" style="cursor: pointer;">
						<i class="fa fa-thumbs-up"></i>&nbsp;強力推薦
					</a>
				</li>
				<li>
					<a id="a_favorite" style="cursor: pointer;">
						<i class="fa fa-heart"></i>&nbsp;我的最愛
					</a>
				</li>
				<li>
					<a id="a_queueing" style="cursor: pointer;">
						<i class="fa fa-clock-o"></i>&nbsp;正在排隊
					</a>
				</li>
				<li>
					<a id="a_create" style="cursor: pointer;">
						<i class="fa fa-play"></i>&nbsp;建立排隊
					</a>
				</li>
				<li>
					<a id="a_notice" style="cursor: pointer;">
						<i class="fa fa-envelope-o"></i>&nbsp;通知
					</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">
						<i class="fa fa-cog"></i>&nbsp;設定<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a id="a_setting" style="cursor: pointer;">
								<i class="fa fa-cog"></i>&nbsp;系統設定
							</a>
						</li>
						<li>
							<a id="a_logout" style="cursor: pointer;">
								<i class="fa fa-sign-out"></i>&nbsp;登出
							</a>
						</li>
					</ul>
				</li>
			</ul>
			<div class="navbar-form navbar-right">
				<div class="input-group">
					<input type="text" id="input_search" class="form-control" placeholder="搜尋...">
					<span class="input-group-btn">
						<button type="button" id="btn_search" class="btn btn-default">
							<i class="fa fa-search"></i>&nbsp;
						</button>
					</span>
				</div>
			</div>
		</div>
	</div>
</nav>

<div class="container">
	<div id="div_main"></div>
	<div id="div_detail"></div>
</div>

</form>

<input type="hidden" id="input_userId"        value="<c:out value="${RESPONSE_MAP.user.userId}"/>">
<input type="hidden" id="input_userAccountId" value="">
<input type="hidden" id="input_queueTypeId"   value="">
<input type="hidden" id="input_queueId"       value="">
<input type="hidden" id="input_starId"        value="">
<input type="hidden" id="input_promotionId"   value="">
<input type="hidden" id="input_favoriteId"    value="">
<input type="hidden" id="input_queuingId"     value="">
<input type="hidden" id="input_isMyQueues"    value="">

</body>
</html>