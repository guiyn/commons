package org.cmcc.ecip.common.utils.urlConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ProjectName super-man
 * @PackageName org.superman.service.http
 * @ClassName HttpUtil
 * @Description
 * @author guiyn
 * @date 2020年4月17日 下午8:21:35
 * @version 2020年4月17日 下午8:21:35
 * 
 */
public class UrlConnectionExecute {
	final static Logger log = LoggerFactory.getLogger(UrlConnectionExecute.class);

	public static ResponseViewObj execute(String url, ReqParameter obj) throws Exception {

//		ReqParameter obj = req.getReqParameter();
		Proxy proxy = null;
		if (obj.getReqProxy() != null) {
			InetSocketAddress address = new InetSocketAddress(obj.getReqProxy().getProxHost(),
					obj.getReqProxy().getProxPort());
			proxy = new Proxy(Proxy.Type.valueOf(obj.getReqProxy().getProxType()), address); // http 代理
		}
		return execute(url, obj.getMethod(), obj.getHeaders(), proxy, obj.getRequestBody(),5000,15000);
	}

	public static ResponseViewObj execute(String urlStr, String method, Map<String, List<String>> headers, Proxy proxy,
			String requestBody,int connectiontimeout, int sockettimeout) throws Exception {

		long beginTime = System.currentTimeMillis();
		if ("GET".equals(method)) {
			urlStr = urlStr + "?" + requestBody;
		}

		URL url = new URL(urlStr);
		HttpURLConnection connection;
		if (proxy != null)
			connection = (HttpURLConnection) url.openConnection(proxy);
		else
			connection = (HttpURLConnection) url.openConnection();

		connection.setConnectTimeout(connectiontimeout);
		connection.setReadTimeout(sockettimeout);

		connection.setRequestMethod(method);
		connection.setDoOutput(true);
		connection.setDoInput(true);
		if (headers != null)
			for (String k : headers.keySet()) {
				List<String> l = headers.get(k);
				for (String val : l)
					connection.addRequestProperty(k, val);
			}
		log.debug("http begin connection...");
		connection.connect();
		log.debug("http  connectionned and write info...");
		if ("POST".equals(method) && requestBody != null) {
			wirteBody(connection.getOutputStream(), requestBody);
		}
		log.debug("http  write over...");
		Map<String, List<String>> map = null;
		String resultBody = null;
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = connection.getInputStream();
			resultBody = getResponseBody(inputStream);// 将流转换为字符串。
			log.debug("response inof >>> " + resultBody);
			map = connection.getHeaderFields();
		}
		long endTime = System.currentTimeMillis();

		return new ResponseViewObj(map, resultBody, responseCode, beginTime, endTime);
	}

	static String getResponseBody(InputStream is) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String result = br.lines().collect(Collectors.joining(System.lineSeparator()));
		br.close();
		return result;

	}

	private static String streamToString(InputStream inputStream, String encodeType) {
		String resultString = null;

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int len = 0;
		byte data[] = new byte[1024];
		try {
			while ((len = inputStream.read(data)) != -1) {
				byteArrayOutputStream.write(data, 0, len);
			}
			byte[] allData = byteArrayOutputStream.toByteArray();
			resultString = new String(allData, encodeType);
		} catch (IOException e) {
			log.error("read response error:", e);
		}

		return resultString;
	}

	static void wirteBody(OutputStream os, String requestBody) throws Exception {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(requestBody);
		writer.close();
	}
}
