package org.cmcc.ecip.common.eos.client.models.request.wallet.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import  org.cmcc.ecip.common.eos.client.models.common.transaction.PackedTransaction;


@JSONType(mappingTo=Array.class)
public class SignTransactionRequest {

	private PackedTransaction packed_transaction;

	private List<String> public_keys;

	private String chain_id;

	public SignTransactionRequest(PackedTransaction packedTransaction, List<String> publicKeys, String chainId) {
		this.packed_transaction = packedTransaction;
		this.public_keys = publicKeys;
		this.chain_id = chainId;
	}
	
	public List<Object> getrequestList(){
		List<Object> l =new ArrayList<Object>();
		l.add(packed_transaction);
		l.add(public_keys);
		l.add(chain_id);
		return l;
	}
	
	public String arrayJSON(){
		
		String[] arr=new String[] {
		JSON.toJSONString(packed_transaction),
		JSON.toJSONString(public_keys),
		chain_id};
		
		return JSON.toJSONString(arr);
	}

	public PackedTransaction getPacked_transaction() {
		return packed_transaction;
	}

	public void setPacked_transaction(PackedTransaction packed_transaction) {
		this.packed_transaction = packed_transaction;
	}

	public List<String> getPublic_keys() {
		return public_keys;
	}

	public void setPublic_keys(List<String> public_keys) {
		this.public_keys = public_keys;
	}

	public String getChain_id() {
		return chain_id;
	}

	public void setChain_id(String chain_id) {
		this.chain_id = chain_id;
	}
	
	
}
