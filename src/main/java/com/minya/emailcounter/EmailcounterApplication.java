package com.minya.emailcounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Minya
 * TO test run as spring boot application 
 * 
 * http://localhost:8080/count?emails=test.email@gmail.com,test.email%2Bspam@gmail.com,testemail@gmail.com
 *
 */

@SpringBootApplication
public class EmailcounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailcounterApplication.class, args);
	}

}
