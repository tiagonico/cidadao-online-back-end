package com.tiago.cidadaoonline.domain.enums;

public enum StatusSolicitacao {

	SOLICITADO(1, "Solicitado"),
	EM_ANDAMENTO(2, "Em andamento"),
	CONCLUIDO(3, "Concluído"),
	REJEITADO(4, "Rejeitado"),;

	private int cod;
	private String descricao;

	private StatusSolicitacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao () {
		return descricao;
	}

	public static StatusSolicitacao toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (StatusSolicitacao x : StatusSolicitacao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
