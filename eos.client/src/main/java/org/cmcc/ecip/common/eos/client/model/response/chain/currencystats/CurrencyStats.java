package org.cmcc.ecip.common.eos.client.model.response.chain.currencystats;



 


public class CurrencyStats {

    
    private String supply;
    
    
    private String maxSupply;
    
    private String issuer;

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	public String getMaxSupply() {
		return maxSupply;
	}

	public void setMaxSupply(String maxSupply) {
		this.maxSupply = maxSupply;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

}
