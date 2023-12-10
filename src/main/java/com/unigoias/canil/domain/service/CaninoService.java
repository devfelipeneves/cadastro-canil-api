package com.unigoias.canil.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unigoias.canil.domain.dto.CaninoDTO;
import com.unigoias.canil.domain.exception.CaninoExistsException;
import com.unigoias.canil.domain.exception.CaninoNotFoundException;
import com.unigoias.canil.domain.exception.RacaNotFoundException;
import com.unigoias.canil.domain.model.Canino;
import com.unigoias.canil.domain.model.Raca;
import com.unigoias.canil.domain.repository.CaninoRepository;
import com.unigoias.canil.domain.repository.RacaRepository;

@Service
public class CaninoService {
	
	@Autowired
	private CaninoRepository repository;
	
	@Autowired
	private RacaRepository racaRepository;
	
	@Autowired
	private RacaService racaService;
	
	public CaninoDTO toDTO(Canino canino) {
		CaninoDTO caninoDTO = new CaninoDTO();
		caninoDTO.setId(canino.getId());
		caninoDTO.setNome(canino.getNome());
		caninoDTO.setRacaDTO(racaService.toDTO(canino.getRaca()));
		return caninoDTO;
	}
	
	public Canino toEntity(CaninoDTO caninoDTO) {
		Canino canino = new Canino();
		canino.setId(caninoDTO.getId());
		canino.setNome(caninoDTO.getNome());
		canino.setRaca(racaService.toEntity(caninoDTO.getRacaDTO()));
		return canino;
	}
	
	private List<CaninoDTO> toCollectionDTO(List<Canino> caninos) {
		return caninos.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public List<CaninoDTO> findAll() {
		return toCollectionDTO(this.repository.findAll());
	}
	
	public CaninoDTO getById(Integer id) {
		Optional<Canino> canino = this.repository.findById(id);
		
		if(canino.isPresent()) {
			return toDTO(canino.get());
		} else {
			throw new CaninoNotFoundException("Canino não encontrado");
		}
	}

	public CaninoDTO save(CaninoDTO caninoDTO) {
		
		Optional<Canino> canino = this.repository.findByNomeAndRacaId(caninoDTO.getNome(), caninoDTO.getRacaDTO().getId());
		Optional<Raca> raca = this.racaRepository.findById(caninoDTO.getRacaDTO().getId());
		
		if (!raca.isPresent()) {
			throw new RacaNotFoundException("Raça não encontrada!");
		}
		
		if (canino.isPresent()) {
			throw new CaninoExistsException("Canino já cadastrado!");
		} else {
			caninoDTO.setRacaDTO(racaService.toDTO(raca.get()));
			return this.toDTO(repository.save(toEntity(caninoDTO)));
		}
	}
	
	public CaninoDTO update(Integer id, CaninoDTO caninoDTO) {
		Optional<Canino> canino = this.repository.findById(id);
		Optional<Raca> raca = this.racaRepository.findById(caninoDTO.getRacaDTO().getId());
		
		if (!raca.isPresent()) {
			throw new RacaNotFoundException("Raça não encontrada!");
		}
		
		if (canino.isPresent()) {
			CaninoDTO caninoAtualizado = toDTO(canino.get());
			caninoAtualizado.setNome(caninoDTO.getNome());
			caninoAtualizado.setRacaDTO(racaService.toDTO(raca.get()));
			
			return this.save(caninoAtualizado);
		} else {
			throw new CaninoNotFoundException("Canino não encontrado!");
		}
	}
	
	public void remove(Integer id) {
		
		Optional<Canino> canino = this.repository.findById(id);
		
		if (canino.isPresent()) {
			this.repository.delete(canino.get());
		} else {
			throw new CaninoNotFoundException("Canino não encontrado!");
		}
		
	}

}
