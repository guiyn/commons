package com.cmcc.boss.security;

import java.rmi.RemoteException;

/**   
* @ProjectName tw.api
* @PackageName com.cmcc.boss.security
* @ClassName ConfigRMIServer
* @Description
* @author guiyn
* @date 2020年4月1日 上午12:07:47
* @version   2020年4月1日 上午12:07:47
*    
*/
public interface ConfigRMIServer {
	/**
	 * 获取私钥
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getprivatekey() throws RemoteException;

	/**
	 * 获取自身证书
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getselfcert() throws RemoteException;

	/**
	 * 获取公共证书
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getrootcert() throws RemoteException;

	/**
	 * 获取公钥
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getmainkey() throws RemoteException;

	/**
	 * 根据ID 获取证书
	 * 
	 * @param s
	 *            ID
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getcert(String s) throws RemoteException;

	/**
	 * 根据ID 获取新KEY
	 * 
	 * @param s
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getnewkey(String s) throws RemoteException;

	/**
	 * 根据ID 获取旧KEY
	 * 
	 * @param s
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getoldkey(String s) throws RemoteException;

	/**
	 * 根据ID 获取过期时间
	 * 
	 * @param s
	 * @return
	 * @throws RemoteException
	 */
	public abstract long getvalidtime(String s) throws RemoteException;

	/**
	 * 新旧KEY 刷新
	 * 
	 * @param s
	 * @param s1
	 * @param flag
	 * @throws RemoteException
	 */
	public abstract void refreshWait(String s, String s1, boolean flag) throws RemoteException;

	/**
	 * 获取日志目录
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getlogfilepath() throws RemoteException;

	/**
	 * 配置初始化
	 * 
	 * @throws RemoteException
	 */
	public abstract void initiate() throws RemoteException;

	/**
	 * 根据ID 生成新加密KEY 长度256bit
	 * 
	 * @param s
	 * @return
	 * @throws RemoteException
	 */
	public abstract String getGenerateKey(String s) throws Exception;

	/**
	 * 设置新KEY
	 * 
	 * @param s
	 * @return
	 * @throws RemoteException
	 */
	public abstract int getSetKey(String s) throws Exception;

	/**
	 * KEY 回滚
	 * 
	 * @param s
	 * @return
	 * @throws RemoteException
	 */
	public abstract int rollbackkey(String s) throws RemoteException;
}
