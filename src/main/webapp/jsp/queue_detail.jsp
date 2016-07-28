<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-md-8">
			<div class="jumbotron">
				<p><%@include file="/jsp/facebook_user.jsp" %></p>
				<h1><c:out value="${RESPONSE_MAP.queue.title}"/></h1>
				<p><%@include file="/jsp/queue_star.jsp" %>&nbsp;</p>
				<p><c:out value="${RESPONSE_MAP.queue.dscr}" escapeXml="false"/></p>
				<p><span id="btn_back" class='btn btn-default'><i class="fa fa-arrow-left"></i>&nbsp;回上頁</span></p>
			</div>
		</div>
		
		<div class="col-md-4">
			<table style="width: 100%">
				<tr>
					<td width="15%" nowrap align="center"><h3><i class="fa fa-star"></i></h3></td>
					<td width="85%" nowrap><h3><%@include file="/jsp/user_star.jsp" %></h3></td>
				</tr>
				<tr>
					<td nowrap align="center"><h3><i class="fa fa-heart"></i></h3></td>
					<td nowrap><h3>最愛人數&nbsp;&nbsp;<c:out value="${RESPONSE_MAP.favoriteCount}"/></h3></td>
				</tr>
				<tr>
					<td nowrap align="center"><h3><i class="fa fa-users"></i></h3></td>
					<td nowrap><h3>排隊人數&nbsp;&nbsp;<c:out value="${RESPONSE_MAP.queuingCount}"/></h3></td>
				</tr>
				<tr>
					<td nowrap align="center"><h3><i class="fa fa-clock-o"></i></h3></td>
					<td nowrap><h3>平均等待&nbsp;&nbsp;<c:out value="${RESPONSE_MAP.avgWaittingTime}"/></h3></td>
				</tr>
				<tr>
					<td nowrap align="center"><h3><i class="fa fa-list-ol"></i></h3></td>
					<td nowrap><h3>排隊號碼&nbsp;&nbsp;<span id="span_queueNum" class="label label-default"><c:out value="${RESPONSE_MAP.queuing.queueNum}"/></span></h3></td>
				</tr>
				<tr>
					<td nowrap align="center"><h3><i class="fa fa-info"></i></h3></td>
					<td nowrap><h3>排隊明細</h3></td>
				</tr>
				<tr>
					<td nowrap></td>
					<td nowrap>
						<p><i class="fa fa-phone"></i>&nbsp;<c:out value="${RESPONSE_MAP.queue.phone}"/></p>
						<p><i class="fa fa-map-marker"></i>&nbsp;<a href="http://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=<c:out value="${RESPONSE_MAP.queue.address}"/>&z=16&output=&t=" target="_blank"><c:out value="${RESPONSE_MAP.queue.address}"/></a></p>
					</td>
				</tr>
			</table>
			<h3>
				<c:choose>
					<c:when test="${RESPONSE_MAP.canEdit}">
						<span id="btn_revert" class='btn btn-default' style="display: none;"><i class="fa fa-arrow-left"></i>&nbsp;</span>
						<span id="btn_save"   class='btn btn-default' style="display: none;"><i class="fa fa-floppy-o"></i>&nbsp;</span>
						<span id="btn_edit"   class='btn btn-default'><i class="fa fa-pencil"></i>&nbsp;</span>
						<span id="btn_delete" class='btn btn-default'><i class="fa fa-trash"></i>&nbsp;</span>
					</c:when>
					<c:otherwise>
						<span id="btn_favorite" class='btn btn-default'>
							<c:choose>
								<c:when test="${not empty RESPONSE_MAP.favorite}">
									<i class="fa fa-heart"></i>&nbsp;<span id="span_favorite_dscr">不喜歡了</span>
								</c:when>
								<c:otherwise>
									<i class="fa fa-heart-o"></i>&nbsp;<span id="span_favorite_dscr">加入最愛</span>
								</c:otherwise>
							</c:choose>
						</span>
						<span id="btn_queuing" class='btn btn-default'>
							<c:choose>
								<c:when test="${not empty RESPONSE_MAP.queuing}">
									<i class="fa fa-user-times"></i>&nbsp;<span id="span_queuing_dscr">不想等了</span>
								</c:when>
								<c:otherwise>
									<i class="fa fa-user-plus"></i>&nbsp;<span id="span_queuing_dscr">抽號碼牌</span>
								</c:otherwise>
							</c:choose>
						</span>
					</c:otherwise>
				</c:choose>
			</h3>
		</div>
		
		<div class="col-md-8">
			<hr>
			<div class="fb-comments" data-width="100%" data-href="http://local.ezqueue.com:8080/<c:out value="${RESPONSE_MAP.queue.queueId}"/>" data-numposts="5" data-order-by="reverse_time"></div>
		</div>
		
		<div class="row">
			<c:if test="${RESPONSE_MAP.canEdit}">
			<div class="col-xs-12">
				<hr>
				<c:forEach items="${RESPONSE_MAP.queuings}" var="queuing">
					<div id="div_queuing_<c:out value="${queuing.queuingId}"/>" class="panel panel-default" style="display: inline-block;">
						<div class="panel-heading" align="center"><h1><span class="label label-info"><c:out value="${queuing.queueNum}"/></span></h1></div>
						<div class="panel-body" align="center">
							<img src="http://graph.facebook.com/<c:out value="${queuing.user.id}"/>/picture?width=50&height=50">
							<br/>
							<c:out value="${queuing.user.name}"/>
							<br/><br/>
							<button type="button" class="btn btn-success" id="btn_queuing_success" name="btn_queuing_success"><h4><i class="fa fa-check"></i></h4></button>
							<input type="hidden" id="input_waitting" value="<c:out value="${queuing.queuingId}"/>">
						</div>
					</div>
				</c:forEach>
			</div>
			</c:if>
		</div>
	</div>
</div>

<div id="fb-root"></div>