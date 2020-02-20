package br.com.codeProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.codeProject.model.Clientes;
import br.com.codeProject.repository.ClientesRepository;
import br.com.codeProject.service.exception.ObjectNotFoundException;

@Service
public class ClientesService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	public List<Clientes> listarTodosClientes() {
		return clientesRepository.findAll();
	}
	
	public Clientes findCliente(Integer id_cliente) {
		Optional<Clientes> clientes = clientesRepository.findById(id_cliente);
		return clientes.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id_cliente +
				" Tipo " + Clientes.class.getName()));
	}
	
	public Page<Clientes> pesquisar(String nome, Pageable pageable){
    	return  clientesRepository.findByNomeContaining(nome, pageable) ;
    }
	
	public Clientes insert(Clientes clientes) {
		return clientesRepository.save(clientes);
	}
	
	public void delete(Integer id_cliente) {
		findCliente(id_cliente);
		clientesRepository.deleteById(id_cliente);
	}
	
	public Clientes update(Clientes clientes) {
		findCliente(clientes.getId_cliente());
		return clientesRepository.save(clientes);
	}
	
	
	
}
