package com.minya.emailcounter.service;

import java.util.List;

import org.springframework.stereotype.Service;
	
/**
* 
* @param emails input emails fro user request
* @return count of unique emails based on rules specified in properties
*/
public interface UniqueEmailService {
	
	/**
	
	*/
	public int getCountOfUniqueEmails(List<String> emails);

}
