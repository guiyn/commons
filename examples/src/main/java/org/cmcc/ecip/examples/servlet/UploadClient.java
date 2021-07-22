package org.cmcc.ecip.examples.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest; 

public class UploadClient {

	public static void main(String[] args) throws Exception {
		HttpEntityEnclosingRequest request = newProxyRequestWithEntity("http://127.0.0.1:8888/upload", "attr hello",
				" hello word..".getBytes());
		
		request.setHeader("Connection", "close");
		sender(request);
	}

	static HttpResponse sender(HttpRequest request) throws Exception {
		CloseableHttpClient proxyClient = null;
		try {
			String url = request.getRequestLine().getUri();

			URI uri = new URI(url);
			HttpHost httpHost = URIUtils.extractHost(uri);

			proxyClient = HttpClientBuilder.create().setDefaultRequestConfig(buildRequestConfig()).build();

			return proxyClient.execute(httpHost, request);
			
			
			
			
//			String url = request.getRequestLine().getUri();
//			try {
//				URI uri = new URI(url);
//				HttpHost httpHost = URIUtils.extractHost(uri);
//				
//				CloseableHttpClient proxyClient = HttpClientBuilder.create().setDefaultRequestConfig(buildRequestConfig())
//						.build();
//				
//				return proxyClient.execute(httpHost, request);
//				
		} finally {
			if (proxyClient != null)
				proxyClient.close();
		}

	}

	static RequestConfig buildRequestConfig() {
		RequestConfig.Builder builder = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES)
				.setConnectTimeout(10000).setSocketTimeout(10000);
		return builder.build();
	}

	static HttpEntityEnclosingRequest newProxyRequestWithEntity(String method, String proxyRequestUri, String xmlHeader,
			String xmlBody, byte[] attachfile, Map<String, String> header) throws  Exception {
		HttpEntityEnclosingRequest eProxyRequest = new BasicHttpEntityEnclosingRequest(method, proxyRequestUri);

		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		try {
			multipartEntityBuilder.setCharset(Charset.forName("utf-8"));

			if (attachfile != null && attachfile.length > 0)
				multipartEntityBuilder.addBinaryBody("ATTACH_FILE", attachfile, ContentType.MULTIPART_FORM_DATA,"ATTACH_FILE");
			
			multipartEntityBuilder.addTextBody("XML_HEAD", xmlHeader);
			multipartEntityBuilder.addTextBody("XML_BODY", xmlBody);
			HttpEntity httpEntity = multipartEntityBuilder.build();
			eProxyRequest.setEntity(httpEntity);

			Set<String> hks = header.keySet();
			for (String hk : hks) {
				String v = header.get(hk);
				eProxyRequest.addHeader(hk, v);
			}

		} catch (Exception e) {
			throw e;
		}
		return eProxyRequest;
	}
	
	
	static HttpEntityEnclosingRequest newProxyRequestWithEntity(String proxyRequestUri, String attr,
			byte[] attachfile) {
		HttpEntityEnclosingRequest eProxyRequest = new BasicHttpEntityEnclosingRequest("POST", proxyRequestUri);
 
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		multipartEntityBuilder.setCharset(Charset.forName("utf-8"));

		multipartEntityBuilder.addBinaryBody("attr_file", attachfile, ContentType.MULTIPART_FORM_DATA,"");
		multipartEntityBuilder.addTextBody("attr", attr);

		HttpEntity httpEntity = multipartEntityBuilder.build();
		eProxyRequest.setEntity(httpEntity);
 

		return eProxyRequest;
	}
}
