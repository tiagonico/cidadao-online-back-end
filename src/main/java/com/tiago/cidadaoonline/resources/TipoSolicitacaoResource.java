package com.tiago.cidadaoonline.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.tiago.cidadaoonline.domain.TipoSolicitacao;
import com.tiago.cidadaoonline.dto.TipoSolicitacaoDTO;
import com.tiago.cidadaoonline.services.TipoSolicitacaoService;

@RestController
@RequestMapping(value="/tipo-solicitacao")
public class TipoSolicitacaoResource {

	@Autowired
	private TipoSolicitacaoService service;
	
	@PreAuthorize("hasAnyRole('CIDADAO')")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<TipoSolicitacao> find(@PathVariable Integer id) {
		
		TipoSolicitacao obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TipoSolicitacaoDTO objDTO) {
		TipoSolicitacao obj = service.fromDTO(objDTO);
		obj = service.insert(obj);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();	 
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/image", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadPicture(file);
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('CIDADAO')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TipoSolicitacao>> findAll() {
		
		List<TipoSolicitacao> list = service.findAll();
		List<TipoSolicitacaoDTO> listDTO = new ArrayList<>();
		
		
		for(TipoSolicitacao tipoSolicitacao : list) {
			TipoSolicitacaoDTO tipoSolicitacaoDTO = new TipoSolicitacaoDTO(tipoSolicitacao); 
			listDTO.add(tipoSolicitacaoDTO);
		}
		return ResponseEntity.ok().body(list);			
	}
}
