package com.example.setupapp.handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SetupIntercepter implements HandlerInterceptor{
	  private Logger logger = LoggerFactory.getLogger(SetupIntercepter.class);

	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
	      throws Exception {
	    	logger.info("[preHandle][" + request + "]" + "[" + request.getMethod()
	         + "]" + request.getRequestURI() );
	  	  File f=new File(System.getProperty("user.home")+"\\gps-config.json");
		   if(f.exists())
		   {
			   return true;
		   }
		   else
		  {		
			   response.sendRedirect(request.getScheme()+"://"+request.getServerName() +
					   (request.getServerPort()> 0 ? ":"+request.getServerPort():"")+request.getContextPath()+"/setup");
			   response.setStatus(403);
			   logger.info("file not exits");
			   return true;													
		  }
	        
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
	      throws Exception {
	        logger.info("postHandle");
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
	      throws Exception {
	        logger.info("afterCompletion");
	        
	    }
	    
}
