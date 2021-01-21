package org.cmcc.ecip.common.eos.client.model.response.chain.trx;





/**   
* @ProjectName chain.api
* @PackageName org.cmcc.ecip.chain.api.model.response.chain.trx.transaction
* @ClassName Transaction
* @Description
* @author guiyn
* @date 2020年6月17日 下午4:44:27
* @version   2020年6月17日 下午4:44:27
*    
*/

public class Transaction {
	long ref_block_prefix;
	long max_cpu_usage_ms;
	Object[] context_free_actions;
	String expiration;
	
	long max_net_usage_words;
    long  delay_sec;
    long ref_block_num;
    Action[] actions;
	public long getRef_block_prefix() {
		return ref_block_prefix;
	}
	public void setRef_block_prefix(long ref_block_prefix) {
		this.ref_block_prefix = ref_block_prefix;
	}
	public long getMax_cpu_usage_ms() {
		return max_cpu_usage_ms;
	}
	public void setMax_cpu_usage_ms(long max_cpu_usage_ms) {
		this.max_cpu_usage_ms = max_cpu_usage_ms;
	}
	public Object[] getContext_free_actions() {
		return context_free_actions;
	}
	public void setContext_free_actions(Object[] context_free_actions) {
		this.context_free_actions = context_free_actions;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public long getMax_net_usage_words() {
		return max_net_usage_words;
	}
	public void setMax_net_usage_words(long max_net_usage_words) {
		this.max_net_usage_words = max_net_usage_words;
	}
	public long getDelay_sec() {
		return delay_sec;
	}
	public void setDelay_sec(long delay_sec) {
		this.delay_sec = delay_sec;
	}
	public long getRef_block_num() {
		return ref_block_num;
	}
	public void setRef_block_num(long ref_block_num) {
		this.ref_block_num = ref_block_num;
	}
	public Action[] getActions() {
		return actions;
	}
	public void setActions(Action[] actions) {
		this.actions = actions;
	}
}
