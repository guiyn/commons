package org.cmcc.ecip.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * urlconnection 实现的一个 文件上传
 * 
 * @author guiyn
 *
 */
public class UrlConnectionUpload {
	private final String boundary;
	private static final String LINE_FEED = "\r\n";
	private HttpURLConnection httpConn;
	private String charset;
	private OutputStream out;

	public UrlConnectionUpload(String requestURL, String charset, String token) {
		this.charset = charset;
		boundary = createBoundary();
		try {
			URL url = new URL(requestURL);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setUseCaches(false);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("Expect", "100-continue");
			httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			httpConn.setRequestProperty("User-Agent", "Java UrlConnection upload CLient");
			httpConn.setRequestProperty("Authorization", "Bearer " + token);
			httpConn.setRequestProperty("Connection", "close");
			out = httpConn.getOutputStream();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String createBoundary() {
		Random r = new Random();
		String allowed = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 32; i++)
			b.append(allowed.charAt(r.nextInt(allowed.length())));
		return b.toString();
	}

	private UrlConnectionUpload append(String value) throws IOException {
		out.write(value.getBytes(charset));
		return this;
	}

	public void addFormField(String name, String value) throws IOException {
		append("--").append(boundary).append(LINE_FEED);
		append("Content-Disposition: form-data; name=\"").append(name).append("\"").append(LINE_FEED);
		append("Content-Type: text/plain; charset=").append(charset).append(LINE_FEED);
		append(LINE_FEED);
		append(value).append(LINE_FEED);
		out.flush();
	}

	public void upload(String fieldName, byte[] uploadFile) throws IOException {
		append("--").append(boundary).append(LINE_FEED);
		append("Content-Disposition:form-data ; name=\"").append(fieldName).append("\";filename=\"" + fieldName + "\"")
				.append(LINE_FEED);
		append("Content-Type: application/octet-stream").append(LINE_FEED);
		append("Content-Transfer-Encoding: binary").append(LINE_FEED);
		append(LINE_FEED);
		out.flush();
		try {
			out.write(uploadFile);
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		append(LINE_FEED);
		out.flush();
	}

	public String finish() throws IOException {
		StringBuilder b = new StringBuilder();

		append("--" + boundary + "--").append(LINE_FEED);
		out.flush();
		out.close();

		try {
			InputStream is = null;
			int status = httpConn.getResponseCode();
			if (status == HttpURLConnection.HTTP_OK) {
				is = httpConn.getInputStream();

			} else {
				is = httpConn.getErrorStream();
			}

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					b.append(line);
				}
				reader.close();
				is.close();
			} catch (Throwable t) {
				throw new IOException("Server returned status: " + status + " error message: " + b.toString());
			}

			if (status == HttpURLConnection.HTTP_OK)
				return b.toString();

			throw new IOException("Server returned status: " + status + " error message: " + b.toString());
		} finally {
			httpConn.disconnect();
		}
	}
}
