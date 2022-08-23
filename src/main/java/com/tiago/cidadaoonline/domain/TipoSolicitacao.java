package com.tiago.cidadaoonline.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class TipoSolicitacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	private String imgUrl;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tipoSolicitacao")
	private List<Solicitacao> solicitacoes =  new ArrayList<>();
	
	public TipoSolicitacao() {
		
	}
	
	public TipoSolicitacao(Integer id, String nome, String imgUrl) {
		super();
		this.id = id;
		this.nome = nome;
		this.imgUrl = imgUrl;
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
