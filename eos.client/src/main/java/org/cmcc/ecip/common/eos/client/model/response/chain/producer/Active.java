package org.cmcc.ecip.common.eos.client.model.response.chain.producer;


 

import java.util.List;


public class Active {

    
    private Integer version;
    
    private List<Producers> producers;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Producers> getProducers() {
		return producers;
	}

	public void setProducers(List<Producers> producers) {
		this.producers = producers;
	}
}
