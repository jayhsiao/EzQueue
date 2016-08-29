<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span class="star-color" style="font-weight: bold;">
	<i class="star-color fa fa-star"></i>
	<c:out value="${QUEUE_DETAIL.avgStar}"/>
	/
	<c:out value="${QUEUE_DETAIL.totalStar}"/>
	<span class="badge"><c:out value="${QUEUE_DETAIL.starsCount}"/></span>
</span>
