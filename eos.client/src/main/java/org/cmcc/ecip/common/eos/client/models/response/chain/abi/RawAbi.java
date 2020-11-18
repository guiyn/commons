package org.cmcc.ecip.common.eos.client.models.response.chain.abi;



 



public class RawAbi {

    
    public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getCode_hash() {
		return code_hash;
	}

	public void setCode_hash(String code_hash) {
		this.code_hash = code_hash;
	}

	public String getAbi_hash() {
		return abi_hash;
	}

	public void setAbi_hash(String abi_hash) {
		this.abi_hash = abi_hash;
	}

	public String getAbi() {
		return abi;
	}

	public void setAbi(String abi) {
		this.abi = abi;
	}

	private String account_name;
    
    private String code_hash;
    
    private String abi_hash;
    
    private String abi;
}
