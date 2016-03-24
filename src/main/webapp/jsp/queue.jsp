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

	<table>
		<c:forEach items="${RESPONSE_MAP.queues}" var="queue">
		<tr>
			<td>
				<div class="container">
					<div class='row' style='width: 80%;'>
						<div class='panel panel-primary'>
							<div class='panel-heading'>
								<table>
								<tr>
									<td width='100%' nowrap>
										<div class='panel-title'>
											<h1><c:out value="${queue.user.name}"/></h1>
										</div>
									</td>
								</tr>
								</table>
							</div>
							<div class='panel-body'>
								<div style='height: 200px'>
									<h2><c:out value="${queue.dscr}"/></h2>
									
									<div class="btn-group" data-toggle="buttons" style="display: none;">
										<label class="btn btn-default <c:if test="${queue.enable}">active</c:if>">
											<input type="radio" name="enable" id="enable_true" autocomplete="off" value="true"> 開啟
										</label>
										<label class="btn btn-default <c:if test="${!queue.enable}">active</c:if>">
											<input type="radio" name="enable" id="enable_false" autocomplete="off" value="false"> 暫時關閉
										</label>
									</div>
									<br/><br/>
									<button type='button' id='btn_edit'    class='btn btn-default' style="display: none;">修改內容</button>
									<button type='button' id='btn_delete'  class='btn btn-default' style="display: none;">刪除排隊</button>
									<button type='button' id='btn_save'    class='btn btn-default' style="display: none;">儲存變更</button>
									<button type='button' id='btn_queuing' class='btn btn-default' style="display: none;">我要排隊</button>
									<br/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<input type="hidden" id="userId" name="userId" value="<c:out value="${RESPONSE_MAP.user.userId}"/>">
</form>
</div>
</body>
</html>
