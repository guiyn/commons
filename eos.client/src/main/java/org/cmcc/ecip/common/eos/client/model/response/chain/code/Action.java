package org.cmcc.ecip.common.eos.client.model.response.chain.code;



 


public class Action {

    
    
    public String getRicardianContract() {
		return ricardianContract;
	}

	public void setRicardianContract(String ricardianContract) {
		this.ricardianContract = ricardianContract;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String ricardianContract;
    
    private String name;
    
    private String type;
}
