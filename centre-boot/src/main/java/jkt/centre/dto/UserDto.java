package jkt.centre.dto;

import java.util.Set;

public class UserDto {
	Long id;
	String login;
	String mail;
	boolean enabled;
	Set<String> profiles;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<String> getProfiles() {
		return profiles;
	}
	
	public void setProfiles(Set<String> profiles) {
		this.profiles = profiles;
	}
}
