package com.ravali.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ravali.spring.security.filter.JwtRequestFilter;
import com.ravali.spring.security.jwt.JwtAuthenticationEntryPoint;

@Configuration

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguratioin extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// We don't need CSRF for this example
		http.csrf().disable()
		// dont authenticate this particular request
		.authorizeRequests().antMatchers("/authenticate").permitAll().
		// all other requests need to be authenticated
		anyRequest().authenticated().and().
		// make sure we use stateless session; session won't be used to
		// store user's state.
		exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	//login details from the database starts here
	//to access the login related details from the dataase we hav to override authenticationMangaer builder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//here we have so pass our own authentication prodiver
		auth.authenticationProvider(authenticationProvider());
		super.configure(auth);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}

	//here we are creating our own authenticatoin provider that is of type DAO so that we can fetch data from database
	//for this we have set 2 properties to the provider 1.userdetails Servide 2.password encodeer
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		//here userDetails service is interface we have implement it and autowire it
		provider.setUserDetailsService(userDetailsService);
		//Password Encoder to secure the password
		provider.setPasswordEncoder(passwordEncoder());
		return provider; 
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



}
