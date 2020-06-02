package com.minya.emailcounter.service;

import java.util.List;

import org.springframework.stereotype.Service;

public interface UniqueEmailService {
	
	public int getCountOfUniqueEmails(List<String> emails);

}
