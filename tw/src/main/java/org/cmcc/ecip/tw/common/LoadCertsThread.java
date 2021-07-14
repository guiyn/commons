package org.cmcc.ecip.tw.common;

import org.cmcc.ecip.tw.common.load.TwLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ProjectName tw_new_client
 * @PackageName com.cmcc.ecip.tw.client
 * @ClassName TimingLoadCerts
 * @Description
 * @author guiyn
 * @date 2019年12月10日 上午10:42:04
 * @version 2019年12月10日 上午10:42:04
 * 
 */

public class LoadCertsThread implements Runnable {
	static Logger log = LoggerFactory.getLogger("TW");

	String[] args;
	int timming;

	TwLoader loader;

	public LoadCertsThread(String[] args, int timing, TwLoader loader) {
		this.args = args;
		timming = timing;
		this.loader = loader;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(timming * 1000);
				log.debug("begin load certs..."+timming);
				loader.init(args);
				log.debug("load certs over...");
			} catch (Exception e) {
				log.error("load certs error", e);
			}

		}
	}
}