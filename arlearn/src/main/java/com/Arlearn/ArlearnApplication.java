package com.Arlearn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArlearnApplication {

	private static Logger Log=LoggerFactory.getLogger(ArlearnApplication.class);
	public static void main(String[] args) 
	{
		SpringApplication.run(ArlearnApplication.class, args);
		Log.info("Holis soy un logger");
	}

}



