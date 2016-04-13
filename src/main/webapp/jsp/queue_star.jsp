<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
	<c:when test="${map.avgStar > 0}">
		<span class="star-color"><c:out value="${map.avgStar}"/></span>&nbsp;
		<c:forEach begin="1" end="${map.avgStar / 1}" step="1">
			<i class="star-color fa fa-star"></i>
		</c:forEach>
		<c:choose>
			<c:when test="${map.avgStar % 1 > 0}">
				<i class="star-color fa fa-star-half-o"></i>
				<c:forEach begin="1" end="${5 - (map.avgStar / 1) - 1}" step="1">
					<i class="star-color fa fa-star-o"></i>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach begin="1" end="${5 - (map.avgStar / 1)}" step="1">
					<i class="star-color fa fa-star-o"></i>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<span class="badge"><c:out value="${fn:length(map.queue.stars)}"/></span>
	</c:when>
	<c:otherwise>
		尚無評分
	</c:otherwise>
</c:choose>