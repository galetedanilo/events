package com.devsuperior.events.dto;

import java.time.LocalDate;

import com.devsuperior.events.entities.Event;

public class EventDTO {

	private Long id;
	private String name;
	private LocalDate date;
	private String url;
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
