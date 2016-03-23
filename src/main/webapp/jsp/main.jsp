<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>ezQueue</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/fb.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ezqueue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="background-color: gray;">
<form id="submitForm" style="font-family: 微軟正黑體;" target="mainFrame">
<div align="center">
	<table style="width: 100%;">
		<tr>
			<td width="10%" valign="top">
				<div id="div_menu" class="list-group">
					<a class="list-group-item" id="a_promotions"><i class="fa fa-thumbs-up"></i>強力推薦</a>
					<a class="list-group-item" id="a_favorite"><i class="fa fa-heart"></i>我的最愛</a>
					<a class="list-group-item" id="a_myQueue"><i class="fa fa-user"></i>我的排隊</a>
					<a class="list-group-item" id="a_queueing"><i class="fa fa-clock-o"></i>正在排隊</a>
					<a class="list-group-item" id="a_create"><i class="fa fa-play"></i>創造排隊</a>
					<a class="list-group-item" id="a_setting"><i class="fa fa-cog"></i>系統設定</a>
				</div>
			</td>
			<td  width="90%" valign="top">
				<iframe id="mainFrame" name="mainFrame" src="" width="100%" scrolling="auto" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</div>	
	<input type="hidden" id="name"   name="name"   value="<c:out value="${RESPONSE_MAP.user.name}"/>">
	<input type="hidden" id="userId" name="userId" value="<c:out value="${RESPONSE_MAP.user.userId}"/>">
</form>
</div>
</body>
</html>
