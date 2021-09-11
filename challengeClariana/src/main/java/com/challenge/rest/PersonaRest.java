package com.challenge.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.dao.PersonaDAO;
import com.challenge.models.Persona;

@RestController
@RequestMapping("api/personas")
public class PersonaRest {
	
	@Autowired
	private PersonaDAO personaDAO;
	
	@PostMapping("/save")
	public void save(@RequestBody Persona persona) {
		personaDAO.save(persona);
	}

}
