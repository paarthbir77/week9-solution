package com.greatlearning.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import com.greatlearning.security.service.UserService;
import com.greatlearning.security.repository.UserRepository;

//import com.greatlearning.security.entity.User;
import com.greatlearning.security.serviceImpl.AdminOnUserServiceImpl;

@EnableWebSecurity
public class SecurityConfigurationWithJDBC extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
    UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/viewMonthlyFinancials", "/viewBillsToday","/welcomeAdmin","/createUser","/viewUsers","/updateUsers","/deleteUsers").hasAuthority("Admin")
		.antMatchers("/checkInventory", "/viewInventory", "/placeOrder").hasAnyAuthority("Admin", "Customer")
		.antMatchers("/","/Register").permitAll()
		.and()
		.formLogin();
	}

//	If you don't want to encode the created password, you can write the below bean method, FYI: not recommended for Prod env
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		//return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");

	}
}
