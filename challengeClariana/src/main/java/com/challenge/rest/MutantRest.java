package com.challenge.rest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.challenge.bll.MutantBll;
import com.challenge.models.Adn;
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

            updateStat(true);
            
            registerNewAdn(mutant.getDna(), true);

			return ResponseEntity.ok().build();

		} catch (Exception e) {
			if(e.getMessage().equals("0")) {
				registerNewAdn(mutant.getDna(), false);
				updateStat(false);
			}
			
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}

	}
	
	private void registerNewAdn(String[] dna, boolean isMutant) {
		
		try {
			
			String concat = "";
			for(String x : dna) {
				concat = concat+"."+ x;
			}
			
			HistoricoAdn historicoAdn = new HistoricoAdn();
			
			historicoAdn.setAdn(concat);	
			historicoAdn.setMutant(isMutant);
			
			adnService.saveAdn(historicoAdn);
			
		}catch(Exception e) {
			
		}
		
	}

	private void updateStat(boolean isMutant) {
		
		try {
			Optional<Adn> adnO;

			Adn adn = new Adn();
			adnO = adnService.getAdnStat(1);
			adn = adnO.get();
			
			if(isMutant) {
				adn.setCount_mutant_dna(adn.getCount_mutant_dna() + 1);
				if (adn.getCount_human_dna() != 0) {
					
					float mutant = adn.getCount_human_dna();
					float human = adn.getCount_mutant_dna() + 1;		
					
					float res = mutant/human;
					
					res = (float) (Math.round(res * 10) / 10d);
					
					adn.setRatio(res);
				}
					
					
			}else {
				adn.setCount_human_dna(adn.getCount_human_dna() + 1);
				if(adn.getCount_human_dna() != 0) {
					float mutant = adn.getCount_mutant_dna();
					float human = adn.getCount_human_dna()+1;		
					
					float res = mutant/human;
					
					res = (float) (Math.round(res * 10) / 10d);
					
					adn.setRatio(res);
				}
				
			}
			
			
			adnService.SaveStat(adn);
		}catch(Exception e) {
			
		}
		
	}

	@GetMapping("/stats")
	public AdnResponse stats(){
		return adnService.getAdnStats();
	}

}
