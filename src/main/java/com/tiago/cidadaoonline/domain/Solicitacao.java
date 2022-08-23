package com.tiago.cidadaoonline.domain;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiago.cidadaoonline.domain.enums.StatusSolicitacao;

@Entity
public class Solicitacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer status;
	private String motivoRejeicao;
	
	private String imgUrl;
	
	private String descricao;
	private String endereco;
	private String latitude;
	private String longitude;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private ZonedDateTime dataSolicitacao;
	
	@ManyToOne
	@JoinColumn(name = "tipo_solicitacao_id")
	private TipoSolicitacao tipoSolicitacao;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	
	public Solicitacao() {		
	}

	public Solicitacao(Integer id, String descricao, String endereco, String latitude, String longitude,
			ZonedDateTime dataSolicitacao, TipoSolicitacao tipoSolicitacao, Usuario usuario,
			StatusSolicitacao status,String imgUrl) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataSolicitacao = dataSolicitacao;
		this.tipoSolicitacao = tipoSolicitacao;
		this.usuario = usuario;
		this.status = (status==null) ? null : status.getCod();
		this.motivoRejeicao = null;
		this.imgUrl = imgUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonIgnore
	public int getStatusInt() {
		return status;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoSolicitacao getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ZonedDateTime getDataSolicitacao() {
		
		return dataSolicitacao.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
	}

	public void setDataSolicitacao(ZonedDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	@JsonIgnore
	public StatusSolicitacao getStatusSolicitacao() {
		return StatusSolicitacao.toEnum(status);
	}
	
	public String getStatus() {
		return StatusSolicitacao.toEnum(status).getDescricao();
	}

	public void setStatusSolicitacao(StatusSolicitacao status) {
		this.status = status.getCod();
	}

	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}	
	
	
}
