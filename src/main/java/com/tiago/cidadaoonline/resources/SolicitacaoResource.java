package com.tiago.cidadaoonline.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiago.cidadaoonline.domain.Solicitacao;
import com.tiago.cidadaoonline.dto.SolicitacaoNewDTO;
import com.tiago.cidadaoonline.services.SolicitacaoService;
import com.turkraft.springfilter.boot.Filter;

@RestController
@RequestMapping(value="/solicitacao")
public class SolicitacaoResource {

	@Autowired
	private SolicitacaoService service;
	
	@PreAuthorize("hasAnyRole('CIDADAO')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody SolicitacaoNewDTO objDTO) {
		Solicitacao obj = service.fromDTO(objDTO);
		obj = service.insert(obj);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();	 
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Solicitacao>> findAll(@Filter Specification<Solicitacao> spec) {
		
		List<Solicitacao> list = service.findAll(spec);
		
		return ResponseEntity.ok().body(list);			
	}
	
	@PreAuthorize("hasAnyRole('CIDADAO')")
	@RequestMapping(value="/image", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadPicture(file);
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Solicitacao obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
