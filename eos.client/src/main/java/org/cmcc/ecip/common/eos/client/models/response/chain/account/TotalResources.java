package org.cmcc.ecip.common.eos.client.models.response.chain.account;


 

import java.math.BigInteger;

/**
 * 抵押的总资源
 */

public class TotalResources {

    public String getCpuWeight() {
		return cpuWeight;
	}

	public void setCpuWeight(String cpuWeight) {
		this.cpuWeight = cpuWeight;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BigInteger getRamBytes() {
		return ramBytes;
	}

	public void setRamBytes(BigInteger ramBytes) {
		this.ramBytes = ramBytes;
	}

	/**
     *  "0.9002 EOS"
     *  抵押给CPU 的代币总量
     */
    
    
    private String cpuWeight;

    /**
     *  "0.9002 EOS"
     *  抵押给NET 的代币总量
     */
    
    
    private String netWeight;

    /**
     * 拥有者
     */
    
    
    private String owner;

    /**
     * 购买的 ram 总量
     */
    
    
    private BigInteger ramBytes;
}
