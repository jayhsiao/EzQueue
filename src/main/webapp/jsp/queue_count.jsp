<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span class="info-color" style="font-weight: bold;">
	<i class="fa fa-star"></i>
	<c:out value="${QUEUE_DETAIL.avgStar}"/>
	<span class="badge"><c:out value="${QUEUE_DETAIL.starsCount}"/></span>
	&nbsp;
	<i class="fa fa-heart"></i>
	<span class="favorite_count"><c:out value="${QUEUE_DETAIL.favoriteCount}"/></span>
	&nbsp;
	<i class="fa fa-users"></i>
	<span class="queuing_count"><c:out value="${QUEUE_DETAIL.queuingCount}"/></span>
</span>