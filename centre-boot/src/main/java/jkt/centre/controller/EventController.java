package jkt.centre.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jkt.centre.dao.EventDao;
import jkt.centre.dto.EventDto;
import jkt.centre.model.Event;
import jkt.centre.model.Event.EventParameterType;

@RestController
public class EventController {

	public static int EVENT_PAGE_SIZE = 100;
	
	@Autowired
	private EventDao eventDao;

	/**
	 * Get the all events list
	 * 
	 * @return http response
	 */
	@GetMapping("/admin/event/list/{pageNumber}")
	public ResponseEntity<List<EventDto>> adminList(@PathVariable int pageNumber) {
		final List<EventDto> dtos = new ArrayList<>();
		final Iterable<Event> events = eventDao.findAll(new PageRequest(pageNumber, EVENT_PAGE_SIZE));

		for(final Event event : events) {
			final Map<String, String> parameters = new HashMap<>();
			
			for(final Entry<EventParameterType, String> paramEntry : event.getParameters().entrySet()) {
				parameters.put(paramEntry.getKey().name(), paramEntry.getValue());	
			}
			
			final EventDto dto = new EventDto(event.getHorodatage(), event.getType().name(), parameters);
			dtos.add(dto);
		}

		return new ResponseEntity<List<EventDto>>(dtos, HttpStatus.OK);
	}
}
