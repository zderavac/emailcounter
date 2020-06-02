package com.minya.emailcounter.serrvice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.minya.emailcounter.service.impl.UniqueEmailProcessor;


@SpringBootTest
public class UniqueEmailProcessorTest {
	
	@Autowired
	UniqueEmailProcessor uniqueEmailProcessor;
	
	@Test
	public void testCutOff() {
		Assertions.assertEquals("my", uniqueEmailProcessor.applyCutoff("mytest.com", "my-user-name"));
		Assertions.assertEquals("my", uniqueEmailProcessor.applyCutoff("mytest.com", "my-user name"));
		Assertions.assertEquals("my", uniqueEmailProcessor.applyCutoff("mytest.com", "my user-name"));
		Assertions.assertEquals("my", uniqueEmailProcessor.applyCutoff("mytest.com", "my user name"));
		Assertions.assertEquals("my+user+name", uniqueEmailProcessor.applyCutoff("yahoo.com", "my+user+name"));
	}
	
	@Test
	public void testIgnoreChar() {		
		Assertions.assertEquals("myusername", uniqueEmailProcessor.applyIgnoreChar("mytest.com", "my.user..name"));
		Assertions.assertEquals("myusername", uniqueEmailProcessor.applyIgnoreChar("mytest.com", "my.us+er..na++me"));
		Assertions.assertEquals("my.user..name", uniqueEmailProcessor.applyCutoff("yahoo.com", "my.user..name"));
	}
	
	@Test
	public void testApplyUniqueRules() {
		Assertions.assertEquals("another", uniqueEmailProcessor.applyUniqueRules("mytest.com", "Another User+name@yahoo.com"));
	}

	@Test
	public void testCount() {
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
		
		Assertions.assertEquals(7, uniqueEmailProcessor.getUniqueEmailCounts(input));
	}
	
	@Test
	public void testAssignementCount() {
		List<String> input = new ArrayList<String>();
		input.add("test.email@gmail.com");
		input.add("test.email+spam@gmail.com");
		input.add("testemail@gmail.com");
	
		Assertions.assertEquals(1, uniqueEmailProcessor.getUniqueEmailCounts(input));
	}

}
