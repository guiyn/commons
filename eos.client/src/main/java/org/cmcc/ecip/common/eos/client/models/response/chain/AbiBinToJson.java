package org.cmcc.ecip.common.eos.client.models.response.chain;




import java.util.List;


public class AbiBinToJson {

    private Object args;

    private List<String> requiredScope;

    private List<String> requiredAuth;

	public Object getArgs() {
		return args;
	}

	public void setArgs(Object args) {
		this.args = args;
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

}
