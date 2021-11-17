package com.icesi.edu.Stiven.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.icesi.edu.Stiven.model.person.UserType;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private MyCustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeRequests()
		// .antMatchers("/**").permitAll()
		.antMatchers("/login/**").permitAll().antMatchers("/logout/**").permitAll()
		// persons
		.antMatchers("/person*").permitAll().antMatchers("/persons/addPersons/**")
		.hasRole(UserType.Administrador.toString()).antMatchers("/persons/updatePersons/**")
		.hasRole(UserType.Administrador.toString())
		// provinceAddress
		.antMatchers("/provinceAddress*").permitAll().antMatchers("/provinceAddress/addAddress/**")
		.hasRole(UserType.Administrador.toString()).antMatchers("/provinceAddress/updateAddress/**")
		.hasRole(UserType.Administrador.toString())
		// business address
		.antMatchers("/businessAddress*").permitAll().antMatchers("/businessAddress/addAddress/**")
		.hasRole(UserType.Operador.toString()).antMatchers("/businessAddress/updateAddress/**")
		.hasRole(UserType.Operador.toString())
		// business contact
		.antMatchers("/businessentitycontact*").permitAll().antMatchers("/businessentitycontact/index/**").permitAll()
		.antMatchers("/documents/add/**").hasRole(UserType.Operador.toString())
		.antMatchers("/documents/edit/**").hasRole(UserType.Operador.toString()).antMatchers("/**")
		
		.authenticated().anyRequest().permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/")
		.failureUrl("/login?error").and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
}