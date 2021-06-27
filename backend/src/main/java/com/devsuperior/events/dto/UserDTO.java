package com.devsuperior.events.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.devsuperior.events.entities.User;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "O campo e-mail é obrigatório!")
	@Email(message = "Informe um e-mail valido!")
	private String email;
	
	private Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO() {
		
	}
	
	public UserDTO(Long id, String email) {
		this.id = id;
		this.email = email;
	}
	
	public UserDTO(User entity) {
		this.id = entity.getId();
		this.email = entity.getEmail();
		
		entity.getRoles().forEach(roles -> this.roles.add(new RoleDTO(roles)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

}
