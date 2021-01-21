package org.cmcc.ecip.common.eos.client.model.response.chain;



import java.util.List;


public class AbiJsonToBin {

    private String binargs;

    
    
    public String getBinargs() {
		return binargs;
	}

	public void setBinargs(String binargs) {
		this.binargs = binargs;
	}

	public List<String> getRequiredScope() {
		return requiredScope;
	}

	public void setRequiredScope(List<String> requiredScope) {
		this.requiredScope = requiredScope;
	}

	public List<String> getRequiredAuth() {
		return requiredAuth;
	}

	public void setRequiredAuth(List<String> requiredAuth) {
		this.requiredAuth = requiredAuth;
	}

	private List<String> requiredScope;

    private List<String> requiredAuth;

}
