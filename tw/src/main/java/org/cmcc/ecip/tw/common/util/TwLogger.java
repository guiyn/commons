package org.cmcc.ecip.tw.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TwLogger {

	static Logger log = LoggerFactory.getLogger("TW");

	public static void info(String format, Object... arguments) {
		log.info(format, arguments);
	}

	public static void warn(String mess) {
		log.warn(mess);
	}

	public static void warn(String format, Object... arguments) {
		log.warn(format, arguments);
	}

	public static void error(String mess) {
		log.error(mess);
	}

	public static void sysError(String mess, Throwable t) {
		log.error(mess, t);
	}
}
