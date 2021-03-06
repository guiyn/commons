package org.cmcc.ecip.common.eos.client.conf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.cmcc.ecip.common.eos.client.exception.ChainApiException;
import org.cmcc.ecip.common.eos.client.exception.FeignError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration

public class ChainClientConfig {

	static Logger log = LoggerFactory.getLogger("CHAIN");

	public ErrorDecoder feignerrorDecider() {

		return new ErrorDecoder() {

			@Override
			public Exception decode(String methodKey, Response response) {
				log.info("------------" + methodKey);
				try {
					String body = IOUtils.toString(response.body().asReader());
					log.info("resp body  " + body);
					FeignError eae = JSON.parseObject(body, FeignError.class);
					return new ChainApiException(eae);

				} catch (Exception e) {
					log.error("format error", e);
					return new ChainApiException(e.getMessage());
				}
			}

		};
	}

	@Bean
	public ResponseEntityDecoder feignDecoder() {
		HttpMessageConverter<?> fastJsonConverter = createFastJsonConverter();
		ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(fastJsonConverter);
		return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
	}

	@Bean
	public SpringEncoder feignEncoder() {
		HttpMessageConverter<?> fastJsonConverter = createFastJsonConverter();
		ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(fastJsonConverter);
		return new SpringEncoder(objectFactory);
	}

	private HttpMessageConverter<?> createFastJsonConverter() {

		// ??????fastJson???????????????
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		// ????????????????????????=============================================================
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XML);
		supportedMediaTypes.add(MediaType.IMAGE_GIF);
		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
		supportedMediaTypes.add(MediaType.IMAGE_PNG);
		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.TEXT_XML);
		fastConverter.setSupportedMediaTypes(supportedMediaTypes);

		// ???????????????
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		// ?????????????????????????????????
		// WriteNullListAsEmpty ???List???????????????null,?????????[],??????null
		// WriteNullStringAsEmpty ??? ???????????????????????????null,?????????"",??????null
		// DisableCircularReferenceDetect ?????????????????????????????????????????????????????????false????????????????????????????????????????????????
		// WriteNullBooleanAsFalse???Boolean???????????????null,?????????false,??????null
		// WriteMapNullValue?????????????????????null?????????,?????????false
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullBooleanAsFalse);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		return fastConverter;
	}
}
