package com.unigoias.canil.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unigoias.canil.domain.model.Canino;

@Repository
public interface CaninoRepository extends JpaRepository<Canino, Integer> {

	Optional<Canino> findByNomeAndRacaId(String nomeCanino, Integer racaId);
	List<Optional<Canino>> findByRacaId(Integer id);

}
