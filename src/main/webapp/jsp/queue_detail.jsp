<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="margin-top-20 col-xs-12 col-md-8">
		<div class="jumbotron">
			<p><%@include file="/jsp/facebook_user.jsp" %></p>
			<h1><c:out value="${QUEUE_DETAIL.queue.title}"/></h1>
			<p><%@include file="/jsp/queue_count.jsp" %></p>
			<p><span id="span_dscr"><c:out value="${QUEUE_DETAIL.queue.dscr}" escapeXml="false"/></span></p>
			<p>
				<a id="btn_back" class='btn btn-default btn-lg' href="#div_<c:out value="${QUEUE_DETAIL.queue.queueId}"/>"><i class="fa fa-arrow-left"></i></a>
				<c:choose>
					<c:when test="${QUEUE_DETAIL.canEdit}">
						<button type="button" id="btn_edit_confirm"   class='btn btn-default btn-lg' data-toggle="modal" data-target="#div_edit_modal"><i class="fa fa-pencil"></i></button>
						<button type="button" id="btn_delete_confirm" class="btn btn-default btn-lg" data-toggle="modal" data-target="#div_delete_modal"><i class="fa fa-trash"></i></button>
						<button type="button" id="btn_open_confirm"   class="btn btn-default btn-lg" data-toggle="modal" data-target="#div_open_modal" style="<c:if test="${QUEUE_DETAIL.isOpen}">display: none;</c:if>">
							<i class="fa fa-play"></i>
						</button>
						<button type="button" id="btn_close_confirm"  class="btn btn-default btn-lg" data-toggle="modal" data-target="#div_close_modal" style="<c:if test="${not QUEUE_DETAIL.isOpen}">display: none;</c:if>">
							<i class="fa fa-pause"></i>
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" id="btn_favorite" class='btn btn-default btn-lg' style="display: none;">
							<c:choose>
								<c:when test="${not empty QUEUE_DETAIL.favorite}">
									<i class="fa fa-heart"></i>
								</c:when>
								<c:otherwise>
									<i class="fa fa-heart-o"></i>
								</c:otherwise>
							</c:choose>
						</button>
						<button type="button" id="btn_queuing" class='btn btn-default btn-lg' style="display: none;">
							<c:choose>
								<c:when test="${not empty QUEUE_DETAIL.queuing}">
									<i class="fa fa-user-times"></i>
								</c:when>
								<c:otherwise>
									<i class="fa fa-user-plus"></i>
								</c:otherwise>
							</c:choose>
						</button>
					</c:otherwise>
				</c:choose>
				<i class="fa <c:out value="${QUEUE_DETAIL.queue.queueType.iconClass}"/>"></i>
			</p>
			<p>
				<span id="span_message" class="label label-danger"></span>
			</p>
		</div>
	</div>
	
	<div class="col-xs-12 col-md-4">
		<table style="width: 100%;">
			<c:if test="${not QUEUE_DETAIL.canEdit}">
			<tr>
				<td style="text-align: center;"><h3><i class="fa fa-star"></i></h3></td>
				<td><h3><%@include file="/jsp/user_star.jsp" %></h3></td>
			</tr>
			<tr>
				<td style="text-align: center;"><h3><i class="fa fa-list-ol"></i></h3></td>
				<td><h3><span id="span_queueNum" class="label label-default"><c:out value="${QUEUE_DETAIL.queuing.queueNum}"/></span></h3></td>
			</tr>
			</c:if>
			<tr>
				<td style="text-align: center;"><h3><i class="fa fa-phone"></i></h3></td>
				<td><h3><span id="span_phone"><c:out value="${QUEUE_DETAIL.queue.phone}"/></span></h3></td>
			</tr>
			<tr>
				<td style="text-align: center;"><h3><i class="fa fa-map-marker"></i></h3></td>
				<td><h3><span id="span_address"><c:out value="${QUEUE_DETAIL.queue.address}"/></span></h3></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<iframe id="iframe_map" width="90%" height="230" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=<c:out value="${QUEUE_DETAIL.queue.address}"/>&z=16&output=embed&t="></iframe>
				</td>
			</tr>
		</table>
	</div>
	
	<hr>
	
	<c:if test="${QUEUE_DETAIL.canEdit}">
		<div class="col-xs-12 col-md-6">
			<h3><i class="fa fa-users"></i>&nbsp;正在排隊&nbsp;<span id="span_waiting_count" class="badge badge-default"><c:out value="${QUEUE_DETAIL.waitingCount}"/></span></h3>
			<table id="table_waiting" class="table table-hover">
				<c:forEach items="${QUEUE_DETAIL.waitingQueuings}" var="queuing">
					<tr>
						<td align="center" width="20%"><h1><span class="label label-default"><c:out value="${queuing.queueNum}"/></span></h1></td>
						<td align="center" width="40%" style="word-break : break-all;">
							<img class="img-circle" src="http://graph.facebook.com/<c:out value="${queuing.user.facebookId}"/>/picture?width=50&height=50">
							<br/>
							<c:out value="${queuing.user.name}"/>
						</td>
						<td width="40%">
							<button type="button" class="btn btn-default" name="btn_waiting_success"><h4><i class="fa fa-check"></i></h4></button>
							<button type="button" class="btn btn-default" name="btn_pass"><h4><i class="fa fa-clock-o"></i></h4></button>
							<input type="hidden" value="<c:out value="${queuing.queuingId}"/>">
						</td>
					</tr>
				</c:forEach>
			</table>
			<h3 style="text-align: center;">
				尚有<span id="span_waiting_less_count"><c:out value="${QUEUE_DETAIL.waitingLessCount }"/></span>位
			</h3>
		</div>
		<div class="col-xs-12 col-md-6">
			<h3><i class="fa fa-clock-o"></i>&nbsp;過號&nbsp;<span id="span_pass_count" class="badge badge-default"><c:out value="${QUEUE_DETAIL.passCount}"/></span></h3>
			<table id="table_pass" class="table table-hover">
				<c:forEach items="${QUEUE_DETAIL.passQueuings}" var="queuing">
					<tr>
						<td align="center" width="20%"><h1><span class="label label-default"><c:out value="${queuing.queueNum}"/></span></h1></td>
						<td align="center" width="40%" style="word-break : break-all;">
							<img class="img-circle" src="http://graph.facebook.com/<c:out value="${queuing.user.facebookId}"/>/picture?width=50&height=50">
							<br/>
							<c:out value="${queuing.user.name}"/>
						</td>
						<td width="40%">
							<button type="button" class="btn btn-default" name="btn_pass_success"><h4><i class="fa fa-check"></i></h4></button>
							<input type="hidden" value="<c:out value="${queuing.queuingId}"/>">
						</td>
					</tr>
				</c:forEach>
			</table>
			<h3 style="text-align: center;">
				尚有<span id="span_pass_less_count"><c:out value="${QUEUE_DETAIL.passLessCount }"/></span>位
			</h3>
		</div>
	</c:if>
	
	<hr>
	
	<div class="col-xs-12 col-md-12">
		<div class="fb-comments" data-width="100%" data-href="<c:out value="${ENV_URL }"/><c:out value="${QUEUE_DETAIL.queue.queueId}"/>" data-numposts="5" data-order-by="reverse_time"></div>
	</div>
	
	<input type="hidden" id="input_detail_userAccountId" value="<c:out value="${QUEUE_DETAIL.queue.user.userId}"/>">
	<input type="hidden" id="input_detail_queueId"       value="<c:out value="${QUEUE_DETAIL.queue.queueId}"/>">
	<input type="hidden" id="input_detail_starId"        value="<c:if test="${not empty QUEUE_DETAIL.star}"><c:out value="${QUEUE_DETAIL.star.starId}"/></c:if>">
	<input type="hidden" id="input_detail_promotionId"   value="<c:if test="${not empty QUEUE_DETAIL.promotion}"><c:out value="${QUEUE_DETAIL.promotion.promotionId}"/></c:if>">
	<input type="hidden" id="input_detail_favoriteId"    value="<c:if test="${not empty QUEUE_DETAIL.favorite}"><c:out value="${QUEUE_DETAIL.favorite.favoriteId}"/></c:if>">
	<input type="hidden" id="input_detail_queuingId"     value="<c:if test="${not empty QUEUE_DETAIL.queuing}"><c:out value="${QUEUE_DETAIL.queuing.queuingId}"/></c:if>">
	<input type="hidden" id="input_detail_queueStatus"   value="<c:out value="${QUEUE_DETAIL.queue.status}"/>">
</div>

<%@include file="/jsp/queue_detail_modal.jsp" %>

<div id="fb-root"></div>