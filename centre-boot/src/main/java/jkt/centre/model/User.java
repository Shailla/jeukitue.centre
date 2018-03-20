package jkt.centre.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USR_ID")
	private long id;
	
	@Column(name="USR_LOGIN", nullable=false)
	private String login;
	
	@Column(name="USR_PASSWORD_HASH", nullable=false)
	private String passwordHash;
	
	@Column(name="USR_MAIL", nullable=false)
	private String mail;
	
	@Column(name="USR_UUID_REGISTRATION", nullable=true)
	private String uuidRegistration;
	
	@Column(name="USR_ENABLED", nullable=false)
	private boolean enabled;

	@OneToMany
	@JoinTable(name="PROFILE_OF_USER", joinColumns=@JoinColumn(name="USR_ID"), inverseJoinColumns=@JoinColumn(name="PRF_NAME"))
    private Set<Profile> profiles = new HashSet<>(0);

	public User() {
	}

	public User(long id, String login, String passwordHash, String mail, String uuidRegistration) {
		this.id = id;
		this.login = login;
		this.passwordHash = passwordHash;
		this.mail = mail;
		this.uuidRegistration = uuidRegistration;
	}

	public User(long id, String login, String passwordHash, String mail, String uuidRegistration, Set<Profile> profiles) {
		this.id = id;
		this.login = login;
		this.passwordHash = passwordHash;
		this.mail = mail;
		this.profiles = profiles;
		this.uuidRegistration = uuidRegistration;
	}


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
    public String getUuidRegistration() {
        return this.uuidRegistration;
    }

    public void setUuidRegistration(String uuidRegistration) {
        this.uuidRegistration = uuidRegistration;
    }

	public Set<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}

	public boolean isEnabled() {
	    return enabled;
	}

	public void setEnabled(boolean enabled) {
	    this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		return String.format("User[id=%d, login='%s']", id, login);
	}
}
