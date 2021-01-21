/**
  * Copyright 2019 bejson.com 
  */
package org.cmcc.ecip.common.eos.client.model.response.chain.transaction;
import java.util.List;





public class Act {

    public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAuthorization() {
		return authorization;
	}
	public void setAuthorization(List<String> authorization) {
		this.authorization = authorization;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	private String account;
    private String name;
    private List<String> authorization;
    private String data;

}