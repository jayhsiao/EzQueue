<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span class="star-color" style="font-weight: bold;">
	<i class="star-color fa fa-star"></i>
	<c:out value="${QUEUE_DETAIL.avgStar}"/>
	<span class="badge"><c:out value="${QUEUE_DETAIL.starsCount}"/></span>
	&nbsp;
	<i class="fa fa-heart"></i>
	<c:out value="${QUEUE_DETAIL.favoriteCount}"/>
	&nbsp;
	<i class="fa fa-users"></i>
	<span id="span_queuing_count"><c:out value="${QUEUE_DETAIL.queuingCount}"/></span>
</span>