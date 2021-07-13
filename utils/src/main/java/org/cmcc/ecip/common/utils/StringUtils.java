package org.cmcc.ecip.common.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static boolean isNull(String s) {
		return isEmpty(s);
	}

	public static boolean isEmpty(String string) {
		return string == null || "".equals(string);
	}

	public static boolean isTrimEmpty(String string) {
		return string == null || "".equals(string.trim());
	}

	public static String getNonNull(String string) {
		return string == null ? "" : string;
	}

	public static String getNonNullTrim(String string) {
		return string == null ? "" : string.trim();
	}

	public static String escapeStr(String s) {
		if (s == null)
			return s;
		return s.replace("\n", "\\n").replace("\r", "\\r");
	}

	public static String unescapeStr(String s) {
		if (s == null)
			return s;
		return s.replace("\\n", "\n").replace("\\r", "\r");
	}

	public static String getJsonString(Object object) {
		return (object == null || "".equals(object.toString())) ? "\"\"" : "\"" + object.toString() + "\"";
	}

	public static String generateRandomString(int length) {
		String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();

	}

	public static String replaceBlank(String old) {
		Pattern p = Pattern.compile("\t|\r|\n");
		Matcher m = p.matcher(old);
		String after = m.replaceAll("");
		return after;
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

 
}
