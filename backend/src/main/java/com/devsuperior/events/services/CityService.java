package com.devsuperior.events.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.events.dto.CityDTO;
import com.devsuperior.events.entities.City;
import com.devsuperior.events.repositoreis.CityRepository;
import com.devsuperior.events.services.exceptions.DatabaseException;
import com.devsuperior.events.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	private void convertCityToCityDTO(City entity, CityDTO dto) {
		
		entity.setName(dto.getName());
	}
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAllList() {
		
		List<City> list = new ArrayList<>();
		
		list = repository.findAll(Sort.by("name"));
		
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<CityDTO> findAllPage(Pageable pageable) {
		
		Page<City> entity = repository.findAll(pageable);
		
		return entity.map(x -> new CityDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CityDTO findById(Long id) {
		
		Optional<City> optional = repository.findById(id);
		
		City entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new CityDTO(entity);
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		
		City entity = new City();
		
		convertCityToCityDTO(entity, dto);
		
		entity = repository.save(entity);
		
		return new CityDTO(entity);		
	}
	
	@Transactional
	public CityDTO update(Long id, CityDTO dto) {
		
		try {
			
			City entity = repository.getById(id);
			
			entity.setName(dto.getName());
			
			entity = repository.save(entity);
			
			return new CityDTO(entity);
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
			throw new DatabaseException("Integrity violantion");
		}
	}
}
