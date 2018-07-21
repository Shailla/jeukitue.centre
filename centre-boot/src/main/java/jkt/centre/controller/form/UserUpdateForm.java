package jkt.centre.controller.form;

import java.util.Set;

import javax.validation.constraints.Email;

public class UserUpdateForm {
	public static final String LOGIN = "login";
	public static final String MAIL = "mail";
	public static final String ENABLED = "enabled";
	public static final String PROFILES = "profiles";
	
	private String login;
	
	@Email
	private String mail;
	
	private Boolean enabled;
	private Set<String> profiles;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(final String login) {
		this.login = login;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(final String mail) {
		this.mail = mail;
	}
	
	public Boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(final Boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<String> getProfiles() {
		return profiles;
	}
	
	public void setProfiles(final Set<String> profiles) {
		this.profiles = profiles;
	}
}
