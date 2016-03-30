<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>queue</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value="/css/ezqueue.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<form style="font-family: 微軟正黑體;">
	<c:forEach items="${RESPONSE_MAP}" var="map">
		<div 
			id="div_<c:out value="${map.queue.queueId}"/>" 
			class='panel panel-primary' 
			style='
			<c:choose>
				<c:when test="${map.isMyQueues}">
					display: block;
				</c:when>
				<c:otherwise>
					width: 420px; display: inline-block;
				</c:otherwise>
			</c:choose>'>
			
			<div class='panel-heading'>
				<table>
				<tr>
					<td width='100%' nowrap>
						<div class='panel-title'>
							<h4>
								<c:out value="${map.queue.user.name}"/>&nbsp;&nbsp;
								<c:if test="${not empty map.promotionId}">
									<span class="label label-danger">Hot</span>
								</c:if>
							</h4>
						</div>
					</td>
				</tr>
				</table>
			</div>
			<div class='panel-body'>
				<div>
					<button 
						type="button" 
						id="btn_favorite" 
						name="btn_favorite" 
						class="btn btn-warning btn-sm"
						value="<c:out value="${map.queue.queueId}"/>">
						<i id="i_heart" class="fa 
							<c:choose>
								<c:when test="${not empty map.favoriteId}">
									fa-heart
								</c:when>
								<c:otherwise>
									fa-heart-o
								</c:otherwise>
							</c:choose>
						">
							<c:if test="${map.isMyQueues}">
								<c:out value="${fn:length(map.queue.favorites)}"/>
							</c:if>
						</i>
					</button>
					<button type="button" id="btn_comment"  name="btn_comment"  class="btn btn-warning btn-sm" data-toggle="modal" data-target="#div_comments_<c:out value="${map.queue.queueId}"/>"><i class="fa fa-comment"></i></button>
					<br/><br/>
				</div>
				
				<div>
					<c:out value="${map.queue.dscr}" escapeXml="false"/>
					<br/><br/>
				</div>
				
				<c:if test="${!map.isMyQueues}">
				<div id="div_avgWaittingTime">
					<div style="display: inline-block;">
						<div class="panel panel-info">
							<div class="panel-heading">等待組數</div>
							<div class="panel-body" style="height: 45px; font-weight: bold;"><c:out value="${fn:length(map.queuings)}"/> 組</div>
						</div>
					</div>
					<div style="display: inline-block;">
						<div class="panel panel-info">
							<div class="panel-heading">今日平均等待時間</div>
							<div class="panel-body" style="height: 45px; font-weight: bold;"><c:out value="${map.avgWaittingTime}"/></div>
						</div>
					</div>
					<div style="display: inline-block;">
						<div class="panel panel-info">
							<div class="panel-heading">號碼牌</div>
							<div class="panel-body" style="height: 45px; font-weight: bold;"><span id="span_queueNum"><c:out value="${map.queueNum}"/></span>&nbsp;</div>
						</div>
					</div>
				</div>
				</c:if>
				
				<c:if test="${map.isMyQueues and not empty map.queuings}">
				<div>
					<c:forEach items="${map.queuings}" var="queuing">
						<div class="panel panel-default" style="width: 100px; display: inline-block; cursor: pointer;" data-toggle="modal" data-target="#div_queuing_<c:out value="${queuing.queuingId}"/>">
							<div class="panel-heading" align="center"><h1><span class="label label-success"><c:out value="${queuing.queueNum}"/></span></h1></div>
							<div class="panel-body" align="center"><img src="http://graph.facebook.com/<c:out value="${queuing.user.fbId}"/>/picture?width=50&height=50"><br/><c:out value="${queuing.user.name}"/></div>
						</div>
						
						<div id="div_queuing_<c:out value="${queuing.queuingId}"/>" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
							<div class="modal-dialog">
								<div class="modal-content" align="center">
									<span class="label label-success"><c:out value="${queuing.queueNum}"/></span>
									<c:out value="${queuing.user.name}"/>
									<img src="http://graph.facebook.com/<c:out value="${queuing.user.fbId}"/>/picture?width=50&height=50">
									<button type="button" class="btn btn-success" id="btn_queuing_success" name="btn_queuing_success" value="<c:out value="${queuing.queuingId}"/>"><i class="fa fa-thumbs-up"></i>完成</button>
									<button type="button" class="btn btn-warning" id="btn_queuing_pass"    name="btn_queuing_pass"    value="<c:out value="${queuing.queuingId}"/>"><i class="fa fa-thumbs-up"></i>過號</button>
									<button type="button" class="btn btn-danger"  id="btn_queuing_cancel"  name="btn_queuing_cancel"  value="<c:out value="${queuing.queuingId}"/>"><i class="fa fa-thumbs-up"></i>拒絕</button>
								</div>
							</div>
						</div>
					</c:forEach>
					<i class="fa fa-chevron-circle-right fa-6"></i>
				</div>
				</c:if>
				
				<div>
					<c:choose>
						<c:when test="${map.isMyQueues}">
							<button type='button' id="btn_edit" name="btn_edit" class='btn btn-default' value="<c:out value="${map.queue.queueId}"/>">編輯排隊</button>
						</c:when>
						<c:otherwise>
							<c:if test="${empty map.queuingId}">
								<button type='button' id="btn_queuing" name="btn_queuing" class='btn btn-default' value="<c:out value="${map.queue.queueId}"/>">我要排隊</button>
							</c:if>
							<c:if test="${not empty map.queuingId}">
								<button type='button' id="btn_cancel"  name="btn_cancel"  class='btn btn-default' value="<c:out value="${map.queue.queueId}"/>">取消排隊</button>
							</c:if>
						</c:otherwise>
					</c:choose>
					<span id="span_result"></span>
					<br/>
				</div>
				
				<input type="hidden" id="input_promotionId" value="<c:out value="${map.promotionId}"/>">
				<input type="hidden" id="input_favoriteId"  value="<c:out value="${map.favoriteId}"/>">
				<input type="hidden" id="input_queuingId"   value="<c:out value="${map.queuingId}"/>">
				<input type="hidden" id="input_isMyQueues"  value="<c:out value="${map.isMyQueues}"/>">
				
				<div id="div_comments_<c:out value="${map.queue.queueId}"/>" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content" align="center">
							<c:out value="${map.queue.user.name}"/><br/>
							<div class="fb-comments" data-width="700" data-href="http://local.ezqueue.com:8080/<c:out value="${map.queue.queueId}"/>" data-numposts="5" data-order-by="reverse_time"></div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</c:forEach>
	
</form>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.5&appId=554860634671080";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
</div>
</body>
</html>
