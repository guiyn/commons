package org.cmcc.ecip.common.eos.client.models.response.chain;



public class RawCodeAndAbi {

    
    private String abi;

    
    private String accountName;
    
    private String wasm;

	public String getAbi() {
		return abi;
	}

	public void setAbi(String abi) {
		this.abi = abi;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getWasm() {
		return wasm;
	}

	public void setWasm(String wasm) {
		this.wasm = wasm;
	}
}
