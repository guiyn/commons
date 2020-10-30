package org.cmcc.ecip.common.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ProjectName util.zk
 * @PackageName cmcc.ecip.common.util.zk
 * @ClassName CuratorBuilder
 * @Description
 * @author guiyn
 * @date 2019年11月28日 下午3:52:34
 * @version 2019年11月28日 下午3:52:34
 * 
 */
public class CuratorBuilder {
	static Logger log = LoggerFactory.getLogger(CuratorBuilder.class);
	private static CuratorBuilder container = new CuratorBuilder();;
	private CuratorFramework client;

	long sessionID;
	String clientId;
	String ips;
	int sessionTimeout;
	RetryPolicy retryPolicy;

	ConnectionState status;

	public ConnectionState getStatus() {
		return status;
	}

	public void setStatus(ConnectionState status) {
		this.status = status;
	}

	private CuratorBuilder() {

	}

	public static CuratorBuilder getInstance() {

		return container;
	}

	
	/**
	 * 
	 * 
	 */
	/**
	 * 
	 * @param clientId
	 * @param ips
	 * @param sessionTimeout
	 * @param retryPolicy RetryPolicyBuilder
	 * @return org.apache.curator.framework.CuratorFramework
	 * CuratorBuilder.getInstance().buildCratorClinet(MyAppId, ZkConnectionString, Zktimeout,
	 * RetryPolicyBuilder.defRetryPolicy.Forever.getPolicy());
	 */
	public CuratorFramework buildCratorClinet(String clientId, String ips, int sessionTimeout,
			RetryPolicy retryPolicy) {
		this.clientId = clientId;
		this.ips = ips;
		this.sessionTimeout = sessionTimeout;
		this.retryPolicy = retryPolicy;
		log.info("build zk connection ...");
		buildAndStart();
		return client;
	}

	void buildAndStart() {
		// 实例化Curator客户端，Curator的编程风格可以让我们使用方法链的形式完成客户端的实例化
		org.apache.curator.framework.CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder() // 使用工厂类来建造客户端的实例对象
				.connectString(ips) // 放入zookeeper服务器ip
				.sessionTimeoutMs(sessionTimeout); // 设定会话时间以及重连策略
		if (retryPolicy != null) {
			builder.retryPolicy(retryPolicy);
		}

		client = builder.build(); // 建立连接通道
		// 启动Curator客户端

		client.getConnectionStateListenable().addListener(new SessionConnectionListener());

		client.start();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			log.error("thread sleep error..", e);
		}

		if (isConnection()) {
			sessionID = getZkSessiontId();
			log.info("zk client start...ID[" + sessionID + "]");
		} else {
			log.info("zk client start fail... connection fail...");
		}

	}

	public CuratorFramework getZKClinet() {
		if (client == null)
			buildAndStart();

		return client;

	}

	public void reBuildZKClient() {
		buildAndStart();
	}

	public void closeZKClient() {
		if (client != null) {
			client.close();
		}

	}

	public String getClientId() {
		return clientId;
	}

	public long getZkSessiontId() {
		try {
			return client.getZookeeperClient().getZooKeeper().getSessionId();
		} catch (Exception e) {
			log.error("get zk session id error...");
			return 0;
		}

	}

	public boolean isConnection() {
		try {
			return client.getZookeeperClient().getZooKeeper().getState().isConnected();
		} catch (Exception e) {
			log.error("get zk state connecte status error...");
			return false;
		}
	}

	public boolean isAlive() {
		try {

			return client.getZookeeperClient().getZooKeeper().getState().isAlive();
		} catch (Exception e) {
			log.error("get zk state alive status error...");
			return false;
		}

	}
}
