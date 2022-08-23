package com.tiago.cidadaoonline.dto;

import java.io.Serializable;

public class AcessoGestorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cpf;
	
	public AcessoGestorDTO() {
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


}
