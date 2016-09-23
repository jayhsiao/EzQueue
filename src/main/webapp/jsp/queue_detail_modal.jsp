<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="div_search_modal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header"><h3><i class="fa fa-search"></i>搜尋</h3></div>
			<div class="modal-body">
				<div class="form-group">
					<input type="text" id="input_search" class="form-control" placeholder="搜尋...">
				</div>
			</div>
			<div class="modal-footer">
				<span class="btn btn-default" data-dismiss="modal">關閉視窗</span>
				<span id="btn_search" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-search"></i>&nbsp;搜尋</span>
			</div>
		</div>
	</div>
</div>
<div id="div_edit_modal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header"><h3><i class="fa fa-pencil"></i>修改</h3></div>
			<div class="modal-body">
				<div class="container-fluid">
					<form>
						<div class="form-group">
							<label class="form-control-label">簡介:</label>
							<textarea id="textarea_dscr" class="form-control" cols="10" rows="10" maxlength="500"><c:out value="${QUEUE_DETAIL.queue.dscr}" escapeXml="false"/></textarea>
						</div>
						<div class="form-group">
							<label class="form-control-label">電話:</label>
							<input type="text" id="input_phone" class="form-control" maxlength="20" value="<c:out value="${QUEUE_DETAIL.queue.phone}"/>">
						</div>
						<div class="form-group">
							<label class="form-control-label">地址:</label>
							<input type="text" id="input_address" class="form-control" maxlength="100" value="<c:out value="${QUEUE_DETAIL.queue.address}"/>">
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<span class="btn btn-default" data-dismiss="modal">關閉視窗</span>
				<span id="btn_save" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-floppy-o"></i>儲存</span>
				<br/><br/>
			</div>
		</div>
	</div>
</div>
<div id="div_delete_modal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header"><h3><i class="fa fa-trash"></i>刪除排隊</h3></div>
			<div class="modal-body">確定刪除排隊?</div>
			<div class="modal-footer">
				<span class="btn btn-default" data-dismiss="modal">關閉視窗</span>
				<span id="btn_delete" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-trash"></i>刪除</span>
			</div>
		</div>
	</div>
</div>
<div id="div_open_modal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header"><h3><i class="fa fa-play"></i>開啟排隊</h3></div>
			<div class="modal-body">確定開啟排隊?</div>
			<div class="modal-footer">
				<span class="btn btn-default" data-dismiss="modal">關閉視窗</span>
				<span id="btn_open" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-play"></i>開啟</span>
			</div>
		</div>
	</div>
</div>
<div id="div_close_modal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header"><h3><i class="fa fa-pause"></i>暫停排隊</h3></div>
			<div class="modal-body">確定暫停排隊?</div>
			<div class="modal-footer">
				<span class="btn btn-default" data-dismiss="modal">關閉視窗</span>
				<span id="btn_close" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-pause"></i>暫停</span>
			</div>
		</div>
	</div>
</div>
