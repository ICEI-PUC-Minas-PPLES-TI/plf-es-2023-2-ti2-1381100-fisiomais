/* Query 1: Recuperar informações do paciente */

SELECT * FROM paciente;

/* Query 2: Listar consultas de um paciente
 Dados: Nome do fisioterapeuta, data e hora, nome do paciente, link da consulta e confirmação
 substituir o id do paciente na última linha */

SELECT
    consulta.data_e_hora,
    consulta.link,
    consulta.confirmacao,
    f.nome AS nome_fisioterapeuta,
    p.nome AS nome_paciente
FROM consulta AS consulta
    INNER JOIN fisioterapeuta AS f ON consulta.fisioterapeuta__id = f._id
    INNER JOIN paciente AS p ON consulta.paciente__id = p._id
WHERE consulta.paciente__id = 5;

/* Query 3: Encontrar horários disponíveis para um fisioterapeuta
 Dados: Nome do fisioterapeuta, dia, horário de início e horário de fim 
 substituir id do fisioterapeuta na penúltima linha*/

SELECT
    f.nome,
    a.dia,
    a.horario_inicio,
    a.horario_fim
FROM agenda AS a
    INNER JOIN fisioterapeuta AS f ON a.fisioterapeuta__id = f._id
WHERE
    a.fisioterapeuta__id = 1
    AND a.disponivel = 1;

/* Query 5: Encontrar todos os exercícios criados por um fisioterapeuta
 Dados: Nome do exercício, descrição e nome do fisioterapeuta
 substituir id do fisioterapeuta na última linha
 */

SELECT
    e.nome,
    e.descricao,
    f.nome AS nome_fisioterapeuta
FROM exercicio AS e
    INNER JOIN fisioterapeuta AS f ON e.fisioterapeuta__id = f._id
WHERE fisioterapeuta__id = 2;

/* Query 6: Recuperar mídia (imagens, vídeos, etc.) para um exercício 
 Dados: Nome do arquivo, tipo de arquivo, link do arquivo e arquivo, título, descrição e nome do fisioterapeuta autor
 substituir id do exercício na última linha
 */

SELECT
    m.titulo,
    m.type,
    m.link_arquivo,
    m.arquivo,
    m.descricao,
    f.nome AS autor
FROM midia AS m
    INNER JOIN exercicio_has_midias AS ev ON m._id = ev.midia__id
    INNER JOIN exercicio AS e ON ev.exercicio__id = e._id
    INNER JOIN fisioterapeuta AS f ON e.fisioterapeuta__id = f._id
WHERE ev.exercicio__id = 6;

/* Query 8: Recuperar todas as consultas de um fisioterapeuta em um intervalo de datas específico 
 Dados: Nome do fisioterapeuta, nome do paciente, data e hora, link da consulta e confirmação
 substituir id do fisioterapeuta na penúltima linha
 substituir data inicial e data final na última linha
 */

SELECT
    f.nome AS fisioterapeuta,
    p.nome AS paciente,
    c.data_e_hora,
    c.link,
    c.confirmacao
FROM consulta AS c
    INNER JOIN fisioterapeuta AS f ON c.fisioterapeuta__id = f._id
    INNER JOIN paciente AS p ON c.paciente__id = p._id
WHERE
    c.fisioterapeuta__id = 1
    AND c.data_e_hora BETWEEN '2023-10-25 00:00:00' AND '2023-10-25 23:59:59';