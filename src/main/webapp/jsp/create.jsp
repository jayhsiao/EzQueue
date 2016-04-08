<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class='panel panel-default' style='width: 45%;'>
	<div class='panel-heading'>
		<table>
		<tr>
			<td width='100%' nowrap>
				<div class='panel-title'>
					<h4><span id="span_title"></span></h4>
				</div>
			</td>
		</tr>
		</table>
	</div>
	<div class='panel-body'>
		
		<div class="form-group">
			<div class="col-md-6 dropdown">
				<button type="button" id="btn_accounts" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" aria-expanded="true">
					請選擇&nbsp;<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<c:forEach items="${RESPONSE_MAP.accounts }" var="account">
						<li>
							<a><img src="http://graph.facebook.com/<c:out value="${account.id}"/>/picture?width=12&height=14">&nbsp;<c:out value="${account.name}"/></a>
							<input type="hidden" id="input_accounts_userId"      value="<c:out value="${account.userId}"/>">
							<input type="hidden" id="input_accounts_accountId"   value="<c:out value="${account.id}"/>">
							<input type="hidden" id="input_accounts_accountName" value="<c:out value="${account.name}"/>">
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	
		<div class="form-group">
			<div id="div_btn-group" class="col-md-6 btn-group" data-toggle="buttons">
				<label class="btn btn-default active">
					<input type="radio" name="enable" id="enable_true" autocomplete="off" value="true" checked> 開啟
				</label>
				<label class="btn btn-default">
					<input type="radio" name="enable" id="enable_false" autocomplete="off" value="false"> 暫時關閉
				</label>
				<br/><br/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-md-12">
				<input type="text" class="form-control" id="input_title" name="title" maxlength="45" placeholder="名稱">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-md-12">
				<input type="text" class="form-control" id="input_phone" name="title" maxlength="45" placeholder="電話">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-md-12">
				<input type="text" class="form-control" id="input_address" name="title" maxlength="45" placeholder="地址">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-md-12">
				<textarea id="textarea_dscr" rows='3' cols='33' class="form-control" maxlength='150' placeholder="簡介"></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-md-12">
				<button type='button' id="btn_create" class='btn btn-default'>建立排隊</button>
				<span id="span_result"></span>
				<span id="span_spinner" style="display: none;"><i class="fa fa-spinner fa-spin"></i></span>
			</div>
		</div>
			
		<br/>
	</div>
</div>
