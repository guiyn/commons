package org.cmcc.ecip.common.eos.client.model.response.chain.account;

 

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
	 * eos ?????????
	 */

	private String account_name;

	private boolean privileged;

	private String lastCodeUpdate;

	/**
	 * ????????????
	 */

	private String created;

	/**
	 * ????????????
	 */

	private String coreLiquidBalance;

	/**
	 * ?????????RAM?????????????????????
	 */

	private BigInteger ramQuota;

	/**
	 * ??????RAM ?????????????????????
	 */

	private BigInteger ramUsage;

	/**
	 * net ???????????? ?????????????????? ???????????????
	 *
	 */

	private BigInteger netWeight;

	/**
	 * cpu ???????????? ?????????????????? ???????????????
	 */

	private BigInteger cpuWeight;

	/**
	 * new ????????????
	 */

	private Limit netLimit;

	/**
	 * cpu ????????????
	 */

	private Limit cpuLimit;

	private List<Permission> permissions;

	/**
	 * ??????????????????EOS ??????
	 */

	private TotalResources totalResources;

	/**
	 * ?????????????????? CPU NET ?????? ????????????
	 */

	private SelfDelegatedBandwidth selfDelegatedBandwidth;

	/**
	 * ????????????
	 */

	private RefundRequest refundRequest;

	/**
	 * ????????????
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
