package org.cmcc.ecip.common.zk;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ProjectName util.zk
 * @PackageName cmcc.ecip.common.util.zk
 * @ClassName LockUtils
 * @Description
 * @author guiyn
 * @date 2019年11月28日 下午4:10:56
 * @version 2019年11月28日 下午4:10:56
 * 
 */
public class ZkLock {
	Logger log = LoggerFactory.getLogger(getClass());
	private InterProcessLock l;
	CuratorFramework client;
	String path;

	public ZkLock(CuratorFramework client, String path,boolean flag) {

		this.client = client;
		this.path = path;
		if(!flag)
		l = new InterProcessSemaphoreMutex(client, path);
		else 
			l=new InterProcessMutex(client, path);
		log.debug("build lock over..");
	}

	public ZkLock(String path) {
		this(CuratorBuilder.getInstance().getZKClinet(), path,false);
	}

	public ZkLock(String path,boolean flag) {
		this(CuratorBuilder.getInstance().getZKClinet(), path,flag);
	}

//	public boolean buildAndLock(CuratorFramework client, String path) throws Exception {
//		l = new InterProcessMutex(client, path);
//		return l.acquire(3, TimeUnit.SECONDS);
//	}
//
//	public boolean buildAndLock(String path) throws Exception {
//		l = new InterProcessMutex(CuratorBuilder.getInstance().getZKClinet(), path);
//		return l.acquire(3, TimeUnit.SECONDS);
//	}
	/**
	 * def 5 seconds
	 * 
	 * @author guiyn
	 * @date 2019年12月4日 上午11:00:59
	 * @version 2019年12月4日
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public boolean doLock() throws Exception {
		
		return doLock(5);
	}

	/**
	 * 
	 * @author guiyn
	 * @date 2019年12月4日 上午11:00:56
	 * @version 2019年12月4日
	 * @param seconds
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public boolean doLock(int seconds) throws Exception {
		log.debug("do lock begin...");
		boolean res= l.acquire(seconds, TimeUnit.SECONDS);
		log.debug("do lock over...");
		return res;
	}

	public void unlock() {
		try {
			if (l != null)
				l.release();
			log.debug("unlock over...");
		} catch (Exception e) {
			log.error("lock unlock or release error..", e);
		}

	}

}
