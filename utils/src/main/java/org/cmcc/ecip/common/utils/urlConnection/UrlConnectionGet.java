package org.cmcc.ecip.common.utils.urlConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class UrlConnectionGet {

	public static String doGet(String urlPath) throws Exception {
		return doGet(urlPath, null,3000,10000);
	}
	
	public static String doGet(String urlPath,int connectionTimeout ,int readTimeout) throws Exception {
		return doGet(urlPath, null,connectionTimeout,readTimeout);
	}
	

	public static String doGet(String urlPath, String token,int connectionTimeout ,int readTimeout) throws Exception {
		StringBuilder b = new StringBuilder();
		URL url = new URL(urlPath);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setConnectTimeout(connectionTimeout);
		httpURLConnection.setReadTimeout(readTimeout);
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestProperty("Connection", "close");
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setRequestProperty("User-Agent", "Java UrlConnection Get CLient");
		if (token != null && "".equals(token.trim()))
			httpURLConnection.setRequestProperty("Authorization", "Bearer " + token);

		try {
			InputStream is = null;
			int status = httpURLConnection.getResponseCode();
			if (status == HttpURLConnection.HTTP_OK) {
				is = httpURLConnection.getInputStream();

			} else {
				is = httpURLConnection.getErrorStream();
			}

			try {
			b.append(	IOUtils.toString(is,"utf-8"));
//				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
//				String line;
//				while ((line = reader.readLine()) != null) {
//					b.append(line);
//				}
//				reader.close();
				is.close();
			} catch (Throwable t) {
				throw new IOException("Server returned status: " + status + " error message: " + b.toString());
			}

			if (status == HttpURLConnection.HTTP_OK)
				return b.toString();

			throw new IOException("Server returned status: " + status + " error message: " + b.toString());
		} finally {
			if (httpURLConnection != null)
				httpURLConnection.disconnect();
		}
	}

}
