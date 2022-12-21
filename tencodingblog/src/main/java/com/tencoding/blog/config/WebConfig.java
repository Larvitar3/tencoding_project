package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;


@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// 해당 주소가 발견되면 낚아챈다.
		registry.addResourceHandler("/upload/**")
		.setCachePeriod(50 +10 *8)
		.addResourceLocations("file:///" + uploadFolder)
		.resourceChain(false)
		.addResolver(new PathResourceResolver()); // 자원을 찾는걸 최적화 한다.
		
		
		
	}
	
	
	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean(){
		
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		
		filterRegistrationBean.setFilter(new XssEscapeServletFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;		
	}

}
