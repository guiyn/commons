package org.cmcc.ecip.common.eos.client.service.feigns;

import java.util.List;

import org.cmcc.ecip.common.eos.client.model.common.transaction.SignedPackedTransaction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@ConditionalOnProperty(prefix = "eos.wallet",name = "url", matchIfMissing = true)
@FeignClient(contextId = "walletcenter", name = "walletcenter", url = "${eos.wallet.url}")
public interface EosWalletApiClient {

	@PostMapping("/v1/wallet/unlock")
	String unlockWallet(List<String> requestFields);

	@PostMapping("/v1/wallet/sign_transaction")
	SignedPackedTransaction signTransaction(List<Object> json);

	@GetMapping("/v1/wallet/list_wallets")
	String listWallets();

	@PostMapping("/v1/wallet/list_keys")
	String[][] listKeys(String[] param);

	@PostMapping("/v1/wallet/sign_digest")
	String signDigest(String[] param);
}
