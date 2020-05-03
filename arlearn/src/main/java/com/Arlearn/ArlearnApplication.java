package com.Arlearn;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import com.Arlearn.controlPanel.TestSTrayFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArlearnApplication {

	//static TestSTrayFrame trayFrame = new TestSTrayFrame();

	private static Logger Log = LoggerFactory.getLogger(ArlearnApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(ArlearnApplication.class, args);
		Log.info("Holis soy un logger");
		//trayFrame.panelControl(args);
		
	
	}

}



