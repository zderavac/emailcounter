package com.minya.emailcounter.serrvice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.minya.emailcounter.service.UniqueEmailService;

@SpringBootTest
public class UniqueEmailServiceTest {

	@Autowired
	UniqueEmailService uniqueEmailService;
	
	@Test
	public void testUniqueEmailServiceNullEmails() {		
		Assertions.assertEquals(0, uniqueEmailService.getCountOfUniqueEmails(null) );		
	}
	
	@Test
	public void testUniqueEmailServiceEmptyEmails() {		
		Assertions.assertEquals(0, uniqueEmailService.getCountOfUniqueEmails(new ArrayList<String>()) );		
	}
	
	@Test
	public void testUniqueEmailServiceEmails() {		
		List<String> input = new ArrayList<String>();
		input.add("my+user+name@mytest.com");
		input.add("my+user..name@mytest.com");
		input.add("my user+name@mytest.com");
		input.add("Another   User+name@mytest.com");
		input.add("another user-name@mytest.com");
		input.add("Another User+name@yahoo.com");
		input.add("another user+name@yahoo.com");
		input.add("another+user name@yahoo.com");
		input.add("zzz@yahoo.com");
		input.add("zzz@yahoo.com");
		input.add("zzz@mytest.com");
		
		
		Assertions.assertEquals(7, uniqueEmailService.getCountOfUniqueEmails(input));		
	}
}
