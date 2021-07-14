package org.cmcc.ecip.tw.common.model;



/**
 * @ProjectName tw.server
 * @PackageName org.cmcc.ecip.R.tw.server.model
 * @ClassName SelfCert
 * @Description
 * @author guiyn
 * @date 2020年1月10日 下午3:38:19
 * @version 2020年1月10日 下午3:38:19
 * 
 */

public class SelfCert {

	String cert;
	
	String mainKey;
	
	String privateKey;
	
	String rootCert;

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getMainKey() {
		return mainKey;
	}

	public void setMainKey(String mainKey) {
		this.mainKey = mainKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getRootCert() {
		return rootCert;
	}

	public void setRootCert(String rootCert) {
		this.rootCert = rootCert;
	}

	@Override
	public String toString() {
		return "SelfCert [cert=" + cert + ", mainKey=" + mainKey + ", privateKey=" + privateKey + ", rootCert="
				+ rootCert + "]";
	}

}
