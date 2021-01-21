package org.cmcc.ecip.common.eos.client.model.response.chain.abi;



 


public class Abi {

    
    private String accountName;

    
    private  org.cmcc.ecip.common.eos.client.model.response.chain.code.Abi abi;


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public  org.cmcc.ecip.common.eos.client.model.response.chain.code.Abi getAbi() {
		return abi;
	}


	public void setAbi( org.cmcc.ecip.common.eos.client.model.response.chain.code.Abi abi) {
		this.abi = abi;
	}

}
