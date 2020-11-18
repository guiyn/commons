package org.cmcc.ecip.common.eos.client.models.request.chain.transaction;





import java.util.List;

import  org.cmcc.ecip.common.eos.client.models.common.transaction.PackedTransaction;


public class PushTransactionRequest {

    
    private String compression;
    
    private PackedTransaction transaction;
    
    private List<String> signatures;

    public PushTransactionRequest(String compression, PackedTransaction packedTransaction, List<String> signatures) {
        this.compression = compression;
        this.transaction = packedTransaction;
        this.signatures = signatures;
    }

	public String getCompression() {
		return compression;
	}

	public void setCompression(String compression) {
		this.compression = compression;
	}

	public PackedTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(PackedTransaction transaction) {
		this.transaction = transaction;
	}

	public List<String> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<String> signatures) {
		this.signatures = signatures;
	}
    
    
}
