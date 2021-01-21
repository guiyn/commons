package org.cmcc.ecip.common.eos.client.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

public class MapBeanExchange {

	public static <T> T map2bean(Map<String, ?> map, Class<T> beanType) throws Exception {

		String jsonString = JSON.toJSONString(map);

		return JSON.parseObject(jsonString, beanType);

	}

	@SuppressWarnings("unchecked")
	public static Map<String, ?> bean2map(Object bean) throws Exception {

		String jsonStr = JSON.toJSONString(bean);

		return JSON.parseObject(jsonStr, Map.class);

	}

	public static <T> List<T> listMap2beans(List<Map<String, ?>> list, Class<T> beanType) throws Exception {

		String jsonStr = JSON.toJSONString(list);

		return JSON.parseArray(jsonStr, beanType);

	}

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
