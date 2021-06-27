package com.devsuperior.events.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.events.dto.EventDTO;
import com.devsuperior.events.entities.City;
import com.devsuperior.events.entities.Event;
import com.devsuperior.events.repositoreis.EventRepository;
import com.devsuperior.events.services.exceptions.DatabaseException;
import com.devsuperior.events.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	private void convertDtoToEntity(Event entity, EventDTO dto) {
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity(new City(dto.getCityId(),null));
	}
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable) {
		
		Page<Event> page = repository.findAll(pageable);
		
		return page.map(x -> new EventDTO(x));
	}
	
	@Transactional(readOnly = true)
	public EventDTO findById(Long id) {
		
		Optional<Event> result = repository.findById(id);
		
		Event entity = result.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new EventDTO(entity);
	}
	
	@Transactional
	public EventDTO insert(EventDTO dto) {
		
		Event entity = new Event();
		
		convertDtoToEntity(entity, dto);
		
		entity = repository.save(entity);
		
		return new EventDTO(entity);
	}
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		
		try {
			Event entity = repository.getById(id);
			
			convertDtoToEntity(entity, dto);
			
			entity = repository.save(entity);
			
			return new EventDTO(entity);
		} catch(EntityNotFoundException error) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException error) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException error) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
}
