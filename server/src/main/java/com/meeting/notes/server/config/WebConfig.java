package com.meeting.notes.server.config;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(value = {"com.meeting.notes.server"}, excludeFilters = @ComponentScan.Filter(value = {Configuration.class}))
@Import({ApplicationConfig.class, WebsocketConfigBroker.class})
public class WebConfig extends WebMvcConfigurationSupport {
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter handlerAdapter = super.requestMappingHandlerAdapter();
        handlerAdapter.getMessageConverters().add(mappingJackson2HttpMessageConverter());
        return handlerAdapter;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(Log4jConfigurer.class);
        methodInvokingFactoryBean.setTargetMethod("initLogging");
        methodInvokingFactoryBean.setArguments(new Object[] {"../resources/server/log4j.xml"});
        return methodInvokingFactoryBean;
    }
}
