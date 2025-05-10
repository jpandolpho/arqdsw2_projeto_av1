CREATE DATABASE calmind;
USE calmind;

CREATE TABLE Cidade (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);

CREATE TABLE Cliente (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  cpf VARCHAR(14) NOT NULL UNIQUE,
  endereco TEXT,
  contato VARCHAR(100),
  email VARCHAR(100) NOT NULL UNIQUE,
  senha_hash VARCHAR(255) NOT NULL,
  cidade_id INT NOT NULL,
  FOREIGN KEY (cidade_id) REFERENCES Cidade(id)
    ON DELETE CASCADE
);

CREATE TABLE Prestador (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome_fantasia VARCHAR(100),
  nome_completo VARCHAR(100) NOT NULL,
  foto_perfil VARCHAR(255),
  especialidade VARCHAR(100),
  endereco TEXT,
  descricao TEXT,
  data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
  email VARCHAR(100) NOT NULL UNIQUE,
  senha_hash VARCHAR(255) NOT NULL,
  cidade_id INT NOT NULL,
  FOREIGN KEY (cidade_id) REFERENCES Cidade(id)
    ON DELETE CASCADE
);

CREATE TABLE ImagemServico (
  id INT PRIMARY KEY AUTO_INCREMENT,
  prestador_id INT NOT NULL,
  caminho_imagem VARCHAR(255) NOT NULL,
  descricao TEXT,
  FOREIGN KEY (prestador_id) REFERENCES Prestador(id)
    ON DELETE CASCADE
);

CREATE TABLE Disponibilidade (
  id INT PRIMARY KEY AUTO_INCREMENT,
  prestador_id INT NOT NULL,
  dia_mes DATETIME NOT NULL,
  dia_semana ENUM('SEG','TER','QUA','QUI','SEX','SAB','DOM') NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fim TIME NOT NULL,
  FOREIGN KEY (prestador_id) REFERENCES Prestador(id)
    ON DELETE CASCADE
);

-- agendamento em si
CREATE TABLE Agendamento (
  id INT PRIMARY KEY AUTO_INCREMENT,
  cliente_id INT NOT NULL,
  prestador_id INT NOT NULL,
  disponibilidade_id INT NOT NULL,
  status ENUM('solicitado', 'aceito', 'concluido') DEFAULT 'solicitado',
  criado_em DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
    ON DELETE CASCADE,
  FOREIGN KEY (prestador_id) REFERENCES Prestador(id)
    ON DELETE CASCADE,
  FOREIGN KEY (disponibilidade_id) REFERENCES Disponibilidade(id)
    ON DELETE CASCADE,
  UNIQUE (prestador_id, disponibilidade_id)
);

-- historico do agendamento
CREATE TABLE LogAgendamento (
  id INT PRIMARY KEY AUTO_INCREMENT,
  agendamento_id INT NOT NULL,
  status ENUM('solicitado', 'aceito', 'concluido') NOT NULL,
  data_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (agendamento_id) REFERENCES Agendamento(id)
    ON DELETE CASCADE
);

INSERT INTO Cidade (nome) VALUES ('Araraquara'), ('Matão'), ('Jaboticabal');
