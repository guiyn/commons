package org.cmcc.ecip.common.eos.client.model.response.chain.transaction;

public class PushedTransaction {

	private String transaction_id;
	private Processed processed;
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Processed getProcessed() {
		return processed;
	}
	public void setProcessed(Processed processed) {
		this.processed = processed;
	}
}
