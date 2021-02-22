package com.pds.model;

import java.util.Calendar;

public class DepreciacaoAnualAceleradaDecrescente implements DepreciacaoStrategy {

	@Override
	public Double calcularValorDepreciado(Material material) {
		Double valorTotalBem = material.getQuantidadeEntrada() * material.getValorUnitarioEntrada();
		Double valorBem = valorTotalBem - material.getValorResidual();
		Integer somaAnos = (material.getVidaUtil() * (material.getVidaUtil() + 1)) / 2;
		Calendar dataAtual = Calendar.getInstance();
		Calendar dataEntrada = Calendar.getInstance();
		dataEntrada.setTime(material.getEntrada().getDataEntrada());
		Integer tempoUso = dataAtual.get(Calendar.YEAR) - dataEntrada.get(Calendar.YEAR);
		Double valorDepreciado = 0.0;
		for (int i = tempoUso; i > 0; i--) {
			valorDepreciado += valorBem * (i / somaAnos);
		}
		return valorDepreciado;
	}
}