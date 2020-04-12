package com.ravali.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguratioin extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(
				"/registration**",
				"/js/**",
				"/css/**",
				"/img/**",
				"/webjars/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().defaultSuccessUrl("/loginSuccess")
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logoutSuccess")
		.permitAll();
		http.csrf().disable();
	}

	//login details from the database starts here
	//to access the login related details from the dataase we hav to override authenticationMangaer builder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//here we have so pass our own authentication prodiver
		auth.authenticationProvider(authenticationProvider());
		super.configure(auth);
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
