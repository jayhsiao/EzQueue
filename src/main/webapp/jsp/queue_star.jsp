<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span class="star-color" style="font-weight: bold;">
	<i class="star-color fa fa-star"></i>
	<c:out value="${RESPONSE_MAP.avgStar}"/>
	/
	<c:out value="${RESPONSE_MAP.totalStar}"/>
	<span class="badge"><c:out value="${RESPONSE_MAP.starsCount}"/></span>
</span>
