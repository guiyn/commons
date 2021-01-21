package org.cmcc.ecip.common.utils.urlConnection;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName super-man
 * @PackageName org.superman.service.http
 * @ClassName ResponseViewObj
 * @Description
 * @author guiyn
 * @date 2020年4月17日 下午9:00:13
 * @version 2020年4月17日 下午9:00:13
 * 
 */
public class ResponseViewObj {

	
	
	
	
	/** 
	* @Fields @param headerMap
	* @Fields @param responseBody
	* @Fields @param responseCode
	* @Fields @param reqTime
	* @Fields @param respTime
	*/
	public ResponseViewObj(Map<String, List<String>> headerMap, String responseBody, int responseCode, long reqTime,
			long respTime) {
		super();
		this.headerMap = headerMap;
		this.responseBody = responseBody;
		this.responseCode = responseCode;
		this.reqTime = reqTime;
		this.respTime = respTime;
	}

	Map<String, List<String>> headerMap;
	String responseBody;
	int responseCode;
	long reqTime;
	long respTime;

	/**
	 * @return the headerMap
	 */
	public Map<String, List<String>> getHeaderMap() {
		return headerMap;
	}

	/**
	 * @param headerMap the headerMap to set
	 */
	public void setHeaderMap(Map<String, List<String>> headerMap) {
		this.headerMap = headerMap;
	}

	/**
	 * @return the responseBody
	 */
	public String getResponseBody() {
		return responseBody;
	}

	/**
	 * @param responseBody the responseBody to set
	 */
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the reqTime
	 */
	public long getReqTime() {
		return reqTime;
	}

	/**
	 * @param reqTime the reqTime to set
	 */
	public void setReqTime(long reqTime) {
		this.reqTime = reqTime;
	}

	/**
	 * @return the respTime
	 */
	public long getRespTime() {
		return respTime;
	}

	/**
	 * @param respTime the respTime to set
	 */
	public void setRespTime(long respTime) {
		this.respTime = respTime;
	}

	public String getRspHeaderStr() {
		if (headerMap == null)
			return null;
		StringBuffer resultHeaderBuffer = new StringBuffer();
		for (String k : headerMap.keySet()) {
			List<String> l = headerMap.get(k);
			for (String rv : l) {
				resultHeaderBuffer.append(k + ":" + rv + "\n");
			}
		}
		return resultHeaderBuffer.toString();
	}

	public long useTime()
	{
		return respTime- reqTime;
	}
}
