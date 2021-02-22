package com.pds.model;

public enum Depreciacao {

	DN(0), DAL(1), DML(2), DAAC(3), DAAD(4), DUP(5);
	
	private final int valor;
	
	Depreciacao (int valorOpcao){
		valor = valorOpcao;
	}
	
	public int getValor(){
		return valor;
	}
}