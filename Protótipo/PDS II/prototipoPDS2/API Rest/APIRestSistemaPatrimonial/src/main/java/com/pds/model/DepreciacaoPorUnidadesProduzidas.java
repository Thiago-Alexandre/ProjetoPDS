package com.pds.model;

public class DepreciacaoPorUnidadesProduzidas implements DepreciacaoStrategy {

	@Override
	public Double calcularValorDepreciado(Material material) {
		Double valorTotalBem = material.getQuantidadeEntrada() * material.getValorUnitarioEntrada();
		Double valorBem = valorTotalBem - material.getValorResidual();
		Double quotaDepreciacao = valorBem / material.getProducaoEstimada();
		return material.getProducaoEstimada() * quotaDepreciacao;
	}
}