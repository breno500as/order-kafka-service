package com.br.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.order.document.Event;
import com.br.order.dto.EventFilters;
import com.br.order.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping
	public Event findByFilters(EventFilters filters) {
		return this.eventService.findByFilters(filters);
	}

	@GetMapping("all")
	public List<Event> findAll() {
		return this.eventService.findAll();
	}
}
