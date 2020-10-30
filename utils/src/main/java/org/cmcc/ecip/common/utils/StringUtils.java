package org.cmcc.ecip.common.utils;

public class StringUtils {
	public static boolean isNull(String s) {
		if (s == null || "".equals(s.trim()))
			return true;
		else
			return false;
	}
}
