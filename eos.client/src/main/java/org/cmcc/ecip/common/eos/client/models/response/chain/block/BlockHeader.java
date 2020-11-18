package org.cmcc.ecip.common.eos.client.models.response.chain.block;



 


public class BlockHeader {

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getTransactionMroot() {
		return transactionMroot;
	}

	public void setTransactionMroot(String transactionMroot) {
		this.transactionMroot = transactionMroot;
	}

	public String getActionMroot() {
		return actionMroot;
	}

	public void setActionMroot(String actionMroot) {
		this.actionMroot = actionMroot;
	}

	public Integer getScheduleVersion() {
		return scheduleVersion;
	}

	public void setScheduleVersion(Integer scheduleVersion) {
		this.scheduleVersion = scheduleVersion;
	}

	public Object getHeaderExtensions() {
		return headerExtensions;
	}

	public void setHeaderExtensions(Object headerExtensions) {
		this.headerExtensions = headerExtensions;
	}

	public String getProducerSignature() {
		return producerSignature;
	}

	public void setProducerSignature(String producerSignature) {
		this.producerSignature = producerSignature;
	}

	private String timestamp;

	private String producer;

	private Integer confirmed;

	private String previous;

	private String transactionMroot;

	private String actionMroot;

	private Integer scheduleVersion;

	private Object headerExtensions;

	private String producerSignature;

}
