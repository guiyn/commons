package org.cmcc.ecip.common.eos.client.service.wallet;

import java.util.ArrayList;

import java.util.List;

import org.cmcc.ecip.common.eos.client.exception.ChainApiException;
import org.cmcc.ecip.common.eos.client.exception.FeignError;
import org.cmcc.ecip.common.eos.client.models.common.transaction.PackedTransaction;
import org.cmcc.ecip.common.eos.client.models.common.transaction.SignedPackedTransaction;
import org.cmcc.ecip.common.eos.client.models.request.wallet.transaction.SignTransactionRequest;
import org.cmcc.ecip.common.eos.client.service.feigns.EosWalletApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import feign.FeignException.InternalServerError;
import feign.RetryableException;

@ConditionalOnProperty(value = "eos.wallet.url", havingValue = "true")
@Service
public class EosWalletService {

	static Logger log = LoggerFactory.getLogger("CHAIN");
	static int already_unlocked_code = 3120007;
	@Autowired
	EosWalletApiClient client;

	public String unlockWallet(String account_name, String account_pass) throws ChainApiException {
		List<String> requestFields = new ArrayList<String>();
		requestFields.add(account_name);
		requestFields.add(account_pass);
		try {
			log.debug("name:[" + account_name + "]  pass:[" + account_pass + "]");
			return client.unlockWallet(requestFields);
		} catch (RetryableException e) {

			throw new ChainApiException("connection error ," + e.getMessage(), e);
		} catch (InternalServerError e) {
			FeignError ferr = JSON.parseObject(e.contentUTF8(), FeignError.class);
			if (ferr != null && ferr.getError() != null && ferr.getError().getCode() == already_unlocked_code) {
				return "success";
			}
			throw new ChainApiException(e);
		} catch (Exception e) {

			throw new ChainApiException("unknow error ," + e.getMessage(), e);
		}
	}

	public SignedPackedTransaction signTransaction(PackedTransaction packedTransaction, List<String> publicKeys,
			String chainId) {

		return client
				.signTransaction(new SignTransactionRequest(packedTransaction, publicKeys, chainId).getrequestList());

	}

	public String listWallets() {

		return client.listWallets();

	}

	public String[][] listKeys(String walletUserName, String walletUserPass) {
		String[] para = new String[] { walletUserName, walletUserPass };
		return client.listKeys(para);
	}

	public String signDigest(String hexStr, String pubKey) {
		String[] para = new String[] { hexStr, pubKey };
		return client.signDigest(para);
	}

}
