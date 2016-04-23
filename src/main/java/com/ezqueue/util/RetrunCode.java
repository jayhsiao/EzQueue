package com.ezqueue.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RetrunCode {
	SUCCESS("0000"),
	FAIL("9999");
	
	private final String value;
	
	private RetrunCode(String value){
		this.value = value;
	}
	
	@JsonValue
    public String getValue() {
        return value;
    }
}
