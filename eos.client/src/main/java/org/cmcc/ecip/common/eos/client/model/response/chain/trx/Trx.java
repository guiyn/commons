package org.cmcc.ecip.common.eos.client.model.response.chain.trx;

import java.util.List;



/**   
* @ProjectName chain.api
* @PackageName org.cmcc.ecip.chain.api.model.response.chain.trx
* @ClassName Trx
* @Description
* @author guiyn
* @date 2020年6月17日 下午4:36:53
* @version   2020年6月17日 下午4:36:53
*    
*/

public class Trx {

	String packed_trx;
	String packed_context_free_data;
	String id;
	String compression;
	List<String> signatures;
	Transaction transaction;
	List<String> context_free_data;
	public String getPacked_trx() {
		return packed_trx;
	}
	public void setPacked_trx(String packed_trx) {
		this.packed_trx = packed_trx;
	}
	public String getPacked_context_free_data() {
		return packed_context_free_data;
	}
	public void setPacked_context_free_data(String packed_context_free_data) {
		this.packed_context_free_data = packed_context_free_data;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompression() {
		return compression;
	}
	public void setCompression(String compression) {
		this.compression = compression;
	}
	public List<String> getSignatures() {
		return signatures;
	}
	public void setSignatures(List<String> signatures) {
		this.signatures = signatures;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public List<String> getContext_free_data() {
		return context_free_data;
	}
	public void setContext_free_data(List<String> context_free_data) {
		this.context_free_data = context_free_data;
	}
}
