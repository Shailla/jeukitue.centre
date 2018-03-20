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

	public static final int PAGE_SIZE_MAXIMUM = 100;
	
	@Autowired
	private EventDao eventDao;

	/**
	 * Get the all events list
	 * 
	 * @return http response
	 */
	@GetMapping("/admin/event/{pageSize}/{pageNumber}")
	public ResponseEntity<?> adminList(@PathVariable int pageSize, @PathVariable int pageNumber) {
		if(pageSize > PAGE_SIZE_MAXIMUM) {
			return new ResponseEntity<String>("Page size is limited to " + PAGE_SIZE_MAXIMUM + " elements", HttpStatus.BAD_REQUEST);
		}
		
		final List<EventDto> dtos = new ArrayList<>();
		final Iterable<Event> events = eventDao.findAll(PageRequest.of(pageNumber, pageSize));

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
