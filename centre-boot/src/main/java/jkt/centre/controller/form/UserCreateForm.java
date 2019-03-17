package jkt.centre.controller.form;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserCreateForm {
	public static final String LOGIN = "login";
	public static final String MAIL = "mail";
	public static final String ENABLED = "enabled";
	public static final String PROFILES = "profiles";
	
	@Email
	@NotNull
	private String mail;
	private Boolean enabled;
	private String password;
	private Set<String> profiles;
	
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
