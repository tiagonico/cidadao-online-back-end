package com.tiago.cidadaoonline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tiago.cidadaoonline.domain.Solicitacao;
import com.tiago.cidadaoonline.domain.Usuario;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer>, JpaSpecificationExecutor<Solicitacao> {

	
	@Transactional(readOnly=true)
	List<Solicitacao> findByUsuario(Usuario usuario);
	
	
}
