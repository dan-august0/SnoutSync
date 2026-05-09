USE petsync;

INSERT INTO cliente (nome, telefone, tipo) VALUES
('João Silva', '11999999999', 'AVULSO'),
('Maria Souza', '11988888888', 'PLANO');

INSERT INTO pet (cliente_id, nome, especie, raca, peso, porte) VALUES
(1, 'Rex', 'Cachorro', 'Labrador', 25.5, 'G'),
(2, 'Mimi', 'Gato', 'Siamês', 4.2, 'P');

INSERT INTO servico (nome, descricao, preco_pequeno, preco_medio, preco_grande, duracao_pequeno, duracao_medio, duracao_grande) VALUES
('Banho', 'Banho completo', 30, 40, 50, 30, 40, 50);

INSERT INTO plano (cliente_id, data_inicio, data_fim) VALUES
(2, '2026-04-01', '2026-04-30');

INSERT INTO agendamento (pet_id, servico_id, data, hora, status) VALUES
(1, 1, '2026-04-26', '10:00:00', 'AGENDADO'),
(2, 1, '2026-04-26', '11:00:00', 'AGENDADO');