package org.cmcc.ecip.common.eos.client.models.response.chain.transaction;




import java.util.ArrayList;

public class ScheduledTransactionResponse {

    
    private ArrayList<ScheduledTransaction> transactions;
    
    private String more;

	public ArrayList<ScheduledTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<ScheduledTransaction> transactions) {
		this.transactions = transactions;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

}
