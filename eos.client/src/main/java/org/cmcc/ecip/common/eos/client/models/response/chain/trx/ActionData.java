package org.cmcc.ecip.common.eos.client.models.response.chain.trx;



/**
 * @ProjectName chain.api
 * @PackageName org.cmcc.ecip.chain.api.model.response.chain.trx
 * @ClassName Data
 * @Description
 * @author guiyn
 * @date 2020年6月17日 下午4:53:52
 * @version 2020年6月17日 下午4:53:52
 * 
 */

public class ActionData {
	Object param;
	String account;
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
