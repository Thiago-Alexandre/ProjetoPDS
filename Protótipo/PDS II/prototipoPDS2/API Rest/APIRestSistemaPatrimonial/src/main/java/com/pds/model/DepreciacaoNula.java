package com.pds.model;

public class DepreciacaoNula implements DepreciacaoStrategy {

	@Override
	public Double calcularValorDepreciado(Material material) {
		return 0.0;
	}
}