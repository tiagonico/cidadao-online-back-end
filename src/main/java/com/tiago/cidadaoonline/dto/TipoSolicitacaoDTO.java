package com.tiago.cidadaoonline.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.tiago.cidadaoonline.domain.TipoSolicitacao;

public class TipoSolicitacaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	private String imgUrl;
	
	public TipoSolicitacaoDTO() {
		
	}
	
	public TipoSolicitacaoDTO(TipoSolicitacao obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.imgUrl = obj.getImgUrl();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}	

}
