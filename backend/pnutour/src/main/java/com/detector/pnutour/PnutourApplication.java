package com.detector.pnutour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories(basePackages = "com.detector.pnutour.repository")
public class PnutourApplication {

	public static void main(String[] args) {
		SpringApplication.run(PnutourApplication.class, args);
	}


}
