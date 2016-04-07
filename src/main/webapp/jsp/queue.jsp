<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:forEach items="${RESPONSE_MAP}" var="map">
	<div id="div_<c:out value="${map.queue.queueId}"/>" class="col-sm-6 col-md-4" style="cursor: pointer;">
		<div class="thumbnail" data-toggle="modal" data-target="#div_info_<c:out value="${map.queue.queueId}"/>">
			<img src="http://graph.facebook.com/<c:out value="${map.queue.user.fbId}"/>/picture?width=283&height=283">
			<div class="caption">
				<h3><c:out value="${map.queue.title}"/></h3>
				<div>
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
				</div>
				
				<c:if test="${map.isQueuing}">
				<div>
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
				</div>
				</c:if>
				
			</div>
		</div>
	</div>
	
	<div id="div_info_<c:out value="${map.queue.queueId}"/>" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">
						<table width="100%">
							<tr>
								<td>
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
									<c:if test="${!map.isMyQueues}">
									<c:choose>
										<c:when test="${not empty map.favoriteId}">
											<button type="button" id="btn_favorite" name="btn_favorite" class="btn btn-warning"><i id="i_heart" class="fa fa-heart"></i></button>
										</c:when>
										<c:otherwise>
											<button type="button" id="btn_favorite" name="btn_favorite" class="btn btn-warning"><i id="i_heart" class="fa fa-heart-o"></i></button>
										</c:otherwise>
									</c:choose>
									</c:if>
									<c:if test="${not empty map.promotionId}">
										<span class="label label-danger">Hot</span>
									</c:if>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<i title="電話" class="fa fa-phone"></i>&nbsp;<span id="span_phone"><c:out value="${map.queue.phone}"/></span>
							<input type="text" class="form-control" id="input_phone" placeholder="電話" value="<c:out value="${map.queue.phone}"/>" style="display: none;">
						</div>
						<div class="col-xs-12">
							<i class="fa fa-map-marker"></i>&nbsp;<span id="span_address"><a href="http://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=<c:out value="${map.queue.address}"/>&z=16&output=&t=" target="_blank"><c:out value="${map.queue.address}"/></a></span>
							<input type="text" class="form-control" id="input_address" placeholder="地址" value="<c:out value="${map.queue.address}"/>" style="display: none;">
						</div>
						<div class="col-xs-12">
							<i class="fa fa-thumbs-up"></i>&nbsp;<span id="span_dscr"><c:out value="${map.queue.dscr}" escapeXml="false"/></span>
							<textarea id="textarea_dscr" rows='3' cols='33' class="form-control" maxlength='150' placeholder="簡介" style="display: none;"><c:out value="${map.queue.dscr}" escapeXml="false"/></textarea>
						</div>
						
						<c:if test="${!map.isMyQueues}">
						<div class="col-xs-12">
							<hr>
							<div class="col-sm-3" align="center">
								<i class="fa fa-users"></i>等待組數
								<h1><c:out value="${map.queuingCount}"/>&nbsp;</h1>
							</div>
							<div class="col-sm-6" align="center">
								<i class="fa fa-clock-o"></i>今日平均等待時間
								<h1><c:out value="${map.avgWaittingTime}"/>&nbsp;</h1>
							</div>
							<div class="col-sm-3" align="center">
								<i class="fa fa-tags"></i>號碼牌
								<h1><span id="span_queueNum" class="label label-info"><c:out value="${map.queueNum}"/></span>&nbsp;</h1>
							</div>
						</div>
						</c:if>
						
						<c:if test="${map.isMyQueues}">
						<div class="col-xs-12">
							<hr>
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
						</c:if>
						
						<c:if test="${!map.isMyQueues}">
						<div class="col-xs-12" align="center">
							<hr>
							<div><i class="fa fa-star">評分</i></div>
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
						
						<div class="col-xs-12" align="right">
							<hr>
							<span id="span_result"></span>
							<span id="span_spinner" style="display: none;"><i class="fa fa-spinner fa-spin"></i></span>
							<button type='button' id="btn_close"  name="btn_close"  class='btn btn-default' data-dismiss="modal">關閉</button>
							<c:choose>
								<c:when test="${map.isMyQueues}">
									<button type='button' id="btn_back"   name="btn_back"   class='btn btn-default' style="display: none;"><i class="fa fa-arrow-left"></i>恢復</button>
									<button type='button' id="btn_save"   name="btn_save"   class='btn btn-default' style="display: none;"><i class="fa fa-floppy-o"></i>儲存</button>
									<button type='button' id="btn_edit"   name="btn_edit"   class='btn btn-default'><i class="fa fa-pencil"></i>修改</button>
									<button type='button' id="btn_delete" name="btn_delete" class='btn btn-default'><i class="fa fa-trash"></i>刪除</button>
								</c:when>
								<c:otherwise>
									<button type='button' id="btn_queuing"  name="btn_queuing"  class='btn btn-success' style="<c:if test="${not empty map.queuingId}">display: none;</c:if>"><i class="fa fa-user-plus"></i>我要排隊</button>
									<button type='button' id="btn_cancel"   name="btn_cancel"   class='btn btn-danger'  style="<c:if test="${empty map.queuingId}">display: none;</c:if>"><i class="fa fa-user-times"></i>取消排隊</button>
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
