package org.cmcc.ecip.common.eos.client.models.response.chain.account;

 

import java.math.BigInteger;
import java.util.List;


public class Account {

	
	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public boolean isPrivileged() {
		return privileged;
	}

	public void setPrivileged(boolean privileged) {
		this.privileged = privileged;
	}

	public String getLastCodeUpdate() {
		return lastCodeUpdate;
	}

	public void setLastCodeUpdate(String lastCodeUpdate) {
		this.lastCodeUpdate = lastCodeUpdate;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getCoreLiquidBalance() {
		return coreLiquidBalance;
	}

	public void setCoreLiquidBalance(String coreLiquidBalance) {
		this.coreLiquidBalance = coreLiquidBalance;
	}

	public BigInteger getRamQuota() {
		return ramQuota;
	}

	public void setRamQuota(BigInteger ramQuota) {
		this.ramQuota = ramQuota;
	}

	public BigInteger getRamUsage() {
		return ramUsage;
	}

	public void setRamUsage(BigInteger ramUsage) {
		this.ramUsage = ramUsage;
	}

	public BigInteger getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigInteger netWeight) {
		this.netWeight = netWeight;
	}

	public BigInteger getCpuWeight() {
		return cpuWeight;
	}

	public void setCpuWeight(BigInteger cpuWeight) {
		this.cpuWeight = cpuWeight;
	}

	public Limit getNetLimit() {
		return netLimit;
	}

	public void setNetLimit(Limit netLimit) {
		this.netLimit = netLimit;
	}

	public Limit getCpuLimit() {
		return cpuLimit;
	}

	public void setCpuLimit(Limit cpuLimit) {
		this.cpuLimit = cpuLimit;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public TotalResources getTotalResources() {
		return totalResources;
	}

	public void setTotalResources(TotalResources totalResources) {
		this.totalResources = totalResources;
	}

	public SelfDelegatedBandwidth getSelfDelegatedBandwidth() {
		return selfDelegatedBandwidth;
	}

	public void setSelfDelegatedBandwidth(SelfDelegatedBandwidth selfDelegatedBandwidth) {
		this.selfDelegatedBandwidth = selfDelegatedBandwidth;
	}

	public RefundRequest getRefundRequest() {
		return refundRequest;
	}

	public void setRefundRequest(RefundRequest refundRequest) {
		this.refundRequest = refundRequest;
	}

	public Voter getVoterInfo() {
		return voterInfo;
	}

	public void setVoterInfo(Voter voterInfo) {
		this.voterInfo = voterInfo;
	}

	/**
	 * eos 账户名
	 */

	private String account_name;

	private boolean privileged;

	private String lastCodeUpdate;

	/**
	 * 创建时间
	 */

	private String created;

	/**
	 * 可用余额
	 */

	private String coreLiquidBalance;

	/**
	 * 持有的RAM量，单位字节。
	 */

	private BigInteger ramQuota;

	/**
	 * 已用RAM 量，单位字节。
	 */

	private BigInteger ramUsage;

	/**
	 * net 抵押总量 包括自己抵押 和别人抵押
	 *
	 */

	private BigInteger netWeight;

	/**
	 * cpu 抵押总量 包括自己抵押 和别人抵押
	 */

	private BigInteger cpuWeight;

	/**
	 * new 资源限制
	 */

	private Limit netLimit;

	/**
	 * cpu 资源限制
	 */

	private Limit cpuLimit;

	private List<Permission> permissions;

	/**
	 * 分配给自己的EOS 资源
	 */

	private TotalResources totalResources;

	/**
	 * 抵押给自己的 CPU NET 总量 可赎回量
	 */

	private SelfDelegatedBandwidth selfDelegatedBandwidth;

	/**
	 * 赎回请求
	 */

	private RefundRequest refundRequest;

	/**
	 * 投票信息
	 */

	private Voter voterInfo;

	public String activeKey() {
		return findKey("active");
	}

	public String ownerKey() {
		return findKey("owner");
	}

	public String defaultKey() {
		return findKey("default");
	}

	public String findKey(String name) {

		if (permissions == null || permissions.size() < 0)
			return null;

		for (Permission p : permissions) {
			if (p.getPermName().equals(name)) {
				List<Key> keys = p.getRequiredAuth().getKeys();
				if (keys != null && keys.size() > 0) {
					return keys.get(0).getKey();
				}
			}
		}
		return null;
	}
}
