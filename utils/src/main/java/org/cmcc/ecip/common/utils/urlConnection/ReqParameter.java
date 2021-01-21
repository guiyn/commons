package org.cmcc.ecip.common.utils.urlConnection;


import java.util.List;
import java.util.Map;

import org.cmcc.ecip.common.utils.JsonUtils;

/**
 * @ProjectName super-man
 * @PackageName org.superman.service.http
 * @ClassName RequesutObj
 * @Description
 * @author guiyn
 * @date 2020年4月17日 下午10:34:19
 * @version 2020年4月17日 下午10:34:19
 * 
 */
public class ReqParameter {

	ReqProxy reqProxy;
	
	String method;
	Map<String, List<String>> headers;
	String requestBody;


	
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the headers
	 */
	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	/**
	 * @return the requestBody
	 */
	public String getRequestBody() {
		return requestBody;
	}

	/**
	 * @param requestBody the requestBody to set
	 */
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	/**
	 * @return the reqProxy
	 */
	public ReqProxy getReqProxy() {
		return reqProxy;
	}

	/**
	 * @param reqProxy the reqProxy to set
	 */
	public void setReqProxy(ReqProxy reqProxy) {
		this.reqProxy = reqProxy;
	}

	@Override
	public String toString() {
		return  JsonUtils.formatJsonStr(this);
	}

}
