<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<span class="label label-default"><i class="fa fa-heart"></i>&nbsp;<c:out value="${fn:length(map.queue.favorites)}"/></span>
<span class="label label-default"><i class="fa fa-users"></i>&nbsp;<c:out value="${fn:length(map.queue.queuings)}"/></span>
<span class="label label-default"><i class="fa fa-eye"></i>&nbsp;<c:out value="${map.queue.readCount}"/></span>