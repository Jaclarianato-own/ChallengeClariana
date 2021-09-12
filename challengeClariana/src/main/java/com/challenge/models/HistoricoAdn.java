package com.challenge.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "historicoAdn")
public class HistoricoAdn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false) 
	private Long id;
	@Column(nullable = false, length = 1337)
	private String adn;
	
	private boolean isMutant;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the adn
	 */
	public String getAdn() {
		return adn;
	}

	/**
	 * @param adn the adn to set
	 */
	public void setAdn(String adn) {
		this.adn = adn;
	}

	/**
	 * @return the isMutant
	 */
	public boolean isMutant() {
		return isMutant;
	}

	/**
	 * @param isMutant the isMutant to set
	 */
	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	
	
}
