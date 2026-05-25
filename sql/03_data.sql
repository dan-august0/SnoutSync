USE petsync;

INSERT INTO cliente (nome, telefone, tipo) VALUES
('Joao Silva', '(11) 99999-1111', 'PLANO'),
('Maria Souza', '(11) 99999-2222', 'AVULSO'),
('Carlos Lima', '(11) 99999-3333', 'PLANO');

INSERT INTO pet (cliente_id, nome, especie, raca, peso, porte) VALUES
(1, 'Thor', 'Cachorro', 'Golden Retriever', 25.5, 'G'),
(2, 'Luna', 'Cachorro', 'Shih-tzu', 4.2, 'P'),
(3, 'Mel', 'Cachorro', 'Poodle', 7.0, 'M');

INSERT INTO servico (nome, descricao, preco_pequeno, preco_medio, preco_grande, duracao_pequeno, duracao_medio, duracao_grande) VALUES
('Banho', 'Banho completo', 55, 70, 90, 30, 40, 50),
('Tosa', 'Tosa completa', 55, 70, 90, 30, 45, 60),
('Banho + Tosa', 'Banho completo com tosa', 90, 120, 150, 60, 75, 90);

INSERT INTO plano (cliente_id, data_inicio, data_fim) VALUES
(1, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 MONTH)),
(3, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 MONTH));

INSERT INTO agendamento (pet_id, servico_id, data, hora, status, observacoes) VALUES
(1, 1, CURDATE(), '10:00:00', 'AGENDADO', ''),
(2, 3, CURDATE(), '11:30:00', 'AGENDADO', ''),
(3, 2, CURDATE(), '14:00:00', 'CANCELADO', '');
