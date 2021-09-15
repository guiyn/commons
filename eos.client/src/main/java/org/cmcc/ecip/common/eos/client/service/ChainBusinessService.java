package org.cmcc.ecip.common.eos.client.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cmcc.ecip.common.eos.client.conf.ChainConfig;
import org.cmcc.ecip.common.eos.client.conf.vo.Chain;
import org.cmcc.ecip.common.eos.client.conf.vo.Wallet;
import org.cmcc.ecip.common.eos.client.exception.ChainApiException;
import org.cmcc.ecip.common.eos.client.exception.FeignError;
import org.cmcc.ecip.common.eos.client.model.ChainDataObject;
import org.cmcc.ecip.common.eos.client.model.common.transaction.PackedTransaction;
import org.cmcc.ecip.common.eos.client.model.common.transaction.SignedPackedTransaction;
import org.cmcc.ecip.common.eos.client.model.common.transaction.TransactionAction;
import org.cmcc.ecip.common.eos.client.model.common.transaction.TransactionAuthorization;
import org.cmcc.ecip.common.eos.client.model.response.chain.AbiJsonToBin;
import org.cmcc.ecip.common.eos.client.model.response.chain.Block;
import org.cmcc.ecip.common.eos.client.model.response.chain.ChainInfo;
import org.cmcc.ecip.common.eos.client.model.response.chain.TableRow;
import org.cmcc.ecip.common.eos.client.model.response.chain.account.Account;
import org.cmcc.ecip.common.eos.client.model.response.chain.transaction.PushedTransaction;
import org.cmcc.ecip.common.eos.client.model.response.chain.trx.Action;
import org.cmcc.ecip.common.eos.client.model.response.chain.trx.Trx;
import org.cmcc.ecip.common.eos.client.model.response.history.transaction.Transaction;
import org.cmcc.ecip.common.eos.client.service.chain.EosChainService;
import org.cmcc.ecip.common.eos.client.service.wallet.EosWalletService;
import org.cmcc.ecip.common.eos.client.util.MapBeanExchange;
import org.cmcc.ecip.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import feign.FeignException.InternalServerError;

@Service
public class ChainBusinessService {

	static Logger log = LoggerFactory.getLogger("CHAIN");
	final static String save_action = "save";
	static final String GMT_ID = "GMT";

	static final int push_expiration = 10;

	@Autowired
	EosChainService chainService;

	@Autowired
	EosWalletService walletService;
	@Autowired
	ChainConfig conf;

	public <T> List<T> getAll(Class<T> c) throws ChainApiException {

		return getAll(conf.getChain(), c, conf.getChain().getTable(), 100);
	}

	public <T> List<T> getAll(Chain chain, Class<T> c, String table, int limit) throws ChainApiException {

		try {
			List<Map<String, ?>> list = null;
			TableRow tr = chainService.getTableRows(chain.getScope(), chain.getAccount(), table);
			list = tr.getRows();
			while (tr.getMore().booleanValue()) {

				Map<String, ?> m = list.get(list.size() - 1);
				Object id = m.get("id");

				tr = chainService.getTableRows(chain.getScope(), chain.getAccount(), table, limit, id.toString(), null);
				list.addAll(tr.getRows());
			}

			return MapBeanExchange.listMap2beans(list, c);
		} catch (ChainApiException e) {
			throw e;
		} catch (Exception e) {
			String mess = "find " + chain.toString() + " error";
			log.error(mess, e);
			throw new ChainApiException(mess);
		}

	}

	public <T> T getById(String id, Class<T> c) throws ChainApiException {
		return getById(conf.getChain(), id, c);
	}

	public <T> T getById(Chain chain, String id, Class<T> c) throws ChainApiException {

		try {
			Map<String, ?> m = chainService.getTableRow(chain.getScope(), chain.getAccount(), chain.getTable(), id);
			if (m != null) {
				return MapBeanExchange.map2bean(m, c);
			} else {
				return null;
			}

		} catch (ChainApiException e) {
			throw e;
		} catch (Exception e) {
			String mess = "find " + chain.toString() + " id[" + id + "] error";
			log.error(mess, e);
			throw new ChainApiException(mess);
		}
	}

	public boolean push(ChainDataObject t, boolean checkBlockData, String action) throws ChainApiException {
		AbiJsonToBin data = buildBinData(t, conf.getChain().getAccount(), action);
		return push(conf.getWallet(), conf.getChain().getAccount(), conf.getChain().getPermission(),
				conf.getChain().getExpiration(), true, checkBlockData, action, data);
	}

	public boolean push(ChainDataObject t, String action) throws ChainApiException {
		return push(conf.getWallet(), conf.getChain().getAccount(), conf.getChain().getPermission(), t, action);
	}

	public boolean push(ChainDataObject t) throws ChainApiException {
//		AbiJsonToBin data = buildBinData(t, chainAccount, action);
		return push(conf.getWallet(), conf.getChain().getAccount(), conf.getChain().getPermission(), t, save_action);
	}

	public boolean push(Wallet wallet, String chainAccount, String permission, ChainDataObject t, String action)
			throws ChainApiException {
		AbiJsonToBin data = buildBinData(t, chainAccount, action);
		return push(wallet, chainAccount, permission, conf.getChain().getExpiration(), true, true, action, data);
	}

	public boolean push(Wallet wallet, String chainAccount, String permission, List<? extends ChainDataObject> t,
			String action) throws ChainApiException {
		AbiJsonToBin data = buildBinData(t, chainAccount, action);
		return push(wallet, chainAccount, permission, conf.getChain().getExpiration(), true, true, action, data);
	}

	public boolean push(List<? extends ChainDataObject> t, String action) throws ChainApiException {
		return push(conf.getWallet(), conf.getChain().getAccount(), conf.getChain().getPermission(), t, action);
	}

	public boolean push(AbiJsonToBin data, String action) throws ChainApiException {
		return push(conf.getWallet(), conf.getChain().getAccount(), conf.getChain().getPermission(),
				conf.getChain().getExpiration(), true, true, action, data);
	}

	public boolean push(Wallet wallet, String chainAccount, String permission, long expiration_time,
			boolean checkLastBlockNum, boolean checkBlockData, String action, AbiJsonToBin data)
			throws ChainApiException {

		if (expiration_time < push_expiration) {
			log.warn("expiration_time has error expiration_time mast greater than 10 .. set expiration_time is 10 ");
			expiration_time = push_expiration;
		}
		// unlock wallet
		try {
			if (!StringUtils.isTrimEmpty(wallet.getPass())) {
				log.debug("wallet unlock..");
				String result = walletService.unlockWallet(wallet.getName(), wallet.getPass());
				log.debug("wallet unlock result : " + result);
			} else {
				log.debug("wallet  pass is null .not. unlock");
			}

		} catch (Exception e) {
			log.warn("wallet unlock error : " + e.getMessage());
		}
		// get account info and public key
		Account acc = chainService.getAccount(conf.getChain().getActor());
		log.info("get account info >> {}", acc.toString());

		String account_public_key = acc.findKey(permission);
		log.info("get {} permission {} key {}", chainAccount, permission, account_public_key);

		if (StringUtils.isEmpty(account_public_key)) {
			throw new ChainApiException("can not find public key " + permission + " ");
		}

		// get chain info
		ChainInfo chainInfo = chainService.getChainInfo();

//		Stirng  chainInfo.getHeadBlockTime();

		int last_irreversible_block_num = chainInfo.getLastIrreversibleBlockNum();
		log.info("get last irreversible block num from chainInfo >> {}", last_irreversible_block_num);
		log.info("get chain id from chainInfo >> {}", chainInfo.getChainId());

		Block block = chainService.getBlock(String.valueOf(last_irreversible_block_num));
		log.debug("get block by {}  >> {}", last_irreversible_block_num, block.toString());

		TransactionAuthorization transactionAuthorization = new TransactionAuthorization();
		transactionAuthorization.setActor(conf.getChain().getActor());
		transactionAuthorization.setPermission(permission);

		/* Create Transaction Action */
		TransactionAction transactionAction = new TransactionAction();
		transactionAction.setAccount(chainAccount);
		transactionAction.setName(action);
		transactionAction.setData(data.getBinargs());

		transactionAction.setAuthorization(Collections.singletonList(transactionAuthorization));

		/* Create a transaction */
		PackedTransaction packedTransaction = new PackedTransaction();
		packedTransaction.setRef_block_prefix(block.getRef_block_prefix());
		packedTransaction.setRef_block_num(block.getBlockNum());
		String expiration = ZonedDateTime.now(ZoneId.of(GMT_ID)).
				plusMinutes(expiration_time)
				.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		packedTransaction.setExpiration(expiration);
		packedTransaction.setRegion("0");
		packedTransaction.setActions(Collections.singletonList(transactionAction));

//  避免重复交易
//		TransactionAction freetransactionAction = new TransactionAction();
//		freetransactionAction.setAccount("eosio.null");
//		freetransactionAction.setName("nonce");
//		freetransactionAction.setData(String.valueOf(System.currentTimeMillis()));
//
//		packedTransaction.setContext_free_actions(Collections.singletonList(freetransactionAction));

		/* Sign the Transaction */

		SignedPackedTransaction signedPackedTransaction = null;
		try {
			log.info("begin signed packed transaction >>{}", packedTransaction);
			signedPackedTransaction = walletService.signTransaction(packedTransaction,
					Collections.singletonList(account_public_key), chainInfo.getChainId());
			log.info("Signed Packed Transaction >>{}", signedPackedTransaction);
		} catch (InternalServerError ise) {
			log.error("sign packed transaction internal server error >> " + ise.contentUTF8());
			FeignError ee = JSON.parseObject(ise.contentUTF8(), FeignError.class);
			throw new ChainApiException(ee);

		} catch (Exception e) {
			throw new ChainApiException("sign packed transaction error:", e);
		}
		/* Push the transaction */
		PushedTransaction pushedTransaction = chainService.pushTransaction("none", signedPackedTransaction);

		String transactionId = pushedTransaction.getTransaction_id();

		if (pushedTransaction == null || transactionId == null || pushedTransaction.getProcessed() == null
				|| "".equals(transactionId.trim())) {
			throw new ChainApiException("push has error ,transaction is null..");

		}
		long result_block_num = pushedTransaction.getProcessed().getBlock_num();
		log.info("put over transaction id is >>>>> {} >>>>> result block num>>> {}", pushedTransaction,
				result_block_num);

		if (!checkLastBlockNum) {
			log.info("NO CHECK !!!!! ; check block and transaction flag is false ....");
			return true;
		}

		log.info("begin check block and transaction..");
		/*
		 * check block and tranceaction.
		 */
		for (int i = 0; i <= push_expiration; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				log.error("thread sleep error..");
			}
			ChainInfo c = chainService.getChainInfo();
			log.info("[{}]begin check... get last irreversible block num from chainInfo >> {}",i, c.getLastIrreversibleBlockNum());
			if (c.getLastIrreversibleBlockNum() >= result_block_num) {
				if (!checkBlockData) {
					return true;
				}
				// block check
				try {
					if (checkBlockTransactionId(result_block_num, transactionId))
						return true;
					else
						log.warn("transactionId {} not in result_nlock_num {}", transactionId ,result_block_num);		
				} catch (ChainApiException e) {
					log.warn(e.getMessage());
				}
				if (checkBlockTransactionId(result_block_num + 1, transactionId))
					return true;
				else
					log.warn("transactionId {} not in result_nlock_num {}", transactionId ,(result_block_num+1));	
				
			} else {
				log.warn(
						"chain consensus has waring..push transaction result block num is {} ; but current chain block num is {}",
						result_block_num, c.getLastIrreversibleBlockNum());
			}
		}
		throw new ChainApiException("chain consensus fail..");
	}

	public AbiJsonToBin buildBinData(ChainDataObject t, String chainAccount, String action) {
		// build push bin data
		Map<String, Object> args = new HashMap<>(4);
		args.put("account", chainAccount);
		args.put("param", t);
		AbiJsonToBin data = chainService.abiJsonToBin(chainAccount, action, args);
		log.info("abiJsonToBin >> " + data);
		return data;
	}

	public AbiJsonToBin buildBinData(List<? extends ChainDataObject> list, String chainAccount, String action) {
		// build push bin data
		Map<String, Object> args = new HashMap<>(4);
		args.put("account", chainAccount);
		args.put("data", list);
		AbiJsonToBin data = chainService.abiJsonToBin(chainAccount, action, args);
		log.info("abiJsonToBin >> " + data);
		return data;
	}

	public AbiJsonToBin buildBinDataByMap(List<Map> list, String chainAccount, String action) {
		// build push bin data
		Map<String, Object> args = new HashMap<>(4);
		args.put("account", chainAccount);
		args.put("data", list);
		AbiJsonToBin data = chainService.abiJsonToBin(chainAccount, action, args);
		log.info("abiJsonToBin >> " + data);
		return data;
	}

	public AbiJsonToBin buildBinData(String key, Object o, String chainAccount, String action) {
		// build push bin data
		Map<String, Object> args = new HashMap<>(4);
		args.put("account", chainAccount);
		args.put(key, o);
		AbiJsonToBin data = chainService.abiJsonToBin(chainAccount, action, args);
		log.info("abiJsonToBin >> " + data);
		return data;
	}

	public AbiJsonToBin buildBinData(String key, Object o, String action) {
		// build push bin data
		Map<String, Object> args = new HashMap<>(4);
		args.put("account", conf.getChain().getAccount());
		args.put(key, o);
		AbiJsonToBin data = chainService.abiJsonToBin(conf.getChain().getAccount(), action, args);
		log.info("abiJsonToBin >> " + data);
		return data;
	}

	public AbiJsonToBin buildBinData(Map<String, Object> args, String chainAccount, String action) {
		try {
			args.put("account", chainAccount);
			AbiJsonToBin data = chainService.abiJsonToBin(chainAccount, action, args);
			log.info("abiJsonToBin >> " + data);
			return data;
		} catch (ChainApiException e) {
			log.error(e.getMessage()+""+e.getEosError() );
			throw e;
		}

	}

	public AbiJsonToBin buildBinData(Map<String, Object> args, String action) {

		args.put("account", conf.getChain().getAccount());
		AbiJsonToBin data = chainService.abiJsonToBin(conf.getChain().getAccount(), action, args);
		log.info("abiJsonToBin >> " + data);
		return data;
	}

	boolean checkBlockTransactionId(long blockNum, String transactionId) throws ChainApiException {
		Transaction[] ts = chainService.getBlock(String.valueOf(blockNum)).getTransactions();
		if (ts == null || ts.length <= 0)
			throw new ChainApiException(
					"chain consensus fail.. block [" + blockNum + "]>> transactions;array is null or length is 0");
		for (Transaction tts : ts) {
			Trx trx = tts.getTrx();
			if (trx == null)
				throw new ChainApiException(
						"chain consensus fail.. block [" + blockNum + "]>> transactions>> trx; is null");
			if (transactionId.equals(trx.getId())) {
				return true;
			}
		}
		return false;
	}

	boolean checkBlockTransactionData(long blockNum, String hex_data) throws ChainApiException {
		Transaction[] ts = chainService.getBlock(String.valueOf(blockNum)).getTransactions();
		if (ts == null || ts.length <= 0)
			throw new ChainApiException("chain consensus fail.. block >> transactions;array is null or length is 0");
		for (Transaction tts : ts) {
			Trx trx = tts.getTrx();
			if (trx == null)
				throw new ChainApiException("chain consensus fail.. block >> transactions[0] >> trx; is null");
			Action[] actions = trx.getTransaction().getActions();

			if (actions == null || actions.length <= 0)
				throw new ChainApiException(
						"chain consensus fail.. block >> transaction >> trx >> transaction >> action ;array is null or length is 0");

			for (Action act : actions) {
				String t_hex_data = act.getHex_data();

				if (hex_data.equals(t_hex_data)) {
					return true;
				}
			}

		}

		return false;
	}

}
