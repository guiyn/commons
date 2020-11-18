package org.cmcc.ecip.common.eos.client.models.response.chain.trx;

import java.util.List;


/**
 * @ProjectName chain.api
 * @PackageName org.cmcc.ecip.chain.api.model.response.chain.trx.action
 * @ClassName Action
 * @Description
 * @author guiyn
 * @date 2020年6月17日 下午4:51:50
 * @version 2020年6月17日 下午4:51:50
 * 
 */

public class Action {
	List <Authorization> authorization;
	String hex_data;
	ActionData data;
	String name;
	String account;
	public List<Authorization> getAuthorization() {
		return authorization;
	}
	public void setAuthorization(List<Authorization> authorization) {
		this.authorization = authorization;
	}
	public String getHex_data() {
		return hex_data;
	}
	public void setHex_data(String hex_data) {
		this.hex_data = hex_data;
	}
	public ActionData getData() {
		return data;
	}
	public void setData(ActionData data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
