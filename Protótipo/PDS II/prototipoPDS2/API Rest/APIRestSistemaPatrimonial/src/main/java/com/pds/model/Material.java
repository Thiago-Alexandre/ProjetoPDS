package com.pds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 		/** Cria um construtor vazio */
@Entity
@Data
public abstract class Material{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=255)
	private String descricao;
	
	@Column(nullable=false)
	private Double valorUnitarioEntrada;
	
	@Column(nullable=false)
	private Double quantidadeEntrada;
	
	@Column(nullable=false)
	private Double quantidadeAtual;
	
	@Column(nullable=false)
	private Double valorResidual;
	
	@Column(nullable=false)
	private Integer vidaUtil;
	
	@Column(nullable=false)
	private Double producaoEstimada;
	
	@Column(nullable=false)
	private Double producaoAtual;
	
	@Enumerated
	@Column(nullable=false)
	private Depreciacao tipoDepreciacao;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Setor setorAtual;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Funcionario responsavel;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Entrada entrada;
	
	@Transient
	private DepreciacaoStrategy depreciacao;
	
	public Material(
			Long id, 
			String descricao, 
			Double valorUnitarioEntrada, 
			Double quantidadeEntrada,
			Double quantidadeAtual, 
			Double valorResidual, 
			Integer vidaUtil, 
			Double producaoEstimada, 
			Double producaoAtual, 
			Depreciacao tipoDepreciacao) {
		this.id = id;
		this.descricao = descricao;
		this.valorUnitarioEntrada = valorUnitarioEntrada;
		this.quantidadeEntrada = quantidadeEntrada;
		this.quantidadeAtual = quantidadeAtual;
		this.valorResidual = valorResidual;
		this.vidaUtil = vidaUtil;
		this.producaoEstimada = producaoEstimada;
		this.producaoAtual = producaoAtual;
		switch (tipoDepreciacao){
			case DAL:
				depreciacao = new DepreciacaoAnualLinear();
				break;
			case DML:
				depreciacao = new DepreciacaoMensalLinear();
				break;
			case DAAC:
				depreciacao = new DepreciacaoAnualAceleradaCrescente();
				break;
			case DAAD:
				depreciacao = new DepreciacaoAnualAceleradaDecrescente();
				break;
			case DUP:
				depreciacao = new DepreciacaoPorUnidadesProduzidas();
				break;
			default:
				depreciacao = new DepreciacaoNula();
		}
	}
	
	public Double calcularValorDepreciado() {
		return depreciacao.calcularValorDepreciado(this);
	}
	
	public Double calcularValorAtual() {
		Double valorTotalBem = quantidadeEntrada * valorUnitarioEntrada;
		return valorTotalBem - depreciacao.calcularValorDepreciado(this);
	}
	
	public Double calcularCusto() {
		return 0.0;
	};
}