package com.tiago.cidadaoonline.dto;

import java.io.Serializable;


public class SolicitacaoNewDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private String imgUrl;
		
		private String descricao;
		
		private String endereco;
		
		private Integer tipoSolicitacaoId;
		
		private String latitude;
		
		private String longitude;
		
		private String cpf;

		public SolicitacaoNewDTO() {
			
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

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public Integer getTipoSolicitacaoId() {
			return tipoSolicitacaoId;
		}

		public void setTipoSolicitacaoId(Integer tipoSolicitacaoId) {
			this.tipoSolicitacaoId = tipoSolicitacaoId;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}				

}
