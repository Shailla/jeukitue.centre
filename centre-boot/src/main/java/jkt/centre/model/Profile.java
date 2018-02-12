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
public class Profile {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRF_NAME")
	private String name;

	@OneToMany
	@JoinTable(name="ROLE_OF_PROFILE", joinColumns=@JoinColumn(name="PRF_NAME"), inverseJoinColumns=@JoinColumn(name="ROL_NAME"))
    private Set<Role> roles = new HashSet<>(0);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Profile[id='%s']",
				name);
	}
}
