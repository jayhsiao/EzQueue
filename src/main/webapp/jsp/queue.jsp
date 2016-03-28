<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>queue</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value="/css/ezqueue.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/queue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_util.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body style="background-color: silver;">
<div class="container">
<form style="font-family: 微軟正黑體;">
	<c:forEach items="${RESPONSE_MAP.queues}" var="queue">
		<div id="div_<c:out value="${queue.queueId}"/>" class='panel panel-primary' style='display: inline-block; width: 300px;'>
			<div class='panel-heading'>
				<table>
				<tr>
					<td width='100%' nowrap>
						<div class='panel-title'>
							<h4><c:out value="${queue.user.name}"/>&nbsp;&nbsp;&nbsp;<span class="label label-danger">New</span></h4>
						</div>
					</td>
				</tr>
				</table>
			</div>
			<div class='panel-body' style='height: 250px'>
				
				<div>
					<button 
						type="button" 
						id="btn_favorite" 
						name="btn_favorite" 
						class="btn btn-danger btn-xs"
						value="<c:out value="${queue.queueId}"/>">
						<i id="i_heart" class="fa"></i>我的最愛
					</button>
					<button type="button" id="btn_comment"  name="btn_comment"  class="btn btn-warning btn-xs" data-toggle="modal" data-target="#div_comments_<c:out value="${queue.queueId}"/>"><i class="fa fa-comment"></i>網友評論</button>
					<br/><br/>
				</div>
				
				<div>
					<c:out value="${queue.dscr}" escapeXml="false"/>
				</div>
				
				<div id="div_avgWaittingTime">
					<h3 style="font-weight: bold;"> <c:out value="${queue.avgWaittingTime}"/> 秒<small>（平均等待時間）</small></h3>
					<br/>
				</div>
				
				<div>
					<button type='button' id="btn_queuing" name="btn_queuing" class='btn btn-default' value="<c:out value="${queue.queueId}"/>" style="display: none;">我要排隊</button>
					<button type='button' id="btn_cancel"  name="btn_cancel"  class='btn btn-default' value="<c:out value="${queue.queueId}"/>" style="display: none;">取消排隊</button>
					<span id="span_result"></span>
					<br/>
				</div>
				
				<input type="hidden" id="input_queueId"    value="<c:out value="${queue.queueId}"/>">
				<input type="hidden" id="input_queuingId"  value="<c:out value="${queue.queuingId}"/>">
				<input type="hidden" id="input_favoriteId" value="<c:out value="${queue.favoriteId}"/>">
				
				<div id="div_comments_<c:out value="${queue.queueId}"/>" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content" align="center">
							<div class="fb-comments" data-width="700" data-href="http://local.ezqueue.com:8080/<c:out value="${queue.queueId}"/>" data-numposts="5" data-order-by="reverse_time"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	
</form>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.5&appId=554860634671080";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<input type="hidden" id="input_isPromotion" value="<c:out value="${RESPONSE_MAP.isPromotion}"/>">
<input type="hidden" id="input_isFavorite"  value="<c:out value="${RESPONSE_MAP.isFavorite}"/>">
<input type="hidden" id="input_isQueuing"   value="<c:out value="${RESPONSE_MAP.isQueuing}"/>">

</div>
</body>
</html>
