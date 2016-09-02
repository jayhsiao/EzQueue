<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row margin-top-20">
	<div class="col-md-6 col-md-offset-3">
		<div class='panel panel-default'>
			<div class='panel-heading'>
				<div class='panel-title'>
					<h4>建立排隊</h4>
				</div>
			</div>
			<div class='panel-body'>
				<div class="form-group">
					<div class="col-md-12">
						<div class="btn-group">
							<button type="button" id="btn_accounts" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false" style="height: 40px;">
								帳號 <span class="caret"></span>
							</button>
							<ul id="ul_accounts" class="dropdown-menu">
								<c:forEach items="${CREATE_QUEUE.accounts }" var="account">
									<li>
										<a><img src="http://graph.facebook.com/<c:out value="${account.facebookId}"/>/picture?width=14&height=14">&nbsp;<c:out value="${account.name}"/></a>
										<input type="hidden" id="input_accounts_userId"      value="<c:out value="${account.userId}"/>">
										<input type="hidden" id="input_accounts_accountId"   value="<c:out value="${account.facebookId}"/>">
										<input type="hidden" id="input_accounts_accountName" value="<c:out value="${account.name}"/>">
									</li>
								</c:forEach>
							</ul>
						</div>
						<div class="btn-group">
							<button type="button" id="btn_queueTypes" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false" style="height: 40px;">
								類別 <span class="caret"></span>
							</button>
							<ul id="ul_queueTypes" class="dropdown-menu">
								<c:forEach items="${CREATE_QUEUE.queueTypes }" var="queueType">
									<li>
										<a><i class="fa <c:out value="${queueType.iconClass }"/>" style="width: 24px; height: 24px;"></i>&nbsp;<c:out value="${queueType.dscr }"/></a>
										<input type="hidden" id="input_queueType_queueTypeId" value="<c:out value="${queueType.queueTypeId }"/>">
										<input type="hidden" id="input_queueType_dscr"        value="<c:out value="${queueType.dscr }"/>">
										<input type="hidden" id="input_queueType_iconClass"   value="<c:out value="${queueType.iconClass }"/>">
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-xs-12 col-md-6">
						開始日期<input type="date" id="input_startDate" class="form-control">
					</div>
					<div class="col-xs-12 col-md-6">
						結束日期(可不輸入)<input type="date" id="input_endDate" class="form-control">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-12">
						<input type="text" class="form-control" id="input_title" maxlength="16" placeholder="名稱">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-12">
						<input type="text" class="form-control" id="input_phone" maxlength="45" placeholder="電話">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-12">
						<input type="text" class="form-control" id="input_address" maxlength="45" placeholder="地址">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-12">
						<textarea id="textarea_dscr" rows='3' cols='33' class="form-control" maxlength='150' style="resize: none;" placeholder="簡介"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-12">
						<button type='button' id="btn_create" class='btn btn-default'>建立排隊</button>
						<span id="span_result" class="label label-danger"></span>
						<span id="span_spinner" style="display: none;"><i class="fa fa-spinner fa-spin"></i></span>
					</div>
				</div>
					
				<br/>
			</div>
		</div>
	</div>
</div>

<input type="hidden" id="input_create_userId"      value="">
<input type="hidden" id="input_create_queueTypeId" value="">
