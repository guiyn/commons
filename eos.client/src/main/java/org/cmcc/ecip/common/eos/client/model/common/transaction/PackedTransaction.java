package org.cmcc.ecip.common.eos.client.model.common.transaction;


import java.util.List;

public class PackedTransaction {

	private String region;

	private long max_net_usage_words;

	private String expiration;

	private long ref_block_prefix;

	private long ref_block_num;

	private long max_cpu_usage_ms;

	private List<String> context_free_data;

	private List<String> transaction_extensions;

	private List<TransactionAction> context_free_actions;

	private List<TransactionAction> actions;

	private List<String> signatures;
	private long delay_sec;
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public long getMax_net_usage_words() {
		return max_net_usage_words;
	}
	public void setMax_net_usage_words(long max_net_usage_words) {
		this.max_net_usage_words = max_net_usage_words;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public long getRef_block_prefix() {
		return ref_block_prefix;
	}
	public void setRef_block_prefix(long ref_block_prefix) {
		this.ref_block_prefix = ref_block_prefix;
	}
	public long getRef_block_num() {
		return ref_block_num;
	}
	public void setRef_block_num(long ref_block_num) {
		this.ref_block_num = ref_block_num;
	}
	public long getMax_cpu_usage_ms() {
		return max_cpu_usage_ms;
	}
	public void setMax_cpu_usage_ms(long max_cpu_usage_ms) {
		this.max_cpu_usage_ms = max_cpu_usage_ms;
	}
	public List<String> getContext_free_data() {
		return context_free_data;
	}
	public void setContext_free_data(List<String> context_free_data) {
		this.context_free_data = context_free_data;
	}
	public List<String> getTransaction_extensions() {
		return transaction_extensions;
	}
	public void setTransaction_extensions(List<String> transaction_extensions) {
		this.transaction_extensions = transaction_extensions;
	}
	public List<TransactionAction> getContext_free_actions() {
		return context_free_actions;
	}
	public void setContext_free_actions(List<TransactionAction> context_free_actions) {
		this.context_free_actions = context_free_actions;
	}
	public List<TransactionAction> getActions() {
		return actions;
	}
	public void setActions(List<TransactionAction> actions) {
		this.actions = actions;
	}
	public List<String> getSignatures() {
		return signatures;
	}
	public void setSignatures(List<String> signatures) {
		this.signatures = signatures;
	}
	public long getDelay_sec() {
		return delay_sec;
	}
	public void setDelay_sec(long delay_sec) {
		this.delay_sec = delay_sec;
	}
}
