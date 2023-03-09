package com.example.setupapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.setupapp.model.GPSConfiguration;
import com.google.gson.Gson;


@SpringBootApplication(scanBasePackages={"com.example.setupapp"})
@EnableAutoConfiguration(exclude = {
	    DataSourceAutoConfiguration.class,
	    DataSourceTransactionManagerAutoConfiguration.class,
	    HibernateJpaAutoConfiguration.class})
//@EnableJpaRepositories(basePackages={"com.example.setupapp"}) 
public class SetupAppApplication {
//    private static final Logger LOGGER = LoggerFactory.getLogger(SetupController.class);

	 private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
//		SpringApplication.run(SetupAppApplication.class, args);

			 context= new SpringApplicationBuilder(SetupAppApplication.class)
	           .run(args);
	   }
	
	 public static String restart() {
	        ApplicationArguments args = context.getBean(ApplicationArguments.class);
	    	 Thread thread = new Thread(() -> {
	            context.close();
	    		//Create a new Gson object
			    File config_file=new File(System.getProperty("user.home")+"\\gps-config.json");
			    Gson gson=new Gson();
			    
			    Properties props = new Properties();
				 if(config_file.exists()) {
		            	
				        //Read the employee.json file
				        BufferedReader bufferedReader = null;
						try {
							bufferedReader = new BufferedReader(  
							        new FileReader(config_file));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
				        //convert the json to  Java object (Employee)
				        GPSConfiguration gpsConfiguration = gson.fromJson(bufferedReader, GPSConfiguration.class);
				        
				        System.out.println("GPS : ==============="+gpsConfiguration.getDatabaseConfiguration().getDbName());
				        String db_url=null;
				        //Printing the Employee Details
//				      jdbc:sqlserver://NAV0674;encrypt=false;trustServerCertificate=false;databaseName=gps
				        db_url = "jdbc:sqlserver://NAV0674:"+gpsConfiguration.getDatabaseConfiguration().getDbPort()+
				        ";encrypt=false;trustServerCertificate=false;databaseName="+gpsConfiguration.getDatabaseConfiguration().getDbName();
				        System.out.println("GPS : ==============="+db_url);

				       
				        props.put("spring.datasource.username", gpsConfiguration.getDatabaseConfiguration().getDbUsername());
				        props.put("spring.datasource.password", gpsConfiguration.getDatabaseConfiguration().getDbPassword());
				        props.put("pring.datasource.url", db_url);
				        System.out.println("GPS : ==============="+gpsConfiguration.getDatabaseConfiguration().getDbPort());
				        System.out.println("GPS : ==============="+gpsConfiguration.getDatabaseConfiguration().getDbUsername());

		            }
	            context = SpringApplication.run(SetupAppApplication.class, args.getSourceArgs());

	        });
	       
	        thread.setDaemon(false);
	        thread.start();
	        return "login";
	    }

}
