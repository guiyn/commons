package org.cmcc.ecip.common.eos.client.model.response.chain.code;



 

import java.util.List;


public class Abi {

    
    public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public List<Type> getTypes() {
		return types;
	}



	public void setTypes(List<Type> types) {
		this.types = types;
	}



	public List<Struct> getStructs() {
		return structs;
	}



	public void setStructs(List<Struct> structs) {
		this.structs = structs;
	}



	public List<Action> getActions() {
		return actions;
	}



	public void setActions(List<Action> actions) {
		this.actions = actions;
	}



	public List<Table> getTables() {
		return tables;
	}



	public void setTables(List<Table> tables) {
		this.tables = tables;
	}



	public List<String> getRicardianClauses() {
		return ricardianClauses;
	}



	public void setRicardianClauses(List<String> ricardianClauses) {
		this.ricardianClauses = ricardianClauses;
	}



	public List<ErrorMessage> getErrorMessages() {
		return errorMessages;
	}



	public void setErrorMessages(List<ErrorMessage> errorMessages) {
		this.errorMessages = errorMessages;
	}



	public Object getAbiExtensions() {
		return abiExtensions;
	}



	public void setAbiExtensions(Object abiExtensions) {
		this.abiExtensions = abiExtensions;
	}



	private String version;

    
    private List<Type> types;

    
    private List<Struct> structs;

    
    private List<Action> actions;

    
    private List<Table> tables;

    
    
    private List<String> ricardianClauses;

    
    
    private List<ErrorMessage> errorMessages;

    
    
    // TODO: this might be of some particular type, but I couldn't find any examples of how this would look like
    private Object abiExtensions;

}
