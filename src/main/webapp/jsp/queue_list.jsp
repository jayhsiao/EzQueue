<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${not empty QUEUES}">
		<c:forEach items="${QUEUES}" var="QUEUE_DETAIL" varStatus="status">
		<c:if test="${(status.index+1) % 3 == 1}">
			<div class="row">
		</c:if>
			<div id="div_<c:out value="${QUEUE_DETAIL.queue.queueId}"/>" class="queue col-xs-12 col-sm-4 col-md-4" style="cursor: pointer;">
				<table style="width: 100%;" class="queue-top-buffer">
					<tr>
						<td width="10%" valign="top">
							<p>
								<img src="http://graph.facebook.com/<c:out value='${QUEUE_DETAIL.user.facebookId }'/>/picture?width=80&height=80">
								<input type="hidden" id="input_list_queueId" value="<c:out value="${QUEUE_DETAIL.queue.queueId}"/>">
							</p>
						</td>
						<td width="90%" style="padding-left: 15px; vertical-align: top;">
							<p>
								<img class="img-circle" src="http://graph.facebook.com/<c:out value='${QUEUE_DETAIL.user.facebookId }'/>/picture?width=12&height=12">
								<span style="word-break : break-all;"><c:out value='${QUEUE_DETAIL.user.name }'/></span>
								<c:if test="${QUEUE_DETAIL.user.isVerified}">
									<img width="16px" height="16px" src="<c:url value="/img/facebook-verified.png"/>">
								</c:if>
								<c:if test="${not empty QUEUE_DETAIL.promotion}">
									<span class="label label-danger"><i class="fa fa-thumbs-up"></i></span>
								</c:if>
							</p>
							<p style="font-size: x-large;"><c:out value="${QUEUE_DETAIL.queue.title}"/></p>
							<p><%@include file="/jsp/queue_count.jsp" %></p>
						</td>
					</tr>
				</table>
			</div>
		<c:if test="${(status.index+1) % 3 == 0}">
			</div>
		</c:if>
		</c:forEach>
		
		<input type="hidden" id="input_list_size" value="<c:out value="${QUEUES_SIZE}"/>">
	</c:when>
	<c:otherwise>
		<div style="text-align: center;">
			<h1>無資料</h1>
		</div>
	</c:otherwise>
</c:choose>
