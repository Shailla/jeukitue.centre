package jkt.centre.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jkt.centre.Constants;
import jkt.centre.SecurityUtils;
import jkt.centre.controller.dto.UserRegisterDto;
import jkt.centre.dao.ProfileDao;
import jkt.centre.dao.UserDao;
import jkt.centre.model.Profile;
import jkt.centre.model.User;
import jkt.centre.service.EventService;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private EventService eventService;

	/**
	 * Create a user
	 * Security : admin only can do this
	 * 
	 * @param login user identifier
	 * @param userData new data of the user, null for not-updated data
	 * @return http response
	 */
	@PostMapping("/rest/user/check-login-availability")
	public ResponseEntity<?> checkLoginAvailability(final String login, final Errors errors) {
		final boolean alreadyExists = userDao.existsByLogin(login);

		if(alreadyExists) {
			return new ResponseEntity<String>("Login not available", HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<String>("Login available", HttpStatus.OK);
		}
	}
	
	/**
	 * A user registers himself
	 * @param login user identifier
	 * @param userData new data of the user, null for not-updated data
	 * @return http response
	 */
	@PostMapping("/rest/public/user/register")
	public ResponseEntity<?> registerUser(final @Valid @RequestBody UserRegisterDto userData, final Errors errors) {
		final String login = userData.getLogin(); 

		// Check login unicity
		final boolean loginAlreadyExists = userDao.existsByLogin(login);

		if(loginAlreadyExists) {
			return new ResponseEntity<JktHttpError>(new JktHttpError(Messages.ERROR_METHOD_USER_ALREADY_EXISTS, "Login already exists"), HttpStatus.CONFLICT);
		}

		// Check mail unicity
		final boolean mailAlreadyExists = userDao.existsByMail(login);

		if(mailAlreadyExists) {
			return new ResponseEntity<JktHttpError>(new JktHttpError(Messages.ERROR_METHOD_USER_ALREADY_EXISTS, "Mail invalid"), HttpStatus.CONFLICT);
		}


		/* *********************************************** */
		// Action
		/* *********************************************** */

		final User user = userDao.save(new User());
		
		// Login
		user.setLogin(login);

		// Profiles
		final Optional<Profile> userProfile = profileDao.findById(Constants.PROFILE_USER);

		final Set<Profile> profiles = new HashSet<>();
		profiles.add(userProfile.get());
		user.setProfiles(profiles);

		// Mail (validated by annotation in UserDto)
		user.setMail(userData.getMail());

		// Update password
		final String passwordHash = SecurityUtils.hashWithSel(userData.getPassword(), login);
		user.setPasswordHash(passwordHash);
		
		// Enabled
		user.setEnabled(true);

		// Trace an history event
		eventService.userRegistration(login);

		return new ResponseEntity<String>("User registered", HttpStatus.OK);
	}
}
