package com.minya.emailcounter.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UniqueEmailsControllerTest {
	
	@Autowired
	private MockMvc mvc;

	private  String emails;
	
	private  String assignementEmails;

	@BeforeEach
    public void setUp() throws Exception {       
        emails = 
        		"my+user+name@mytest.com"+","
        		+"my+user..name@mytest.com"+","
        		+"my user+name@mytest.com"+","
        		+"Another   User+name@mytest.com"+","
        		+"another user-name@mytest.com"+","
        		+"Another User+name@yahoo.com"+","
        		+"another user+name@yahoo.com"+","
        		+"another+user name@yahoo.com"+","
        		+"zzz@yahoo.com"+","
        		+"zzz@yahoo.com"+","
        		+"zzz@mytest.com";  
        
        assignementEmails = "test.email@gmail.com"+","
        		+"test.email+spam@gmail.com"+","
        		+"testemail@gmail.com";
    }

	@Test
	public void test() throws Exception {
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/count").accept(MediaType.APPLICATION_JSON).param("emails", emails))
				.andExpect(status().isOk());
		MvcResult result = resultActions.andReturn();
		Assertions.assertEquals("7", result.getResponse().getContentAsString());
	}	
	
	@Test
	public void testGoogleExample() throws Exception {
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/count").accept(MediaType.APPLICATION_JSON).param("emails", assignementEmails))
				.andExpect(status().isOk());
		MvcResult result = resultActions.andReturn();
		Assertions.assertEquals("1", result.getResponse().getContentAsString());
	}
}
