 package com.tejas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.tejas.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class Security {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationManager() {		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(customUserDetailsService);
		provider.setPasswordEncoder(encoder());
		
		return provider;
	}
	
	@Bean
	public SecurityFilterChain manageSecurity(HttpSecurity http) {
		http.csrf(csrf->csrf.disable());
		
		http.authorizeHttpRequests(auth->{
			auth.requestMatchers("/create","/welcome").permitAll();
			auth.requestMatchers("/user").hasRole("USER");
			auth.requestMatchers("/admin").hasRole("ADMIN");
			auth.requestMatchers("/contact","/about").hasAnyRole("USER","ADMIN")
			.anyRequest()
			.authenticated() ;
			
		}).httpBasic(Customizer.withDefaults())
		.formLogin(form->form.permitAll());
		
		return http.build();
	}
}
