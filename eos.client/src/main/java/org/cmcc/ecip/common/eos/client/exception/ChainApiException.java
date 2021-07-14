package org.cmcc.ecip.common.eos.client.exception;




public class ChainApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	int code;
	EosError eosError;
	String message;
	public ChainApiException(FeignError e) {
		super(e.getMessage());
		this.code = e.getCode();
		this.eosError = e.getError();
		this.message=e.getMessage();
	}

	public ChainApiException(String mess) {
		super(mess);
		this.message=mess;
	}

	public ChainApiException(String mess, Throwable e) {
		super(mess, e);
		this.message=mess;
	}
	
	public ChainApiException(Throwable e) {
		super(e);
		this.message=e.getMessage();
	}

	public int getCode() {
		return code;
	}
	
	public void setMessage(String mess)
	{
		this.message=mess;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public EosError getEosError() {
		return eosError;
	}

	public void setEosError(EosError eosError) {
		this.eosError = eosError;
	}

	@Override
	public String toString() {
		return "ChainApiException [code=" + code + ", eosError=" + eosError + ", message=" + message + "]";
	}

 
	
	
	
}
