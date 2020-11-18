package org.cmcc.ecip.common.eos.client.models.response.chain.transaction;


public class Lock {


    private String scope;

    public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	private String account;
}
