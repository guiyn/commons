package org.cmcc.ecip.common.eos.client.models.response.chain.producer;






public class Producers {

    
    
    private String producerName;

    
    
    public String getProducerName() {
		return producerName;
	}



	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}



	public String getBlockSigning() {
		return blockSigning;
	}



	public void setBlockSigning(String blockSigning) {
		this.blockSigning = blockSigning;
	}



	private String blockSigning;
}
