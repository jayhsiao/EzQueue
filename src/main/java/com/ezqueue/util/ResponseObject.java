package com.ezqueue.util;

public class ResponseObject {

	private RetrunCode returnCode;
	private String returnMessage;
	private Object returnObject;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}
	public RetrunCode getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(RetrunCode returnCode) {
		this.returnCode = returnCode;
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
