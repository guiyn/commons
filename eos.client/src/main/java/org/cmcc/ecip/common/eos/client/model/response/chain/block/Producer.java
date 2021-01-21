package org.cmcc.ecip.common.eos.client.model.response.chain.block;



 


public class Producer {

    
    
    public String getProducerName() {
		return producerName;
	}



	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}



	public String getBlockSigningKey() {
		return blockSigningKey;
	}



	public void setBlockSigningKey(String blockSigningKey) {
		this.blockSigningKey = blockSigningKey;
	}



	private String producerName;

    
    
    private String blockSigningKey;
}
