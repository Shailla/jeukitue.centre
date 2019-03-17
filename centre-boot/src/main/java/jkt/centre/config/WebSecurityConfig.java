package jkt.centre.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import jkt.centre.Constants.Role;
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
                .antMatchers("/gui/**").permitAll()												// Images et contenus statiques du site
        		.antMatchers("/rest/public/**").permitAll()										// Enregistrement nouvel utilisateur, ...
        		.antMatchers("/rest/auth/login").authenticated()								// Login
        		.antMatchers("/rest/admin/**").hasAuthority(Role.ROLE_ADMIN.name())				// API d'administration
        		.anyRequest().hasAnyRole(Role.ROLE_ADMIN.name(), Role.ROLE_USER.name())			// Tout le reste
                .and()
            .logout()
            	.logoutUrl("/rest/public/auth/logout")
                .permitAll()
            .and()
            	.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(jktAuthenticationProvider);
    }
}
