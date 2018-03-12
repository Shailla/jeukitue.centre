package jkt.centre.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import jkt.centre.SecurityUtils;
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
	private EventService eventService;

	/**
	 * Get the all users list
	 * @return
	 */
	@GetMapping("/admin/user/list")
	public ResponseEntity<List<UserDto>> adminList() {
		final List<UserDto> dtos = new ArrayList<>();
		final Iterable<User> users = userDao.findAll();

		for(final User user : users) {
			final UserDto dto = new UserDto();
			dto.setId(user.getId());
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
	 * Update an existing user
	 * Security : admin only can do this
	 * 
	 * @param login user identifier
	 * @param userData new data of the user, null for not-updated data
	 * @return http response
	 */
	@PutMapping("/admin/user/{login}")
	public ResponseEntity<String> adminUpdate(final @PathVariable String login, final UserDto userData, final Principal principal) {
		final User user = userDao.findByLogin(login);

		if(user == null) {
			return new ResponseEntity<String>("Not user with this login", HttpStatus.NOT_FOUND);
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
	public ResponseEntity<String> adminUpdatePassword(final @PathVariable String login, final String newPassword, final Principal principal) {
		final User user = userDao.findByLogin(login);

		if(user == null) {
			return new ResponseEntity<String>("Not user with this login", HttpStatus.NOT_FOUND);
		}

		// Update password
		final String passwordHash = SecurityUtils.hashWithSel(newPassword, login);
		user.setPasswordHash(passwordHash);

		// Trace an history event
		eventService.adminUpdateUserPassword(principal.getName(), login);
		
		return new ResponseEntity<String>("User's password updated", HttpStatus.OK);
	}
}
