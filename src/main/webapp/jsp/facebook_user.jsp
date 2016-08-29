<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span id="span_facebook_user" style="cursor: pointer;">
	<img id="img_facebook_user" class="img-circle" src="http://graph.facebook.com/<c:out value='${QUEUE_DETAIL.user.facebookId }'/>/picture?width=12&height=12">
	<span id="span_facebook_user_name" style="word-break : break-all;"><c:out value='${QUEUE_DETAIL.user.name }'/></span>
	<c:if test="${QUEUE_DETAIL.user.isVerified}">
		<img width="16px" height="16px" src="<c:url value="/img/facebook-verified.png"/>">
	</c:if>
	<input type="hidden" id="input_userAccountId" value="<c:out value="${QUEUE_DETAIL.user.userId }"/>">
</span>