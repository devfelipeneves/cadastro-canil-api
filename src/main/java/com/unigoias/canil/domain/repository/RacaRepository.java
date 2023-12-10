package com.unigoias.canil.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unigoias.canil.domain.model.Raca;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Integer> {

	public Optional<Raca> findByNome(String nome);
}
