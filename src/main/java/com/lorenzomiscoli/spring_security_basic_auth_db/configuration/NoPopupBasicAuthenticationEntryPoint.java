package com.lorenzomiscoli.spring_security_basic_auth_db.configuration;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoPopupBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	public NoPopupBasicAuthenticationEntryPoint() {
		this.setRealmName("spring-security-basic.auth-db");
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
	}

}
