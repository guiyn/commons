package org.cmcc.ecip.tw.common.model;


public class CmccCert {
	String nodeId;
	String newKey;
	String oldKey;
	long validTime;
	String cert;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNewKey() {
		return newKey;
	}
	public void setNewKey(String newKey) {
		this.newKey = newKey;
	}
	public String getOldKey() {
		return oldKey;
	}
	public void setOldKey(String oldKey) {
		this.oldKey = oldKey;
	}
	public long getValidTime() {
		return validTime;
	}
	public void setValidTime(long validTime) {
		this.validTime = validTime;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	@Override
	public String toString() {
		return "CmccCert [nodeId=" + nodeId + ", newKey=" + newKey + ", oldKey=" + oldKey + ", validTime=" + validTime
				+ ", cert=" + cert + "]";
	}
	
}
