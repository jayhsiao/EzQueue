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
		<p><button id="btn_login" type="button">Connect to Facebook</button></p>
		<input id="scope" value="public_profile">
	<br/>
	<br/>
	<span id="span_result" class="label label-default"></span>
	<br/>
	<br/>
	<span id="span_result" class="label label-default"><c:out value="${profile.id }"/></span>
	<span id="span_result" class="label label-default"><c:out value="${profile.name }"/></span>
	<span id="span_result" class="label label-default"><c:out value="${profile.email }"/></span>
	<br/>
	<br/>
	<form action="/connect/facebook" method="POST">
         <input type="hidden" name="scope" value="email,public_profile" />
         <div class="formInfo">
             <p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
         </div>
         <p><button type="submit">Connect to Facebook</button></p>
     </form>
</div>
</div>
</body>
</html>
