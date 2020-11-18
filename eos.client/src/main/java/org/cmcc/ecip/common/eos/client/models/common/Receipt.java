package org.cmcc.ecip.common.eos.client.models.common;




import java.util.List;


public class Receipt {

    
    private Integer abiSequence;

    
    private String actDigest;

    
    private List<List<String>> authSequence = null;

    private Integer codeSequence;

    private Long globalSequence;

    private String receiver;

    private Integer recvSequence;

	public Integer getAbiSequence() {
		return abiSequence;
	}

	public void setAbiSequence(Integer abiSequence) {
		this.abiSequence = abiSequence;
	}

	public String getActDigest() {
		return actDigest;
	}

	public void setActDigest(String actDigest) {
		this.actDigest = actDigest;
	}

	public List<List<String>> getAuthSequence() {
		return authSequence;
	}

	public void setAuthSequence(List<List<String>> authSequence) {
		this.authSequence = authSequence;
	}

	public Integer getCodeSequence() {
		return codeSequence;
	}

	public void setCodeSequence(Integer codeSequence) {
		this.codeSequence = codeSequence;
	}

	public Long getGlobalSequence() {
		return globalSequence;
	}

	public void setGlobalSequence(Long globalSequence) {
		this.globalSequence = globalSequence;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getRecvSequence() {
		return recvSequence;
	}

	public void setRecvSequence(Integer recvSequence) {
		this.recvSequence = recvSequence;
	}
    
    
    
}
