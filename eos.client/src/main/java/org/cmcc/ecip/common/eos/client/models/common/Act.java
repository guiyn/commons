
package org.cmcc.ecip.common.eos.client.models.common;




import java.util.List;

import org.cmcc.ecip.common.eos.client.models.response.history.Authorization;


public class Act {

    private String account;
    private List<Authorization> authorization = null;
    
    private Object data;
    
    
    private String hexData;
    
    
    private String name;


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public List<Authorization> getAuthorization() {
		return authorization;
	}


	public void setAuthorization(List<Authorization> authorization) {
		this.authorization = authorization;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public String getHexData() {
		return hexData;
	}


	public void setHexData(String hexData) {
		this.hexData = hexData;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
    
    

}
