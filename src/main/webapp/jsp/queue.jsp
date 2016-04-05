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
<body>
<div class="container">
<form style="font-family: 微軟正黑體;">
	<c:forEach items="${RESPONSE_MAP}" var="map">
		<div id="div_<c:out value="${map.queue.queueId}"/>" class="col-sm-6 col-md-4" style="cursor: pointer;">
			<div class="thumbnail" data-toggle="modal" data-target="#div_info_<c:out value="${map.queue.queueId}"/>">
				<img src="http://graph.facebook.com/<c:out value="${map.queue.user.fbId}"/>/picture?width=283&height=283">
				<div class="caption">
					<h3><c:out value="${map.queue.title}"/></h3>
					<p>
						<c:if test="${not empty map.avgStar}">
							<span class="star-color"><c:out value="${map.avgStar}"/></span>&nbsp;
							<c:forEach begin="1" end="${map.avgStar}" step="1">
								<i class="star-color fa fa-star"></i>
							</c:forEach>
							<c:forEach begin="1" end="${5 - map.avgStar}" step="1">
								<i class="star-o-color fa fa-star-o"></i>
							</c:forEach>
							<span class="badge"><c:out value="${fn:length(map.queue.stars)}"/></span>
						</c:if>
						<c:if test="${not empty map.promotionId}">
							<span class="label label-danger">Hot</span>
						</c:if>
					</p>
				</div>
			</div>
		</div>
		
		<div id="div_info_<c:out value="${map.queue.queueId}"/>" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<div class="modal-title">
							<table width="100%">
								<tr>
									<td width="60%" valign="top">
										<h2><c:out value="${map.queue.title}"/></h2>
										<c:if test="${not empty map.avgStar}">
											<span class="star-color"><c:out value="${map.avgStar}"/></span>&nbsp;
											<c:forEach begin="1" end="${map.avgStar}" step="1">
												<i class="star-color fa fa-star"></i>
											</c:forEach>
											<c:forEach begin="1" end="${5 - map.avgStar}" step="1">
												<i class="star-o-color fa fa-star-o"></i>
											</c:forEach>
											<span class="badge"><c:out value="${fn:length(map.queue.stars)}"/></span>
										</c:if>
									</td>
									<td width="40%" valign="top" align="right">
										<c:if test="${!map.isMyQueues}">
										<div>
											<h1 class="star">
												<c:forEach begin="1" end="${map.star}" step="1">
													<i style="cursor: pointer;" class="star-color fa fa-star"></i>
												</c:forEach>
												<c:forEach begin="1" end="${5 - map.star}" step="1">
													<i style="cursor: pointer;" class="star-o-color fa fa-star-o"></i>
												</c:forEach>
											</h1>
										</div>
										</c:if>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="modal-body">
						<table width="100%">
							<tr>
								<td width="40%" valign="top">
									<div>
										<div>
											<table>
												<tr>
													<td valign="top"><h4><i class="fa fa-phone"></i></h4></td>
													<td valign="top">
														<h4><span id="span_phone"><c:out value="${map.queue.phone}"/></span></h4>
														<input type="text" class="form-control" id="input_phone" placeholder="電話" value="<c:out value="${map.queue.phone}"/>" style="display: none;">
													</td>
												</tr>
												<tr>
													<td valign="top"><h4><i class="fa fa-map-marker"></i></h4></td>
													<td valign="top">
														<h4><span id="span_address"><c:out value="${map.queue.address}"/></span></h4>
														<input type="text" class="form-control" id="input_address" placeholder="地址" value="<c:out value="${map.queue.address}"/>" style="display: none;">
													</td>
												</tr>
												<tr>
													<td valign="top"><h4><i class="fa fa-thumbs-up"></i></h4></td>
													<td valign="top">
														<h4><span id="span_dscr"><c:out value="${map.queue.dscr}" escapeXml="false"/></span></h4>
														<textarea id="textarea_dscr" rows='3' cols='33' class="form-control" maxlength='150' placeholder="簡介" style="display: none;"><c:out value="${map.queue.dscr}" escapeXml="false"/></textarea>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div>
										<br/>
										<c:choose>
											<c:when test="${map.isMyQueues}">
												<button type='button' id="btn_close"  name="btn_close"  class='btn btn-default' style="display: none;" data-dismiss="modal"></button>
												<button type='button' id="btn_back"   name="btn_back"   class='btn btn-default' style="display: none;"><i class="fa fa-arrow-left"></i></button>
												<button type='button' id="btn_save"   name="btn_save"   class='btn btn-default' style="display: none;"><i class="fa fa-floppy-o"></i></button>
												<button type='button' id="btn_edit"   name="btn_edit"   class='btn btn-default'><i class="fa fa-pencil"></i></button>
												<button type='button' id="btn_delete" name="btn_delete" class='btn btn-default'><i class="fa fa-trash"></i></button>
											</c:when>
											<c:otherwise>
												<button type='button' id="btn_queuing"  name="btn_queuing"  class='btn btn-success' style="<c:if test="${not empty map.queuingId}">display: none;</c:if>"><i class="fa fa-user-plus"></i></button>
												<button type='button' id="btn_cancel"   name="btn_cancel"   class='btn btn-danger'  style="<c:if test="${empty map.queuingId}">display: none;</c:if>"><i class="fa fa-user-times"></i></button>
												<button type="button" id="btn_favorite" name="btn_favorite" class="btn btn-warning">
													<i id="i_heart" class="fa 
														<c:choose>
															<c:when test="${not empty map.favoriteId}">
																fa-heart
															</c:when>
															<c:otherwise>
																fa-heart-o
															</c:otherwise>
														</c:choose>
													"></i>
												</button>
											</c:otherwise>
										</c:choose>
										<span id="span_result"></span>
									</div>
									
									<c:if test="${!map.isMyQueues}">
									<div align="center">
										<hr>
										<table width="100%">
											<tr>
												<td width="40%" align="center">
													<div>等待組數</div>
													<h1><c:out value="${map.queuingCount}"/>&nbsp;</h1>
												</td>
												<td width="60%" align="center">
													<div>今日平均等待時間</div>
													<h1><c:out value="${map.avgWaittingTime}"/>&nbsp;</h1>
												</td>
											</tr>
										</table>
										<hr>
										<div style="display: inline-block;">
											<div>號碼牌</div>
											<h1><span id="span_queueNum" class="label label-info label-lg"><c:out value="${map.queueNum}"/></span>&nbsp;</h1>
										</div>
									</div>
									</c:if>
								</td>
								<td width="60%" valign="top">
									<c:if test="${!map.isMyQueues}">
										<div>
											<iframe width="100%" height="300" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=<c:out value="${map.queue.address}"/>&z=16&output=embed&t="></iframe>
										</div>
									</c:if>
									<div class="fb-comments" data-width="100%" data-href="http://local.ezqueue.com:8080/<c:out value="${map.queue.queueId}"/>" data-numposts="5" data-order-by="reverse_time"></div>
								</td>
							</tr>
						</table>
						
						<c:if test="${map.isMyQueues and not empty map.queuings}">
						<div>
							<hr>
							<c:forEach items="${map.queuings}" var="queuing">
								<div id="div_queuing_<c:out value="${queuing.queuingId}"/>" class="panel panel-default" style="display: inline-block; cursor: pointer;" data-toggle="modal" data-target="#div_modal_<c:out value="${queuing.queuingId}"/>">
									<div class="panel-heading" align="center"><h1><span class="label label-info"><c:out value="${queuing.queueNum}"/></span></h1></div>
									<div class="panel-body" align="center">
										<img src="http://graph.facebook.com/<c:out value="${queuing.user.fbId}"/>/picture?width=50&height=50">
										<br/>
										<c:out value="${queuing.user.name}"/>
										<br/><br/>
										<button type="button" class="btn btn-success" id="btn_queuing_success" name="btn_queuing_success" value="<c:out value="${queuing.queuingId}"/>"><h4><i class="fa fa-circle-o"></i></h4></button>
										&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-danger"  id="btn_queuing_pass"    name="btn_queuing_pass"    value="<c:out value="${queuing.queuingId}"/>"><h4><i class="fa fa-times"></i></h4></button>
									</div>
								</div>
							</c:forEach>
						</div>
						</c:if>
						
					</div>
				</div>
			</div>
			
			<input type="hidden" id="input_map_queueId"     value="<c:out value="${map.queue.queueId}"/>">
			<input type="hidden" id="input_map_promotionId" value="<c:out value="${map.promotionId}"/>">
			<input type="hidden" id="input_map_favoriteId"  value="<c:out value="${map.favoriteId}"/>">
			<input type="hidden" id="input_map_queuingId"   value="<c:out value="${map.queuingId}"/>">
			<input type="hidden" id="input_map_isMyQueues"  value="<c:out value="${map.isMyQueues}"/>">
		</div>
	</c:forEach>
	
	
	<input type="hidden" id="input_queueId"     value="">
	<input type="hidden" id="input_promotionId" value="">
	<input type="hidden" id="input_favoriteId"  value="">
	<input type="hidden" id="input_queuingId"   value="">
	<input type="hidden" id="input_isMyQueues"  value="">
</form>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.5&appId=592070890950054";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
</div>
</body>
</html>
