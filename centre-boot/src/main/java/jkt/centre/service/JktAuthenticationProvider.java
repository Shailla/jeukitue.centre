package jkt.centre.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jkt.centre.SecurityUtils;
import jkt.centre.dao.UserDao;
import jkt.centre.model.Profile;
import jkt.centre.model.Role;
import jkt.centre.model.User;

@Component
public class JktAuthenticationProvider implements AuthenticationProvider {
	static final Logger LOGGER = LoggerFactory.getLogger(JktAuthenticationProvider.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String authLogin = authentication.getName();

		// Find the user
		final User user = userDao.findByLogin(authLogin);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		// Check if user is enabled
		if(!user.isEnabled()) {
			throw new DisabledException("User not active");
		}
		
		// Check password
		if(user.getPasswordHash() == null) {
			LOGGER.error("Should never happen, this user has no credentitial set in database");
			throw new AuthenticationCredentialsNotFoundException("Should never happen, this user has no credentitial set in database");
		}
		
		final String authPassword = authentication.getCredentials().toString();
		
		if(!SecurityUtils.checkHashWithSel(authPassword, authLogin, user.getPasswordHash())) {
			throw new BadCredentialsException("Bad password");
		}
		
		// Collect all user's roles
		final Set<GrantedAuthority> authorities = new HashSet<>();
		final Set<Profile> profiles = user.getProfiles();
		
		for(final Profile profile : profiles) {
			final Set<Role> roles = profile.getRoles();
			
			for(final Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.name()));
			}
		}
		
		return new UsernamePasswordAuthenticationToken(authLogin, authPassword, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
