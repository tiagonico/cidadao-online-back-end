package com.tiago.cidadaoonline.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tiago.cidadaoonline.domain.TipoSolicitacao;
import com.tiago.cidadaoonline.dto.TipoSolicitacaoDTO;
import com.tiago.cidadaoonline.repositories.TipoSolicitacaoRepository;
import com.tiago.cidadaoonline.services.exceptions.DataIntegrityException;
import com.tiago.cidadaoonline.services.exceptions.ObjectNotFoundException;

@Service
public class TipoSolicitacaoService {
	
	@Autowired
	private TipoSolicitacaoRepository repo;
	
	@Autowired
	private S3Service s3Service;
	
	public TipoSolicitacao find(Integer id) {
		Optional<TipoSolicitacao> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + TipoSolicitacao.class.getName()));
	}
	
	public List<TipoSolicitacao> findAll() {
		return repo.findAll();		
	}
	
	public TipoSolicitacao fromDTO(TipoSolicitacaoDTO objDTO){
		return new TipoSolicitacao(objDTO.getId(),objDTO.getNome(),objDTO.getImgUrl());		
	}
	
	public URI uploadPicture(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile,"tipo-solicitacao");
	}
	
	@Transactional
	public TipoSolicitacao insert(TipoSolicitacao obj) {
		obj.setId(null);
		
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

}
