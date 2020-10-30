package org.cmcc.ecip.common.zk;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorUtils {

	final static int def_data_version = 1;
	static Logger log = LoggerFactory.getLogger(CuratorUtils.class);

	public static String[] pathformat(String path) {

		String h = path.substring(0, 1);
		if (h.equals("/")) {
			path = path.substring(1);
		}

		String[] p = path.split("/");
		for (int i = 0; i < p.length; i++) {
			p[i] = "/" + p[i];
		}

		return p;
	}

	/**
	 * 
	 * @param client   zk链接
	 * @param nodePath 路径
	 * @param nodeData 数据
	 * @return 返回类型
	 */
	public static String createNode(CuratorFramework client, String nodePath, String nodeData) throws Exception {
		if (client != null) {
			String nodeName = client.create().creatingParentsIfNeeded().forPath(nodePath, nodeData.getBytes("UTF-8"));
			return nodeName;
		}
		return null;
	}

	/**
	 * @param client
	 * @param nodePath
	 * @param nodeData
	 * @return
	 * @throws Exception
	 */
	public static String createNode(CuratorFramework client, String nodePath) throws Exception {
		if (client != null)
			return client.create().creatingParentsIfNeeded().forPath(nodePath);
		return null;
	}

	/**
	 * 
	 * @param client
	 * @param nodePath
	 * @param data
	 * @throws Exception
	 */
	public static void addNodeData(CuratorFramework client, String nodePath, String data) throws Exception {

		if (!hasNode(client, nodePath)) {
			createNode(client, nodePath);
		}

		int result = updateNodeData(client, nodePath, data);

		if (log.isDebugEnabled()) {
			log.debug("ADD NODE VALUE [" + nodePath + "][" + data + "][" + result + "]");
		}
	}
	
	
	public static void addNodeData(CuratorFramework client, String nodePath, byte[] data) throws Exception {

		if (!hasNode(client, nodePath)) {
			createNode(client, nodePath);
		}

		int result = updateNodeByteData(client, nodePath, data);

		if (log.isDebugEnabled()) {
			log.debug("ADD NODE VALUE [" + nodePath + "][" + data + "][" + result + "]");
		}
	}

	/**
	 * 创建临时目录
	 * 
	 * @param client
	 * @param nodePath
	 * @param nodeData
	 * @return
	 * @throws Exception
	 */

	public static String createTmpNode(CuratorFramework client, String nodePath, String nodeData) throws Exception {
		if (client != null) {
			String nodeName = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(nodePath,
					nodeData.getBytes("UTF-8"));
			return nodeName;
		}
		return null;
	}

	/**
	 * 更新节点信息值
	 * 
	 * @param client
	 * @param nodePath
	 * @param nodeData
	 * @return
	 * @throws Exception
	 */
	public static int updateNodeData(CuratorFramework client, String nodePath, String nodeData) throws Exception {

		byte[] newData = nodeData.getBytes();
		Stat resultStat = client.setData().forPath(nodePath, newData);
		return resultStat.getVersion();
	}

	/**
	 * 更新节点信息
	 * 
	 * @param client
	 * @param nodePath
	 * @param nodeData
	 * @return
	 * @throws Exception
	 */
	public static int updateNodeByteData(CuratorFramework client, String nodePath, byte[] nodeData) throws Exception {
		Stat resultStat = client.setData().forPath(nodePath, nodeData);
		return resultStat.getVersion();
	}

	/**
	 * 递归删除node 和node下所有子节点 一直删除直到成功
	 * 
	 * @param client
	 * @param nodePath
	 * @throws Exception
	 */
	public static void delNodes(CuratorFramework client, String nodePath) throws Exception {
		client.delete().guaranteed() // 如果删除失败，那么在后端还是会继续删除，直到成功
				.deletingChildrenIfNeeded() // 子节点也一并删除，也就是会递归删除
				.forPath(nodePath);
	}

	/**
	 * 只删除一次,有子节点无法删除
	 * 
	 * @param client
	 * @param nodePath
	 * @throws Exception
	 */
	public static void delNode(CuratorFramework client, String nodePath) throws Exception {
		client.delete().forPath(nodePath);
	}

	/**
	 * 获取节点数据
	 * 
	 * @param client
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public static String getNodeData(CuratorFramework client, String nodePath) throws Exception {

		return new String(getNodeDataBytes(client, nodePath));
	}

	public static byte[] getNodeDataBytes(CuratorFramework client, String nodePath) throws Exception {
		return client.getData().forPath(nodePath);

	}

	/**
	 * 查询所有子节点
	 * 
	 * @param client
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public static List<String> listNodes(CuratorFramework client, String nodePath) throws Exception {
		return client.getChildren().forPath(nodePath);

	}

	/**
	 * 节点是否存在
	 * 
	 * @param client
	 * @param nodePath
	 * @return true 存在 | false 不存在
	 * @throws Exception
	 */
	public static boolean hasNode(CuratorFramework client, String nodePath) throws Exception {
		Stat statExist = client.checkExists().forPath(nodePath);
		if (statExist == null)
			return false;
		else
			return true;
	}

	/**
	 * 添加状态
	 * 
	 * @param client
	 * @param nodePath
	 * @param watcher
	 * @throws Exception
	 */
	public static void addWatcher(CuratorFramework client, String nodePath, Watcher watcher) throws Exception {
		client.getData().usingWatcher(watcher).forPath(nodePath);
	}

}