package org.cmcc.ecip.common.eos.client.models.common.transaction;





import java.util.List;

public class TransactionAction {

    
    private String account;
    
    private String name;
    
    private List<TransactionAuthorization> authorization;
    
    private String data;

    /*TODO Dynamically Unpack JSON Data */
    
    private String hex_data;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TransactionAuthorization> getAuthorization() {
		return authorization;
	}

	public void setAuthorization(List<TransactionAuthorization> authorization) {
		this.authorization = authorization;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHex_data() {
		return hex_data;
	}

	public void setHex_data(String hex_data) {
		this.hex_data = hex_data;
	}

}
