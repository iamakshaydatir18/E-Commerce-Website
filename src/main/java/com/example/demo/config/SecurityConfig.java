package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Autowired
	private UserDetailsService userdetailservice;
	
	@Bean
	AuthenticationProvider authenticationprovider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userdetailservice);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * 
	 * @Bean public UserDetailsService jdbcUserDetailsService(DataSource dataSource)
	 * { return new JdbcUserDetailsManager(dataSource); }
	 */

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * System.out.println("Inside security!!!!!");
	 * System.out.println("Data Source is ----"+ datasource);
	 * auth.jdbcAuthentication().dataSource(datasource)
	 * .usersByUsernameQuery("SELECT emailId, password, enabled FROM users WHERE emailId=?"
	 * )
	 * .authoritiesByUsernameQuery("SELECT emailId, authorities FROM authorities WHERE emailId=?"
	 * ) .passwordEncoder(passwordEncoder()); }
	 */

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * 
	 * 
	 * auth.inMemoryAuthentication().withUser("akshaydatir123@gmail.com").password(
	 * passwordEncoder().encode("1234")) .roles("ADMIN");
	 * auth.inMemoryAuthentication().withUser("sanket123@gmail.com").password(
	 * passwordEncoder().encode("1234")) .roles("USER");
	 * auth.inMemoryAuthentication().withUser("pranav123@gmail.com").password(
	 * passwordEncoder().encode("1234")) .roles("USER");
	 * 
	 * }
	 */

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/bootstrap/**");
		web.ignoring().antMatchers("/js/*");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests(requests -> requests
				.antMatchers("/index/**", "/index1/**", "/login", "/cart/**", "/img", "/images/**", "/resource/**",
						"/customer/**", "/aboutus", "/contactus", "/hello")
				.permitAll().antMatchers("/get*/**").hasAnyRole("ADMIN", "USER").antMatchers("/admin*/**")
				.hasRole("ADMIN").anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login_form")
						.usernameParameter("j_username").passwordParameter("j_password").failureUrl("/login?error=true")
						.defaultSuccessUrl("/index"))
				.logout(logout -> logout.logoutUrl("/logout").deleteCookies("JSESSIONID")
						.logoutSuccessUrl("/login?logout=true"))
				.csrf(csrf -> csrf.disable());

	}
}
