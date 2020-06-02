package com.minya.emailcounter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minya.emailcounter.service.UniqueEmailService;

@Service
public class UniqueEmailServiceImpl implements UniqueEmailService{
	@Autowired
	UniqueEmailProcessor uniqueEmailProcessor;
		
	public int getCountOfUniqueEmails(List<String> emails) {
		return uniqueEmailProcessor.getUniqueEmailCounts(emails);
	}
}
