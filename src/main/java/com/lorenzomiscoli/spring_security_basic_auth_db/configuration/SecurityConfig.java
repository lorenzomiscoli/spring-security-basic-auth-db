package com.lorenzomiscoli.spring_security_basic_auth_db.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {

	private final BasicAuthenticationProvider authProvider;

	public SecurityConfig(BasicAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authProvider);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authProvider)
				.sessionManagement((session) -> session.sessionCreationPolicy(STATELESS))
				.authorizeHttpRequests((matcher) -> {
					matcher.requestMatchers("/login.html", "/favicon.ico").permitAll();
					matcher.anyRequest().authenticated();
				}).httpBasic(basic -> {
					basic.authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint());
				});
		return http.build();
	}

}
