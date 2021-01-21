
package org.cmcc.ecip.common.eos.client.model.response.chain.transaction;

import java.util.Date;
import java.util.List;

public class Processed {

	private String id;
	private long block_num;
	private Date block_time;
	private String producer_block_id;
	private Receipt receipt;
	private int elapsed;
	private int net_usage;
	private boolean scheduled;
	private List<ActionTraces> action_traces;
	private String except;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setBlock_num(long block_num) {
		this.block_num = block_num;
	}

	public long getBlock_num() {
		return block_num;
	}

	public void setBlock_time(Date block_time) {
		this.block_time = block_time;
	}

	public Date getBlock_time() {
		return block_time;
	}

	public void setProducer_block_id(String producer_block_id) {
		this.producer_block_id = producer_block_id;
	}

	public String getProducer_block_id() {
		return producer_block_id;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setElapsed(int elapsed) {
		this.elapsed = elapsed;
	}

	public int getElapsed() {
		return elapsed;
	}

	public void setNet_usage(int net_usage) {
		this.net_usage = net_usage;
	}

	public int getNet_usage() {
		return net_usage;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public boolean getScheduled() {
		return scheduled;
	}

	public void setAction_traces(List<ActionTraces> action_traces) {
		this.action_traces = action_traces;
	}

	public List<ActionTraces> getAction_traces() {
		return action_traces;
	}

	public void setExcept(String except) {
		this.except = except;
	}

	public String getExcept() {
		return except;
	}

}