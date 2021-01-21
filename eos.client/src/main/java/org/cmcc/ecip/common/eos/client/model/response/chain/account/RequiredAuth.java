package org.cmcc.ecip.common.eos.client.model.response.chain.account;


 

import java.util.List;


public class RequiredAuth {

    
    public List<SimpleAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<SimpleAccount> accounts) {
		this.accounts = accounts;
	}

	public List<Key> getKeys() {
		return keys;
	}

	public void setKeys(List<Key> keys) {
		this.keys = keys;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public List<Wait> getWaits() {
		return waits;
	}

	public void setWaits(List<Wait> waits) {
		this.waits = waits;
	}

	private List<SimpleAccount> accounts;
    
    private List<Key> keys;
    
    private Integer threshold;
    
    private List<Wait> waits;
}
