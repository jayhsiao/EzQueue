<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>ezQueue</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<LINK type="text/css" rel="stylesheet" href="../css/test.css">
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/jquery-dateFormat.min.js"></script>
<script type="text/javascript" src="../js/fb.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/ezqueue.js"></script>

<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- 選擇性佈景主題 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- 最新編譯和最佳化的 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<form id="submitForm" action="/init/5" method="get" style="font-family: 微軟正黑體;">
	<div id="div_login" align="center">
		<br/>
		<br/>
		<br/>
		<br/>
		<div class="fb-login-button" data-max-rows="1" data-size="medium" data-show-faces="false" data-auto-logout-link="false"></div>
		<br/>
		<br/>
		<br/>
		<br/>
		<button type="submit" id="btn_login" class="btn btn-lg btn-default">登入</button>
	</div>
</form>
</div>
</body>
</html>
