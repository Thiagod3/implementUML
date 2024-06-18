package com.thiagoCompass.ImplementacaoUML.domain.enums;

public enum EstadoPagamento {
	PENDENTE (1, "Pagamento pendente"),
	QUITADO(2, "Pagamento quitado"),
	CANCELADO(3, "Pagamento cancelado");
	
	private Integer cod;
	private String status;
	
	private EstadoPagamento(Integer cod, String status) {
		this.cod = cod;
		this.status = status;
	}

	public Integer getCod() {
		return cod;
	}

	public String getStatus() {
		return status;
	}
	

	
	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: " + cod);
	}

}
