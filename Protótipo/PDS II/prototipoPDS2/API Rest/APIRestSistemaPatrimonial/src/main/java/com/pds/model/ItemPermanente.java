package com.pds.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class ItemPermanente extends Material {

	@Column(nullable=true, length=50)
	private String tombamento;
	
	@OneToMany(mappedBy = "item")
	private List<Manutencao> manutencoes = new ArrayList<>();
	
	public ItemPermanente(
			Long id, 
			String descricao, 
			Double valorUnitarioEntrada, 
			Double quantidadeEntrada,
			Double quantidadeAtual,
			Double valorResidual, 
			Integer vidaUtil, 
			Double producaoEstimada, 
			Double producaoAtual,
			Depreciacao depreciacao,
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
				depreciacao
		);
		this.tombamento = tombamento;
	}
	
	@Override
	public Double calcularCusto() {
		Iterator<Manutencao> lista = manutencoes.iterator();
		Double custo = 0.0;
		while (lista.hasNext()) {
			custo += lista.next().getValor();
		}
		return custo;
	};
	
	public Double calcularValorAtual() {
		Double valorTotalBem = super.calcularValorAtual();
		Iterator<Manutencao> lista = manutencoes.iterator();
		while (lista.hasNext()) {
			if (lista.next().getAgregaValorPatrimonial()) {
				valorTotalBem += lista.next().getValor();
			}
		}
		return valorTotalBem;
	}
}