package com.challenge.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challenge.models.AdnResponse;
import com.challenge.models.HistoricoAdn;
import com.challenge.repositories.HistoricalAdnRepository;

@Service
public class AdnService {

	@Autowired
	HistoricalAdnRepository historicalAdnRepository;

	public AdnResponse getAdnStats() {

		try {
			ArrayList<HistoricoAdn> adns = (ArrayList<HistoricoAdn>) historicalAdnRepository.findAll();

			float humans = 0;
			float mutants = 0;
			for (HistoricoAdn adn : adns) {

				if (adn.isMutant())
					mutants++;
				else
					humans++;
			}

			float res = 0;
			if (humans < 0) {
				res = mutants / humans;
			}else {
				res = mutants;
			}

			res = (float) (Math.round(res * 10) / 10d);

			AdnResponse adn = new AdnResponse();

			adn.setCount_human_dna((long) humans);
			adn.setCount_mutant_dna((long) mutants);
			adn.setRatio(res);

			return adn;

		} catch (Exception e) {

			return null;

		}
	}

	public HistoricoAdn saveAdn(HistoricoAdn historicoAdn) {

		try {
			boolean exitsAdn = historicalAdnRepository.existAdn(historicoAdn.getAdn());

			if (exitsAdn)
				return null;

			return historicalAdnRepository.save(historicoAdn);

		} catch (Exception e) {

			return null;

		}

	}

}
