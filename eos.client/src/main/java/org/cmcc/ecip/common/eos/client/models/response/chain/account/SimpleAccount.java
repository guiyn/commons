package org.cmcc.ecip.common.eos.client.models.response.chain.account;


 


public class SimpleAccount {

    
    public Permission getPermission() {
		return permission;
	}


	public void setPermission(Permission permission) {
		this.permission = permission;
	}


	public Integer getWeight() {
		return weight;
	}


	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	private Permission permission;

    
    private Integer weight;

    
    public static class Permission {
        
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
}
