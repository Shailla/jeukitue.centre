package jkt.centre.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class EventDto {
	private LocalDateTime horodatage;
	private String type;
	private Map<String, String> parameters;
	
	public EventDto(final LocalDateTime horodatage, final String type, final Map<String, String> parameters) {
		this.horodatage = horodatage;
		this.type = type;
		this.parameters = parameters;
	}
	
	public LocalDateTime getHorodatage() {
		return horodatage;
	}
	
	public String getType() {
		return type;
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}
}
