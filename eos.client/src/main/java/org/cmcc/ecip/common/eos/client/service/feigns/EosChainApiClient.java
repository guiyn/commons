package org.cmcc.ecip.common.eos.client.service.feigns;

import org.cmcc.ecip.common.eos.client.model.request.chain.*;
import org.cmcc.ecip.common.eos.client.model.request.chain.transaction.PushTransactionRequest;
import org.cmcc.ecip.common.eos.client.model.response.chain.*;
import org.cmcc.ecip.common.eos.client.model.response.chain.Producer;
import org.cmcc.ecip.common.eos.client.model.response.chain.abi.*;
import org.cmcc.ecip.common.eos.client.model.response.chain.abi.Abi;
import org.cmcc.ecip.common.eos.client.model.response.chain.account.*;
import org.cmcc.ecip.common.eos.client.model.response.chain.block.*;
import org.cmcc.ecip.common.eos.client.model.response.chain.code.*;
import org.cmcc.ecip.common.eos.client.model.response.chain.currencystats.CurrencyStats;
import org.cmcc.ecip.common.eos.client.model.response.chain.transaction.PushedTransaction;
import org.cmcc.ecip.common.eos.client.model.response.chain.transaction.ScheduledTransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@FeignClient(contextId = "eoschainapi", name = "eoschainapi", url = "${eos.chain.url}")
public interface EosChainApiClient {

	@GetMapping("/v1/chain/get_info")
	ChainInfo getChainInfo();

	@PostMapping("/v1/chain/get_block")
	Block getBlock(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_block_header_state")
	HeaderStateBlock getBlockHeaderState(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_account")
	Account getAccount(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_abi")
	Abi getAbi(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_raw_code_and_abi")
	RawCodeAndAbi getRawCodeAndAbi(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_raw_abi")
	RawAbi getRawAbi(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_code")
	Code getCode(Map<String, Object> requestFields);

	@PostMapping("/v1/chain/get_table_rows")
	TableRow getTableRows(Map<String, Object> requestFields);

	@PostMapping("/v1/chain/get_table_by_scope")
	TableRow getTableByScope(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_currency_balance")
	List<String> getCurrencyBalance(Map<String, String> requestFields);

	@PostMapping("/v1/chain/abi_json_to_bin")
	AbiJsonToBin abiJsonToBin(AbiJsonToBinRequest<?> abiJsonToBinRequest);

	@PostMapping("/v1/chain/abi_bin_to_json")
	AbiBinToJson abiBinToJson(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_producers")
	Producer getProducers(Map<String, String> requestFields);

	@PostMapping("/v1/chain/push_transaction")
	PushedTransaction pushTransaction(PushTransactionRequest pushTransactionRequest);

	@PostMapping(name = "/v1/chain/push_transaction", consumes = "application/json;charset=UTF-8")
	PushedTransaction pushRawTransaction(String tx);

	@PostMapping("/v1/chain/push_transactions")
	List<PushedTransaction> pushTransactions(List<PushTransactionRequest> pushTransactionRequests);

	@PostMapping("/v1/chain/get_required_keys")
	RequiredKeys getRequiredKeys(RequiredKeysRequest requiredKeysRequest);

	@PostMapping("/v1/chain/get_currency_stats")
	Map<String, CurrencyStats> getCurrencyStats(Map<String, String> requestFields);

	@PostMapping("/v1/chain/get_scheduled_transactions")
	ScheduledTransactionResponse getScheduledtransaction(Map<String, String> requestFields);
}
