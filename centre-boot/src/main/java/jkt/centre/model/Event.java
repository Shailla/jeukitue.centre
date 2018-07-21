package jkt.centre.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Event {

	public enum EventType {
		/** User updated by an administrator */
		AdminUpdateUser,
		
		/** User's password updated by an administrator */
		AdminUpdateUserPassword;
	}

	public enum EventParameterType {
		/** Login of an administrator */
		AdminLogin,
		
		/** Login of a user */
		UserLogin;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVT_ID")
	private long id;

	@Column(name="EVT_TYPE", nullable=false)
	@Enumerated(EnumType.STRING)
	private EventType type;

	@Column(name="EVT_HORODATAGE", nullable=false)
	private LocalDateTime horodatage;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
    @CollectionTable(name="EVENT_PARAMETER", joinColumns=@JoinColumn(name="EVT_ID"))
    @MapKeyColumn(name="EVP_TYPE")
	@MapKeyEnumerated(EnumType.STRING)
    @Column(name="EVP_VALUE")
	private Map<EventParameterType, String> parameters = new HashMap<>(0);

	public Event() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public LocalDateTime getHorodatage() {
		return horodatage;
	}

	public void setHorodatage(LocalDateTime horodatage) {
		this.horodatage = horodatage;
	}

	public Map<EventParameterType, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<EventParameterType, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return String.format("History[id=%d, horodatage=%s, type='%s']", id, horodatage, type);
	}
}
