package com.flab.Mytube;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MytubeApplication {
	private static final Log LOG = LogFactory.getLog(MytubeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MytubeApplication.class, args);
	}

}