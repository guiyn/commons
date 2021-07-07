package org.cmcc.ecip.common.eos.client.service.chain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cmcc.ecip.common.eos.client.model.common.transaction.PackedTransaction;
import org.cmcc.ecip.common.eos.client.model.common.transaction.SignedPackedTransaction;
import org.cmcc.ecip.common.eos.client.model.request.chain.AbiJsonToBinRequest;
import org.cmcc.ecip.common.eos.client.model.request.chain.RequiredKeysRequest;
import org.cmcc.ecip.common.eos.client.model.request.chain.transaction.PushTransactionRequest;
import org.cmcc.ecip.common.eos.client.model.response.chain.AbiBinToJson;
import org.cmcc.ecip.common.eos.client.model.response.chain.AbiJsonToBin;
import org.cmcc.ecip.common.eos.client.model.response.chain.Block;
import org.cmcc.ecip.common.eos.client.model.response.chain.ChainInfo;
import org.cmcc.ecip.common.eos.client.model.response.chain.RequiredKeys;
import org.cmcc.ecip.common.eos.client.model.response.chain.TableRow;
import org.cmcc.ecip.common.eos.client.model.response.chain.abi.Abi;
import org.cmcc.ecip.common.eos.client.model.response.chain.account.Account;
import org.cmcc.ecip.common.eos.client.model.response.chain.code.Code;
import org.cmcc.ecip.common.eos.client.model.response.chain.currencystats.CurrencyStats;
import org.cmcc.ecip.common.eos.client.model.response.chain.transaction.PushedTransaction;
import org.cmcc.ecip.common.eos.client.model.response.chain.transaction.ScheduledTransactionResponse;
import org.cmcc.ecip.common.eos.client.service.feigns.EosChainApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EosChainService {

	@Autowired()
	private EosChainApiClient eosChainApiClient;

	public ChainInfo getChainInfo() {
		return eosChainApiClient.getChainInfo();
	}

	public Block getBlock(String blockNumberOrId) {
		return eosChainApiClient.getBlock(Collections.singletonMap("block_num_or_id", blockNumberOrId));
	}

	public Account getAccount(String accountName) {
		return eosChainApiClient.getAccount(Collections.singletonMap("account_name", accountName));
	}

	public Abi getAbi(String accountName) {
		return eosChainApiClient.getAbi(Collections.singletonMap("account_name", accountName));
	}

	public Code getCode(String accountName) {
		return eosChainApiClient.getCode(Collections.singletonMap("account_name", accountName));
	}

	public TableRow getTableRows(String scope, String code, String table, Integer limit, String lower_bound,
			String upper_bound, Boolean reverse) {
		LinkedHashMap<String, Object> requestParameters = new LinkedHashMap<>(8);
		requestParameters.put("scope", scope);
		requestParameters.put("code", code);
		requestParameters.put("table", table);
		requestParameters.put("json", "true");
		if (limit != null)
			requestParameters.put("limit", limit);
		if (!StringUtils.isEmpty(lower_bound))
			requestParameters.put("lower_bound", lower_bound);
		if (!StringUtils.isEmpty(upper_bound))
			requestParameters.put("upper_bound", upper_bound);
		if (!StringUtils.isEmpty(reverse))
			requestParameters.put("reverse", reverse);
		return eosChainApiClient.getTableRows(requestParameters);
	}

	public TableRow getTableRows(String scope, String code, String table, Integer limit, String lower_bound,
			String upper_bound) {

		return getTableRows(scope, code, table, limit, lower_bound, upper_bound, null);

	}

	public TableRow getTableRows(String scope, String code, String table) {
		return getTableRows(scope, code, table, 500, null, null);
	}

	public TableRow getTableRows(String scope, String code, String table, Integer limit) {
		return getTableRows(scope, code, table, limit, null, null);
	}

	public TableRow getTableRows(String scope, String code, String table, Integer limit, String lower_bound) {

		return getTableRows(scope, code, table, limit, lower_bound, null, null);

	}
	
	public Map<String, ?> getTableRow(String scope, String code, String table, String id) {

		List<Map<String, ?>> l = getTableRows(scope, code, table, 1, id, id).getRows();

		if (l.size() > 0)
			return l.get(0);
		else
			return null;
	}

	public List<String> getCurrencyBalance(String code, String accountName, String symbol) {
		LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(3);

		requestParameters.put("code", code);
		requestParameters.put("account", accountName);
		requestParameters.put("symbol", symbol);

		return eosChainApiClient.getCurrencyBalance(requestParameters);
	}

	public AbiBinToJson abiBinToJson(String code, String action, String binargs) {
		LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(3);

		requestParameters.put("code", code);
		requestParameters.put("action", action);
		requestParameters.put("binargs", binargs);
		return eosChainApiClient.abiBinToJson(requestParameters);
	}

	public <T> AbiJsonToBin abiJsonToBin(String code, String action, T args) {
		return eosChainApiClient.abiJsonToBin(new AbiJsonToBinRequest<T>(code, action, args));
	}

	public PushedTransaction pushTransaction(String compression, SignedPackedTransaction packedTransaction) {
		return eosChainApiClient.pushTransaction(
				new PushTransactionRequest(compression, packedTransaction, packedTransaction.getSignatures()));
	}

	public PushedTransaction pushRawTransaction(String tx) {
		return eosChainApiClient.pushRawTransaction(tx);
	}

	public List<PushedTransaction> pushTransactions(List<PushTransactionRequest> pushTransactionRequests) {
		return eosChainApiClient.pushTransactions(pushTransactionRequests);
	}

	public RequiredKeys getRequiredKeys(PackedTransaction transaction, List<String> keys) {
		return eosChainApiClient.getRequiredKeys(new RequiredKeysRequest(transaction, keys));
	}

	public Map<String, CurrencyStats> getCurrencyStats(String code, String symbol) {
		LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(2);

		requestParameters.put("code", code);
		requestParameters.put("symbol", symbol);
		return eosChainApiClient.getCurrencyStats(requestParameters);
	}

//    
//    public void unlockWallet(String walletName, String walletPassword) {
//        List<String> requestFields = new ArrayList<>(2);
////        ErrorDecoder
//        requestFields.add(walletName);
//        requestFields.add(walletPassword);
//        eosWalletApiClient.unlockWallet(requestFields);
//    }

//    
//    public SignedPackedTransaction signTransaction(PackedTransaction packedTransaction, List<String> publicKeys, String chainId) {
//
//        List params = new ArrayList(3);
//        params.add(packedTransaction);
//        params.add(publicKeys);
//        params.add(chainId);
//        return eosWalletApiClient.signTransaction(params);
//    }

	public ScheduledTransactionResponse getScheduledtransactions(String lowerBound, String limit) {

		LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(2);

		requestParameters.put("json", "true");

		if (lowerBound != null) {
			requestParameters.put("lower_bound", lowerBound);
		}

		requestParameters.put("limit", limit);

		return eosChainApiClient.getScheduledtransaction(requestParameters);
	}
}
