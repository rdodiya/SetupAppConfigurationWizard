package com.example.setupapp.model;

public class BasicConfiguration {
	String BaseURL;
	String LogFilePath;
	
	public String getBaseURL() {
		return BaseURL;
	}
	public void setBaseURL(String baseURL) {
		BaseURL = baseURL;
	}
	public String getLogFilePath() {
		return LogFilePath;
	}
	public void setLogFilePath(String logFilePath) {
		LogFilePath = logFilePath;
	}
	
	@Override
	public String toString() {
		return "BasicConfiguration [BaseURL=" + BaseURL + ", LogFilePath=" + LogFilePath + "]";
	}

	
}
