package org.cmcc.ecip.common.eos.client.exception;




public class ChainApiException extends Exception {

	private static final long serialVersionUID = 1L;
	int code;
	EosError eosError;

	public ChainApiException(FeignError e) {
		super(e.getMessage());
		this.code = e.getCode();
		this.eosError = e.getError();
	}

	public ChainApiException(String mess) {
		super(mess);
	}

	public ChainApiException(String mess, Throwable e) {
		super(mess, e);
	}
	
	public ChainApiException(Throwable e) {
		super(e);
	}

	public int getCode() {
		return code;
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
}
