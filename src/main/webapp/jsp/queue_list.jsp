<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${RESPONSE_LIST}" var="RESPONSE_MAP">
<div class="queue col-sm-6 col-md-3">
	<div class="thumbnail">
		<div>
			<table style="width: 100%;">
				<tr>
					<td align="left" width="80%">
						<p><%@include file="/jsp/facebook_user.jsp" %></p>
					</td>
					<td align="right"width="20%">
						<p>
							<c:if test="${not empty RESPONSE_MAP.promotion}">
								<span class="label label-danger">Hot</span>
							</c:if>
						</p>
					</td>
				</tr>
			</table>
		</div>
		<img id="img_photo" style="cursor: pointer;" src="http://graph.facebook.com/<c:out value="${RESPONSE_MAP.queue.user.facebookId}"/>/picture?width=245&height=245">
		<div class="caption">
			<table style="width: 100%;">
				<tr>
					<td align="left" width="60%">
						<p><%@include file="/jsp/queue_star.jsp" %></p>
					</td>
					<td align="right" width="40%">
						<p><i class="fa <c:out value="${RESPONSE_MAP.queue.queueType.iconClass}"/>"></i></p>
					</td>
				</tr>
			</table>
			<h3 style="height: 50px;"><c:out value="${RESPONSE_MAP.queue.title}"/></h3>
			<p><%@include file="/jsp/queue_count.jsp" %></p>
		</div>
		
		<input type="hidden" id="input_list_queueId" value="<c:out value="${RESPONSE_MAP.queue.queueId}"/>">
	</div>
</div>
</c:forEach>
