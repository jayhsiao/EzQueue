<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${QUEUES}" var="QUEUE_DETAIL" varStatus="status">
<c:if test="${(status.index+1) % 3 == 1}">
	<div class="row">
</c:if>
	<div id="div_<c:out value="${QUEUE.queue.queueId}"/>" class="queue margin_top_20 col-xs-12 col-sm-4 col-md-4">
		<table style="width: 100%;" class="queue-top-buffer">
			<tr>
				<td width="10%" valign="top">
					<p>
						<img id="img_photo" style="cursor: pointer;" src="http://graph.facebook.com/<c:out value='${QUEUE_DETAIL.user.facebookId }'/>/picture?width=80&height=80">
						<input type="hidden" id="input_list_queueId" value="<c:out value="${QUEUE_DETAIL.queue.queueId}"/>">
					</p>
				</td>
				<td width="90%" style="padding-left: 15px; vertical-align: top;">
					<p><%@include file="/jsp/facebook_user.jsp" %></p>
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