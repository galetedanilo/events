package com.devsuperior.events.repositoreis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.events.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
