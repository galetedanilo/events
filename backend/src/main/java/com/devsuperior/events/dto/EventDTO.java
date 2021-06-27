package com.devsuperior.events.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.devsuperior.events.entities.Event;

public class EventDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo requerido")
	@Size(max = 50, min = 3, message = "O campo nome deve ter entre 3 e 50 caracteres!")
	private String name;
	
	@Future(message = "A data do evento não pode ser passada")
	private LocalDate date;
	
	@NotBlank(message = "Campo requerido")
	@Size(max = 50, min = 3, message =  "O campo url deve ter entre 3 e 50 caracteres!")
	private String url;
	
	@NotNull(message = "Campo requerido")
	private Long cityId;
	
	public EventDTO() {
		
	}

	public EventDTO(Long id, String name, LocalDate date, String url, Long cityId) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.url = url;
		this.cityId = cityId;
	}
	
	public EventDTO(Event entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.date = entity.getDate();
		this.url = entity.getUrl();
		this.cityId = entity.getCity().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
}
