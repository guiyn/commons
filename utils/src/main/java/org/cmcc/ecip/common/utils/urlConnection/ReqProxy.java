package org.cmcc.ecip.common.utils.urlConnection;

/**   
* @ProjectName super-man
* @PackageName org.superman.service.http
* @ClassName ProxyObj
* @Description
* @author guiyn
* @date 2020年4月17日 下午11:05:21
* @version   2020年4月17日 下午11:05:21
*    
*/
public class ReqProxy {
	String proxType;
	String proxHost;
	int proxPort;
	
	
	/** 
	* @Fields @param proxType
	* @Fields @param proxHost
	* @Fields @param proxPort
	*/
	public ReqProxy(String proxType, String proxHost, int proxPort) {
		super();
		this.proxType = proxType;
		this.proxHost = proxHost;
		this.proxPort = proxPort;
	}
	/**
	 * @return the proxType
	 */
	public String getProxType() {
		return proxType;
	}
	
	/**
	 * @return the proxHost
	 */
	public String getProxHost() {
		return proxHost;
	}
	
	/**
	 * @return the proxPort
	 */
	public int getProxPort() {
		return proxPort;
	}
	/**
	 * @author guiyn
	 * @date 2020年4月17日 下午11:12:40
	 * @version 2020年4月17日
	 * @return
	 * @Description
	*/
	@Override
	public String toString() {
		return "[Type=" + proxType + ", Host=" + proxHost + ", Port=" + proxPort + "]";
	}
	
	
	
}
