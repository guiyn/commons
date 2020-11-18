package org.cmcc.ecip.common.eos.client.models.response.chain.code;


 


public class ErrorMessage {

    
    
    public Integer getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	private Integer errorCode;
    
    
    private String errorMsg;
}
