package jkt.centre.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jkt.centre.dao.UserDao;
import jkt.centre.dto.UserDto;
import jkt.centre.model.Profile;
import jkt.centre.model.User;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/admin/user/list", method=RequestMethod.GET)
	public List<UserDto> list() {
		List<UserDto> dtos = new ArrayList<>();
		Iterable<User> users = userDao.findAll();
		
		for(User user : users) {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setLogin(user.getLogin());
			dto.setMail(user.getMail());
			dto.setEnabled(user.isEnabled());
			
			Set<String> profilesStr = new HashSet<>();
			Set<Profile> profiles = user.getProfiles();
			
			for(Profile profile : profiles) {
				profilesStr.add(profile.getName());
			}
			
			dto.setProfiles(profilesStr);
			
			dtos.add(dto);
		}
		
		return dtos;
	}
}
