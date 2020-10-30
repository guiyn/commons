package org.cmcc.ecip.common.eos.crypto.ec;

public enum SecpEnum {
	/**
	 * envoy 支持
	 */
	SECP256R1("P-256", "secp256r1", "1.2.840.10045.3.1.7", 1),
	/**
	 * 区块链支持 envoy 签名不支持的算法
	 */
	SECP256K1("secp256k1", "secp256k1", "1.3.132.0.10", 0);

	private String name;
	private String stdName;
	private String oid;
	private int type;

	private SecpEnum(String name, String stdName, String oid, int type) {

		this.name = name;

		this.stdName = stdName;

		this.oid = oid;
		this.type = type;
	}

	String getName() {
		return name;
	}

	String getStdName() {
		return stdName;
	}

	public String getOid() {
		return oid;
	}

	public int getType() {
		return type;
	}

}
