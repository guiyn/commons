package org.cmcc.ecip.common.eos.client.model.response.chain.code;



 



public class Code {

    
    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCodeHash() {
		return codeHash;
	}

	public void setCodeHash(String codeHash) {
		this.codeHash = codeHash;
	}

	public String getWast() {
		return wast;
	}

	public void setWast(String wast) {
		this.wast = wast;
	}

	public Abi getAbi() {
		return abi;
	}

	public void setAbi(Abi abi) {
		this.abi = abi;
	}

	private String accountName;
    
    private String codeHash;
    
    private String wast;
    
    private Abi abi;
}
