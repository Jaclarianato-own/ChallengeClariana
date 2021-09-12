package com.challenge.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.challenge.bll.MutantBll;
import com.challenge.models.AdnResponse;
import com.challenge.models.HistoricoAdn;
import com.challenge.models.Mutant;
import com.challenge.services.AdnService;

@RestController
@RequestMapping("api/mutant")
public class MutantRest {

	@Autowired
	AdnService adnService;

	@PostMapping("/isMutant")
	public ResponseEntity<?> isMutant(@RequestBody Mutant mutant) {

		try {

			MutantBll mutantBll = new MutantBll();

			int respuesta = mutantBll.isMutant(mutant);

			if (respuesta != 1)
				throw new Exception(String.valueOf(respuesta));

			registerNewAdn(mutant.getDna(), true);

			return ResponseEntity.ok().build();

		} catch (Exception e) {
			
			if (e.getMessage().equals("0")) {
				registerNewAdn(mutant.getDna(), false);

			}

			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}

	}

	private void registerNewAdn(String[] dna, boolean isMutant) {

		try {

			String concat = "";
			for (String x : dna) {
				concat = concat + "." + x;
			}

			HistoricoAdn historicoAdn = new HistoricoAdn();

			historicoAdn.setAdn(concat);
			historicoAdn.setMutant(isMutant);

			adnService.saveAdn(historicoAdn);

		} catch (Exception e) {

		}

	}

	@GetMapping("/stats")
	public AdnResponse stats() {

		return adnService.getAdnStats();
	}

}
