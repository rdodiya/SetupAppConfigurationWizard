package com.example.setupapp.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class AppConfig {

//	   @Bean
//	   public CustomFilter customFilter() {
//		   return new CustomFilter();
//	   }

	 @Bean
	 public FilterRegistrationBean<CustomFilter> filterRegistrationBean() {
	  FilterRegistrationBean < CustomFilter > registrationBean = new FilterRegistrationBean();
	  CustomFilter customURLFilter = new CustomFilter();

	  registrationBean.setFilter(customURLFilter);
	  registrationBean.addUrlPatterns("/l");
	  registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); //set precedence
	  return registrationBean;
	 }

}
