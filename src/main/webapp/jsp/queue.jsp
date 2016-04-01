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
			class='panel panel-default' 
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
				<div class='panel-title'>
					<table style="width: 100%;">
						<tr>
							<td width="50%" align="left">
								<h4>
									<c:out value="${map.queue.title}"/>
									<c:if test="${not empty map.promotionId}">
										&nbsp;&nbsp;<span class="label label-danger">Hot</span>
									</c:if>
								</h4>
							</td>
							<td width="50%" align="right">
								<h4>
									<c:forEach begin="1" end="5" step="1" var="count">
										<i class="fa fa-star<c:if test="${count > map.star}">-o</c:if>"></i>
									</c:forEach>
									（ <c:out value="${fn:length(map.queue.stars)}"/> ）
								</h4>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class='panel-body'>
				<div>
					<c:choose>
						<c:when test="${map.isMyQueues}">
							<button type='button' id="btn_save"   name="btn_save"   class='btn btn-default' value="<c:out value="${map.queue.queueId}"/>" style="display: none;"><i class="fa fa-floppy-o"></i></button>
							<button type='button' id="btn_edit"   name="btn_edit"   class='btn btn-default' value="<c:out value="${map.queue.queueId}"/>"><i class="fa fa-pencil"></i></button>
							<button type='button' id="btn_delete" name="btn_delete" class='btn btn-default' value="<c:out value="${map.queue.queueId}"/>"><i class="fa fa-trash"></i></button>
						</c:when>
						<c:otherwise>
							<button type='button' id="btn_queuing"  name="btn_queuing"  class='btn btn-default' value="<c:out value="${map.queue.queueId}"/>" style="<c:if test="${not empty map.queuingId}">display: none;</c:if>"><i class="fa fa-play"></i></button>
							<button type='button' id="btn_cancel"   name="btn_cancel"   class='btn btn-default' value="<c:out value="${map.queue.queueId}"/>" style="<c:if test="${empty map.queuingId}">display: none;</c:if>"><i class="fa fa-stop"></i></button>
							<button type="button" id="btn_favorite" name="btn_favorite" class="btn btn-warning" value="<c:out value="${map.queue.queueId}"/>">
								<i id="i_heart" class="fa 
									<c:choose>
										<c:when test="${not empty map.favoriteId}">
											fa-heart
										</c:when>
										<c:otherwise>
											fa-heart-o
										</c:otherwise>
									</c:choose>
								"></i>
							</button>
						</c:otherwise>
					</c:choose>
					<button type="button" id="btn_comment"  name="btn_comment"  class="btn btn-warning" data-toggle="modal" data-target="#div_comments_<c:out value="${map.queue.queueId}"/>"><i class="fa fa-comments"></i></button>
					<span id="span_result"></span>
					<br/><br/>
				</div>
				
				<div >
					<span id="span_dscr"><c:out value="${map.queue.dscr}" escapeXml="false"/></span>
					<textarea id="textarea_dscr" rows='3' cols='33' class="form-control" maxlength='150' placeholder="簡介" style="display: none;"></textarea>
					<br/><br/>
				</div>
				
				<c:if test="${!map.isMyQueues}">
				<div>
					<div style="display: inline-block;">
						<div class="panel panel-primary">
							<div class="panel-heading">等待組數</div>
							<div class="panel-body" style="height: 45px; font-weight: bold; text-align: center;"><c:out value="${map.queuingCount}"/>&nbsp;</div>
						</div>
					</div>
					<div style="display: inline-block;">
						<div class="panel panel-primary">
							<div class="panel-heading">今日平均等待時間</div>
							<div class="panel-body" style="height: 45px; font-weight: bold; text-align: center;"><c:out value="${map.avgWaittingTime}"/>&nbsp;</div>
						</div>
					</div>
					<div style="display: inline-block;">
						<div class="panel panel-primary">
							<div class="panel-heading">號碼牌</div>
							<div class="panel-body" style="height: 45px; font-weight: bold; text-align: center;"><span id="span_queueNum" class="label label-success"><c:out value="${map.queueNum}"/></span>&nbsp;</div>
						</div>
					</div>
				</div>
				</c:if>
				
				<c:if test="${map.isMyQueues and not empty map.queuings}">
				<div>
					<c:forEach items="${map.queuings}" var="queuing">
						<div id="div_queuing_<c:out value="${queuing.queuingId}"/>" class="panel panel-default" style="display: inline-block; cursor: pointer;" data-toggle="modal" data-target="#div_modal_<c:out value="${queuing.queuingId}"/>">
							<div class="panel-heading" align="center"><h1><span class="label label-info"><c:out value="${queuing.queueNum}"/></span></h1></div>
							<div class="panel-body" align="center"><img src="http://graph.facebook.com/<c:out value="${queuing.user.fbId}"/>/picture?width=50&height=50"><br/><c:out value="${queuing.user.name}"/></div>
						</div>
						
						<div id="div_modal_<c:out value="${queuing.queuingId}"/>" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
							<div class="modal-dialog modal-sm">
								<div class="modal-content" align="center">
									<h1><span class="label label-info"><c:out value="${queuing.queueNum}"/></span></h1>
									<br/>
									<img src="http://graph.facebook.com/<c:out value="${queuing.user.fbId}"/>/picture?width=50&height=50">
									<br/>
									<c:out value="${queuing.user.name}"/>
									<br/><br/>
									<button type="button" class="btn btn-success" id="btn_queuing_success" name="btn_queuing_success" value="<c:out value="${queuing.queuingId}"/>" data-dismiss="modal"><h4><i class="fa fa-circle-o"></i></h4></button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-danger"  id="btn_queuing_pass"    name="btn_queuing_pass"    value="<c:out value="${queuing.queuingId}"/>" data-dismiss="modal"><h4><i class="fa fa-times"></i></h4></button>
									<br/><br/>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				</c:if>
				
				<input type="hidden" id="input_promotionId" value="<c:out value="${map.promotionId}"/>">
				<input type="hidden" id="input_favoriteId"  value="<c:out value="${map.favoriteId}"/>">
				<input type="hidden" id="input_queuingId"   value="<c:out value="${map.queuingId}"/>">
				<input type="hidden" id="input_isMyQueues"  value="<c:out value="${map.isMyQueues}"/>">
				
				<div id="div_comments_<c:out value="${map.queue.queueId}"/>" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content" align="center">
							<h1 class="star">
								<c:forEach begin="1" end="5" step="1" var="count">
									<i style="cursor: pointer;" class="fa fa-star<c:if test="${count > map.star }">-o</c:if>"></i>
								</c:forEach>
							</h1>
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
  js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.5&appId=592070890950054";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
</div>
</body>
</html>
