package com.greatlearning.rbanewfrontend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.rbanewfrontend.service.FetchPrincipalFromDb;

@Configuration
@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter{

	@Override  //Authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myGlAuthPro());
		
	}
	
	@Bean
	public DaoAuthenticationProvider myGlAuthPro() {
		DaoAuthenticationProvider dap=new DaoAuthenticationProvider();
		dap.setUserDetailsService(myGlUserDetails());
		dap.setPasswordEncoder(myGlPassEnc());
		
		return dap;
	}

	@Bean
	public PasswordEncoder myGlPassEnc() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService myGlUserDetails() //Decorated UserDetails
	{
		return new FetchPrincipalFromDb();
	}


	@Override //Authorisation
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/","/books/save","/books/showFormForAdd","/books/403").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/books/showFormForUpdate","/books/delete").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().loginProcessingUrl("/login").successForwardUrl("/books/list").permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/books/403")
        .and()
        .cors().and().csrf().disable();

	}
}
