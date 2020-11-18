package org.cmcc.ecip.common.eos.client.models.response.chain.trx;





/**
 * @ProjectName chain.api
 * @PackageName org.cmcc.ecip.chain.api.model.response.chain.trx
 * @ClassName Authorization
 * @Description
 * @author guiyn
 * @date 2020年6月17日 下午4:55:13
 * @version 2020年6月17日 下午4:55:13
 * 
 */

public class Authorization {
	String actor;
	String permission;
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
