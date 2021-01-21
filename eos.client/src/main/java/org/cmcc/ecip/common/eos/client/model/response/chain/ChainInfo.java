package org.cmcc.ecip.common.eos.client.model.response.chain;

import com.alibaba.fastjson.annotation.JSONField;


import java.math.BigInteger;


public class ChainInfo {

   
    @JSONField(name="server_version_string")
    private String serverVersion;

   
    @JSONField(name="chain_id")
    private String chainId;

   
    @JSONField(name="head_block_num")
    private Integer headBlockNum;

   
    @JSONField(name="last_irreversible_block_num")
    private Integer lastIrreversibleBlockNum;

   
    @JSONField(name="last_irreversible_block_id")
    private String lastIrreversibleBlockId;

   
    @JSONField(name="head_block_id")
    private String headBlockId;

   
    @JSONField(name="head_block_time")
    private String headBlockTime;

   
    @JSONField(name="head_block_producer")
    private String headBlockProducer;

   
    @JSONField(name="virtual_block_cpu_limit")
    private BigInteger virtualBlockCpuLimit;

   
    @JSONField(name="virtual_block_net_limit")
    private BigInteger virtualBlockNetLimit;

   
    @JSONField(name="block_cpu_limit")
    private BigInteger blockCpuLimit;

   
    @JSONField(name="block_net_limit")
    private BigInteger blockNetLimit;


	public String getServerVersion() {
		return serverVersion;
	}


	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}


	public String getChainId() {
		return chainId;
	}


	public void setChainId(String chainId) {
		this.chainId = chainId;
	}


	public Integer getHeadBlockNum() {
		return headBlockNum;
	}


	public void setHeadBlockNum(Integer headBlockNum) {
		this.headBlockNum = headBlockNum;
	}


	public Integer getLastIrreversibleBlockNum() {
		return lastIrreversibleBlockNum;
	}


	public void setLastIrreversibleBlockNum(Integer lastIrreversibleBlockNum) {
		this.lastIrreversibleBlockNum = lastIrreversibleBlockNum;
	}


	public String getLastIrreversibleBlockId() {
		return lastIrreversibleBlockId;
	}


	public void setLastIrreversibleBlockId(String lastIrreversibleBlockId) {
		this.lastIrreversibleBlockId = lastIrreversibleBlockId;
	}


	public String getHeadBlockId() {
		return headBlockId;
	}


	public void setHeadBlockId(String headBlockId) {
		this.headBlockId = headBlockId;
	}


	public String getHeadBlockTime() {
		return headBlockTime;
	}


	public void setHeadBlockTime(String headBlockTime) {
		this.headBlockTime = headBlockTime;
	}


	public String getHeadBlockProducer() {
		return headBlockProducer;
	}


	public void setHeadBlockProducer(String headBlockProducer) {
		this.headBlockProducer = headBlockProducer;
	}


	public BigInteger getVirtualBlockCpuLimit() {
		return virtualBlockCpuLimit;
	}


	public void setVirtualBlockCpuLimit(BigInteger virtualBlockCpuLimit) {
		this.virtualBlockCpuLimit = virtualBlockCpuLimit;
	}


	public BigInteger getVirtualBlockNetLimit() {
		return virtualBlockNetLimit;
	}


	public void setVirtualBlockNetLimit(BigInteger virtualBlockNetLimit) {
		this.virtualBlockNetLimit = virtualBlockNetLimit;
	}


	public BigInteger getBlockCpuLimit() {
		return blockCpuLimit;
	}


	public void setBlockCpuLimit(BigInteger blockCpuLimit) {
		this.blockCpuLimit = blockCpuLimit;
	}


	public BigInteger getBlockNetLimit() {
		return blockNetLimit;
	}


	public void setBlockNetLimit(BigInteger blockNetLimit) {
		this.blockNetLimit = blockNetLimit;
	}
    
}
