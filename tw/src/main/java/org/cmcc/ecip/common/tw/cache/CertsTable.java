package org.cmcc.ecip.common.tw.cache;

import java.util.Enumeration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.cmcc.ecip.common.tw.model.CmccCert;
import org.cmcc.ecip.common.tw.model.SelfCert;
import org.cmcc.ecip.common.tw.util.Consts;
import org.cmcc.ecip.common.tw.util.StringUtils;
import org.cmcc.ecip.common.tw.util.TwException;

//import org.cmcc.ecip.R.util.StringUtil;
//import org.cmcc.ecip.tw.util.Consts;
//import org.cmcc.ecip.tw.util.TwException;
//import org.cmcc.ecip.tw.vo.CmccCert;

/**
 * @ProjectName tw2
 * @PackageName com.cmcc.ecip.tw
 * @ClassName CertsTable
 * @Description
 * @author guiyn
 * @date 2019年12月9日 下午1:08:08
 * @version 2019年12月9日 下午1:08:08
 * 
 */
public class CertsTable extends ConcurrentHashMap<String, CmccCert> {

	private static final long serialVersionUID = 1L;

	private static SelfCert self;

	public SelfCert getSelf() {
		if (self == null) {
			self = new SelfCert();
			CmccCert cc = this.get("0000");
			self.setCert(cc.getCert());
		}

		return self;
	}

	public void setSelf(SelfCert s) {

		CmccCert cc = new CmccCert();
		cc.setCert(s.getCert());
		this.put("0000", cc);
		self = s;
	}

	private final static CertsTable table = new CertsTable();

	private CertsTable() {
		this.put("0000", new CmccCert());
	}

	public static CertsTable getCert() {

		return table;
	}

	private synchronized CmccCert getCertObj(String nodeid) throws TwException {
		CmccCert cc = null;

		if (nodeid.equalsIgnoreCase(Consts.center)) {
			Enumeration<CmccCert> e = this.elements();
			cc = e.nextElement();
		} else {
			cc = get(nodeid);
		}

		if (cc == null)
			throw new TwException(TwException.error_not_fand_by_nodeid, "not fand object by node id[" + nodeid + "]");

		return cc;
	}

	public synchronized void clearOldKey(String nodeid) throws TwException {
		CmccCert othercert = getCertObj(nodeid);
		othercert.setOldKey("");
	}

	public int rollbackkey(String nodeid) throws TwException {
		CmccCert othercert = getCertObj(nodeid);

		othercert.setNewKey(othercert.getOldKey());
		return 0;

	}

	public synchronized void setNewKey(String newkey, String nodeid) throws TwException {
		CmccCert othercert = getCertObj(nodeid);

		othercert.setOldKey(othercert.getNewKey());
		othercert.setNewKey(newkey);

	}

	public synchronized String getNewKey(String nodeid) throws TwException {
		CmccCert othercert = getCertObj(nodeid);
		String key = othercert.getNewKey();
		if (!StringUtils.isTrimEmpty(key)) {
			return othercert.getNewKey();
		} else {
			throw new TwException(TwException.error_key_null, "not fand key by node id [" + nodeid + "]");
		}
	}

	public synchronized String getOldKey(String nodeid) throws TwException {
		CmccCert othercert = getCertObj(nodeid);
		long time = othercert.getValidTime();
		if (time > System.currentTimeMillis()) {
			return othercert.getOldKey();
		}
		return null;
	}

	public synchronized String getCert(String nodeid) throws TwException {
		CmccCert othercert = getCertObj(nodeid);
		String cert = othercert.getCert();
		if (!StringUtils.isTrimEmpty(cert)) {
			return cert;
		} else {
			throw new TwException(TwException.error_cert_null, "not fand cert by node id [" + nodeid + "]");
		}
	}

	public synchronized long getValidTime(String nodeid) throws TwException {
		CmccCert othercert = getCertObj(nodeid);
		return othercert.getValidTime();
	}

	/**
	 * 
	 * @author guiyn
	 * @date 2019年12月30日 下午4:28:11
	 * @version 2019年12月30日
	 * @param id [NODE ID]
	 * @param cc
	 * @return
	 * @Description
	 */
	public synchronized CmccCert put(String id, CmccCert cc) {

		return super.put(id, cc);

	}

	public synchronized void putAll(List<CmccCert> list) {

		for (CmccCert cc : list) {
//			if (!StringUtils.isTrimEmpty(cc.getNewKey()))
			put(cc.getNodeId(), cc);
		}

	}
}
