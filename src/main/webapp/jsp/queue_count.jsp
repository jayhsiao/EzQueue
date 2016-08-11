<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span class="star-color" style="font-weight: bold;">
	<i class="fa fa-heart"></i>
	<c:out value="${RESPONSE_MAP.favoriteCount}"/>
	&nbsp;
	<i class="fa fa-users"></i>
	<c:out value="${RESPONSE_MAP.queuingCount}"/>
</span>