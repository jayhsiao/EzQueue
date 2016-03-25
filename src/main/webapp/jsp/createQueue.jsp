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
<script type="text/javascript" src="<c:url value="/js/createQueue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body style="background-color: gray;">
<div class="container">
<form style="font-family: 微軟正黑體;">
	<div class='row' style='width: 80%;'>
		<div class='panel panel-primary'>
			<div class='panel-heading'>
				<table>
				<tr>
					<td width='100%' nowrap>
						<div class='panel-heading'>
							<div class='panel-title'>
								<h3><span id="span_name"></span></h3>
							</div>
						</div>
					</td>
				</tr>
				</table>
			</div>
			<div class='panel-body'>
				<div style='height: 200px'>
					<div class="btn-group" data-toggle="buttons">
						<label class="btn btn-default active">
							<input type="radio" name="enable" id="enable_0" autocomplete="off" value="true"> 開啟 
						</label>
						<label class="btn btn-default">
							<input type="radio" name="enable" id="enable_1" autocomplete="off" value="false"> 暫時關閉
						</label>
					</div>
					<br/><br/>
					簡介<br/>
					<textarea id='textarea_dscr' name='dscr' rows='3' cols='33' maxlength='150'></textarea>
					<br/><br/>
					<button type='button' id='btn_create' class='btn btn-default'>開啟排隊</button>
					<br/>
				</div>
			</div>
		</div>
	</div>
</form>
</div>
</body>
</html>
