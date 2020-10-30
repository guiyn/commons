package org.cmcc.ecip.common.utils;

import java.util.List;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * JSON Util bu ailibaba JSON
 * 
 * @author guiyn
 *
 */
public class JsonUtil {

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

	/**
	 * Json 转java bean
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T format2Bean(String json, Class<T> clazz) {
		if(StringUtils.isNull(json))
			return null;
		return JSON.parseObject(json, clazz);
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
}
