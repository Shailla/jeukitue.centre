package jkt.centre.dto;

import java.time.LocalDate;
import java.util.Map;

public class EventDto {
	private LocalDate horodatage;
	private String type;
	private Map<String, String> parameters;
	
	public EventDto(final LocalDate horodatage, final String type, final Map<String, String> parameters) {
		this.horodatage = horodatage;
		this.type = type;
		this.parameters = parameters;
	}
	
	public LocalDate getHorodatage() {
		return horodatage;
	}
	
	public String getType() {
		return type;
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}
}
