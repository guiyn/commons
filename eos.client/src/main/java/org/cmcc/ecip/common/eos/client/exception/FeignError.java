package org.cmcc.ecip.common.eos.client.exception;




public class FeignError {
	int code;
	String message;
	
	EosError error;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EosError getError() {
		return error;
	}

	public void setError(EosError error) {
		this.error = error;
	}
	
	
}
