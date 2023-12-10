package com.unigoias.canil.domain.dto;

public class CaninoDTO {
	
	private Integer id;

	private String nome;
	
	private RacaDTO racaDTO;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public RacaDTO getRacaDTO() {
		return racaDTO;
	}

	public void setRacaDTO(RacaDTO racaDTO) {
		this.racaDTO = racaDTO;
	}
}
