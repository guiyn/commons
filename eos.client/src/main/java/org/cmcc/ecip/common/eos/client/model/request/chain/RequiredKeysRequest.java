package org.cmcc.ecip.common.eos.client.model.request.chain;




import java.util.List;

import org.cmcc.ecip.common.eos.client.model.common.transaction.PackedTransaction;


public class RequiredKeysRequest {

    
    private PackedTransaction transaction;

    
    
    private List<String> availableKeys;

    public RequiredKeysRequest(PackedTransaction transaction, List<String> availableKeys) {
        this.transaction = transaction;
        this.availableKeys = availableKeys;
    }

	public PackedTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(PackedTransaction transaction) {
		this.transaction = transaction;
	}

	public List<String> getAvailableKeys() {
		return availableKeys;
	}

	public void setAvailableKeys(List<String> availableKeys) {
		this.availableKeys = availableKeys;
	}
    
    
}
