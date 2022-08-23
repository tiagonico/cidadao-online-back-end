package com.tiago.cidadaoonline.services;

import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tiago.cidadaoonline.domain.Solicitacao;
import com.tiago.cidadaoonline.domain.TipoSolicitacao;
import com.tiago.cidadaoonline.domain.Usuario;
import com.tiago.cidadaoonline.domain.enums.StatusSolicitacao;
import com.tiago.cidadaoonline.dto.SolicitacaoNewDTO;
import com.tiago.cidadaoonline.repositories.SolicitacaoRepository;
import com.tiago.cidadaoonline.repositories.TipoSolicitacaoRepository;
import com.tiago.cidadaoonline.repositories.UsuarioRepository;

@Service
public class SolicitacaoService {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private TipoSolicitacaoRepository tipoSolicitacaoRepository;
	
	
	public List<Solicitacao> findAll(Specification<Solicitacao> spec) {
		return solicitacaoRepository.findAll(spec);		
	}
	
	public Solicitacao fromDTO(SolicitacaoNewDTO objDTO){
		ZonedDateTime zdt = ZonedDateTime.now( ZoneId.of("America/Sao_Paulo") );
		
		System.out.print(zdt.toString());
		Usuario usuario = usuarioRepository.findByCpf(objDTO.getCpf());
		Optional<TipoSolicitacao> tipoSolicitacao = tipoSolicitacaoRepository.findById(objDTO.getTipoSolicitacaoId());
		return new Solicitacao(null, objDTO.getDescricao(),objDTO.getEndereco(),objDTO.getLatitude(),objDTO.getLongitude(),zdt,tipoSolicitacao.get(),usuario,StatusSolicitacao.SOLICITADO,objDTO.getImgUrl());	
	}
	
	public URI uploadPicture(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile,"solicitacao");
	}
	
	
	public Solicitacao update(Solicitacao obj) {
		Optional<Solicitacao> newObj = solicitacaoRepository.findById(obj.getId());
		updateData(newObj.get(),obj);
		return solicitacaoRepository.save(newObj.get());
	}
	
	private void updateData(Solicitacao newObj, Solicitacao obj) {
		
		newObj.setStatus(obj.getStatusInt());
		newObj.setMotivoRejeicao(obj.getMotivoRejeicao());
	}
	
	@Transactional
	public Solicitacao insert(Solicitacao obj) {
		obj.setId(null);
		
		return solicitacaoRepository.save(obj);
	}

}
