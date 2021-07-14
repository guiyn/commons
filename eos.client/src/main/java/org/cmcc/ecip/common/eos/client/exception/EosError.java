package org.cmcc.ecip.common.eos.client.exception;

import java.util.List;




public class EosError {

	int code;
	String name;
	String what;
	List<?> details;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public List<?> getDetails() {
		return details;
	}
	public void setDetails(List<?> details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "EosError [code=" + code + ", name=" + name + ", what=" + what + ", details=" + details + "]";
	}
	
	
}
