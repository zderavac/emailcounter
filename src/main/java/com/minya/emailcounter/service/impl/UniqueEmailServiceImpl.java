package com.minya.emailcounter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minya.emailcounter.service.UniqueEmailService;

@Service
public class UniqueEmailServiceImpl implements UniqueEmailService{
	
	//Helper class with required methods
	@Autowired
	UniqueEmailProcessor uniqueEmailProcessor;
	
	/**
	* 
	* @param emails input emails fro user request
	* @return count of unique emails based on rules specified in properties
	*/		
	public int getCountOfUniqueEmails(List<String> emails) {
		return uniqueEmailProcessor.getUniqueEmailCounts(emails);
	}
}
