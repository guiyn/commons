package org.cmcc.ecip.common.eos.client.models.response.chain.account;


 


public class Limit {

    
    public Integer getUsed() {
		return used;
	}


	public void setUsed(Integer used) {
		this.used = used;
	}


	public Integer getAvailable() {
		return available;
	}


	public void setAvailable(Integer available) {
		this.available = available;
	}


	public Integer getMax() {
		return max;
	}


	public void setMax(Integer max) {
		this.max = max;
	}


	private Integer used;

    
    private Integer available;

    
    private Integer max;
}
