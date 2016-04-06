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
<script type="text/javascript" src="<c:url value="/js/fb_other.js"/>"></script>
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
					
					<c:if test="${map.isQueuing}">
					<p>
						<hr>
						<table width="100%">
							<tr>
								<td width="20%" align="center">
									<div><i class="fa fa-users"></i></div>
									<h3><c:out value="${map.queuingCount}"/></h3>
								</td>
								<td width="60%" align="center">
									<div><i class="fa fa-clock-o"></i></div>
									<h3><c:out value="${map.avgWaittingTime}"/></h3>
								</td>
								<td width="20%" align="center">
									<div><i class="fa fa-tags"></i></div>
									<h3><span id="span_queueNum" class="label label-info label-lg"><c:out value="${map.queueNum}"/></span></h3>
								</td>
							</tr>
						</table>
					</p>
					</c:if>
					
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
									<td width="30%" valign="top">
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
									<td width="40%" valign="top" align="center">
										<c:if test="${!map.isMyQueues}">
											<h1><span id="span_queueNum" class="label label-info" title="號碼牌"><c:out value="${map.queueNum}"/></span></h1>
										</c:if>
									</td>
									<td width="30%" valign="top" align="right">
										<c:if test="${!map.isMyQueues}">
										<div>
											<h1 class="star">
												<c:forEach begin="1" end="${map.star}" step="1">
													<i title="評分" style="cursor: pointer;" class="star-color fa fa-star"></i>
												</c:forEach>
												<c:forEach begin="1" end="${5 - map.star}" step="1">
													<i title="評分" style="cursor: pointer;" class="star-o-color fa fa-star-o"></i>
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
													<td valign="top"><h4><i title="電話" class="fa fa-phone"></i></h4></td>
													<td valign="top">
														<h4><span title="電話" id="span_phone"><c:out value="${map.queue.phone}"/></span></h4>
														<input type="text" class="form-control" id="input_phone" placeholder="電話" value="<c:out value="${map.queue.phone}"/>" style="display: none;">
													</td>
												</tr>
												<tr>
													<td valign="top"><h4><i title="地址" class="fa fa-map-marker"></i></h4></td>
													<td valign="top">
														<h4><span title="地址" id="span_address"><c:out value="${map.queue.address}"/></span></h4>
														<input type="text" class="form-control" id="input_address" placeholder="地址" value="<c:out value="${map.queue.address}"/>" style="display: none;">
													</td>
												</tr>
												<tr>
													<td valign="top"><h4><i title="簡介" class="fa fa-thumbs-up"></i></h4></td>
													<td valign="top">
														<h4><span title="簡介" id="span_dscr"><c:out value="${map.queue.dscr}" escapeXml="false"/></span></h4>
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
												<button type='button' id="btn_back"   name="btn_back"   class='btn btn-default' title="恢復" style="display: none;"><i class="fa fa-arrow-left"></i></button>
												<button type='button' id="btn_save"   name="btn_save"   class='btn btn-default' title="儲存" style="display: none;"><i class="fa fa-floppy-o"></i></button>
												<button type='button' id="btn_edit"   name="btn_edit"   class='btn btn-default' title="修改"><i class="fa fa-pencil"></i></button>
												<button type='button' id="btn_delete" name="btn_delete" class='btn btn-default' title="刪除"><i class="fa fa-trash"></i></button>
											</c:when>
											<c:otherwise>
												<button type='button' id="btn_queuing"  name="btn_queuing"  class='btn btn-success' title="我要排隊" style="<c:if test="${not empty map.queuingId}">display: none;</c:if>"><i class="fa fa-user-plus"></i></button>
												<button type='button' id="btn_cancel"   name="btn_cancel"   class='btn btn-danger'  title="取消排隊" style="<c:if test="${empty map.queuingId}">display: none;</c:if>"><i class="fa fa-user-times"></i></button>
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
										<span id="span_spinner" style="display: none;"><i class="fa fa-spinner fa-spin"></i></span>
									</div>
									
									<c:if test="${!map.isMyQueues}">
									<div align="center">
										<hr>
										<table width="100%">
											<tr>
												<td width="30%" align="center">
													<div><i title="等待人數" class="fa fa-users"></i></div>
													<h1 title="等待人數"><c:out value="${map.queuingCount}"/></h1>
												</td>
												<td width="70%" align="center">
													<div><i title="今日平均等待時間" class="fa fa-clock-o"></i></div>
													<h1 title="今日平均等待時間"><c:out value="${map.avgWaittingTime}"/></h1>
												</td>
											</tr>
										</table>
									</div>
									</c:if>
									
								</td>
								<td width="60%" valign="top">
									<c:choose>
										<c:when test="${map.isMyQueues}">
											<div>
												<c:forEach items="${map.queuings}" var="queuing">
													<div id="div_queuing_<c:out value="${queuing.queuingId}"/>" class="panel panel-default" style="display: inline-block;">
														<div class="panel-heading" align="center"><h1><span class="label label-info"><c:out value="${queuing.queueNum}"/></span></h1></div>
														<div class="panel-body" align="center">
															<img src="http://graph.facebook.com/<c:out value="${queuing.user.fbId}"/>/picture?width=50&height=50">
															<br/>
															<c:out value="${queuing.user.name}"/>
															<br/><br/>
															<button type="button" class="btn btn-success" id="btn_queuing_success" name="btn_queuing_success" title="完成" value="<c:out value="${queuing.queuingId}"/>"><h4><i class="fa fa-check"></i></h4></button>
														</div>
													</div>
												</c:forEach>
											</div>
										</c:when>
										<c:otherwise>
											<div>
												<iframe width="100%" height="300" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=<c:out value="${map.queue.address}"/>&z=16&output=embed&t="></iframe>
											</div>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
						
						<div>
							<hr>
							<div class="fb-comments" data-width="100%" data-href="http://local.ezqueue.com:8080/<c:out value="${map.queue.queueId}"/>" data-numposts="5" data-order-by="reverse_time"></div>
						</div>
						
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
</body>
</html>
