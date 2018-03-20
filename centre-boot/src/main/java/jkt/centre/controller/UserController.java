package jkt.centre.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jkt.centre.SecurityUtils;
import jkt.centre.controller.form.UpdatePasswordForm;
import jkt.centre.dao.ProfileDao;
import jkt.centre.dao.UserDao;
import jkt.centre.dto.UserDto;
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
	 * Get the all users list
	 * 
	 * @return all users information
	 */
	@GetMapping("/admin/user")
	public ResponseEntity<List<UserDto>> adminLoadUsers() {
		final List<UserDto> dtos = new ArrayList<>();
		final Iterable<User> users = userDao.findAll();

		for(final User user : users) {
			final UserDto dto = new UserDto();
			dto.setLogin(user.getLogin());
			dto.setMail(user.getMail());
			dto.setEnabled(user.isEnabled());

			final Set<String> profilesStr = new HashSet<>();
			final Set<Profile> profiles = user.getProfiles();

			for(final Profile profile : profiles) {
				profilesStr.add(profile.getName());
			}

			dto.setProfiles(profilesStr);

			dtos.add(dto);
		}

		return new ResponseEntity<List<UserDto>>(dtos, HttpStatus.OK);
	}

	/**
	 * Get one user
	 * 
	 * @param login user identifier 
	 * @return one user information
	 */
	@GetMapping("/admin/user/{login}")
	public ResponseEntity<?> admiLoadUser(final @PathVariable String login) {
		final User user = userDao.findByLogin(login);

		if(user == null) {
			return new ResponseEntity<String>("No user found with this login", HttpStatus.NOT_FOUND);
		}

		final UserDto dto = new UserDto();
		dto.setLogin(user.getLogin());
		dto.setMail(user.getMail());
		dto.setEnabled(user.isEnabled());

		final Set<String> profilesStr = new HashSet<>();
		final Set<Profile> profiles = user.getProfiles();

		for(final Profile profile : profiles) {
			profilesStr.add(profile.getName());
		}

		dto.setProfiles(profilesStr);

		return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
	}

	/**
	 * Update an existing user
	 * Security : admin only can do this
	 * 
	 * @param login user identifier
	 * @param userData new data of the user, null for not-updated data
	 * @return http response
	 */
	@PutMapping("/admin/user/{login}")
	public ResponseEntity<String> adminUpdateUser(final @PathVariable String login, final @Valid @RequestBody UserDto userData, final Errors errors, final Principal principal) {
		final User user = userDao.findByLogin(login);

		if(user == null) {
			return new ResponseEntity<String>("No user found with this login", HttpStatus.NOT_FOUND);
		}


		/* *********************************************** */
		// Validation
		/* *********************************************** */

		// Profiles
		final Set<String> newProfiles = userData.getProfiles();

		if(newProfiles != null) {
			for(final String newProfile : newProfiles) {
				if(!profileDao.existsById(newProfile)) {
					errors.rejectValue(UserDto.PROFILES, Messages.MSG_VALID_PROFILE_UNKNWON);
					break;
				}
			}
		}

		if(errors.hasErrors()) {
			return new ResponseEntity<String>("Errors found in form", HttpStatus.CONFLICT);
		}


		/* *********************************************** */
		// Action
		/* *********************************************** */

		// Profiles
		if(newProfiles != null) {
			user.getProfiles().clear();
			
			for(final String newProfile : newProfiles) {
				final Optional<Profile> profileEntity = profileDao.findById(newProfile);
				profileEntity.ifPresent(theProfile -> user.getProfiles().add(theProfile));
			}
		}

		// Mail (validated by annotation in UserDto)
		final String newMail = userData.getMail();

		if(newMail != null) {
			user.setMail(newMail);
		}

		// Enabled
		final Boolean enabled = userData.isEnabled();

		if(enabled != null) {
			user.setEnabled(enabled);
		}

		// Trace an history event
		eventService.adminUpdateUser(principal.getName(), login);

		return new ResponseEntity<String>("User updated", HttpStatus.OK);
	}

	/**
	 * Update the password of a user, it's an admin operation no need to know the old password
	 * Security : admin only can do this
	 * 
	 * @param login user identifier
	 * @param newPassword the new password value
	 * @return
	 */
	@PutMapping("/admin/user/{login}/password")
	public ResponseEntity<String> adminUpdatePasswordUser(final @PathVariable String login, final @RequestBody UpdatePasswordForm updatePasswordForm, final Errors errors, final Principal principal) {
		final User user = userDao.findByLogin(login);

		if(user == null) {
			return new ResponseEntity<String>("No user found with this login", HttpStatus.NOT_FOUND);
		}


		/* *********************************************** */
		// Validation
		/* *********************************************** */

		final String newPassword = updatePasswordForm.getPassword();
		
		if(newPassword == null) {
			return new ResponseEntity<String>("Missing new password", HttpStatus.PRECONDITION_FAILED);
		}
		
		if(!SecurityUtils.checkPasswordComplexity(newPassword)) {
			return new ResponseEntity<String>("New password refused (bad format or low complexity)", HttpStatus.PRECONDITION_FAILED);
		}


		/* *********************************************** */
		// Action
		/* *********************************************** */

		// Update password
		final String passwordHash = SecurityUtils.hashWithSel(newPassword, login);
		user.setPasswordHash(passwordHash);

		// Trace an history event
		eventService.adminUpdateUserPassword(principal.getName(), login);

		return new ResponseEntity<String>("User's password updated", HttpStatus.OK);
	}
}
