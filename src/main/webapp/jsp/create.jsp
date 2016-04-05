<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>create</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value="/css/ezqueue.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/create.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<form style="font-family: 微軟正黑體;">
	<div class='panel panel-default' style='width: 420px;'>
		<div class='panel-heading'>
			<table>
			<tr>
				<td width='100%' nowrap>
					<div class='panel-title'>
						<h4><span id="span_title"></span></h4>
					</div>
				</td>
			</tr>
			</table>
		</div>
		<div class='panel-body'>
		
			<div class="form-group row col-xs-10">
				<div id="div_btn-group" class="btn-group" data-toggle="buttons">
					<label class="btn btn-default active">
						<input type="radio" name="enable" id="enable_true" autocomplete="off" value="true" checked> 開啟
					</label>
					<label class="btn btn-default">
						<input type="radio" name="enable" id="enable_false" autocomplete="off" value="false"> 暫時關閉
					</label>
					<br/><br/>
				</div>
			</div>
			
			<div class="form-group row col-xs-10">
				<input type="text" class="form-control" id="input_title" name="title" maxlength="45" placeholder="名稱">
			</div>
			<div class="form-group row col-xs-10">
				<input type="text" class="form-control" id="input_phone" name="title" maxlength="45" placeholder="電話">
			</div>
			<div class="form-group row col-xs-10">
				<input type="text" class="form-control" id="input_address" name="title" maxlength="45" placeholder="地址">
			</div>
			<div class="form-group row col-xs-10">
				<textarea id="textarea_dscr" rows='3' cols='33' class="form-control" maxlength='150' placeholder="簡介"></textarea>
			</div>
			
			<div class="form-group row col-xs-10">
				<button type='button' id="btn_create" class='btn btn-default'>建立排隊</button>
			</div>
			<div id="div_result"></div>
			
			<br/>
		</div>
	</div>
	
</form>
</div>
</body>
</html>
