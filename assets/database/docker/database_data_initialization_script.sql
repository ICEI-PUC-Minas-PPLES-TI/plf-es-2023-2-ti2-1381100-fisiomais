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
        1
    ), (
        'Guilherme Henrique Coelho Santos',
        '1395175@sga.pucminas.br',
        'senha1234',
        '31999999998',
        'Avenida Central, 456',
        1
    ), (
        'José Victor Mendes Dias',
        '1433596@sga.pucminas.br',
        'senha12345',
        '31999999997',
        'Praça da Árvore, 789',
        1
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
VALUES (1, 1, '08:00:00', '10:00:00', 5), (0, 1, '11:00:00', '13:00:00', 5), (0, 2, '14:00:00', '16:00:00', 1), (1, 2, '17:00:00', '19:00:00', 5), (1, 3, '09:00:00', '18:00:00', 1), (1, 3, '14:00:00', '16:00:00', 5), (1, 4, '10:00:00', '12:00:00', 1), (0, 4, '15:00:00', '17:00:00', 1), (1, 5, '11:00:00', '13:00:00', 1), (1, 5, '16:00:00', '18:00:00', 1);

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
        '2023-11-15 09:00:00',
        'Consulta de rotina.',
        'confirmado',
        NULL,
        NULL
    ), (
        2,
        1,
        '2023-11-15 13:30:00',
        'Acompanhamento pós-cirúrgico.',
        'confirmado',
        NULL,
        NULL
    ), (
        3,
        3,
        '2023-11-14 11:00:00',
        'Avaliação inicial.',
        'confirmado',
        NULL,
        NULL
    ), (
        4,
        4,
        '2023-11-16 14:45:00',
        'Tratamento de lesão muscular.',
        'confirmado',
        NULL,
        NULL
    ), (
        5,
        5,
        '2023-11-18 10:30:00',
        'Reabilitação pós-acidente.',
        'confirmado',
        NULL,
        NULL
    ), (
        6,
        1,
        '2023-11-20 09:30:00',
        'Consulta de rotina.',
        'confirmado',
        NULL,
        NULL
    ), (
        7,
        1,
        '2023-11-22 16:15:00',
        'Acompanhamento pós-cirúrgico.',
        'confirmado',
        NULL,
        NULL
    ), (
        8,
        1,
        '2023-11-24 12:45:00',
        'Avaliação inicial.',
        'confirmado',
        NULL,
        NULL
    ), (
        9,
        1,
        '2023-11-26 14:00:00',
        'Tratamento de lesão muscular.',
        'confirmado',
        NULL,
        NULL
    ), (
        10,
        1,
        '2023-11-28 10:00:00',
        'Reabilitação pós-acidente.',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-15 10:00:00',
        'Consulta de rotina',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-15 10:00:00',
        'Acompanhamento pós-cirúrgico',
        'confirmado',
        NULL,
        NULL
    ), (
        8,
        1,
        '2023-11-24 12:45:00',
        'Avaliação inicial',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-15 10:30:00',
        'Tratamento de lesão muscular',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-15 13:30:00',
        'Reabilitação pós-acidente',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-06 01:30:00',
        'Consulta de rotina',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-15 11:30:00',
        'Acompanhamento pós-cirúrgico',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-15 11:00:00',
        'Avaliação inicial',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-15 15:30:00',
        'Tratamento de lesão muscular',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-08 11:30:00',
        'Reabilitação pós-acidente',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-15 12:30:00',
        'Consulta de rotina',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-08 10:00:00',
        'Acompanhamento pós-cirúrgico',
        'confirmado',
        NULL,
        NULL
    ), (
        1,
        1,
        '2023-11-17 12:30:00',
        'Avaliação inicial',
        'confirmado',
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
        'Demonstração de Exercício 1',
        'Um vídeo demonstrando um exercício específico.',
        'https://www.youtube.com/watch?v=tkjcnh1NPEw'
    ), (
        2,
        'Imagem',
        'Imagem Anatômica',
        'Uma imagem anatômica para referência.',
        'https://picsum.photos/201'
    ), (
        1,
        'GIF',
        'GIF de Exercício Terapêutico',
        'Um GIF demonstrando um exercício terapêutico.',
        'https://media.tenor.com/6gHLhmwO87sAAAAi/gg.gif'
    ), (
        1,
        'Video',
        'Sessão de Fisioterapia',
        'Um vídeo de uma sessão de fisioterapia em andamento.',
        'https://www.youtube.com/watch?v=2'
    ), (
        2,
        'Imagem',
        'Gráfico de Músculos',
        'Um gráfico de músculos para fins educacionais.',
        'https://picsum.photos/202'
    ), (
        4,
        'GIF',
        'GIF de Exercício de Reabilitação',
        'Um GIF demonstrando um exercício de reabilitação.',
        'https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExdmVoMnhqNTNtcWExdzhuejNkejlmM2NoYWk4bnNiMGtxYzU2cWI1ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/fZDXPWad3Rhx0H0gDp/giphy.gif'
    ), (
        1,
        'Video',
        'Rotina de Alongamento',
        'Um vídeo mostrando uma rotina de alongamento.',
        'https://www.youtube.com/watch?v=3'
    ), (
        5,
        'Imagem',
        'Imagem de Lesão no Tornozelo',
        'Uma imagem de lesão no tornozelo para diagnóstico.',
        'https://picsum.photos/203'
    ), (
        4,
        'GIF',
        'GIF de Técnica de Exercício Terapêutico',
        'Um GIF ilustrando uma técnica de exercício terapêutico.',
        'https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExdmVoMnhqNTNtcWExdzhuejNkejlmM2NoYWk4bnNiMGtxYzU2cWI1ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/fZDXPWad3Rhx0H0gDp/giphy.gif'
    ), (
        1,
        'Video',
        'Video de Prevenção de Lesões',
        'Um vídeo sobre a prevenção de lesões comuns.',
        'https://www.youtube.com/watch?v=4'
    ), (
        1,
        'Imagem',
        'Raio-X da Coluna',
        'Uma imagem de raio-X da coluna para avaliação.',
        'https://picsum.photos/204'
    ), (
        5,
        'GIF',
        'GIF de Exercício de Equilíbrio',
        'Um GIF demonstrando um exercício de equilíbrio.',
        'https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExdmVoMnhqNTNtcWExdzhuejNkejlmM2NoYWk4bnNiMGtxYzU2cWI1ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/fZDXPWad3Rhx0H0gDp/giphy.gif'
    ), (
        2,
        'Video',
        'Correção de Postura',
        'Um vídeo sobre a correção de postura para melhor saúde.',
        'https://www.youtube.com/watch?v=5'
    ), (
        4,
        'Imagem',
        'Anatomia do Joelho',
        'Uma imagem mostrando a anatomia da articulação do joelho.',
        'https://picsum.photos/205'
    ), (
        1,
        'GIF',
        'GIF de Exercício de Amplitude de Movimento',
        'Um GIF demonstrando um exercício de amplitude de movimento.',
        'https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExdmVoMnhqNTNtcWExdzhuejNkejlmM2NoYWk4bnNiMGtxYzU2cWI1ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/fZDXPWad3Rhx0H0gDp/giphy.gif'
    ), (
        1,
        'Video',
        'Fortalecimento do Núcleo',
        'Um vídeo demonstrando exercícios de fortalecimento do núcleo.',
        'https://www.youtube.com/watch?v=6'
    ), (
        2,
        'Imagem',
        'Gráfico de Flexibilidade',
        'Um gráfico de flexibilidade para avaliação.',
        'https://picsum.photos/206'
    ), (
        4,
        'GIF',
        'GIF de Exercício de Relaxamento',
        'Um GIF mostrando exercícios de relaxamento.',
        'https://www.google.com/url?sa=i&url=https%3A%2F%2Fgiphy.com%2Fstickers%2Fwork-progress-workinprogress-Lr4HRF6DEEJo90SQXF&psig=AOvVaw1vMC_mja0Ez4dXhQTbAyC1&ust=1698546938031000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCIj_jcnal4IDFQAAAAAdAAAAABAP'
    ), (
        1,
        'Video',
        'Reabilitação do Ombro',
        'Um vídeo de reabilitação do ombro após lesão.',
        'https://www.youtube.com/watch?v=hZPbQZUqjKA'
    ), (
        5,
        'Imagem',
        'Anatomia do Quadril',
        'Uma imagem mostrando a anatomia da articulação do quadril.',
        'https://picsum.photos/207'
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
        `exercicio__id`,
        `midia__id`,
        `midia_fisioterapeuta__id`
    )
VALUES (1, 1, 1), (2, 2, 2);

-- Populando a tabela tratamento_has_exercicios:

INSERT INTO
    `fisiomais_db`.`tratamento_has_exercicios` (
        `tratamento__id`,
        `exercicio__id`
    )
VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9);