package com.devsuperior.events.repositoreis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.events.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
