package com.minya.emailcounter.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.minya.emailcounter.configuration.UniqueEmailProperties;

/**
 * Assumptions: 
 * 1. Subdomains resolve to different email addresses 
 * 		Ex: me@me.com is not equal to me@me.me.com 
 * 2. Same rules will apply for subdomains 
 * 		Ex: me@me.com will have same rules as me@me.me.com
 * 3. emails are valid unless they are null 
 * 		validation of email address format is out of the scope)
 * 
 */

@Component
@EnableConfigurationProperties(UniqueEmailProperties.class)
public class UniqueEmailProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(UniqueEmailProcessor.class);

	private static String AT = "@";
	private static char EMPTY = ' ';
	private static String EMPTYSTR = "";
		
	/**
	 * Domain specific rules for user name uniqueness  
	 * stored in application.properties
	 */
	@Autowired
	private UniqueEmailProperties uniqueEmailProperties;

	/*
	 * If email is null it will be ignored
	 * In future we can validate email format here and log it
	 */
	public boolean validateFormat(String email) {
		if (email == null) {
			logger.warn("Invalid email found");
			return false;
		}
		return true;
	}

	/*
	 * Method that does the count of unique email adresss
	 */
	
	public int getUniqueEmailCounts(List<String> emails) {
		if (emails == null) {
			return 0;
		}		
		//Map of unique emails grouped by domain name
		//Please see assumptions above for domain name uniqueness
		Map<String, Set<String>> uniqueByDomain = groupUserNamesByDomain(emails);
		
		//Sums up all unique email addresses (sum of the all unique user names within all domains)
		return uniqueByDomain.values().stream().reduce( 0, (tempSum, s) -> tempSum + s.size(), Integer::sum );
	}
	
	/**
	 * 
	 * @param emails
	 * @return Unique usernames within domains by applying rules
	 */	
	public Map<String, Set<String>> groupUserNamesByDomain(List<String> emails) {
		if (emails == null) {
			return null;
		}
		
		Stream<String> stream = emails.stream();
		
		return stream
				.filter(e -> validateFormat(e))
				.map(email -> email.toLowerCase().trim())
				.map(email -> email.split(AT))
				.collect(
						Collectors.groupingBy(em -> em[1], 
								Collectors.mapping(e -> applyUniqueRules(e[1], e[0]), 
										Collectors.toSet()
										)
								)
						);

	}

	/**
	 * 
	 * @param domain
	 * @param userName
	 * @return user name after applying domain specific rules for uniqueness for particular domain
	 */
	public String applyUniqueRules(String domain, String userName) {
		domain = domain.toLowerCase().trim();
		userName = userName.toLowerCase().trim();
		String result = userName;
		result = applyCutoff(domain, result);
		result = applyIgnoreChar(domain, result);
		return result;
	}
	
	
	/**
	 * 
	 * @param domain
	 * @param userName
	 * @return domain specific user name after cutting off suffix 
	 * 		starting with first occurrence of characters specified in properties
	 */
	public String applyCutoff(String domain, String userName) {
		domain = domain.toLowerCase().trim();
		userName = userName.toLowerCase().trim();
		String res = userName;
		HashMap<String, Set<Character>> cutoffMap = uniqueEmailProperties.getCutoff();
		if(cutoffMap != null) {
			Set<Character> domainSetChar = cutoffMap.get(domain);			
			if (domainSetChar != null) {
				Iterator<Character> it = domainSetChar.iterator();
				while(it.hasNext()) {
					char ch = getChar(it.next());
					if(res.indexOf(ch) != -1) {
						res = res.substring(0, res.indexOf(ch));
					}
				}				
			}
		}
		return res;		
	}
	
	/**
	 * 
	 * @param domain
	 * @param userName
	 * @return domain specific user name after removing characters to ignore
	 */
	public String applyIgnoreChar(String domain, String userName) {
		domain = domain.toLowerCase().trim();
		userName = userName.toLowerCase().trim();
		String res = userName;
		HashMap<String, Set<Character>> ignored = uniqueEmailProperties.getIgnored();
		if(ignored != null) {
			Set<Character> domainSetChar = ignored.get(domain);			
			if (domainSetChar != null) {
				Iterator<Character> it = domainSetChar.iterator();
				while(it.hasNext()) {
					char ch = getChar(it.next());
					res = res.replaceAll(Pattern.quote(String.valueOf(ch)), EMPTYSTR);
				}				
			}
		}
		return res;		
	}
	
	/**
	 * Empty character from properties is read into map as null
	 * This is hlpere method to transform it to empty
	 * @param ch
	 * @return ' ' if character was null
	 */
	private char getChar(Character ch) {
		return ch == null ? EMPTY : ch;
	}

}
