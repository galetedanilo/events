package com.devsuperior.events.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.events.dto.RoleDTO;
import com.devsuperior.events.dto.UserDTO;
import com.devsuperior.events.dto.UserInsertDTO;
import com.devsuperior.events.entities.Role;
import com.devsuperior.events.entities.User;
import com.devsuperior.events.repositoreis.RoleRepository;
import com.devsuperior.events.repositoreis.UserRepository;
import com.devsuperior.events.services.exceptions.DatabaseException;
import com.devsuperior.events.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
		
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private void copyDtoToEntity(UserInsertDTO dto, User entity) {
		
		entity.setEmail(dto.getEmail());
		
		entity.getRoles().clear();
		
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		for(RoleDTO roleDTO : dto.getRoles()) {
			Role role = roleRepository.getById(roleDTO.getId());
		
			entity.getRoles().add(role);
		}
	}
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		
		Page<User> page = repository.findAll(pageable);
		
		return page.map(entity -> new UserDTO(entity));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		
		Optional<User> optional = repository.findById(id);
		
		User entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		
		User entity = new User();
		
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO update(Long id, UserInsertDTO dto) {
		try {
			User entity = repository.getById(id);
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new UserDTO(entity);
		} catch(EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Integrity violation");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		
		return user;
	}
}
