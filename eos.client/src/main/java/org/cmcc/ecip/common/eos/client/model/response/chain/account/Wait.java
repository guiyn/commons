package org.cmcc.ecip.common.eos.client.model.response.chain.account;


 


public class Wait {

    
    
    public Integer getWaitSec() {
		return waitSec;
	}

	public void setWaitSec(Integer waitSec) {
		this.waitSec = waitSec;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	private Integer waitSec;
    
    private Integer weight;
}
