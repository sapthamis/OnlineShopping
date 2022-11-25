//package com.microservice.demo.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@SuppressWarnings("deprecation")
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("Sapthami")
//				.password("shetty").roles("USER","ADMIN").and().withUser("admin").password("secret").roles("ADMIN");
//	}
//
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.httpBasic().and().authorizeRequests().antMatchers("/h2-console/**", "/actuator/**").hasRole("ADMIN")
//				.antMatchers("/orders/**").hasRole("USER").and()
//				.csrf().disable().headers().frameOptions().disable();
//
//	}
//
//}
