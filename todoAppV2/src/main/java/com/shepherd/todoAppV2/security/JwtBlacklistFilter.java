package com.shepherd.todoAppV2.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shepherd.todoAppV2.service.TokenBlackListService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtBlacklistFilter extends OncePerRequestFilter{
	
	
	private final TokenBlackListService tokenBlackListService;
	
	public JwtBlacklistFilter(TokenBlackListService tokenBlackListService) {
		this.tokenBlackListService = tokenBlackListService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 String token = extractTokenFromRequest(request);
	        if (token != null && tokenBlackListService.isTokenBlackListed(token)) {
	            SecurityContextHolder.clearContext();
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Token is blacklisted");
	            return;
	        }
	        filterChain.doFilter(request, response);
		
	}
	
	 private String extractTokenFromRequest(HttpServletRequest request) {
	        String bearerToken = request.getHeader("Authorization");
	        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	            return bearerToken.substring(7);
	        }
	        return null;
	    }

}
