package org.cmcc.ecip.common.eos.client.models.response.chain.block;



 

import java.math.BigInteger;




public class HeaderStateBlock {

    public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Long getBlockNum() {
		return blockNum;
	}



	public void setBlockNum(Long blockNum) {
		this.blockNum = blockNum;
	}



	public Integer getDposProposedIrreversibleBlocknum() {
		return dposProposedIrreversibleBlocknum;
	}



	public void setDposProposedIrreversibleBlocknum(Integer dposProposedIrreversibleBlocknum) {
		this.dposProposedIrreversibleBlocknum = dposProposedIrreversibleBlocknum;
	}



	public BigInteger getDposIrreversibleBlocknum() {
		return dposIrreversibleBlocknum;
	}



	public void setDposIrreversibleBlocknum(BigInteger dposIrreversibleBlocknum) {
		this.dposIrreversibleBlocknum = dposIrreversibleBlocknum;
	}



	public BigInteger getBftIrreversibleBlocknum() {
		return bftIrreversibleBlocknum;
	}



	public void setBftIrreversibleBlocknum(BigInteger bftIrreversibleBlocknum) {
		this.bftIrreversibleBlocknum = bftIrreversibleBlocknum;
	}



	public BigInteger getPendingScheduleLibNum() {
		return pendingScheduleLibNum;
	}



	public void setPendingScheduleLibNum(BigInteger pendingScheduleLibNum) {
		this.pendingScheduleLibNum = pendingScheduleLibNum;
	}



	public String getPendingScheduleHash() {
		return pendingScheduleHash;
	}



	public void setPendingScheduleHash(String pendingScheduleHash) {
		this.pendingScheduleHash = pendingScheduleHash;
	}



	public BlockHeader getHeader() {
		return header;
	}



	public void setHeader(BlockHeader header) {
		this.header = header;
	}



	private String id;

    
    
    private Long blockNum;

    
    
    private Integer dposProposedIrreversibleBlocknum;

    
    
    private BigInteger dposIrreversibleBlocknum;

    
    
    private BigInteger bftIrreversibleBlocknum;

    
    
    private BigInteger pendingScheduleLibNum;

    
    
    private String pendingScheduleHash;

    
    
    private BlockHeader header;

}
