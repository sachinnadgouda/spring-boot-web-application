package com.springboot.web.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	 @Bean
	    public UserDetailsService userDetailsService() {

	        @SuppressWarnings("deprecation")
			User.UserBuilder users = User.withDefaultPasswordEncoder();
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(users.username("User2").password("test_1234").roles("USER").build());
	        manager.createUser(users.username("User1").password("test_1234").roles("USER", "ADMIN").build());
	        return manager;

	    }
	 
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/login").permitAll()
					.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
					.formLogin();
		}

}
