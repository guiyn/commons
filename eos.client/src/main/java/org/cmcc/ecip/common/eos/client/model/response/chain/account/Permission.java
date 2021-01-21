package org.cmcc.ecip.common.eos.client.model.response.chain.account;



 


public class Permission {

    
    public String getParent() {
		return parent;
	}



	public void setParent(String parent) {
		this.parent = parent;
	}



	public String getPermName() {
		return permName;
	}



	public void setPermName(String permName) {
		this.permName = permName;
	}



	public RequiredAuth getRequiredAuth() {
		return requiredAuth;
	}



	public void setRequiredAuth(RequiredAuth requiredAuth) {
		this.requiredAuth = requiredAuth;
	}



	private String parent;

    
    
    private String permName;

    
    
    private RequiredAuth requiredAuth;
}
