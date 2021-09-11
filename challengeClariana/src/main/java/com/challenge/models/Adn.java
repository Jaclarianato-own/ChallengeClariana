package com.challenge.models;

import javax.persistence.*;


@Entity
@Table(name = "adn")
public class Adn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false) 
	private Long id;
	private Long count_mutant_dna;
	private Long count_human_dna;
	private float ratio;
	
	
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
	 * @return the count_mutant_dna
	 */
	public Long getCount_mutant_dna() {
		return count_mutant_dna;
	}
	/**
	 * @param count_mutant_dna the count_mutant_dna to set
	 */
	public void setCount_mutant_dna(Long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	/**
	 * @return the count_human_dna
	 */
	public Long getCount_human_dna() {
		return count_human_dna;
	}
	/**
	 * @param count_human_dna the count_human_dna to set
	 */
	public void setCount_human_dna(Long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	/**
	 * @return the ratio
	 */
	public float getRatio() {
		return ratio;
	}
	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(float ratio) {
		this.ratio = ratio;	
		
	}
	
	
}
