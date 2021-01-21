package org.cmcc.ecip.common.eos.client.model.response.chain.code;


 

import java.util.List;


public class Table {

    
    private String name;
    
    private String type;
    
    
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getIndexType() {
		return indexType;
	}


	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}


	public List<String> getKeyNames() {
		return keyNames;
	}


	public void setKeyNames(List<String> keyNames) {
		this.keyNames = keyNames;
	}


	public List<String> getKeyTypes() {
		return keyTypes;
	}


	public void setKeyTypes(List<String> keyTypes) {
		this.keyTypes = keyTypes;
	}


	private String indexType;
    
    
    private List<String> keyNames;
    
    
    private List<String> keyTypes;
}
