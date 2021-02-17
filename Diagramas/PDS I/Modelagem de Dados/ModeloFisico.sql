DROP DATABASE if EXISTS inventario;
CREATE DATABASE inventario CHARSET latin1 COLLATE latin1_general_cs;
USE inventario;

CREATE TABLE TipoUsuario (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE Usuario (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	login VARCHAR(20) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	senha VARCHAR(15) NOT NULL,
	acesso BOOLEAN NOT NULL,
	tipo INTEGER NOT NULL,
	FOREIGN KEY (tipo) REFERENCES TipoUsuario(id)
);

CREATE TABLE FormaAquisicao (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE Aquisicao (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	datahora DATETIME NOT NULL,
	detalhes VARCHAR(100) NOT NULL,
	valorTotal DECIMAL(8,2) NOT NULL,
	formaAquisicao INTEGER NOT NULL,
	usuario INTEGER NOT NULL,
	FOREIGN KEY (formaAquisicao) REFERENCES FormaAquisicao(id),
	FOREIGN KEY (usuario) REFERENCES Usuario(id)
);

CREATE TABLE FormaBaixa (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE Baixa (
	id INTEGER PRIMARY KEY NOT NULL,
	datahora DATETIME NOT NULL,
	detalhes VARCHAR(100) NOT NULL,
	formaBaixa INTEGER NOT NULL,
	usuario INTEGER NOT NULL,
	FOREIGN KEY (formaBaixa) REFERENCES FormaBaixa(id),
	FOREIGN KEY (usuario) REFERENCES Usuario(id)
);

CREATE TABLE Marca (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
);

CREATE TABLE Especificacao (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE Filial (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE Responsavel (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
);

CREATE TABLE Setor (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL,
	filial INTEGER NOT NULL,
	responsavel INTEGER NOT NULL,
	FOREIGN KEY (filial) REFERENCES Filial(id),
	FOREIGN KEY (responsavel) REFERENCES Responsavel(id)
);

CREATE TABLE EstadoFisico (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE ItemPatrimonio (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	tombamento VARCHAR(20) NOT NULL,
	vidaUtil INTEGER NOT NULL,
	ativo BOOLEAN NOT NULL,
	valorResidual DECIMAL(8,2) NOT NULL,
	estado INTEGER NOT NULL,
	baixa INTEGER,
	FOREIGN KEY (estado) REFERENCES EstadoFisico(id),
	FOREIGN KEY (baixa) REFERENCES Baixa(id)
);

CREATE TABLE Material (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(100) NOT NULL,
	permanente BOOLEAN NOT NULL,
	cargaPatrimnonial BOOLEAN NOT NULL,
	valorAquisicao DECIMAL(8,2) NOT NULL,
	quantidadeAquisicao DECIMAL(8,2) NOT NULL,
	quantidadeAtual DECIMAL(8,2) NOT NULL,
	garantia INTEGER,
	especificacao INTEGER NOT NULL,
	marca INTEGER NOT NULL,
	aquisicao INTEGER NOT NULL,
	setorAtual INTEGER NOT NULL,
	responsavelatual INTEGER NOT NULL,
	patrimonio INTEGER,
	FOREIGN KEY (especificacao) REFERENCES Especificacao(id),
	FOREIGN KEY (marca) REFERENCES Marca(id),
	FOREIGN KEY (aquisicao) REFERENCES Aquisicao(id),
	FOREIGN KEY (setorAtual) REFERENCES Setor(id),
	FOREIGN KEY (responsavelatual) REFERENCES Responsavel(id),
	FOREIGN KEY (patrimonio) REFERENCES ItemPatrimonio(id)
);

CREATE TABLE TipoMovimentacao (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE Movimentacao (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	datahora DATETIME NOT NULL,
	detalhes VARCHAR(100) NOT NULL,
	quantidade DECIMAL(8,2) NOT NULL,
	material INTEGER NOT NULL,
	tipoMovimentacao INTEGER NOT NULL,
	setorAntigo INTEGER NOT NULL,
	setorNovo INTEGER NOT NULL,
	responsavelAntigo INTEGER NOT NULL,
	responsavelNovo INTEGER NOT NULL,
	usuario INTEGER NOT NULL,
	FOREIGN KEY (material) REFERENCES Material(id),
	FOREIGN KEY (tipoMovimentacao) REFERENCES TipoMovimentacao(id),
	FOREIGN KEY (setorAntigo) REFERENCES Setor(id),
	FOREIGN KEY (setorNovo) REFERENCES Setor(id),
	FOREIGN KEY (responsavelAntigo) REFERENCES Responsavel(id),
	FOREIGN KEY (responsavelNovo) REFERENCES Responsavel(id),
	FOREIGN KEY (usuario) REFERENCES Usuario(id)
);

CREATE TABLE PreInventario (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	datahora DATETIME NOT NULL,
	usuario INTEGER NOT NULL,
	FOREIGN KEY (usuario) REFERENCES Usuario(id)
);

CREATE TABLE ItemInventario (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	ativo BOOLEAN NOT NULL,
	material INTEGER NOT NULL,
	setor INTEGER NOT NULL,
	responsavel INTEGER NOT NULL,
	estado INTEGER NOT NULL,
	inventario INTEGER NOT NULL,
	FOREIGN KEY (material) REFERENCES Material(id),
	FOREIGN KEY (setor) REFERENCES Setor(id),
	FOREIGN KEY (responsavel) REFERENCES Responsavel(id),
	FOREIGN KEY (estado) REFERENCES EstadoFisico(id),
	FOREIGN KEY (inventario) REFERENCES PreInventario(id)
);

INSERT INTO TipoUsuario(nome) VALUES ('Administrador');
INSERT INTO Usuario(login, nome, senha, acesso, tipo) VALUES ('admin','Thiago','123', true, 1);