<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span class="star-color" style="font-weight: bold;">
	<i class="star-color fa fa-star"></i>
	<c:out value="${QUEUE_DETAIL.avgStar}"/>
	&nbsp;
	<i class="fa fa-heart"></i>
	<c:out value="${QUEUE_DETAIL.favoriteCount}"/>
	&nbsp;
	<i class="fa fa-users"></i>
	<c:out value="${QUEUE_DETAIL.queuingCount}"/>
</span>