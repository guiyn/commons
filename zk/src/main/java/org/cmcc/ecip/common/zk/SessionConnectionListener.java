package org.cmcc.ecip.common.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionConnectionListener implements ConnectionStateListener {
	Logger log = LoggerFactory.getLogger(SessionConnectionListener.class);

	public void stateChanged(CuratorFramework client, ConnectionState newState) {

		log.info("zk connection state change...===>>" + newState);
		CuratorBuilder.getInstance().setStatus(newState);
		if (!newState.isConnected()) {
			log.error("zk lost connected ..");
			log.info("begin close and restart..");
			CuratorBuilder.getInstance().closeZKClient();
			log.info("zk client closed..");
			CuratorBuilder.getInstance().reBuildZKClient();
			log.info("zk client started..");
		}
	}

}
