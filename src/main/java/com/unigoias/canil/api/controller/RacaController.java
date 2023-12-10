package com.unigoias.canil.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unigoias.canil.domain.dto.RacaDTO;
import com.unigoias.canil.domain.service.RacaService;

@RestController
@RequestMapping("/api/v1/racas")
public class RacaController {
	
	@Autowired
	private RacaService service;

	@GetMapping
	public ResponseEntity<List<RacaDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RacaDTO> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.service.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<RacaDTO> addRaca(@RequestBody RacaDTO racaDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(racaDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RacaDTO> updateRaca(@PathVariable Integer id,@RequestBody RacaDTO racaDTO) {
		return ResponseEntity.ok(this.service.update(id, racaDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeRaca(@PathVariable Integer id) {
		this.service.remove(id);
		return ResponseEntity.noContent().build();
	}
}
