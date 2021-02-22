package com.pds.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class ItemCessao extends Material {

	@Column(nullable=true, length=50)
	private String tombamento;
	
	public ItemCessao(
			Long id, 
			String descricao, 
			Double valorUnitarioEntrada, 
			Double quantidadeEntrada,
			Double quantidadeAtual,
			Double valorResidual, 
			Integer vidaUtil, 
			Double producaoEstimada, 
			Double producaoAtual,
			String tombamento
	) {
		super(
				id, 
				descricao, 
				valorUnitarioEntrada, 
				quantidadeEntrada, 
				quantidadeAtual,
				valorResidual, 
				vidaUtil, 
				producaoEstimada, 
				producaoAtual,
				Depreciacao.DN
		);
		this.tombamento = tombamento;
	}

}