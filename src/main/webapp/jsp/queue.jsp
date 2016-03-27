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
<script type="text/javascript" src="<c:url value="/js/queue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body style="background-color: Silver;">
<div class="container">
<form style="font-family: 微軟正黑體;">
	<c:forEach items="${RESPONSE_MAP.queues}" var="queue">
		<div id="div_<c:out value="${queue.queueId}"/>" class='row' style='width: 40%;'>
			<div class='panel panel-primary'>
				<div class='panel-heading'>
					<table>
					<tr>
						<td width='100%' nowrap>
							<div class='panel-title'>
								<h3><c:out value="${queue.user.name}"/></h3>
							</div>
						</td>
					</tr>
					</table>
				</div>
				<div class='panel-body'>
					<div style='height: 200px'>
						<div id="div_dscr" style="display: none;">
							<c:out value="${queue.dscr}"/>
						</div>
						
						<div id="div_avgWaittingTime" style="display: none;">
							<h1 style="font-weight: bold;">平均等待時間 <c:out value="${queue.avgWaittingTime}"/> 秒</h1>
							<br/><br/>
						</div>
						
						<div id="div_btn-group" class="btn-group" data-toggle="buttons" style="display: none;">
							<label class="btn btn-default <c:if test="${queue.enable}">active</c:if>">
								<input type="radio" name="enable" id="enable_true" autocomplete="off" value="true"> 開啟
							</label>
							<label class="btn btn-default <c:if test="${!queue.enable}">active</c:if>">
								<input type="radio" name="enable" id="enable_false" autocomplete="off" value="false"> 暫時關閉
							</label>
							<br/><br/>
						</div>
						
						<div id="div_textarea" style="display: none;">
							簡介<br/>
							<textarea id="textarea_dscr" rows='3' cols='33' maxlength='150'></textarea>
						</div>
						
						<div>
							<button type='button' id="btn_create"  name='btn_create'  class='btn btn-default' style="display: none;" value="<c:out value="${queue.queueId}"/>">創造排隊</button>
							<button type='button' id="btn_edit"    name='btn_edit'    class='btn btn-default' style="display: none;" value="<c:out value="${queue.queueId}"/>">修改內容</button>
							<button type='button' id="btn_delete"  name='btn_delete'  class='btn btn-default' style="display: none;" value="<c:out value="${queue.queueId}"/>">刪除排隊</button>
							<button type='button' id="btn_save"    name='btn_save'    class='btn btn-default' style="display: none;" value="<c:out value="${queue.queueId}"/>">儲存變更</button>
							<button type='button' id="btn_queuing" name='btn_queuing' class='btn btn-default' style="display: none;" value="<c:out value="${queue.queueId}"/>">我要排隊</button>
						</div>
						
						<br/>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	
	<input type="hidden" id="isCreate"    value="<c:out value="${RESPONSE_MAP.isCreate}"/>">
	<input type="hidden" id="isPromotion" value="<c:out value="${RESPONSE_MAP.isPromotion}"/>">
	<input type="hidden" id="isFavorite"  value="<c:out value="${RESPONSE_MAP.isFavorite}"/>">
	<input type="hidden" id="isMyQueues"  value="<c:out value="${RESPONSE_MAP.isMyQueues}"/>">
	<input type="hidden" id="isQueuing"   value="<c:out value="${RESPONSE_MAP.isQueuing}"/>">
</form>
</div>
</body>
</html>
