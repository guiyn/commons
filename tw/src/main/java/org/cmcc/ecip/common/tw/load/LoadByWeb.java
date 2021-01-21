package org.cmcc.ecip.common.tw.load;


import java.util.List;

import org.cmcc.ecip.common.tw.cache.CertsTable;
import org.cmcc.ecip.common.tw.model.CmccCert;
import org.cmcc.ecip.common.tw.util.StringUtils;
import org.cmcc.ecip.common.tw.util.TwException;
import org.cmcc.ecip.common.utils.urlConnection.UrlConnectionGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @ProjectName tw.api
 * @PackageName cmcc.ecip.R.tw.api.load
 * @ClassName LoadByWeb
 * @Description
 * @author guiyn
 * @date 2019年12月23日 下午1:33:38
 * @version 2019年12月23日 下午1:33:38
 * 
 */

public class LoadByWeb implements TwLoader {
	static Logger log = LoggerFactory.getLogger("TW");

	public void init(String[] args) throws TwException {

		log.debug("begin tw web load init..");
		String url = args[0];
		boolean isLoadEmpty = false;

		if (args.length > 1 && "true".equals(args[1])) {
			isLoadEmpty = true;
		}
		log.debug("begin tw web load url:[{}] isLoadEmpty:[{}]   init..", url, isLoadEmpty);

		try {

			String respStr = UrlConnectionGet.doGet(url);
//			RequestConfig.Builder conf_builder = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES)
//					.setConnectTimeout(5000).setSocketTimeout(10000);
//
//			HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(conf_builder.build());
//			CloseableHttpClient proxyClient = builder.build();
//
//			HttpGet get = new HttpGet(url);
//			CloseableHttpResponse responseEntity = proxyClient.execute(get);
//			int statuscode = responseEntity.getStatusLine().getStatusCode();
//			if (responseEntity.getStatusLine().getStatusCode() == 200) {
//
//				String respStr = EntityUtils.toString(responseEntity.getEntity());

			List<CmccCert> list = JSON.parseArray(respStr, CmccCert.class);

			for (CmccCert cc : list) {
				if (!StringUtils.isTrimEmpty(cc.getNewKey()) || isLoadEmpty)
					CertsTable.getCert().put(cc.getNodeId(), cc);
			}
//			} else {
//				String mess = "get cert from [" + url + "] status is " + statuscode + "please check ...";
//				throw new TwException(TwException.START_ERROR_CODE, mess);

//			}
		} catch (TwException e) {
			throw e;
		} catch (Exception e) {

			throw new TwException(TwException.START_ERROR_CODE, "load error by :" + url, e);
		}

	}
}
