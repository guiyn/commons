package org.cmcc.ecip.common.eos.client.models.response.chain.block;


 


public class NewProducers {

	
    public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public Producer[] getProducers() {
		return producers;
	}


	public void setProducers(Producer[] producers) {
		this.producers = producers;
	}


	private int version;

	
    private Producer[] producers;
}
