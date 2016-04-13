<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">
<c:forEach items="${RESPONSE_MAP}" var="map">
	<div id="div_<c:out value="${map.queue.queueId}"/>" class="col-sm-6 col-md-3">
		<div class="thumbnail" style="cursor: pointer;">
			<c:if test="${not empty map.promotionId}">
				<span style="font-size: 14px; z-index: 1; position: absolute; top: 3%; left: 9%;" class="label label-danger">Hot</span>
			</c:if>
			<span style="font-size: 16px; z-index: 1; position: absolute; top: 3%; left: 80%;" class="label label-default"><i class="fa <c:out value="${map.queue.queueType.iconClass}"/>"></i></span>
			<img style="z-index: 2;" src="http://graph.facebook.com/<c:out value="${map.queue.user.id}"/>/picture?width=245&height=245">
			<div class="caption" data-toggle="modal" data-target="#div_info_<c:out value="${map.queue.queueId}"/>">
				<h4><c:out value="${map.queue.title}"/></h4>
				<p><%@include file="/jsp/queue_star.jsp" %></p>
				<p><%@include file="/jsp/queue_count.jsp" %></p>
				
				<c:if test="${map.isQueuing}">
				<div>
					<hr>
					<table width="100%">
						<tr>
							<td width="20%" align="center" valign="top">
								<div><i class="fa fa-users"></i></div>
								<h3><c:out value="${map.queuingCount}"/>&nbsp;</h3>
							</td>
							<td width="60%" align="center" valign="top">
								<div><i class="fa fa-clock-o"></i></div>
								<h3><c:out value="${map.avgWaittingTime}"/>&nbsp;</h3>
							</td>
							<td width="20%" align="center" valign="top">
								<div><i class="fa fa-list-ol"></i></div>
								<h3><span id="span_queueNum" class="label label-default"><c:out value="${map.queueNum}"/></span>&nbsp;</h3>
							</td>
						</tr>
					</table>
				</div>
				</c:if>
				
				<input type="hidden" id="input_map_queueId"     value="<c:out value="${map.queue.queueId}"/>">
				<input type="hidden" id="input_map_starId"      value="<c:out value="${map.star.starId}"/>">
				<input type="hidden" id="input_map_promotionId" value="<c:out value="${map.promotionId}"/>">
				<input type="hidden" id="input_map_favoriteId"  value="<c:out value="${map.favoriteId}"/>">
				<input type="hidden" id="input_map_queuingId"   value="<c:out value="${map.queuingId}"/>">
				<input type="hidden" id="input_map_isMyQueues"  value="<c:out value="${map.isMyQueues}"/>">
			</div>
		</div>
	</div>
	
	<div id="div_info_<c:out value="${map.queue.queueId}"/>" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="jumbotron">
						<h1><c:out value="${map.queue.title}"/></h1>
						<p><c:out value="${map.queue.dscr}" escapeXml="false"/></p>
						<p><%@include file="/jsp/queue_star.jsp" %></p>
						<p><%@include file="/jsp/queue_count.jsp" %></p>
					</div>
					<div class="row">
						<div class="col-xs-6 col-lg-4">
							<h2><i title="電話" class="fa fa-phone"></i>電話</h2>
							<p>
								<span id="span_phone"><c:out value="${map.queue.phone}"/></span>
								<input type="text" class="form-control" id="input_phone" placeholder="電話" value="<c:out value="${map.queue.phone}"/>" style="display: none;">
							</p>
						</div>
						<div class="col-xs-6 col-lg-4">
							<h2><i class="fa fa-map-marker"></i>地址</h2>
							<p>
								<span id="span_address"><c:out value="${map.queue.address}"/>&nbsp;<a href="http://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=<c:out value="${map.queue.address}"/>&z=16&output=&t=" target="_blank"><i class="fa fa-map"></i></a></span>
								<input type="text" class="form-control" id="input_address" placeholder="地址" value="<c:out value="${map.queue.address}"/>" style="display: none;">
							</p>
						</div>
						
						<c:if test="${!map.isMyQueues}">
						<div class="col-xs-6 col-lg-4">
							<h2><i class="fa fa-star"></i>評分</h2>
							<h3 class="star">
								<c:choose>
									<c:when test="${not empty map.star}">
										<c:forEach begin="1" end="${map.star.star}" step="1">
											<i style="cursor: pointer;" class="star-color fa fa-star"></i>
										</c:forEach>
										<c:forEach begin="1" end="${5 - map.star.star}" step="1">
											<i style="cursor: pointer;" class="star-color fa fa-star-o"></i>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach begin="1" end="5" step="1">
											<i style="cursor: pointer;" class="star-color fa fa-star-o"></i>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</h3>
						</div>
						<div class="col-xs-6 col-lg-4">
							<h2><i class="fa fa-users"></i>排隊人數</h2>
							<h3 align="center"><c:out value="${map.queuingCount}"/>&nbsp;</h3>
						</div>
						<div class="col-xs-6 col-lg-4">
							<h2><i class="fa fa-clock-o"></i>平均等候時間</h2>
							<h3><c:out value="${map.avgWaittingTime}"/>&nbsp;</h3>
						</div>
						<div class="col-xs-6 col-lg-4">
							<h2><i class="fa fa-list-ol"></i>號碼牌</h2>
							<h3><span id="span_queueNum" class="label label-default"><c:out value="${map.queueNum}"/></span>&nbsp;</h3>
						</div>
						</c:if>
						
						<c:if test="${map.isMyQueues}">
						<div class="col-xs-12">
							<hr>
							<c:forEach items="${map.queuings}" var="queuing">
								<div id="div_queuing_<c:out value="${queuing.queuingId}"/>" class="panel panel-default" style="display: inline-block;">
									<div class="panel-heading" align="center"><h1><span class="label label-info"><c:out value="${queuing.queueNum}"/></span></h1></div>
									<div class="panel-body" align="center">
										<img src="http://graph.facebook.com/<c:out value="${queuing.user.id}"/>/picture?width=50&height=50">
										<br/>
										<c:out value="${queuing.user.name}"/>
										<br/><br/>
										<button type="button" class="btn btn-success" id="btn_queuing_success" name="btn_queuing_success"><h4><i class="fa fa-check"></i></h4></button>
										<input type="hidden" id="input_waitting" value="<c:out value="${queuing.queuingId}"/>">
									</div>
								</div>
							</c:forEach>
						</div>
						</c:if>
						
						<div class="col-xs-6 col-lg-4">
							<span id="span_result" class="label label-default"></span>
							<span id="span_spinner" style="display: none;"><i class="fa fa-spinner fa-spin"></i></span>
							<button type='button' id="btn_close"  name="btn_close"  class='btn btn-default' data-dismiss="modal"><i class="fa fa-times"></i></button>
							<c:choose>
								<c:when test="${map.isMyQueues}">
									<a type='button' id="btn_back"   name="btn_back"   class='btn btn-default' style="display: none;"><i class="fa fa-arrow-left"></i></a>
									<a type='button' id="btn_save"   name="btn_save"   class='btn btn-default' style="display: none;"><i class="fa fa-floppy-o"></i></a>
									<a type='button' id="btn_edit"   name="btn_edit"   class='btn btn-default'><i class="fa fa-pencil"></i></a>
									<a type='button' id="btn_delete" name="btn_delete" class='btn btn-default'><i class="fa fa-trash"></i></a>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty map.favoriteId}">
											<a type="button" id="btn_favorite" name="btn_favorite" class="btn btn-default"><i id="i_heart" class="fa fa-heart"></i></a>
										</c:when>
										<c:otherwise>
											<a type="button" id="btn_favorite" name="btn_favorite" class="btn btn-default"><i id="i_heart" class="fa fa-heart-o"></i></a>
										</c:otherwise>
									</c:choose>
									<a type='button' id="btn_queuing"  name="btn_queuing"  class='btn btn-default' style="<c:if test="${not empty map.queuingId}">display: none;</c:if>"><i class="fa fa-user-plus"></i></a>
									<a type='button' id="btn_cancel"   name="btn_cancel"   class='btn btn-default'  style="<c:if test="${empty map.queuingId}">display: none;</c:if>"><i class="fa fa-user-times"></i></a>
								</c:otherwise>
							</c:choose>
						</div>
						
						<div class="col-xs-12">
							<hr>
							<div class="fb-comments" data-width="100%" data-href="http://local.ezqueue.com:8080/<c:out value="${map.queue.queueId}"/>" data-numposts="5" data-order-by="reverse_time"></div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</div>