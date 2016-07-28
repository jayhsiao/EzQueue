<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<img id="img_photo" src="http://graph.facebook.com/<c:out value="${map.queue.user.fbId}"/>/picture?width=245&height=245">