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
<script type="text/javascript" src="<c:url value="/js/queue_type_list.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue_list.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue_detail.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/create.js"/>"></script>
</head>
<body style="font-family: 微軟正黑體;">
<form id="submitForm" class="form-horizontal">
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div id="div_option" class="navbar-header pull-left">
			<div class="navbar-brand">
				<span style="padding-right: 10px;"><img src="<c:url value="/img/ezqueue.png"/>" width="26" height="26"></span><span class="hidden-xs">排排</span>
			</div>
			<ul class="nav navbar-nav pull-left">
				<li class="pull-left title-font-size">
					<a id="a_promotion" style="cursor: pointer;">
						<i class="fa fa-thumbs-up"></i><span class="hidden-xs">精選推薦</span>
					</a>
				</li>
				<li class="pull-left title-font-size">
					<a id="a_type" style="cursor: pointer;">
						<i class="fa fa-list-ul"></i><span class="hidden-xs">分類</span>
					</a>
				</li>
				<li class="pull-left title-font-size">
					<a id="a_search" style="cursor: pointer;" data-toggle="modal" data-target="#div_search_modal">
						<i class="fa fa-search"></i><span class="hidden-xs">搜尋</span>
					</a>
				</li>
			</ul>
		</div>
		<div class="navbar-header pull-right">
			<ul class="nav pull-left">
				<li id="li_facebook_login" style="display: none; margin-top: 5px;">
					<a id="a_fbLogin" style="cursor: pointer;">
						<fb:login-button scope="public_profile,email,manage_pages" onlogin="checkLoginState();"></fb:login-button>
					</a>
				</li>
				<li id="li_facebook_user" class="dropdown pull-right">
					<a	data-toggle="dropdown" style="color: #777; margin-top: 6px;"
						class="dropdown-toggle">
						<i id="i_facebook" class="fa fa-circle-o-notch fa-spin" style="font-size: 20px;"></i>
						<span id="span_user" style="display: none;">
							<img id="img_header_facebook_user" class="img-circle">
							<span id="span_header_facebook_user_name" class="hidden-xs"></span>
							<span class="caret"></span>
						</span>
					</a>
					<ul class="dropdown-menu">
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

<%@include file="/jsp/queue_detail_modal.jsp" %>

<input type="hidden" id="input_userId"              value="">
<input type="hidden" id="input_queueDetailId"       value="<c:out value="${QUEUE_DETAIL_ID }"/>">
<input type="hidden" id="input_init_limit"          value="<c:out value="${RESPONSE.limit }"/>">
<input type="hidden" id="input_init_offset"         value="<c:out value="${RESPONSE.offset }"/>">
<input type="hidden" id="input_offset"              value="">
<input type="hidden" id="input_url"                 value="">
</body>
</html>