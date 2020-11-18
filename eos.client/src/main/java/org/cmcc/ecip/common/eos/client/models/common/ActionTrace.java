package org.cmcc.ecip.common.eos.client.models.common;



import java.util.List;


public class ActionTrace {

    
    private Act act;
    
    private String console;
    
    
    private Integer cpuUsage;

    
    private Integer elapsed;

    
    
    private List<Object> inlineTraces = null;
    
    private Receipt receipt;
    
    private Integer totalCpuUsage;
    
    private String trxId;
    
    private boolean contextFree;
    
    private long blockNum;
    
    private String blockTime;
    
    private String producerBlockId;
    
    private List<AccountRamDelta> accountRamDeltas;
    
    private String trxStatus;
    
    private String createdAt;

	public Act getAct() {
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public Integer getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(Integer cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public Integer getElapsed() {
		return elapsed;
	}

	public void setElapsed(Integer elapsed) {
		this.elapsed = elapsed;
	}

	public List<Object> getInlineTraces() {
		return inlineTraces;
	}

	public void setInlineTraces(List<Object> inlineTraces) {
		this.inlineTraces = inlineTraces;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Integer getTotalCpuUsage() {
		return totalCpuUsage;
	}

	public void setTotalCpuUsage(Integer totalCpuUsage) {
		this.totalCpuUsage = totalCpuUsage;
	}

	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}

	public boolean isContextFree() {
		return contextFree;
	}

	public void setContextFree(boolean contextFree) {
		this.contextFree = contextFree;
	}

	public long getBlockNum() {
		return blockNum;
	}

	public void setBlockNum(long blockNum) {
		this.blockNum = blockNum;
	}

	public String getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(String blockTime) {
		this.blockTime = blockTime;
	}

	public String getProducerBlockId() {
		return producerBlockId;
	}

	public void setProducerBlockId(String producerBlockId) {
		this.producerBlockId = producerBlockId;
	}

	public List<AccountRamDelta> getAccountRamDeltas() {
		return accountRamDeltas;
	}

	public void setAccountRamDeltas(List<AccountRamDelta> accountRamDeltas) {
		this.accountRamDeltas = accountRamDeltas;
	}

	public String getTrxStatus() {
		return trxStatus;
	}

	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
