package br.com.codeProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.codeProject.model.Cidades;
import br.com.codeProject.repository.CidadesRepository;
import br.com.codeProject.service.exception.ObjectNotFoundException;

@Service
public class CidadesService {

	@Autowired
	private CidadesRepository cidadesRepository;
	
	public List<Cidades> listarTodasCidades() {
		return cidadesRepository.findAll();
	}
	
	public Cidades findCidade(Integer id_cidade) {
		Optional<Cidades> cidades = cidadesRepository.findById(id_cidade);
		return cidades.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id_cidade +
				" Tipo " + Cidades.class.getName()));
	}
	
	public Page<Cidades> pesquisar(String nome, Pageable pageable){
    	return  cidadesRepository.findByNomeContaining(nome, pageable) ;
    }
	
	public Cidades insert(Cidades cidades) {
		return cidadesRepository.save(cidades);
	}
	
	public void delete(Integer id_cidade) {
		findCidade(id_cidade);
		cidadesRepository.deleteById(id_cidade);
	}
	
	public Cidades update(Cidades cidades) {
		findCidade(cidades.getId_cidade());
		
		return cidadesRepository.save(cidades);
	}
	
}
