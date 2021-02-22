package com.pds.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class ItemConsumo extends Material {
	
	public ItemConsumo(
			Long id, 
			String descricao, 
			Double valorUnitarioEntrada, 
			Double quantidadeEntrada,
			Double valorResidual, 
			Integer vidaUtil, 
			Double producaoEstimada, 
			Double producaoAtual,
			Double quantidadeAtual
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
	}
}