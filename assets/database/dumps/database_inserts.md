
---

### Inserindo registros nas tabelas

#### Populando a tabela fisioterapeuta:
```sql
INSERT INTO `fisiomais_db`.`fisioterapeuta` (`nome`, `email`, `password`, `telefone`, `endereco`) VALUES 
('Bruna Ribeiro Pérez', '1426468@sga.pucminas.br', 'senha123', '31999999999', 'Rua das Flores, 123'),
('Guilherme Henrique Coelho Santos', '1395175@sga.pucminas.br', 'senha1234', '31999999998', 'Avenida Central, 456'),
('José Victor Mendes Dias', '1433596@sga.pucminas.br', 'senha12345', '31999999997', 'Praça da Árvore, 789'),
('Marco Antonio Miranda Ferreira', '28064@sga.pucminas.br', 'senha123456', '31999999996', 'Rua dos Pássaros, 012'),
('Rubens Marcelo Ramos dos Santos', '1391000@sga.pucminas.br', 'senha1234567', '31999999995', 'Alameda das Estrelas, 345');
```

#### Populando a tabela paciente:
```sql
INSERT INTO `fisiomais_db`.`paciente` (`nome`, `email`, `password`, `data_nascimento`, `cpf`, `telefone`, `genero`, `endereco`) VALUES 
('João Silva', 'joao@email.com', 'senha321', '1990-05-05', '12345678901', '31988888888', 'Homem', 'Rua A, 123'),
('Maria Oliveira', 'maria@email.com', 'senha3210', '1985-10-10', '23456789012', '31988888887', 'Mulher', 'Rua B, 456'),
('Lucas Costa', 'lucas@email.com', 'senha32109', '1995-02-15', '34567890123', '31988888886', 'Homem', 'Rua C, 789'),
('Roberto Alves', 'roberto@email.com', 'senhaRoberto321', '1988-03-03', '45678901234', '31988888885', 'Homem', 'Alameda G, 210'),
('Carla Fernandes', 'carla@email.com', 'senhaCarla321', '1992-06-06', '56789012345', '31988888884', 'Mulher', 'Rua H, 543'),
('Ricardo Junior', 'ricardo@email.com', 'senhaRicardo321', '1997-09-09', '67890123456', '31988888883', 'Homem', 'Avenida I, 876'),
('Felipe Castro', 'felipe@email.com', 'senhaFelipe', '1980-07-07', '78901234567', '31988888882', 'Homem', 'Rua D, 321'),
('Juliana Menezes', 'juliana@email.com', 'senhaJuliana', '1983-08-08', '89012345678', '31988888881', 'Mulher', 'Avenida E, 654'),
('Leonardo Rocha', 'leo@email.com', 'senhaLeo', '1975-04-04', '90123456789', '31988888880', 'Homem', 'Praça F, 987');
```
#### Populando a tabela agenda:
```sql
INSERT INTO `fisiomais_db`.`agenda` (`disponivel`, `dia`, `horario_inicio`, `horario_fim`, `fisioterapeuta__id`) VALUES 
(1, 1, '08:00:00', '09:00:00', 1),  
(0, 2, '10:00:00', '11:00:00', 1),  
(1, 3, '09:30:00', '10:30:00', 2),  
(1, 4, '13:00:00', '14:00:00', 2),  
(0, 5, '15:00:00', '16:00:00', 3),  
(1, 6, '09:00:00', '10:00:00', 3),  
(1, 7, '11:00:00', '12:00:00', 1),  
(0, 1, '14:00:00', '15:00:00', 2), 
(1, 2, '16:00:00', '17:00:00', 3); 
```

#### Populando a tabela consulta:
```sql
INSERT INTO `fisiomais_db`.`consulta` (`paciente__id`, `fisioterapeuta__id`, `data_e_hora`, `observacoes`, `confirmacao`, `owner_id`, `link`) VALUES 
(1, 1, '2023-10-25 10:00:00', 'Primeira consulta do paciente.', 1, 1, 'http://linkconsulta1.com'),
(2, 2, '2023-10-26 11:00:00', 'Retorno após 1 mês.', 0, 2, 'http://linkconsulta2.com'),
(3, 3, '2023-10-27 14:00:00', 'Consulta de rotina.', 1, 3, 'http://linkconsulta3.com'),
(4, 1, '2023-10-28 15:00:00', 'Consulta inicial para avaliação.', 0, 4, 'http://linkconsulta4.com'),
(5, 2, '2023-10-29 16:00:00', 'Reavaliação após 6 meses de tratamento.', 1, 5, 'http://linkconsulta5.com'),
(6, 3, '2023-10-30 17:00:00', 'Consulta de rotina para verificação de postura.', 1, 6, 'http://linkconsulta6.com'),
(7, 1, '2023-11-01 09:00:00', 'Consulta para tratamento de ombro.', 0, 7, 'http://linkconsulta7.com'),
(8, 2, '2023-11-02 12:00:00', 'Reavaliação pós-fratura.', 1, 8, 'http://linkconsulta8.com'),
(9, 3, '2023-11-03 13:00:00', 'Consulta de rotina para tratamento de coluna.', 1, 9, 'http://linkconsulta9.com');
```

#### Populando a tabela midia:
```sql
INSERT INTO `fisiomais_db`.`midia` (`fisioterapeuta__id`, `type`, `titulo`, `descricao`, `link_arquivo`) VALUES 
(1, Video, 'Exercício 1', 'Vídeo demonstrativo do exercício 1.', 'http://linkvideo1.com'),
(2, 'Imagem', 'Posição correta', 'Imagem mostrando a postura correta.', 'http://linkimagem1.com'),
(3, 'GIF', 'Movimento repetido', 'GIF demonstrando um movimento a ser repetido.', 'http://linkgif1.com'),
(1, 'Imagem', 'Postura correta ao sentar', 'Imagem mostrando a postura correta ao sentar em uma cadeira.', 'http://linkimagem2.com'),
(2, 'GIF', 'Exercício de respiração', 'GIF demonstrando exercício de respiração profunda.', 'http://linkgif2.com'),
(3, Video, 'Alongamento de membros inferiores', 'Vídeo com série de alongamentos para pernas.', 'http://linkvideo2.com'),
(1, 'GIF', 'Exercício de rotação', 'GIF demonstrando exercício de rotação de ombro.', 'http://linkgif3.com'),
(2, Video, 'Técnicas de relaxamento', 'Vídeo com técnicas de relaxamento muscular.', 'http://linkvideo3.com'),
(3, 'Imagem', 'Postura ao levantar', 'Imagem mostrando a postura correta ao levantar objetos pesados.', 'http://linkimagem3.com');
```

#### Populando a tabela tratamento:
```sql
INSERT INTO `fisiomais_db`.`tratamento` (`fisioterapeuta__id`, `paciente__id`, `titulo`, `observacoes`, `end_date`, `feedback`) VALUES 
(1, 1, 'Tratamento de coluna', 'Tratamento iniciado após diagnóstico de hérnia de disco.', '2023-12-25', 'Paciente apresentou melhora após 2 meses.'),
(2, 2, 'Reabilitação pós-cirúrgica', 'Reabilitação após cirurgia no joelho.', '2023-11-15', 'Paciente em fase de recuperação, mostrando progresso.'),
(3, 3, 'Fortalecimento muscular', 'Exercícios para fortalecimento da região lombar.', '2023-11-30', 'Paciente sente menos dores após iniciar tratamento.'),
(1, 4, 'Reeducação postural', 'Tratamento focado na correção da postura.', '2023-12-10', 'Paciente apresentou melhora após 1 mês.'),
(2, 5, 'Tratamento respiratório', 'Exercícios de respiração para melhoria pulmonar.', '2023-11-20', 'Paciente sente-se mais disposto após iniciar tratamento.'),
(3, 6, 'Alongamento geral', 'Série de alongamentos para todo o corpo.', '2023-12-05', 'Paciente reporta menor sensação de rigidez.'),
(1, 7, 'Tratamento de ombro', 'Tratamento para tendinite no ombro.', '2023-12-20', 'Paciente apresentou melhora após 3 sessões.'),
(2, 8, 'Reabilitação pós-fratura', 'Reabilitação após fratura no pulso.', '2023-11-28', 'Paciente recuperando movimentos gradativamente.'),
(3, 9, 'Tratamento de coluna', 'Tratamento para lombalgia.', '2023-12-15', 'Paciente reporta menos dores após 2 semanas.');
```

#### Populando a tabela exercicio:
```sql
INSERT INTO `fisiomais_db`.`exercicio` (`nome`, `descricao`, `fisioterapeuta__id`) VALUES 
('Exercício A', 'Exercício de alongamento para a região cervical.', 1),
('Exercício B', 'Exercício de fortalecimento para quadríceps.', 2),
('Exercício C', 'Exercício de mobilidade para o tornozelo.', 3),
('Exercício D', 'Exercício de relaxamento para ombros e pescoço.', 1),
('Exercício E', 'Exercício de fortalecimento para abdominais.', 2),
('Exercício F', 'Exercício de mobilidade para os quadris.', 3),
('Exercício G', 'Exercício de mobilidade para punho.', 1),
('Exercício H', 'Exercício de fortalecimento para deltoides.', 2),
('Exercício I', 'Exercício de relaxamento para a região lombar.', 3);
```

#### Populando a tabela exercicio_has_midias:
```sql
INSERT INTO `fisiomais_db`.`exercicio_has_midias` (`midia__id`, `midia_fisioterapeuta__id`, `exercicio__id`) VALUES 
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 1, 4),
(5, 2, 5),
(6, 3, 6),
(7, 1, 7),
(8, 2, 8),
(9, 3, 9);
```

#### Populando a tabela tratamento_has_exercicios:
```sql
INSERT INTO `fisiomais_db`.`tratamento_has_exercicios` (`tratamento__id`, `exercicio__id`) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9);
```
--- 