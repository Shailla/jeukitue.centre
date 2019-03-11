package jkt.centre.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import jkt.centre.service.JktAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JktAuthenticationProvider jktAuthenticationProvider;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.httpBasic()
        	.and()
        	.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")	// API d'administration
                .antMatchers("/gui/**").permitAll()						// Images et contenus statiques du site
                .anyRequest().authenticated()							// Tout le reste
                .and()
            .logout()
            	.logoutUrl("/auth/logout")
                .permitAll()
            .and()
            	.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(jktAuthenticationProvider);
    }
}
