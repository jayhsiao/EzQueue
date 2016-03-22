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
<script type="text/javascript" src="../js/util.js"></script>

<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- 選擇性佈景主題 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- 最新編譯和最佳化的 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="background-color: gray;">
<form id="submitForm" style="font-family: 微軟正黑體;">
<div align="center">
	<table style="width: 100%;">
		<tr>
			<td width="20%">
				<div class="list-group">
					<a class="list-group-item"><label id="label_promotions">強力推薦</label></a>
					<a class="list-group-item"><label id="label_favorite">我的最愛</label></a>
					<a class="list-group-item"><label id="label_myQueue">我的排隊</label></a>
					<a class="list-group-item"><label id="label_queueing">正在排隊</label></a>
					<a class="list-group-item"><label id="label_create">開啟排隊</label></a>
					<a class="list-group-item"><label id="label_setting">系統設定</label></a>
				</div>
			</td>
			<td  width="80%">
				<iframe id="mainFrame" name="mainFrame" src="" scrolling="auto" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</div>	
	<input type="hidden" id="name"    name="name"    value="<c:out value="${RESPONSE_MAP.user.name}"/>">
	<input type="hidden" id="userId"  name="userId"  value="<c:out value="${RESPONSE_MAP.user.userId}"/>">
</form>
</div>
</body>
</html>
