package com.minya.emailcounter.configuration;

import java.util.HashMap;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("email.username")
public class UniqueEmailProperties {

	private HashMap<String, Set<Character>> ignored;
	
	private HashMap<String, Set<Character>>  cutoff;

	public HashMap<String, Set<Character>> getIgnored() {
		return ignored;
	}

	public void setIgnored(HashMap<String, Set<Character>> ignored) {
		this.ignored = ignored;
	}

	public HashMap<String, Set<Character>> getCutoff() {
		return cutoff;
	}

	public void setCutoff(HashMap<String, Set<Character>> cutoff) {
		this.cutoff = cutoff;
	}

}
