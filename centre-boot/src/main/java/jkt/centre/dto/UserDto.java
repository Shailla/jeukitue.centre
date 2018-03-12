package jkt.centre.dto;

import java.util.Set;

public class UserDto {
	private Long id;
	private String login;
	private String mail;
	private Boolean enabled;
	private Set<String> profiles;
	
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
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
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<String> getProfiles() {
		return profiles;
	}
	
	public void setProfiles(final Set<String> profiles) {
		this.profiles = profiles;
	}
}
