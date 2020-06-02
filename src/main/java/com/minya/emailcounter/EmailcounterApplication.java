package com.minya.emailcounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Minya
 * 
 * Main applicaion class
 * 
 * Run it as spring boot application 
 * Go to web brower and hit
 * http://localhost:8080/count?emails=test.email@gmail.com,test.email%2Bspam@gmail.com,testemail@gmail.com
 * expected result is 1
 */


@SpringBootApplication
public class EmailcounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailcounterApplication.class, args);
	}

}
