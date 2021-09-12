package com.challenge.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challenge.models.Adn;
import com.challenge.models.AdnResponse;
import com.challenge.models.HistoricoAdn;
import com.challenge.repositories.AdnRepository;
import com.challenge.repositories.HistoricalAdnRepository;

@Service
public class AdnService {

	@Autowired
	AdnRepository adnRepository;
	
	@Autowired
	HistoricalAdnRepository historicalAdnRepository;

	public Optional<Adn> getAdnStat(long i) {
		return adnRepository.findById((long) i);
	}

	public AdnResponse getAdnStats() {
		
		try {
			ArrayList<Adn> aux = (ArrayList<Adn>) adnRepository.findAll();

			AdnResponse adn = new AdnResponse();

			adn.setCount_human_dna(aux.get(0).getCount_human_dna());
			adn.setCount_mutant_dna(aux.get(0).getCount_mutant_dna());
			adn.setRatio(aux.get(0).getRatio());
			
			return adn;
		}catch(Exception e){
			
			return null;
			
		}
	}

	public Adn SaveStat(Adn adn) {
		
		try {
			
			return adnRepository.save(adn);
			
		}catch(Exception e) {
			return null;
		}		
	}
	
	public HistoricoAdn saveAdn(HistoricoAdn historicoAdn) {
		
		try {
			boolean exitsAdn = historicalAdnRepository.existAdn(historicoAdn.getAdn());
			
			if(exitsAdn) return null;
			
			return historicalAdnRepository.save(historicoAdn);
			
		}catch (Exception e) {
			
			return null;
			
		}
		
		
		
	}

}
