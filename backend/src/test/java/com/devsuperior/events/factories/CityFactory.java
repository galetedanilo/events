package com.devsuperior.events.factories;

import com.devsuperior.events.entities.City;

public  class CityFactory {

	public static City createCity() {
		
		City city = new City(1L, "Indaiatuba");
		
		return city;
	}
}
