USE petsync;

CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    tipo ENUM('AVULSO','PLANO') NOT NULL DEFAULT 'AVULSO'
);

CREATE TABLE IF NOT EXISTS pet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    raca VARCHAR(50),
    peso DECIMAL(5,2),
    porte ENUM('P','M','G'),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco_pequeno DECIMAL(10,2),
    preco_medio DECIMAL(10,2),
    preco_grande DECIMAL(10,2),
    duracao_pequeno INT,
    duracao_medio INT,
    duracao_grande INT
);

CREATE TABLE IF NOT EXISTS plano (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT UNIQUE,
    data_inicio DATE,
    data_fim DATE,
    banhos_utilizados INT DEFAULT 0,
    banhos_limite INT DEFAULT 4,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pet_id INT NOT NULL,
    servico_id INT NOT NULL,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    status ENUM('AGENDADO','EM_ANDAMENTO','CONCLUIDO','CANCELADO') DEFAULT 'AGENDADO',
    observacoes TEXT,
    FOREIGN KEY (pet_id) REFERENCES pet(id),
    FOREIGN KEY (servico_id) REFERENCES servico(id)
);

CREATE TABLE IF NOT EXISTS atendimento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    agendamento_id INT UNIQUE,
    valor_cobrado DECIMAL(10,2),
    forma_pagamento VARCHAR(50),
    data_hora_conclusao DATETIME,
    FOREIGN KEY (agendamento_id) REFERENCES agendamento(id)
);
