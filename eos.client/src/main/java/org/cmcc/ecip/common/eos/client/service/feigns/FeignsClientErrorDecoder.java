package org.cmcc.ecip.common.eos.client.service.feigns;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.cmcc.ecip.common.eos.client.exception.ChainApiException;
import org.cmcc.ecip.common.eos.client.exception.FeignError;
import org.cmcc.ecip.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignsClientErrorDecoder extends ErrorDecoder.Default {
	static Logger log = LoggerFactory.getLogger("CHAIN");

	@Override
	public Exception decode(String methodKey, Response response) {
//		super.decode(methodKey, response);
		ChainApiException exc = new ChainApiException("execute chain api error");
		if (response.body() != null) {
			String body = "";
			try {
				body = IOUtils.toString(response.body().asInputStream(), "utf-8");
			} catch (IOException e) {
				log.error(" pass response error.", e);
				exc.setStackTrace(e.getStackTrace());
				return exc;
			}

			log.info(" response body >>>" + body);
			FeignError fe = JsonUtils.format2Bean(body, FeignError.class);
			exc.setMessage(fe.getMessage());
			exc.setCode(fe.getCode());
			exc.setEosError(fe.getError());
		}

		log.info("return ....");
		return exc;
	}

}
