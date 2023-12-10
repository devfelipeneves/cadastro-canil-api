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

import com.unigoias.canil.domain.dto.CaninoDTO;
import com.unigoias.canil.domain.service.CaninoService;

@RestController
@RequestMapping("/api/v1/caninos")
public class CaninoController {
	
	@Autowired
	private CaninoService service;

	@GetMapping
	public ResponseEntity<List<CaninoDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CaninoDTO> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.service.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<CaninoDTO> addCanino(@RequestBody CaninoDTO caninoDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(caninoDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CaninoDTO> updateCanino(@PathVariable Integer id,@RequestBody CaninoDTO caninoDTO) {
		return ResponseEntity.ok(this.service.update(id, caninoDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeCanino(@PathVariable Integer id) {
		this.service.remove(id);
		return ResponseEntity.noContent().build();
	}
}
