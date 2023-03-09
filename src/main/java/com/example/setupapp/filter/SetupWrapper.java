package com.example.setupapp.filter;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import org.springframework.web.filter.CharacterEncodingFilter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class SetupWrapper extends HttpServletRequestWrapper{
	
	private String servletPath;
	private String contextPath;
	
	public SetupWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}	
	
	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		String uri=super.getRequestURI();
		if(uri.contains(contextPath)) {
			return uri+"/";
		}
		return super.getRequestURI();
	}
	
	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(contextPath)) {
			return contextPath;
		}
		return super.getContextPath();
	}
	
	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(servletPath)) {
			return servletPath;
		}
		return super.getServletPath();
	}
	
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public void setServeletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	
	
}
