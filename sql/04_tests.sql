USE petsync;

SELECT c.nome AS cliente, p.nome AS pet, p.raca, c.tipo
FROM cliente c
LEFT JOIN pet p ON p.cliente_id = c.id;

SELECT c.nome AS cliente, p.nome AS pet, s.nome AS servico, a.data, a.hora, a.status
FROM agendamento a
JOIN pet p ON p.id = a.pet_id
JOIN cliente c ON c.id = p.cliente_id
JOIN servico s ON s.id = a.servico_id;
