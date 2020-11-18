package org.cmcc.ecip.common.eos.client.models.common.transaction;



public class TransactionAuthorization {

    
    private String actor;
    
    private String permission;

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
