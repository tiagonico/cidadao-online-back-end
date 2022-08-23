package com.tiago.cidadaoonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiago.cidadaoonline.domain.TipoSolicitacao;

@Repository
public interface TipoSolicitacaoRepository extends JpaRepository<TipoSolicitacao, Integer> {

}
