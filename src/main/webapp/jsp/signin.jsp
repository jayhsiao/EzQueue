<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>signin</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<LINK type="text/css" rel="stylesheet" href="../css/test.css">
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/fb.js"></script>
<script type="text/javascript" src="../js/signin.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
</head>
<body>
<div class="container">
<form id="submitForm" style="font-family: 微軟正黑體;">
	<input type="text" id="userId" name="userId" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
	<br/>
	<input type="text" id="name"   name="name"   class="form-control" placeholder="Username" aria-describedby="basic-addon1">
	
	<button type="button" id="btn_signin" class='btn btn-default'>Sign In</button>
</form>
</div>
</body>
</html>
