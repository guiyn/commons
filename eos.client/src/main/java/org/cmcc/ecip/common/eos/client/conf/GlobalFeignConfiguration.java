package org.cmcc.ecip.common.eos.client.conf;

import feign.Logger;



import org.springframework.context.annotation.Bean;

/**
 * feign的配置类
 */

public class GlobalFeignConfiguration {
    @Bean
    public Logger.Level level(){
        // 让feign打印所有请求的细节
        return Logger.Level.FULL;
    }
}