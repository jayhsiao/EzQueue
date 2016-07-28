<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>

<span id="span_star">
	您的評分
	<c:choose>
		<c:when test="${not empty RESPONSE_MAP.star}">
			<c:forEach begin="1" end="${RESPONSE_MAP.star.starNum}" step="1">
				<i style="cursor: pointer;" class="star-color fa fa-star"></i>
			</c:forEach>
			<c:forEach begin="1" end="${5 - RESPONSE_MAP.star.starNum}" step="1">
				<i style="cursor: pointer;" class="star-color fa fa-star-o"></i>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach begin="1" end="5" step="1">
				<i style="cursor: pointer;" class="star-color fa fa-star-o"></i>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</span>