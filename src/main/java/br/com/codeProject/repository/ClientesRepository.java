package br.com.codeProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codeProject.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer>{
	public Page<Clientes> findByNomeContaining(String nome, Pageable pageable);
}
