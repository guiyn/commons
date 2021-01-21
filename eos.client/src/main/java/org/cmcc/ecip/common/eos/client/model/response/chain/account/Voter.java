package org.cmcc.ecip.common.eos.client.model.response.chain.account;



 

import java.math.BigInteger;


public class Voter {
    
    public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public BigInteger getStaked() {
		return staked;
	}

	public void setStaked(BigInteger staked) {
		this.staked = staked;
	}

	public String getLast_vote_weight() {
		return last_vote_weight;
	}

	public void setLast_vote_weight(String last_vote_weight) {
		this.last_vote_weight = last_vote_weight;
	}

	public String getProxied_vote_weight() {
		return proxied_vote_weight;
	}

	public void setProxied_vote_weight(String proxied_vote_weight) {
		this.proxied_vote_weight = proxied_vote_weight;
	}

	public Integer getIs_proxy() {
		return is_proxy;
	}

	public void setIs_proxy(Integer is_proxy) {
		this.is_proxy = is_proxy;
	}

	public Integer getFlags1() {
		return flags1;
	}

	public void setFlags1(Integer flags1) {
		this.flags1 = flags1;
	}

	public Integer getReserved2() {
		return reserved2;
	}

	public void setReserved2(Integer reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	private String owner;
    
    private String proxy;
    
    private BigInteger staked;
    
    private String last_vote_weight;
    
    private String proxied_vote_weight;
    
    private Integer is_proxy;
    
    private Integer flags1;
    
    private Integer reserved2;
    
    private String reserved3;
}
