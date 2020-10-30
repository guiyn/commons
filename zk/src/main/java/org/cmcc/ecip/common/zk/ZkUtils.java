package org.cmcc.ecip.common.zk;

import java.util.List;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @ProjectName util.zk
 * @PackageName cmcc.ecip.common.util.zk
 * @ClassName ZkUtils
 * @Description
 * @author guiyn
 * @date 2019年11月28日 下午5:00:43
 * @version 2019年11月28日 下午5:00:43
 * 
 */
public class ZkUtils extends CuratorUtils {
	/**
	 * 
	 * @param nodePath
	 * @param nodeData
	 * @throws Exception
	 */
	public static void createNode(String nodePath) throws Exception {
		createNode(CuratorBuilder.getInstance().getZKClinet(), nodePath);
	}

	/**
	 * 
	 * @param nodePath
	 * @param data
	 * @throws Exception
	 */
	public static void addNodeData(String nodePath, String data) throws Exception {
		addNodeData(CuratorBuilder.getInstance().getZKClinet(), nodePath, data);
	}

	/**
	 * 
	 * @param nodePath
	 * @param data
	 * @throws Exception
	 */
	public static void addNodeByteData(String nodePath, byte[] data) throws Exception {
		addNodeData(CuratorBuilder.getInstance().getZKClinet(), nodePath, data);
	}

	/**
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet() zk链接
	 * @param nodePath       路径
	 * @param nodeData       数据
	 * @return 返回类型
	 */
	public static String createNodeAndParents(String nodePath, String nodeData) throws Exception {
		if (CuratorBuilder.getInstance().getZKClinet() != null) {
			String nodeName = CuratorBuilder.getInstance().getZKClinet().create().creatingParentsIfNeeded()
					.withMode(CreateMode.PERSISTENT).forPath(nodePath, nodeData.getBytes("UTF-8"));

			return nodeName;
		}
		return null;
	}

	/**
	 * 创建临时目录
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet()
	 * @param nodePath
	 * @param nodeData
	 * @return
	 * @throws Exception
	 */

	public static String createTmpNode(String nodePath, String nodeData) throws Exception {
		if (CuratorBuilder.getInstance().getZKClinet() != null) {
			String nodeName = CuratorBuilder.getInstance().getZKClinet().create().creatingParentsIfNeeded()
					.withMode(CreateMode.EPHEMERAL).forPath(nodePath, nodeData.getBytes("UTF-8"));
			return nodeName;
		}
		return null;
	}

	/**
	 * 更新节点信息值
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet()
	 * @param nodePath
	 * @param nodeData
	 * @return
	 * @throws Exception
	 */
	public static void updateNodeData(String nodePath, String nodeData) throws Exception {
		updateNodeData(CuratorBuilder.getInstance().getZKClinet(), nodePath, nodeData);

	}

	/**
	 * 递归删除node 和node下所有子节点 一直删除直到成功
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet()
	 * @param nodePath
	 * @throws Exception
	 */
	public static void delNodes(String nodePath) throws Exception {
		if (hasNode(nodePath))
			CuratorBuilder.getInstance().getZKClinet().delete().guaranteed() // 如果删除失败，那么在后端还是会继续删除，直到成功
					.deletingChildrenIfNeeded() // 子节点也一并删除，也就是会递归删除
					.forPath(nodePath);
	}

	/**
	 * 只删除一次,有子节点无法删除
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet()
	 * @param nodePath
	 * @throws Exception
	 */
	public static void delNode(String nodePath) throws Exception {
		if (hasNode(nodePath))
			CuratorBuilder.getInstance().getZKClinet().delete().forPath(nodePath);
	}

	/**
	 * 获取节点数据
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet()
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public static String getNodeData(String nodePath) throws Exception {

		return new String(getNodeDataBytes(CuratorBuilder.getInstance().getZKClinet(), nodePath));
	}

	public static byte[] getNodeDataBytes(String nodePath) throws Exception {
		return CuratorBuilder.getInstance().getZKClinet().getData().forPath(nodePath);

	}

	/**
	 * 查询所有子节点
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet()
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public static List<String> listNodes(String nodePath) throws Exception {
		return CuratorBuilder.getInstance().getZKClinet().getChildren().forPath(nodePath);

	}

	/**
	 * 节点是否存在
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet()
	 * @param nodePath
	 * @return true 存在 | false 不存在
	 * @throws Exception
	 */
	public static boolean hasNode(String nodePath) throws Exception {
		Stat statExist = CuratorBuilder.getInstance().getZKClinet().checkExists().forPath(nodePath);

		if (statExist == null)
			return false;
		else
			return true;
	}

	/**
	 * 添加状态
	 * 
	 * @param CuratorBuilder .getInstance().getZKClinet()
	 * @param nodePath
	 * @param watcher
	 * @throws Exception
	 */
	public static void addWatcher(String nodePath, CuratorWatcher watcher) throws Exception {
		CuratorBuilder.getInstance().getZKClinet().getData().usingWatcher(watcher).forPath(nodePath);
	}
}
