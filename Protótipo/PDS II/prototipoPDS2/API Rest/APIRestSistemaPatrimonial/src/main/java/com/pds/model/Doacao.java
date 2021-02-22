package com.pds.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class Doacao extends Entrada {

	@Override
	public Double calcularCusto() {
		return 0.0;
	}
}