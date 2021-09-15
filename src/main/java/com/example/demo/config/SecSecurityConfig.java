package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("test")).roles("USER").and()
				.withUser("user2").password(passwordEncoder().encode("test")).roles("USER").and().withUser("admin")
				.password(passwordEncoder().encode("test")).roles("ADMIN");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/anonymous*")
		.anonymous().antMatchers("/login*").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login.html")
		.loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/greeting", true)
		.failureUrl("/login.html?error=true")
		.failureHandler(authenticationFailureHandler())
		.and().logout().logoutUrl("/perform_logout")
		.deleteCookies("JSESSIONID")
		.logoutSuccessHandler(logoutSuccessHandler());
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		// TODO Auto-generated method stub
		return new CustomLogoutSuccessHandler();
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		// TODO Auto-generated method stub
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
