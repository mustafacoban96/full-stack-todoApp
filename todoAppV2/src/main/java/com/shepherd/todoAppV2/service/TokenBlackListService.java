package com.shepherd.todoAppV2.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService {

	private Set<String> blackList = new HashSet<>();
	
	
	public void blacklistToken(String token) {
		blackList.add(token);
	}
	
	
	public boolean isTokenBlackListed(String token) {
		return blackList.contains(token);
	}
}
