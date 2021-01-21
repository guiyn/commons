package org.cmcc.ecip.common.eos.client.model.response.chain.account;


 


public class SelfDelegatedBandwidth {
    
    public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	public String getNetWeight() {
		return netWeight;
	}



	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}



	public String getCpuWeight() {
		return cpuWeight;
	}



	public void setCpuWeight(String cpuWeight) {
		this.cpuWeight = cpuWeight;
	}



	private String from;
    
    private String to;

    
    
    private String netWeight;

    
    
    private String cpuWeight;
}
