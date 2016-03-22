package com.ezqueue.util;

public class ResponseObject {

	private boolean isSuccess;
	private String returnMessage;
	private Object returnObject;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public Object getReturnObject() {
		return returnObject;
	}
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
}
