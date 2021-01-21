package org.cmcc.ecip.common.tw.util;

/**
 * @ProjectName tw.api
 * @PackageName cmcc.ecip.R.tw.api.util
 * @ClassName StringUtil
 * @Description
 * @author guiyn
 * @date 2019年12月23日 下午1:22:06
 * @version 2019年12月23日 下午1:22:06
 * 
 */
public class StringUtils {

	public static boolean isTrimEmpty(String s) {

		if (s == null || "".equals(s.trim()))
			return true;

		return false;
	}

	public static String noNull(String s) {
		return (s == null) ? "" : s;
	}
}
