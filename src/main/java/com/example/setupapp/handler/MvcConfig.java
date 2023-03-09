package com.example.setupapp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MvcConfig implements WebMvcConfigurer {
	
	@Autowired
	   SetupIntercepter setupIntercepter;
	
	  @Override
	    public void addInterceptors(final InterceptorRegistry registry) {
	        registry.addInterceptor(setupIntercepter).addPathPatterns("/");
	    }
}
