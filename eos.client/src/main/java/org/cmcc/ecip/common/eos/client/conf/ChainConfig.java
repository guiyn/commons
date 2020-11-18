package org.cmcc.ecip.common.eos.client.conf;


import org.cmcc.ecip.common.eos.client.conf.vo.Chain;
import org.cmcc.ecip.common.eos.client.conf.vo.Wallet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Configuration
@ConfigurationProperties(prefix = "eos")
public class ChainConfig {
	Chain chain;
	Wallet wallet;
	public Chain getChain() {
		return chain;
	}
	public void setChain(Chain chain) {
		this.chain = chain;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	
}
