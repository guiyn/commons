package org.cmcc.ecip.tw.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;

import org.cmcc.ecip.tw.common.exception.TwException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmcc.boss.security.ConfigRMIServer;

/**
 * @ProjectName tw.api
 * @PackageName org.cmcc.ecip.R.tw.api
 * @ClassName RmiClient
 * @Description
 * @author guiyn
 * @date 2020年4月1日 上午12:06:52
 * @version 2020年4月1日 上午12:06:52
 * 
 */

public class RmiClient {

	static Logger log = LoggerFactory.getLogger("TW");

	static Registry registry;
	static final String serverStr = "ConfigRMIServer";
	private static RmiClient rc = new RmiClient();

	private RmiClient() {

	}

	public static synchronized RmiClient getClient() {

		return rc;
	}

	private static ConfigRMIServer server = null;

	public void buildRMIServer(String host, int port) throws Exception {
		try {
			try {
				registry = LocateRegistry.getRegistry(host, port, new RMIClientSocketFactory() {
					@Override
					public Socket createSocket(String host, int port) throws IOException {
						Socket socket = new Socket();
						socket.connect(new InetSocketAddress(host, port), 2000);
						return socket;
					}
				});
			} catch (Exception e) {
				log.error("build rmi registry error>>", e);
			}

			server = (ConfigRMIServer) registry.lookup(serverStr);

		} catch (Exception x) {
			log.error("connect RMIServer error:", x);
			throw new TwException(TwException.error_connection_server, "connect RMIServer error..", x);
		}
	}

	public ConfigRMIServer getNewRMI() {
		try {
			server = (ConfigRMIServer) registry.lookup(serverStr);
			return server;
		} catch (Exception e) {
			return null;
		}

	}

	public ConfigRMIServer getRMI() {
		return server;
	}

}
