package com.tradetask.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
		managerBuilder.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER").and()
				.withUser("admin").password("{noop}1234").roles("ADMIN").and()
				.withUser("test").password("{noop}1234").roles("REVIEWER");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic()
					.and()
					.authorizeRequests()
					.antMatchers("/v1/trades/**").permitAll()
					.antMatchers("/v2/trades").hasRole("REVIEWER")
					.antMatchers("/v2/trades/*").hasRole("ADMIN")
					.and().csrf().disable()
					.formLogin()
					.disable();
		/*
		 * httpSecurity.httpBasic().and().authorizeRequests().antMatchers("/v1/trade").
		 * hasRole("ADMIN")
		 * .antMatchers("/v1/trade").hasRole("USER").and().csrf().disable().headers().
		 * frameOptions().disable();
		 */
	}

}
