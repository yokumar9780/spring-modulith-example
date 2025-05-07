package com.example.gatewayservice.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public TomcatProtocolHandlerCustomizer<AbstractHttp11Protocol<?>> maxHeaderCountCustomizer() {
        return protocolHandler -> {
            protocolHandler.setMaxHttpHeaderSize(8192); // Increase header size limit
            protocolHandler.setMaxHttpRequestHeaderSize(8192);
        };
    }
}