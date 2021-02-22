package com.pds.model;

import java.util.Iterator;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class Aquisicao extends Entrada {

	@Override
	public Double calcularCusto() {
		Iterator<Material> materiais = super.getItens().iterator();
		Double custo = 0.0;
		while (materiais.hasNext()) {
			custo += materiais.next().getValorUnitarioEntrada() * materiais.next().getQuantidadeEntrada(); 
		}
		return custo;
	}
}