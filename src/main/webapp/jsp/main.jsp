<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>main</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value="/css/ezqueue.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ezqueue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<form id="submitForm" target="mainFrame" style="font-family: 微軟正黑體;">
	<div class="page-header">
		<h1>排排 </h1>
	</div>
	<div align="center">
		<table style="width: 100%;">
			<tr>
				<td width="10%" valign="top">
					<div id="div_menu" class="list-group">
						<a class="list-group-item" id="a_myQueue"><img src="http://graph.facebook.com/<c:out value="${RESPONSE_MAP.user.fbId}"/>/picture?width=12&height=14"> <c:out value="${RESPONSE_MAP.user.name}"/></a>
						<a class="list-group-item" id="a_promotions"><i class="fa fa-thumbs-up"></i>強力推薦</a>
						<a class="list-group-item" id="a_favorite"><i class="fa fa-heart"></i>我的最愛</a>
						<a class="list-group-item" id="a_queueing"><i class="fa fa-clock-o"></i>正在排隊</a>
						<a class="list-group-item" id="a_create"><i class="fa fa-play"></i>建立排隊</a>
						<a class="list-group-item" id="a_setting"><i class="fa fa-cog"></i>系統設定</a>
					</div>
				</td>
				<td  width="90%" valign="top">
					<iframe id="mainFrame" name="mainFrame" src="" width="100%" scrolling="auto" frameborder="0"></iframe>
				</td>
			</tr>
		</table>
	</div>	
</form>

<input type="hidden" id="name"   name="name"   value="<c:out value="${RESPONSE_MAP.user.name}"/>">
<input type="hidden" id="userId" name="userId" value="<c:out value="${RESPONSE_MAP.user.userId}"/>">
	
</div>
</body>
</html>
