package org.cmcc.ecip.common.eos.client.model.response.chain;



import java.math.BigInteger;

import org.cmcc.ecip.common.eos.client.model.response.chain.block.NewProducers;
import org.cmcc.ecip.common.eos.client.model.response.history.transaction.Transaction;


public class Block {

	private String previous;

	private String timeStamp;

	
	private String transaction_mroot;

	
	private String action_mroot;

	private String block_merkle_root;

	private String producer;

	private String schedule_version;

	private NewProducers new_producers;

	private String producer_signature;

	private String id;

	private Long blockNum;

	private Long ref_block_prefix;

	private BigInteger confirmed;

	private Transaction[] transactions;

	private String[] header_extensions;

	private String[] block_extensions;

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTransaction_mroot() {
		return transaction_mroot;
	}

	public void setTransaction_mroot(String transaction_mroot) {
		this.transaction_mroot = transaction_mroot;
	}

	public String getAction_mroot() {
		return action_mroot;
	}

	public void setAction_mroot(String action_mroot) {
		this.action_mroot = action_mroot;
	}

	public String getBlock_merkle_root() {
		return block_merkle_root;
	}

	public void setBlock_merkle_root(String block_merkle_root) {
		this.block_merkle_root = block_merkle_root;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getSchedule_version() {
		return schedule_version;
	}

	public void setSchedule_version(String schedule_version) {
		this.schedule_version = schedule_version;
	}

	public NewProducers getNew_producers() {
		return new_producers;
	}

	public void setNew_producers(NewProducers new_producers) {
		this.new_producers = new_producers;
	}

	public String getProducer_signature() {
		return producer_signature;
	}

	public void setProducer_signature(String producer_signature) {
		this.producer_signature = producer_signature;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getBlockNum() {
		return blockNum;
	}

	public void setBlockNum(Long blockNum) {
		this.blockNum = blockNum;
	}

	public Long getRef_block_prefix() {
		return ref_block_prefix;
	}

	public void setRef_block_prefix(Long ref_block_prefix) {
		this.ref_block_prefix = ref_block_prefix;
	}

	public BigInteger getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(BigInteger confirmed) {
		this.confirmed = confirmed;
	}

	public Transaction[] getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction[] transactions) {
		this.transactions = transactions;
	}

	public String[] getHeader_extensions() {
		return header_extensions;
	}

	public void setHeader_extensions(String[] header_extensions) {
		this.header_extensions = header_extensions;
	}

	public String[] getBlock_extensions() {
		return block_extensions;
	}

	public void setBlock_extensions(String[] block_extensions) {
		this.block_extensions = block_extensions;
	}
	
}
