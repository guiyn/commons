package org.cmcc.ecip.examples.cronjob;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

import java.util.concurrent.ScheduledFuture;

import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.cmcc.ecip.common.utils.JsonUtils;
import org.cmcc.ecip.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConditionalOnProperty(value = "app.xhzw.testSender.service.enable", havingValue = "true", matchIfMissing = false)

@Component
@Controller
public class TestSender {

	@Value("#{'${app.xhzw.testSender.service.urls:http://127.0.0.1}'.split(',')}")
	Vector<String> urls;

	@Value("${app.xhzw.testSender.service.body:<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header/><soapenv:Body><TestReq><MessageID>$MESS_ID$</MessageID><CommandCode>TEST_REQ</CommandCode><Remark>test</Remark></TestReq></soapenv:Body></soapenv:Envelope>}")
	String body;

	@Resource(name = "testSenderThreadPoolExecutor")
	ThreadPoolExecutor pe;

	@Resource(name = "testSenderTaskScheduler")
	ThreadPoolTaskScheduler ts;

	@Value("${app.xhzw.testSender.service.cronJob:0/30 * * * * ?}")
	String cron;

	ScheduledFuture<?> future;

	@PostConstruct
	public void toSend() {

		taskScheduler();
		log.info("thread pool start..");
	}

	public synchronized void restart() {
		taskstop();
		toSend();

	}

	void taskScheduler() {
		future = ts.schedule(new Runnable() {
			@Override
			public void run() {
				for (String url : urls) {
					pe.execute(new Runnable() {
						@Override
						public void run() {
							log.info(" >>> " + url);
							log.info(" >>> "
									+ body.replace("$MESS_ID$", UUID.randomUUID().toString().replaceAll("-", "")));

						}
					});

				}
			}
		}, new CronTrigger(cron));

	}

	public void send(String url, String body) {
		log.info("begin send to [" + url + "]");
		CloseableHttpClient proxyClient = null;
		HttpResponse response = null;
		try {
			URI targetUriObj = new URI(url);
			RequestConfig.Builder rcbuilder = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES)
					.setConnectTimeout(10).setSocketTimeout(10);
			RequestConfig rc = rcbuilder.build();
			HttpHost httpHost = URIUtils.extractHost(targetUriObj);
			HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(rc);
			proxyClient = builder.build();
			HttpRequest proxyRequest = newProxyRequestWithEntity(url, body);
			response = proxyClient.execute(httpHost, proxyRequest);
			int status = response.getStatusLine().getStatusCode();
			log.info("complete send to [" + url + "] response http status is " + status);

			if (log.isDebugEnabled()) {
				InputStream is = response.getEntity().getContent();
				String responseXML = IOUtils.toString(is, "UTF-8");
				log.debug("complete send to [" + url + "] response xml >>" + responseXML);
			}

		} catch (Exception e) {
			log.error("send to [" + url + "] error", e);
		} finally {
			try {
				if (response != null) {
					EntityUtils.consume(response.getEntity());
				}
				if (proxyClient != null) {
					proxyClient.close();
				}
			} catch (Exception e) {
				log.error("close client error", e);
			}

		}
		log.info("over send to [" + url + "]");
	}

	private HttpRequest newProxyRequestWithEntity(String proxyRequestUri, String sendBody) throws Exception {

		HttpEntityEnclosingRequest eProxyRequest = new BasicHttpEntityEnclosingRequest("POST", proxyRequestUri);
		try {
			InputStream is = IOUtils.toInputStream(sendBody, "UTF-8");
			eProxyRequest.setEntity(new InputStreamEntity(is));
		} catch (Exception e) {
			throw new Exception("buil proxy request error", e);
		}
		return eProxyRequest;
	}

	@ResponseBody
	@RequestMapping(value = "${app.xhzw.testSender.service.urls.setting:/xhzw/test/sender/urls_setting}", method = RequestMethod.POST)
	public String dochangeurls(@RequestBody(required = false) List<String> s) throws IOException {
		if (s == null || s.size() < 1) {
			return "you request urls is null";
		}
//		List<String> s = null;
//		try {
//			s = JsonUtils.format2Array(json, String.class);
//		} catch (Exception e) {
//			return "parse json array error >>" + e.getMessage();
//		}

		for (String _s : s) {
			UrlValidator v = new UrlValidator();
			if (!v.isValid(_s)) {
				return _s + " is not url";
			}

		}

		log.info("urls size >>>" + s.size());
		synchronized (urls) {
			urls.clear();
			urls.addAll(s);
		}
		return JsonUtils.formatJsonStr(urls);

	}

	@ResponseBody
	@RequestMapping(value = "${app.xhzw.testSender.service.cronJob.setting:/xhzw/test/sender/cron_setting}", method = RequestMethod.POST)
	public String docronJob(@RequestBody(required = false) String _corn) throws IOException {
		if (StringUtils.isTrimEmpty(_corn))
			return "you request is null..";

		if (CronSequenceGenerator.isValidExpression(_corn)) {
			synchronized (cron) {
				this.cron = _corn;
				restart();
			}
		} else
			return _corn + " is not cron config";

		return this.cron;
	}

	@ResponseBody
	@RequestMapping(value = "${app.xhzw.testSender.service.cronJob.setting:/xhzw/test/sender/job_stop}", method = RequestMethod.POST)
	public String taskstop() {
		try {
			log.info("thread pool begin shutdown");
			future.cancel(true);
			log.info("thread pool  shutdown..");
			int i = 10;
			log.info(future.isDone() + "   >>>>>   " + future.isCancelled());
			while (!future.isCancelled() && i >= 0) {
				log.info("wait cancel >> " + i);
				Thread.sleep(1000);
				i--;
			}
			return "job is stoped";
		} catch (Exception e) {
			log.error("job is stoped error.", e);
			return "job is stoped error.";
		}

	}

	@ResponseBody
	@RequestMapping(value = "${app.xhzw.testSender.service.cronJob.setting:/xhzw/test/sender/job_start}", method = RequestMethod.POST)
	public String taskstart() {

		if (future.isCancelled()) {
			toSend();
		}
		return "job is started";

	}
}
