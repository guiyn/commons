package org.cmcc.ecip.common.eos.client.models.response.history.transaction;




import java.math.BigInteger;
import java.util.List;

import  org.cmcc.ecip.common.eos.client.models.common.ActionTrace;
import  org.cmcc.ecip.common.eos.client.models.response.chain.trx.Trx;


public class Transaction {

    /**
     * executed - Succeed, no error handler executed
     * soft_fail - Objectively failed (not executed), error handler executed
     * hard_fail - Objectively failed and error handler objectively failed thus no state change
     * delayed - Transaction delayed/deferred/scheduled for future execution
     * expired - Transaction expired and storage space refuned to user
     */
    
    
    private String status;

    
   
    private BigInteger cpu_usage_us;

    
   
    private String net_usage_words;

    
   
    private Trx trx;

    
   
    private String id;

    
    
    private String timestamp;

    
    
    private Integer blockNum;

    
    
    private Integer last_irreversible_block;

    
    
    private List<ActionTrace> actionTraces = null;

    
    
    private boolean irreversible;



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public BigInteger getCpu_usage_us() {
		return cpu_usage_us;
	}



	public void setCpu_usage_us(BigInteger cpu_usage_us) {
		this.cpu_usage_us = cpu_usage_us;
	}



	public String getNet_usage_words() {
		return net_usage_words;
	}



	public void setNet_usage_words(String net_usage_words) {
		this.net_usage_words = net_usage_words;
	}



	public Trx getTrx() {
		return trx;
	}



	public void setTrx(Trx trx) {
		this.trx = trx;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}



	public Integer getBlockNum() {
		return blockNum;
	}



	public void setBlockNum(Integer blockNum) {
		this.blockNum = blockNum;
	}



	public Integer getLast_irreversible_block() {
		return last_irreversible_block;
	}



	public void setLast_irreversible_block(Integer last_irreversible_block) {
		this.last_irreversible_block = last_irreversible_block;
	}



	public List<ActionTrace> getActionTraces() {
		return actionTraces;
	}



	public void setActionTraces(List<ActionTrace> actionTraces) {
		this.actionTraces = actionTraces;
	}



	public boolean isIrreversible() {
		return irreversible;
	}



	public void setIrreversible(boolean irreversible) {
		this.irreversible = irreversible;
	}
    
    
}
