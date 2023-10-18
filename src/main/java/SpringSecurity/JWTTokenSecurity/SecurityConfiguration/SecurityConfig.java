package SpringSecurity.JWTTokenSecurity.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import SpringSecurity.JWTTokenSecurity.Filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	JwtFilter jwtFilter;
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeRequests().antMatchers("/principal").hasRole("principal")
		.antMatchers("/teacher").hasAnyRole("teacher","principal").antMatchers("/student")
		.hasAnyRole("student","teacher","principal").and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic().and().addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
	}
	@Bean
	public AuthenticationManager AuthenticationManagerBean() throws Exception
	{
		return  super.authenticationManagerBean();
	}
}
