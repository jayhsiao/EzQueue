<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>

<span id="span_star">
	<c:choose>
		<c:when test="${not empty QUEUE_DETAIL.star}">
			<c:forEach begin="1" end="${QUEUE_DETAIL.star.starNum}" step="1">
				<i style="cursor: pointer;" class="info-color fa fa-star"></i>
			</c:forEach>
			<c:forEach begin="1" end="${5 - QUEUE_DETAIL.star.starNum}" step="1">
				<i style="cursor: pointer;" class="info-color fa fa-star-o"></i>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach begin="1" end="5" step="1">
				<i style="cursor: pointer;" class="info-color fa fa-star-o"></i>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</span>