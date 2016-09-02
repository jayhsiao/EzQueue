<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>

<html lang="en">
<head>
<title>排排</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="<c:url value="/img/favicon.ico"/>"/>
<link rel="bookmark"      href="<c:url value="/img/favicon.ico"/>"/>
<link rel="apple-touch-icon" href="<c:url value="/img/ezqueue_60x60.png"/>">
<link rel="stylesheet" href="<c:url value="/css/ezqueue.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/facebook.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue_list.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue_detail.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/create.js"/>"></script>
</head>
<body style="font-family: 微軟正黑體;">
<form id="submitForm" class="form-horizontal">
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button id="btn_navbar" type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<i class="fa fa-bars"></i>
			</button>
			<a class="navbar-brand" href="/ezqueue/home">
				<span><img src="<c:url value="/img/ezqueue.png"/>" width="28" height="28"></span>&nbsp;排排
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse"
			aria-expanded="false" style="height: 1px;">
			<ul class="nav navbar-nav">
				<li id="li_promotion">
					<a id="a_promotion" style="cursor: pointer;">
						<i class="fa fa-thumbs-up"></i>&nbsp;精選推薦
					</a>
				</li>
				<li id="li_type" class="dropdown">
					<a class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">
						<i id="i_type" class="fa fa-list"></i>&nbsp;<span id="span_type">分類</span>&nbsp;
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="ul_type_all">
						<li>
							<a id="ALL">
								<i class="fa fa-th"></i>&nbsp;<span>全部</span>
							</a>
						</li>
						<c:forEach items="${RESPONSE.queueTypes}" var="queueType">
							<li>
								<a id="<c:out value="${queueType.queueTypeId }"/>">
									<i class="fa <c:out value="${queueType.iconClass }"/>"></i>&nbsp;<span><c:out value="${queueType.dscr }"/></span>
								</a>
							</li>
						</c:forEach>
					</ul>
				</li>
				<li id="li_search" style="display: none;">
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
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="li_facebook_login" style="display: none;">
					<a id="a_fbLogin" style="cursor: pointer;">
						<fb:login-button scope="public_profile,email,manage_pages" onlogin="checkLoginState();"></fb:login-button>
					</a>
				</li>
				<li id="li_facebook_user" class="dropdown">
					<a class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">
						<i id="i_facebook" class="fa fa-spinner fa-spin"></i>
						<span id="span_user" style="display: none;">
							<img id="img_header_facebook_user" class="img-circle">
							<span id="span_header_facebook_user_name"></span>
							<span class="caret"></span>
						</span>
					</a>
					<ul class="dropdown-menu" id="ul_all">
						<li class="dropdown-header">排隊</li>
						<li>
							<a id="a_me" style="cursor: pointer;">
								<i class="fa fa-user"></i>&nbsp;我的排隊
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
						<li class="dropdown-header">系統</li>
						<li>
							<a id="a_logout" style="cursor: pointer;">
								<i class="fa fa-sign-out"></i>&nbsp;登出
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>

<div class="container">
	<div id="div_list">
		<div id="div_list_queue" class="margin-top-20"></div>
		<div align="center" class="margin_top_20">
			<button id="btn_more" type="button" class="btn btn-default" style="width: 40%; display: none;">更多</button>
		</div>
	</div>
	<div id="div_detail"></div>
</div>

</form>

<input type="hidden" id="input_userId"              value="">
<input type="hidden" id="input_queueDetailId"       value="<c:out value="${QUEUE_DETAIL_ID }"/>">
<input type="hidden" id="input_init_limit"          value="<c:out value="${RESPONSE.limit }"/>">
<input type="hidden" id="input_init_offset"         value="<c:out value="${RESPONSE.offset }"/>">
<input type="hidden" id="input_init_queuing_limit"  value="<c:out value="${RESPONSE.queuingLimit }"/>">
<input type="hidden" id="input_init_queuing_offset" value="<c:out value="${RESPONSE.queuingOffset }"/>">
<input type="hidden" id="input_offset"              value="">
<input type="hidden" id="input_url"                 value="">
</body>
</html>