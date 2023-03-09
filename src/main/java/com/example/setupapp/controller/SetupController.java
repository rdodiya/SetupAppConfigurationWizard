package com.example.setupapp.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.coyote.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.setupapp.SetupAppApplication;
import com.example.setupapp.filter.CustomFilter;
import com.example.setupapp.handler.SetupIntercepter;
import com.example.setupapp.model.BasicConfiguration;
import com.example.setupapp.model.DatabaseConfiguration;
import com.example.setupapp.model.GPSConfiguration;
import com.example.setupapp.model.LDAPConfigurtion;
import com.example.setupapp.model.SMTPConfiguration;
import com.example.setupapp.model.Student;
import com.example.setupapp.repository.StudentRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;

import ch.qos.logback.classic.net.SMTPAppender;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

@Controller
public class SetupController {

	@Autowired
	   SetupIntercepter setupIntercepter;
	
//	@Autowired StudentRepository studentRepository;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SetupController.class);


	
	@GetMapping("/")
	public String customGreet(HttpServletResponse response) throws Exception{
		
			return "login";

	}
	
	/*
	 * @GetMapping("/get")
	 * 
	 * @ResponseBody public List<Student> savestudent() {
	 * 
	 * // return null; return studentRepository.findAll();
	 * 
	 * }
	 */
	
	@GetMapping("/setup")
	public String setupapp() {
		return "setup";
	}
	
	@GetMapping("/setupdata")
	public String setupappdata() {
		
		BasicConfiguration basicConfiguration=new BasicConfiguration();
		basicConfiguration.setBaseURL("urll");
		basicConfiguration.setLogFilePath("c:users");
		
		DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
		databaseConfiguration.setDbName("test");
		databaseConfiguration.setDbHostname("sa");
    	databaseConfiguration.setDbPort("1433");
    	databaseConfiguration.setDbUsername("sa");
    	databaseConfiguration.setDbPassword("123");

    	SMTPConfiguration smtpConfiguration=new SMTPConfiguration();
    	smtpConfiguration.setSmtpPassword("8090");
    	smtpConfiguration.setSmtpServer("asfdd");
    	smtpConfiguration.setSmtpUsername("raj");
    	smtpConfiguration.setSmtpPassword("sdas@ed");
    	
    	LDAPConfigurtion ldapConfigurtion=new LDAPConfigurtion();
    	ldapConfigurtion.setLdapHostname("ldaphost");
    	ldapConfigurtion.setLdapUsername("ldapusername");
    	ldapConfigurtion.setLdapPort("8090");
    	ldapConfigurtion.setLdapAdministratorUser("admin user");
    	ldapConfigurtion.setLdapPassword("ldappassword");
    	ldapConfigurtion.setLdapBaseDN("baseDN");
    	ldapConfigurtion.setLdapAdditionalUserDN("ldapadduser");

    	GPSConfiguration gpsConfiguration=new GPSConfiguration();
    	gpsConfiguration.setBasicConfiguration(basicConfiguration);
    	gpsConfiguration.setDatabaseConfiguration(databaseConfiguration);
    	gpsConfiguration.setSMTPConfiguration(smtpConfiguration);
    	gpsConfiguration.setLDAPConfigurtion(ldapConfigurtion);
    	
    	JSONParser jsonParser=new JSONParser("Configuration");
    	
    	
	    File f=new File(System.getProperty("user.home")+"\\gps-config.json");
	    try {
	    	 if(f.createNewFile()) {

	 	    	FileWriter fileWriter=new FileWriter(System.getProperty("user.home")+"\\gps-config.json");
	 	    	
	 	    	Gson gson=new Gson();
	 	    	
	 	    	String data=gson.toJson(gpsConfiguration);
	 	    	fileWriter.write(data);
	 	    	fileWriter.close();
//	 			
	 	    	return SetupAppApplication.restart();
//	 		    return "login";
	 	    }else {
	 	    	LOGGER.info("file not created " );
	 	    	
	 	    	return "setupfailed";
	 	    }
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
		return "exception";
	   
   	}
}
