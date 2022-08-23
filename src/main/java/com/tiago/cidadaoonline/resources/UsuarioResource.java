package com.tiago.cidadaoonline.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiago.cidadaoonline.domain.Solicitacao;
import com.tiago.cidadaoonline.domain.Usuario;
import com.tiago.cidadaoonline.dto.AcessoGestorDTO;
import com.tiago.cidadaoonline.dto.UsuarioNewDTO;
import com.tiago.cidadaoonline.services.UsuarioService;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		
		List<Usuario> list = service.findAll();
		
		return ResponseEntity.ok().body(list);			
	}
	
	@PreAuthorize("hasAnyRole('CIDADAO')")
	@RequestMapping(value="/{cpf}/solicitacao",method=RequestMethod.GET)
	public ResponseEntity<List<Solicitacao>> getSolicitacao(@PathVariable String cpf) {
		
		List<Solicitacao> list = service.findSolicitacaoByCpf(cpf);
		
		return ResponseEntity.ok().body(list);			
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/acesso-gestor",method=RequestMethod.POST)
	public ResponseEntity<Void> update(@Valid @RequestBody AcessoGestorDTO objDTO ) {
		
		service.acessoGestor(objDTO.getCpf());
		
		return ResponseEntity.noContent().build();		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDTO objDTO) {
		Usuario obj = service.fromDTO(objDTO);
		obj = service.insert(obj);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();	 
		return ResponseEntity.created(uri).build();
	}
}
