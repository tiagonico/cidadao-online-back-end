package com.tiago.cidadaoonline.dto;

import java.io.Serializable;


public class UsuarioNewDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private Integer id;
		
		private String nome;
		
		private String cpf;
		
		private String email;
		
		private String senha;

		public UsuarioNewDTO() {
			
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}		

}
