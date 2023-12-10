package com.unigoias.canil.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unigoias.canil.domain.dto.RacaDTO;
import com.unigoias.canil.domain.exception.RacaExistsException;
import com.unigoias.canil.domain.exception.RacaIntegrityException;
import com.unigoias.canil.domain.exception.RacaNotFoundException;
import com.unigoias.canil.domain.model.Canino;
import com.unigoias.canil.domain.model.Raca;
import com.unigoias.canil.domain.repository.CaninoRepository;
import com.unigoias.canil.domain.repository.RacaRepository;

@Service
public class RacaService {
	
	@Autowired
	private RacaRepository repository;
	
	@Autowired
	private CaninoRepository caninoRepository;
	
	public RacaDTO toDTO(Raca raca) {
		RacaDTO racaDTO = new RacaDTO();
		racaDTO.setId(raca.getId());
		racaDTO.setNome(raca.getNome());
		return racaDTO;
	}
	
	public Raca toEntity(RacaDTO racaDTO) {
		Raca raca = new Raca();
		raca.setId(racaDTO.getId());
		raca.setNome(racaDTO.getNome());
		return raca;
	}
	
	public List<RacaDTO> toCollectionDTO(List<Raca> racas) {
		return racas.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public List<RacaDTO> findAll() {
		return toCollectionDTO(this.repository.findAll());
	}
	
	public RacaDTO getById(Integer id) {
		Optional<Raca> raca = this.repository.findById(id);
		
		if(raca.isPresent()) {
			return toDTO(raca.get());
		} else {
			throw new RacaNotFoundException("Raça não encontrada!");
		}
	}

	public RacaDTO save(RacaDTO racaDTO) {
		Optional<Raca> raca = repository.findByNome(racaDTO.getNome());
		
		if (raca.isPresent()) {
			throw new RacaExistsException("Raca já cadastrada!");
		} else {
			return this.toDTO(repository.save(toEntity(racaDTO)));
		}
	}
	
	public RacaDTO update(Integer id, RacaDTO racaDTO) {
		Optional<Raca> raca = this.repository.findById(id);
		
		if (raca.isPresent()) {
			RacaDTO racaAtualizada = toDTO(raca.get());
			racaAtualizada.setNome(racaDTO.getNome());
			return this.save(racaAtualizada);
		} else {
			throw new RacaNotFoundException("Raca não encontrada!");
		}
	}
	
	public void remove(Integer id) {
		Optional<Raca> raca = repository.findById(id);
		StringBuilder nomeCaninos = new StringBuilder();
		
		if (raca.isPresent()) {
			
			List<Optional<Canino>> caninos = this.caninoRepository.findByRacaId(raca.get().getId());
			
			for (Optional<Canino> canino : caninos) {
				if (canino.isPresent()) {
					nomeCaninos.append(canino.get().getNome() + ",");
				}
			}
			
			if (nomeCaninos.length() > 0) {
				nomeCaninos.setLength(nomeCaninos.length() - 1);
				nomeCaninos.append(".");
				throw new RacaIntegrityException("A Raça " + raca.get().getNome() + " Está vinculada ao(s) Canino(s): " + nomeCaninos);
			}
			
			this.repository.delete(raca.get());
		} else {
			throw new RacaNotFoundException("Raça não encontrada!");
		}
	}
	
}
