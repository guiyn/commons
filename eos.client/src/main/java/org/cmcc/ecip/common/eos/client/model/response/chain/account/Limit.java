package org.cmcc.ecip.common.eos.client.model.response.chain.account;


 


public class Limit {

    
    public String getUsed() {
		return used;
	}


	public void setUsed(String used) {
		this.used = used;
	}


	public String getAvailable() {
		return available;
	}


	public void setAvailable(String available) {
		this.available = available;
	}


	public String getMax() {
		return max;
	}


	public void setMax(String max) {
		this.max = max;
	}


	private String used;

    
    private String available;

    
    private String max;
}
