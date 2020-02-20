package br.com.codeProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codeProject.model.Cidades;

@Repository
public interface CidadesRepository extends JpaRepository<Cidades, Integer>{
	public Page<Cidades> findByNomeContaining(String nome, Pageable pageable);
}
