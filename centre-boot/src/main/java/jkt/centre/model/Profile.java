package jkt.centre.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Profile {

	@Id
	@Column(name="PRF_NAME")
	private String name;
	
	@Column(name="PRF_DESCRIPTION")
	private String description;

	@ElementCollection
	@CollectionTable(name="ROLE", joinColumns=@JoinColumn(name="PRF_NAME"))
	@Column(name="ROL_NAME")
	@Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>(0);
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(final Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return String.format("Profile[id='%s']", name);
	}
}
