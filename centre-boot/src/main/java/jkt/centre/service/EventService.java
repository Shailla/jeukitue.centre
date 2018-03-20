package jkt.centre.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jkt.centre.dao.EventDao;
import jkt.centre.model.Event;
import jkt.centre.model.Event.EventParameterType;
import jkt.centre.model.Event.EventType;

@Component
public class EventService {

	@Autowired
	private EventDao eventDao;

	private void createEvent(final EventType type, final Map<EventParameterType, String> parameters) {
		final Event event = new Event();
		event.setHorodatage(LocalDateTime.now());
		event.setType(type);

		if(parameters != null) {
			for(final Entry<EventParameterType, String> entry : parameters.entrySet()) {			
				event.getParameters().put(entry.getKey(), entry.getValue());
			}
		}

		eventDao.save(event);
	}

	public void adminUpdateUserPassword(final String adminLogin, final String login) {
		final Map<EventParameterType, String> parameters = new HashMap<>();
		parameters.put(EventParameterType.AdminLogin, login);
		parameters.put(EventParameterType.UserLogin, login);

		createEvent(EventType.AdminUpdateUserPassword, parameters);
	}
	
	public void adminUpdateUser(final String adminLogin, final String login) {
		final Map<EventParameterType, String> parameters = new HashMap<>();
		parameters.put(EventParameterType.AdminLogin, login);
		parameters.put(EventParameterType.UserLogin, login);

		createEvent(EventType.AdminUpdateUser, parameters);
	}
}
