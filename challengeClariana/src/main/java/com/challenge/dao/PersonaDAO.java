package com.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.models.Persona;

public interface PersonaDAO extends JpaRepository<Persona,Integer>{

}
