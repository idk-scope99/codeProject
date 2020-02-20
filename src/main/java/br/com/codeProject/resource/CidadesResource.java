package br.com.codeProject.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.codeProject.model.Cidades;
import br.com.codeProject.service.CidadesService;

@RestController
@RequestMapping("/cidades")
public class CidadesResource {

	@Autowired
	private CidadesService cidadesService;
	
	@GetMapping("/todas")
	public List<Cidades> listarTodasCidades(){
		return cidadesService.listarTodasCidades();
	}
	
	@GetMapping("/{id_cidade}")
	public ResponseEntity<Cidades> find(@PathVariable Integer id_cidade){
		Cidades cidades = cidadesService.findCidade(id_cidade);
		return ResponseEntity.ok().body(cidades);
	}
	
	@GetMapping()
	public Page<Cidades> pesquisar(@RequestParam(required = false, defaultValue= "") String nome, Pageable pageable){
		return cidadesService.pesquisar(nome, pageable);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody Cidades cidades){
		cidades = cidadesService.insert(cidades);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_cidade}").buildAndExpand(cidades.getId_cidade()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id_cidade}")
	public ResponseEntity<Void> delete(@PathVariable Integer id_cidade){
		cidadesService.delete(id_cidade);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id_cidade}")
	public ResponseEntity<Void> update(@RequestBody Cidades cidades){
		 cidadesService.update(cidades);
		 return ResponseEntity.noContent().build();
	 }
	
}
