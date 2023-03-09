package com.example.setupapp.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Resource;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConnection;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomFilter extends OncePerRequestFilter{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    	// TODO Auto-generated method stub
//        LOGGER.info("########## Initiating Custom filter ##########");
//    }
    
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		
//	}
	

//	
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		Filter.super.destroy();
//	}
//


	
	protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		  HttpServletRequest request = (HttpServletRequest) servletRequest;
	        HttpServletResponse response = (HttpServletResponse) servletResponse;

	        LOGGER.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());
	        LOGGER.info("Logging Request  : {}", request);

	        //checking if the setupconfig.xml file is exists or not
	        File f=new File(System.getProperty("user.home")+"\\gps-config.json");
		 	if(f.exists())
		 	{
	        	LOGGER.info("File exists");
		        filterChain.doFilter(request, response);

	        }else {
	        	LOGGER.info("File not exists");

				   response.sendRedirect(request.getScheme()+"://"+request.getServerName() +
						   (request.getServerPort()> 0 ? ":"+request.getServerPort():"")+request.getContextPath()+"/setup");
//	        	SetupWrapper wrapper=new SetupWrapper(request);
//	            wrapper.setContextPath(request.getContextPath());
//	            String newPath = request.getServletPath()+"/setup";
//	            wrapper.setServeletPath(newPath);
//	            
//	        	LOGGER.info("path -------- "+wrapper.getRequestURI());
//	        	LOGGER.info("path2 -------- "+newPath);

		        filterChain.doFilter(request, response);


	        }
	        
	        //call next filter in the filter chain

	        LOGGER.info("Logging Response :{}", response.getContentType());
		
	}

}
