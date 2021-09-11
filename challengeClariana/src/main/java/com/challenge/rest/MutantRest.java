package com.challenge.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.bll.MutantBll;
import com.challenge.models.Mutant;


@RestController
@RequestMapping("api/mutant")
public class MutantRest {

	@PostMapping("/isMutant")
	public ResponseEntity<?> isMutant(@RequestBody Mutant mutant) {
		
		try {
			boolean respuesta = MutantBll.isMutant(mutant);
			
			if(!respuesta) throw new Exception();
			
			return ResponseEntity.ok().build();
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			
		}
		
	}
	
}
