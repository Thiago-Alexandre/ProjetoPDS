package com.pds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class Cessao extends Entrada {

	/** Configura o Date para ser compatível com o banco de dados */
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dataTermino;
	
	@Override
	public Double calcularCusto() {
		return 0.0;
	}
}