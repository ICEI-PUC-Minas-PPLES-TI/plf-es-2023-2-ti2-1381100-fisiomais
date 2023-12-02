-- Inserindo registros nas tabelas

-- Populando a tabela fisioterapeuta:

INSERT INTO
    `fisiomais_db`.`fisioterapeuta` (
        `nome`,
        `email`,
        `password`,
        `telefone`,
        `endereco`,
        `controle_automatico`
    )
VALUES (
        'Bruna Ribeiro Pérez',
        '1426468@sga.pucminas.br',
        'senha123',
        '31999999999',
        'Rua das Flores, 123',
        0
    ), (
        'Guilherme Henrique Coelho Santos',
        '1395175@sga.pucminas.br',
        'senha1234',
        '31999999998',
        'Avenida Central, 456',
        0
    ), (
        'José Victor Mendes Dias',
        '1433596@sga.pucminas.br',
        'senha12345',
        '31999999997',
        'Praça da Árvore, 789',
        0
    ), (
        'Lucas Almeida',
        'lucas.almeida@example.com',
        'senha12345678',
        '31933333333',
        'Rua da Liberdade, 789',
        0
    ), (
        'Rubens Marcelo Ramos dos Santos',
        '1391000@sga.pucminas.br',
        'senha1234567',
        '31999999995',
        'Alameda das Estrelas, 345',
        0
    ), (
        'Maria da Silva',
        'maria.silva@example.com',
        'senha5678',
        '31988888888',
        'Rua da Esperança, 987',
        0
    ), (
        'João Ferreira',
        'joao.ferreira@example.com',
        'senhajoao',
        '31977777777',
        'Avenida da Paz, 654',
        0
    ), (
        'Fernanda Oliveira',
        'fernanda.oliveira@example.com',
        'senhaferna',
        '31966666666',
        'Praça da Alegria, 321',
        0
    ), (
        'Rodrigo Sousa',
        'rodrigo.sousa@example.com',
        'senharodrigo',
        '31955555555',
        'Alameda dos Sonhos, 123',
        0
    ), (
        'Mariana Santos',
        'mariana.santos@example.com',
        'senhamariana',
        '31944444444',
        'Rua do Amor, 987',
        0
    );

-- Populando a tabela paciente:

INSERT INTO
    `fisiomais_db`.`paciente` (
        `nome`,
        `email`,
        `password`,
        `data_nascimento`,
        `cpf`,
        `telefone`,
        `genero`,
        `endereco`
    )
VALUES (
        'Maria da Silva',
        'maria.silva@example.com',
        'senha123',
        '1985-03-10',
        '12345678901',
        '31999999999',
        'Mulher',
        'Rua das Flores, 123'
    ), (
        'João Ferreira',
        'joao.ferreira@example.com',
        'senha456',
        '1980-06-15',
        '98765432101',
        '31999999998',
        'Homem',
        'Avenida Central, 456'
    ), (
        'Fernanda Oliveira',
        'fernanda.oliveira@example.com',
        'senha789',
        '1992-09-20',
        '45678901234',
        '31999999997',
        'Mulher',
        'Praça da Árvore, 789'
    ), (
        'Rodrigo Sousa',
        'rodrigo.sousa@example.com',
        'senha1011',
        '1998-12-25',
        '78901234567',
        '31999999996',
        'Homem',
        'Rua dos Pássaros, 012'
    ), (
        'Mariana Santos',
        'mariana.santos@example.com',
        'senha1213',
        '1990-02-08',
        '89012345678',
        '31999999995',
        'Mulher',
        'Alameda das Estrelas, 345'
    ), (
        'Lucas Almeida',
        'lucas.almeida@example.com',
        'senha1314',
        '1987-04-30',
        '90123456789',
        '31999999994',
        'Homem',
        'Avenida das Maravilhas, 678'
    ), (
        'Aline Pereira',
        'aline.pereira@example.com',
        'senha1415',
        '1995-07-14',
        '01234567890',
        '31999999993',
        'Mulher',
        'Rua da Felicidade, 456'
    ), (
        'Gustavo Silva',
        'gustavo.silva@example.com',
        'senha1516',
        '1993-10-03',
        '10123456789',
        '31999999992',
        'Homem',
        'Avenida dos Sonhos, 123'
    ), (
        'Camila Santos',
        'camila.santos@example.com',
        'senha1617',
        '1988-11-18',
        '11234567890',
        '31999999991',
        'Mulher',
        'Praça da Serenidade, 789'
    ), (
        'Rafael Ferreira',
        'rafael.ferreira@example.com',
        'senha1718',
        '1986-01-21',
        '78901234567',
        '31999999990',
        'Homem',
        'Rua do Bem-Estar, 012'
    ), (
        'Larissa Oliveira',
        'larissa.oliveira@example.com',
        'senha1819',
        '1989-04-02',
        '98765432101',
        '31999999989',
        'Mulher',
        'Alameda dos Sonhos, 345'
    ), (
        'Pedro Santos',
        'pedro.santos@example.com',
        'senha1920',
        '1991-06-05',
        '01234567890',
        '31999999988',
        'Homem',
        'Avenida da Tranquilidade, 678'
    ), (
        'Ana Silva',
        'ana.silva@example.com',
        'senha2021',
        '1994-08-09',
        '10123456789',
        '31999999987',
        'Mulher',
        'Rua das Maravilhas, 456'
    ), (
        'Carlos Ferreira',
        'carlos.ferreira@example.com',
        'senha2122',
        '1983-09-12',
        '11234567890',
        '31999999986',
        'Homem',
        'Praça da Harmonia, 123'
    ), (
        'Sofia Oliveira',
        'sofia.oliveira@example.com',
        'senha2223',
        '1996-11-15',
        '78901234567',
        '31999999985',
        'Mulher',
        'Avenida da Serenidade, 789'
    ), (
        'Eduardo Santos',
        'eduardo.santos@example.com',
        'senha2324',
        '1984-01-18',
        '98765432101',
        '31999999984',
        'Homem',
        'Rua do Contentamento, 012'
    ), (
        'Luisa Pereira',
        'luisa.pereira@example.com',
        'senha2425',
        '1987-03-21',
        '01234567890',
        '31999999983',
        'Mulher',
        'Alameda das Maravilhas, 345'
    ), (
        'Mateus Silva',
        'mateus.silva@example.com',
        'senha2526',
        '1992-05-24',
        '10123456789',
        '31999999982',
        'Homem',
        'Avenida da Felicidade, 678'
    ), (
        'Isabella Ferreira',
        'isabella.ferreira@example.com',
        'senha2627',
        '1999-07-27',
        '11234567890',
        '31999999981',
        'Mulher',
        'Praça das Maravilhas, 123'
    );

-- Populando a tabela agenda:

-- Inserir registros na tabela agenda para o fisioterapeuta com _id = 1

INSERT INTO
    `fisiomais_db`.`agenda` (
        `disponivel`,
        `dia`,
        `horario_inicio`,
        `horario_fim`,
        `fisioterapeuta__id`
    )
VALUES (1, 1, '08:00:00', '12:00:00', 1), (1, 1, '14:00:00', '18:00:00', 1), (1, 2, '08:00:00', '12:00:00', 1), (1, 2, '14:00:00', '18:00:00', 1), (1, 3, '08:00:00', '12:00:00', 1);

-- Inserir registros na tabela agenda para o fisioterapeuta com _id = 2

INSERT INTO
    `fisiomais_db`.`agenda` (
        `disponivel`,
        `dia`,
        `horario_inicio`,
        `horario_fim`,
        `fisioterapeuta__id`
    )
VALUES (1, 1, '09:00:00', '13:00:00', 2), (1, 1, '15:00:00', '19:00:00', 2), (1, 2, '09:00:00', '13:00:00', 2), (1, 2, '15:00:00', '19:00:00', 2), (1, 3, '09:00:00', '13:00:00', 2);

-- Inserir registros na tabela agenda para o fisioterapeuta com _id = 3

INSERT INTO
    `fisiomais_db`.`agenda` (
        `disponivel`,
        `dia`,
        `horario_inicio`,
        `horario_fim`,
        `fisioterapeuta__id`
    )
VALUES (1, 1, '10:00:00', '14:00:00', 3), (1, 1, '16:00:00', '20:00:00', 3), (1, 2, '10:00:00', '14:00:00', 3), (1, 2, '16:00:00', '20:00:00', 3), (1, 3, '10:00:00', '14:00:00', 3);

-- Inserir registros na tabela agenda para o fisioterapeuta com _id = 4

INSERT INTO
    `fisiomais_db`.`agenda` (
        `disponivel`,
        `dia`,
        `horario_inicio`,
        `horario_fim`,
        `fisioterapeuta__id`
    )
VALUES (1, 1, '11:00:00', '15:00:00', 4), (1, 1, '17:00:00', '21:00:00', 4), (1, 2, '11:00:00', '15:00:00', 4), (1, 2, '17:00:00', '21:00:00', 4), (1, 3, '11:00:00', '15:00:00', 4);

-- Inserir registros na tabela agenda para o fisioterapeuta com _id = 5

INSERT INTO
    `fisiomais_db`.`agenda` (
        `disponivel`,
        `dia`,
        `horario_inicio`,
        `horario_fim`,
        `fisioterapeuta__id`
    )
VALUES (1, 1, '12:00:00', '16:00:00', 5), (1, 1, '18:00:00', '22:00:00', 5), (1, 2, '12:00:00', '16:00:00', 5), (1, 2, '18:00:00', '22:00:00', 5), (1, 3, '12:00:00', '16:00:00', 5);

-- Inserir registros na tabela agenda para o fisioterapeuta com _id = 6

INSERT INTO
    `fisiomais_db`.`agenda` (
        `disponivel`,
        `dia`,
        `horario_inicio`,
        `horario_fim`,
        `fisioterapeuta__id`
    )
VALUES (1, 1, '13:00:00', '17:00:00', 6), (1, 1, '19:00:00', '23:00:00', 6), (1, 2, '13:00:00', '17:00:00', 6), (1, 2, '19:00:00', '23:00:00', 6), (1, 3, '13:00:00', '17:00:00', 6);

-- Inserir registros na tabela agenda para o fisioterapeuta com _id = 7

INSERT INTO
    `fisiomais_db`.`agenda` (
        `disponivel`,
        `dia`,
        `horario_inicio`,
        `horario_fim`,
        `fisioterapeuta__id`
    )
VALUES (1, 1, '08:00:00', '12:00:00', 7), (1, 1, '14:00:00', '18:00:00', 7), (1, 2, '08:00:00', '12:00:00', 7), (1, 2, '14:00:00', '18:00:00', 7), (1, 3, '08:00:00', '12:00:00', 7);

-- Populando a tabela consulta:

INSERT INTO
    `fisiomais_db`.`consulta` (
        `paciente__id`,
        `fisioterapeuta__id`,
        `data_e_hora`,
        `observacoes`,
        `confirmacao`,
        `link`,
        `google_event_id`
    )
VALUES (
        1,
        1,
        '2023-11-10 10:00:00',
        'Consulta de rotina.',
        'confirmado',
        'https://exemplo.com/consulta1',
        NULL
    ), (
        2,
        2,
        '2023-11-11 11:00:00',
        'Avaliação inicial do paciente.',
        'confirmado',
        'https://exemplo.com/consulta2',
        NULL
    ), (
        3,
        3,
        '2023-11-12 12:00:00',
        'Revisão do tratamento.',
        'cancelado',
        NULL,
        NULL
    ), (
        4,
        4,
        '2023-11-13 13:00:00',
        'Tratamento de lesão no joelho.',
        'realizado',
        'https://exemplo.com/consulta4',
        NULL
    ), (
        5,
        5,
        '2023-11-14 14:00:00',
        'Avaliação pós-operatória.',
        'confirmado',
        'https://exemplo.com/consulta5',
        NULL
    ), (
        6,
        1,
        '2023-11-15 15:00:00',
        'Consulta de acompanhamento.',
        'pendente',
        NULL,
        NULL
    ), (
        7,
        2,
        '2023-11-16 16:00:00',
        'Avaliação da mobilidade.',
        'confirmado',
        'https://exemplo.com/consulta7',
        NULL
    ), (
        8,
        3,
        '2023-11-17 17:00:00',
        'Tratamento de dor nas costas.',
        'cancelado',
        NULL,
        NULL
    ), (
        9,
        4,
        '2023-11-18 18:00:00',
        'Consulta para idoso.',
        'realizado',
        'https://exemplo.com/consulta9',
        NULL
    ), (
        10,
        5,
        '2023-11-19 19:00:00',
        'Avaliação de reabilitação.',
        'confirmado',
        'https://exemplo.com/consulta10',
        NULL
    ), (
        1,
        1,
        '2023-11-20 20:00:00',
        'Consulta de rotina.',
        'pendente',
        NULL,
        NULL
    );

-- Populando a tabela midia:

INSERT INTO
    `fisiomais_db`.`midia` (
        `fisioterapeuta__id`,
        `type`,
        `titulo`,
        `descricao`,
        `link_arquivo`
    )
VALUES (
        1,
        'Video',
        'Exercício 1',
        'Video demonstrativo do exercício 1.',
        'http://linkvideo1.com'
    ), (
        2,
        'Imagem',
        'Posição correta',
        'Imagem mostrando a postura correta.',
        'http://linkimagem1.com'
    ), (
        3,
        'GIF',
        'Movimento repetido',
        'GIF demonstrando um movimento a ser repetido.',
        'http://linkgif1.com'
    ), (
        1,
        'Imagem',
        'Postura correta ao sentar',
        'Imagem mostrando a postura correta ao sentar em uma cadeira.',
        'http://linkimagem2.com'
    ), (
        2,
        'GIF',
        'Exercício de respiração',
        'GIF demonstrando exercício de respiração profunda.',
        'http://linkgif2.com'
    ), (
        3,
        'Video',
        'Alongamento de membros inferiores',
        'Video com série de alongamentos para pernas.',
        'http://linkvideo2.com'
    ), (
        1,
        'GIF',
        'Exercício de rotação',
        'GIF demonstrando exercício de rotação de ombro.',
        'http://linkgif3.com'
    ), (
        2,
        'Video',
        'Técnicas de relaxamento',
        'Video com técnicas de relaxamento muscular.',
        'http://linkvideo3.com'
    ), (
        3,
        'Imagem',
        'Postura ao levantar',
        'Imagem mostrando a postura correta ao levantar objetos pesados.',
        'http://linkimagem3.com'
    );

-- Populando a tabela tratamento:

INSERT INTO
    `fisiomais_db`.`tratamento` (
        `fisioterapeuta__id`,
        `paciente__id`,
        `titulo`,
        `observacoes`,
        `end_date`,
        `feedback`
    )
VALUES (
        1,
        1,
        'Tratamento de coluna',
        'Tratamento iniciado após diagnóstico de hérnia de disco.',
        '2023-12-25',
        'Paciente apresentou melhora após 2 meses.'
    ), (
        2,
        2,
        'Reabilitação pós-cirúrgica',
        'Reabilitação após cirurgia no joelho.',
        '2023-11-15',
        'Paciente em fase de recuperação, mostrando progresso.'
    ), (
        3,
        3,
        'Fortalecimento muscular',
        'Exercícios para fortalecimento da região lombar.',
        '2023-11-30',
        'Paciente sente menos dores após iniciar tratamento.'
    ), (
        1,
        4,
        'Reeducação postural',
        'Tratamento focado na correção da postura.',
        '2023-12-10',
        'Paciente apresentou melhora após 1 mês.'
    ), (
        2,
        5,
        'Tratamento respiratório',
        'Exercícios de respiração para melhoria pulmonar.',
        '2023-11-20',
        'Paciente sente-se mais disposto após iniciar tratamento.'
    ), (
        3,
        6,
        'Alongamento geral',
        'Série de alongamentos para todo o corpo.',
        '2023-12-05',
        'Paciente reporta menor sensação de rigidez.'
    ), (
        1,
        7,
        'Tratamento de ombro',
        'Tratamento para tendinite no ombro.',
        '2023-12-20',
        'Paciente apresentou melhora após 3 sessões.'
    ), (
        2,
        8,
        'Reabilitação pós-fratura',
        'Reabilitação após fratura no pulso.',
        '2023-11-28',
        'Paciente recuperando movimentos gradativamente.'
    ), (
        3,
        9,
        'Tratamento de coluna',
        'Tratamento para lombalgia.',
        '2023-12-15',
        'Paciente reporta menos dores após 2 semanas.'
    );

-- Populando a tabela exercicio:

INSERT INTO
    `fisiomais_db`.`exercicio` (
        `nome`,
        `descricao`,
        `fisioterapeuta__id`
    )
VALUES (
        'Exercício A',
        'Exercício de alongamento para a região cervical.',
        1
    ), (
        'Exercício B',
        'Exercício de fortalecimento para quadríceps.',
        2
    ), (
        'Exercício C',
        'Exercício de mobilidade para o tornozelo.',
        3
    ), (
        'Exercício D',
        'Exercício de relaxamento para ombros e pescoço.',
        1
    ), (
        'Exercício E',
        'Exercício de fortalecimento para abdominais.',
        2
    ), (
        'Exercício F',
        'Exercício de mobilidade para os quadris.',
        3
    ), (
        'Exercício G',
        'Exercício de mobilidade para punho.',
        1
    ), (
        'Exercício H',
        'Exercício de fortalecimento para deltoides.',
        2
    ), (
        'Exercício I',
        'Exercício de relaxamento para a região lombar.',
        3
    );

-- Populando a tabela exercicio_has_midias:

INSERT INTO
    `fisiomais_db`.`exercicio_has_midias` (
        `midia__id`,
        `midia_fisioterapeuta__id`,
        `exercicio__id`
    )
VALUES (1, 1, 1), (3, 1, 4), (4, 1, 7), (7, 1, 1);

-- Populando a tabela tratamento_has_exercicios:

INSERT INTO
    `fisiomais_db`.`tratamento_has_exercicios` (
        `tratamento__id`,
        `exercicio__id`
    )
VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9);

INSERT INTO acompanhamento_virtual (id, data_sessao, plataforma, recursos, feedback, avaliacao, data_criacao) VALUES
(1, '2023-01-01', 'Zoom', 'Vídeos de exercícios', 'Muito eficaz', 'Excelente', '2023-01-01'),
(2, '2023-01-08', 'Google Meet', 'PDFs de orientação', 'Sessão informativa', 'Bom', '2023-01-08'),
(3, '2023-01-15', 'Microsoft Teams', 'Links úteis', 'Precisa de mais informações', 'Regular', '2023-01-15'),
(4, '2023-01-22', 'Zoom', 'Planilha de acompanhamento', 'Excelente interação', 'Excelente', '2023-01-22'),
(5, '2023-01-29', 'Skype', 'E-book de saúde', 'Feedback positivo', 'Bom', '2023-01-29'),
(6, '2023-02-05', 'Google Meet', 'Vídeos ilustrativos', 'Ajuda muito no tratamento', 'Excelente', '2023-02-05'),
(7, '2023-02-12', 'Zoom', 'Artigos científicos', 'Falta de profundidade', 'Regular', '2023-02-12'),
(8, '2023-02-19', 'Skype', 'Infográficos', 'Interessante e educativo', 'Bom', '2023-02-19'),
(9, '2023-02-26', 'Microsoft Teams', 'Podcasts', 'Não muito engajante', 'Regular', '2023-02-26'),
(10, '2023-03-05', 'Zoom', 'Webinars', 'Excelente material', 'Excelente', '2023-03-05');
