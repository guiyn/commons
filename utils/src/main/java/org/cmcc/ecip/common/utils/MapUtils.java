package org.cmcc.ecip.common.utils;

import java.util.Arrays;

import java.util.Map;
import java.util.Set;



public class MapUtils {

	
	public static String getRelatedStrings(Map<String, ?> parameterMap) {
		Set<String> parameterMapKeySet = parameterMap.keySet();
		String[] parameterMapKeySetArray = parameterMapKeySet.toArray(new String[parameterMapKeySet.size()]);
		Arrays.sort(parameterMapKeySetArray);
		StringBuilder requestSignStringBuilder = new StringBuilder();
		for (int i = 0; i < parameterMapKeySetArray.length; i++) {
			String key = parameterMapKeySetArray[i];
			Object obj = parameterMap.get(key);
			String value = "";
			if (obj != null)
				value = obj.toString();
			requestSignStringBuilder.append(key).append(value);
		}
		return requestSignStringBuilder.toString();
	}
}
