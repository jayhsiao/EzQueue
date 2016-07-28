<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span id="span_facebook_user" style="cursor: pointer;">
	<img class="img-circle" src="http://graph.facebook.com/<c:out value="${RESPONSE_MAP.user.fbId}"/>/picture?width=12&height=12">&nbsp;<c:out value="${RESPONSE_MAP.user.name}"/>
</span>