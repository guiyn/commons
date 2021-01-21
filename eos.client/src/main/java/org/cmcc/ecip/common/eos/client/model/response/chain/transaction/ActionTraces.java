
package org.cmcc.ecip.common.eos.client.model.response.chain.transaction;
import java.util.Date;
import java.util.List;




public class ActionTraces {

    private Receipt receipt;
    private Act act;
    private boolean context_free;
    private int elapsed;
    private String console;
    private String trx_id;
    private long block_num;
    public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	public Act getAct() {
		return act;
	}
	public void setAct(Act act) {
		this.act = act;
	}
	public boolean isContext_free() {
		return context_free;
	}
	public void setContext_free(boolean context_free) {
		this.context_free = context_free;
	}
	public int getElapsed() {
		return elapsed;
	}
	public void setElapsed(int elapsed) {
		this.elapsed = elapsed;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public String getTrx_id() {
		return trx_id;
	}
	public void setTrx_id(String trx_id) {
		this.trx_id = trx_id;
	}
	public long getBlock_num() {
		return block_num;
	}
	public void setBlock_num(long block_num) {
		this.block_num = block_num;
	}
	public Date getBlock_time() {
		return block_time;
	}
	public void setBlock_time(Date block_time) {
		this.block_time = block_time;
	}
	public String getProducer_block_id() {
		return producer_block_id;
	}
	public void setProducer_block_id(String producer_block_id) {
		this.producer_block_id = producer_block_id;
	}
	public List<String> getAccount_ram_deltas() {
		return account_ram_deltas;
	}
	public void setAccount_ram_deltas(List<String> account_ram_deltas) {
		this.account_ram_deltas = account_ram_deltas;
	}
	public String getExcept() {
		return except;
	}
	public void setExcept(String except) {
		this.except = except;
	}
	public List<String> getInline_traces() {
		return inline_traces;
	}
	public void setInline_traces(List<String> inline_traces) {
		this.inline_traces = inline_traces;
	}
	private Date block_time;
    private String producer_block_id;
    private List<String> account_ram_deltas;
    private String except;
    private List<String> inline_traces;
    

}