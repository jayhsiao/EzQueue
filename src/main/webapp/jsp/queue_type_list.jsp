<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
<c:forEach items="${QUEUE_TYPES}" var="QUEUE_TYPE" varStatus="status">
	<div id="<c:out value="${QUEUE_TYPE.queueTypeId }"/>" class="queue-type 
		<c:choose>
			<c:when test="${status.index eq 0}">
				col-xs-12 col-md-12
			</c:when>
			<c:otherwise>
				col-xs-6 col-md-2
			</c:otherwise>
		</c:choose>" style="cursor: pointer; text-align: center;">
		<h1><i class="fa <c:out value="${QUEUE_TYPE.iconClass }"/>"></i><c:out value="${QUEUE_TYPE.dscr }"/></h1>
	</div>
</c:forEach>
</div>