package com.devsuperior.events.repositories;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.events.entities.City;
import com.devsuperior.events.factories.CityFactory;
import com.devsuperior.events.repositoreis.CityRepository;

@DataJpaTest
public class CityRepositoryTests {

	@Autowired
	private CityRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	
	@BeforeEach
	void setUp() {
		existingId = 1L;
		nonExistingId = 100L;
	}
	
	@Test
	public void findByIdShoulReturnEmptyOptionalWhenIdDoesNotExisting() {
		
		Optional<City> entity = repository.findById(nonExistingId);
		
		Assertions.assertTrue(entity.isEmpty());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		
		Optional<City> entity = repository.findById(existingId);
		
		Assertions.assertTrue(entity.isPresent());
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementIdWhenIdNull() {
		
		City entity = CityFactory.createCity();
		
		entity.setId(null);
		
		entity = repository.save(entity);
		
		Assertions.assertNotNull(entity.getId());
	}
	
	@Test
	public void updateShouldThrowEntityNotFoundExceptionWhenIdDoesNotExisting() {
		
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			String name = "Campinas";
			
			City entity = repository.getById(nonExistingId);
			
			entity.setName(name);
			
			entity = repository.save(entity);
		});
	}
	
	@Test
	public void updateShouldUpdateCityWhenIdExists() {
		
		String name = "Campinas";
		
		City entity = repository.getById(existingId);
		
		entity.setName(name);
		
		entity =  repository.save(entity);
		
		Assertions.assertEquals(name, entity.getName());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExisting() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		
		Optional<City> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
	
}
