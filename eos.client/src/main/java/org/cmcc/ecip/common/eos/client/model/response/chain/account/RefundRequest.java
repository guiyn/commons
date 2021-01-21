package org.cmcc.ecip.common.eos.client.model.response.chain.account;



 


/**
 * 赎回请求  存在三天的赎回期, 代币并不会马上到账
 */

public class RefundRequest {


    public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getCpuAmount() {
		return cpuAmount;
	}

	public void setCpuAmount(String cpuAmount) {
		this.cpuAmount = cpuAmount;
	}

	/**
     * 拥有者
     */
    
    private String owner;

    /**
     *  请求时间
     */
    
    
    private String requestTime;

    /**
     * 赎回的 带宽金额
     */
    
    
    private String netAmount;

    /**
     *  赎回的cpu 金额
     */
    
    
    private String cpuAmount;
}
