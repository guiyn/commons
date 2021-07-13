package org.cmcc.ecip.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * JSON Util bu ailibaba JSON
 * 
 * @author guiyn
 *
 */
public class JsonUtils {

	/**
	 * 字符串转JSON对象
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject formatJsonObj(String json) {
		return JSON.parseObject(json);
	}

	/**
	 * 对象转Json
	 * 
	 * @param obj
	 * @return
	 */
	public static String formatJsonStr(Object obj) {
		if (obj == null)
			return "";
		return JSON.toJSONString(obj);
	}

	public static String formatJsonStrIncludeNullKey(Object obj) {
		if (obj == null)
			return "";
		ValueFilter filter = (obj1, s, v) -> {
			if (v == null) {
				return "";
			}
			return v;
		};
		return JSON.toJSONString(obj, filter);
	}

	/**
	 * Json 转java bean
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T format2Bean(String json, Class<T> clazz) {
		if (StringUtils.isNull(json))
			return null;
		return JSON.parseObject(json, clazz);
	}

	public static <V> Map<String, V> format2MapBean(String json, Class<V> clazz) {
		Map<String, ?> ac = JSONObject.parseObject(json, Map.class);
		Map<String, V> acs = new HashMap<>();
		for (String key : ac.keySet()) {
			JSONObject act = (JSONObject) ac.get(key);
			V aaaa = act.toJavaObject(clazz);
			acs.put(key, aaaa);
		}
		return acs;
	}

	/**
	 * 
	 * @param text
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> format2Array(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}

	private static String getValue(JSONObject json, String path) {
		if (JSONPath.contains(json, path)) {
			return (String) JSONPath.eval(json, path);
		}
		return null;
	}

	public static String getValue(String json, String path) {
		JSONObject jso = JSON.parseObject(json);
		return getValue(jso, path);
	}

	@SuppressWarnings("unused")
	public static String[] getValues(String json, String paths[]) {
		JSONObject jso = JSON.parseObject(json);
		String[] res = new String[paths.length];
		for (int i = 0; i < paths.length; i++) {
			String path = paths[i];
			String re = getValue(jso, path);
			if (res != null) {
				res[i] = re;
			}
			return null;
		}
		return res;
	}

	public static boolean setValue(JSONObject result, String path, String value) {
		return JSONPath.set(result, path, value);
	}

	public static boolean contains(JSONObject result, String path) {
		return JSONPath.contains(result, path);
	}

	public static <T> T map2bean(Map<String, ?> map, Class<T> beanType) throws Exception {

		String jsonString = JSON.toJSONString(map);
		return JSON.parseObject(jsonString, beanType);

	}

	@SuppressWarnings("unchecked")
	public static Map<String, ?> bean2map(Object bean) throws Exception {
		String jsonStr = JSON.toJSONString(bean);
		return JSON.parseObject(jsonStr, Map.class);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> bean2StrMap(Object bean) throws Exception {
		String jsonStr = JSON.toJSONString(bean);
		return JSON.parseObject(jsonStr, Map.class);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> format2map(String strjson) {
		return JSON.parseObject(strjson, Map.class);
	}

	public static <T> List<T> listMap2beans(List<Map<String, ?>> list, Class<T> beanType) throws Exception {

		String jsonStr = JSON.toJSONString(list);

		return JSON.parseArray(jsonStr, beanType);

	}

}
