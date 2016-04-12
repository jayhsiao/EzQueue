<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value="/css/ezqueue.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/fb_home.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/home.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<div id="div_login" align="center" style="font-family: 微軟正黑體;">
	<br/>
	<br/>
	<br/>
	<br/>
	<fb:login-button scope="public_profile,email" onlogin="checkLoginState();"></fb:login-button><span id="span_spin" style="display: none;"><i class="fa fa-spinner fa-spin"></i></span>
	<br/>
	<br/>
	<span id="span_result" class="label label-default"></span>
	<br/>
	<br/>
	<br/>
	<br/>
</div>
</div>
</body>
</html>
