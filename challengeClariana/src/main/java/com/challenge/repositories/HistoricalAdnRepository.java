package com.challenge.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.models.HistoricoAdn;

public interface HistoricalAdnRepository extends CrudRepository<HistoricoAdn, Long>{
	
	@Query("SELECT COUNT(c) > 0 FROM HistoricoAdn c WHERE c.adn = :adn")
	Boolean existAdn(@Param("adn") String adn);

}
