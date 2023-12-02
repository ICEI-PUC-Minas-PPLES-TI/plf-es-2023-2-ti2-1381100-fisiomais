# Fisio+

### Lista de Contatos:
- **Bruna Ribeiro Pérez**  
  **E-mail**: 1426468@sga.pucminas.br

- **Guilherme Henrique Coelho Santos**  
  **E-mail**: 1395175@sga.pucminas.br

- **José Victor Mendes Dias**  
  **E-mail**: 1433596@sga.pucminas.br

- **Marco Antonio Miranda Ferreira**  
  **E-mail**: 28064@sga.pucminas.br

- **Rubens Marcelo Ramos dos Santos**  
  **E-mail**: 1391000@sga.pucminas.br

---

Professores:

**Prof. Danilo Boechat Seufitelli**

**Prof. Hugo Bastos de Paula**

**Prof. Luiz Henrique da Costa Silva**

---

_Curso de Engenharia de Software, Unidade Praça da Liberdade_

_Instituto de Informática e Ciências Exatas – Pontifícia Universidade Católica de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil_

---


## 1. Introdução

_O nosso sistema proporciona uma experiência integrada de cuidados de saúde, conectando pacientes e fisioterapeutas._

### 1.1 Contextualização

A fisioterapia, tradicionalmente utilizada para reabilitação pós-traumática, evoluiu ao longo dos anos e atualmente aborda uma série de complicações, como complicações motoras, cognitivas e neurológicas. Com o avanço da tecnologia, a fisioterapia online surge como uma ferramenta importante, proporcionando acesso flexível e remoto ao tratamento. Em um contexto pandêmico vivenciado por todos, a fisioterapia online garantiu e continua garantindo a continuidade de tratamentos, diante das dificuldades enfrentadas. Bruno Combat, fisioterapeuta do Conselho Regional de Fisioterapia e Terapia Ocupacional, destaca a necessidade de uma abordagem abrangente, enfatizando que, enquanto técnicas de autocuidado são úteis, a intervenção direta de fisioterapeutas qualificados é indispensável para uma recuperação completa. Neste contexto, a fisioterapia online demonstra sua versatilidade e importância na reabilitação de pacientes afetados pela Covid-19 ou por qualquer outro tipo de complicação.

**Fonte: https://avozdaserra.com.br/noticias/o-importante-papel-da-fisioterapia-na-pandemia**

### 1.2 Problema

Consultas presenciais representam desafios para pacientes com restrições físicas, em termos de locomoção e custos associados a deslocamento e estacionamento. Além disso, com cerca de 70% dos médicos especialistas concentrados nas regiões Sudeste e Sul, muitas áreas enfrentam escassez desses profissionais. A telemedicina surge como solução, e um aplicativo dedicado poderia ampliar a acessibilidade e reduzir custos na área de fisioterapia.


### 1.3 Objetivo geral

_Nosso objetivo é criar um sistema integrado que atenda às demandas de pacientes e fisioterapeutas._

#### 1.3.1 Objetivos específicos

* Estabelecer um sistema de marcação e gerenciamento de consultas online, facilitando agendamentos, alterações e cancelamentos de consultas em tempo real.
* Desenvolver uma plataforma que facilite a “telefisioterapia”, permitindo consultas virtuais.
* Implementar vídeos e materiais educativos que auxiliem pacientes em seu tratamento.
* Implementar um sistema de notificações que mantenha pacientes e fisioterapeutas informados sobre suas consultas agendadas, canceladas e remarcadas.


### 1.4 Justificativas

A concentração de especialistas nas capitais e grande cidades da região Sudeste e Sul e os desafios de locomoção enfrentados por pacientes e fisioterapeutas, tornam o acesso limitado. Nosso sistema de fisioterapia, supera as barreiras geográficas, proporcionando um atendimento acessível e econômico para ambos.

## 2. Participantes do processo
Com base nas descrições fornecidas, podemos separar os participantes em dois grupos: 

### Grupo de Pacientes:
Esses são indivíduos que procuram tratamento e atendimento fisioterapêutico.

1. **Participante 1:**
   - **Nome:** Maria
   - **Idade:** 16 anos
   - **Gênero:** feminino
   - **Ocupação:** Estudante do ensino médio
   - **Descrição:** Adolescente com escoliose que passa muito tempo mexendo no computador. Seus pais trabalham o dia todo, o que impossibilita que alguém a leve para uma consulta.

2. **Participante 2:**
   - **Nome:** Augusto
   - **Idade:** 85 anos
   - **Gênero:** masculino
   - **Ocupação:** aposentado
   - **Descrição:** Idoso que mora em uma cidade pequena longe de uma capital, o que dificulta as possíveis consultas presenciais com um fisioterapeuta. Tem um familiar que é bom com tecnologia e o cadastrou no aplicativo para que ele pudesse tratar sua hérnia de disco.

### Grupo de Fisioterapeutas:
Esses são profissionais da fisioterapia buscando modernizar, expandir ou iniciar suas práticas.

1. **Participante 3:**
   - **Nome:** Lucas
   - **Idade:** 27 anos
   - **Gênero:** masculino
   - **Ocupação:** recém formado em fisioterapia
   - **Descrição:** Lucas é um recém formado que não dispõe de grande capital. Ainda não conseguiu um emprego fixo e deseja começar a carreira, podendo fidelizar sua clientela.

2. **Participante 4:**
   - **Nome:** Vitória
   - **Idade:** 50 anos
   - **Gênero:** feminino
   - **Ocupação:** 20 anos de carreira como fisioterapeuta
   - **Descrição:** Vitória é uma médica experiente que deseja atualizar sua gestão de atendimentos. Seu objetivo é atender mais pessoas de forma mais prática e eficiente, conquistando uma nova clientela.

## 3. Modelagem do processo de negócio
Neste capítulo, será discutido a modelagem do processo de negócio, apresentando uma proposta e detalhando os processos-chave envolvidos.
### 3.1. Análise da situação atual

Os sistemas atuais de consultas online usam plataformas de videoconferência, email e compartilhamento de documentos para conectar médicos e pacientes. Os pacientes agendam consultas por telefone ou email, realizam sessões de videoconferência para avaliação e recebem planos de tratamento, depois, seguem esses planos por conta própria, não possuindo opções de contato para atualizações e possíveis acompanhamentos e feedbacks das soluções propostas pelos médicos. ([AS-IS](as_is.md))

### 3.2. Descrição geral da proposta

Os sistemas existentes carecem de uma solução completa e especializada, principalmente se tratando do acompanhamento depois da consulta. Nossa proposta visa preencher essa lacuna, oferecendo uma plataforma que gerencia desde agendamentos até acompanhamento de progresso, proporcionando uma experiência mais eficiente e personalizada para fisioterapeutas e pacientes. Isso automatizaria tarefas, melhoraria a comunicação e garantiria a segurança das informações médicas. ([TO-BE](to-be.md))

### 3.3. Modelagem dos processos

[PROCESSO 1 - Solicitar Agendamento de Consulta](processo-1-solicitar-agendamento-de-consulta.md "Detalhamento do Processo 1.")

[PROCESSO 2 - Controle de Consultas pelo Fisioterapeuta](processo-2-controle-de-consultas.md "Detalhamento do Processo 2.")

[PROCESSO 3 - Cadastro de Paciente](processo-3-cadastro-de-pacientes.md "Detalhamento do Processo 3.")

[PROCESSO 4 - Cadastro de Fisioterapeuta](processo-4-nome-do-processo.md "Detalhamento do Processo 4.")

[PROCESSO 5 - Gerenciamento do Plano de Tratamento](processo-5-nome-do-processo.md "Detalhamento do Processo 5.")

[PROCESSO 6 - Acompanhamento Virtual](processo-6-acompanhamento-virtual.md "Detalhamento do Processo 6.")

[PROCESSO 7 - Gerenciar Mídias](processo-7-gerenciar-midias.md "Detalhamento do Processo 7.")

[PROCESSO 8 - Criar Plano](processo-8-criar-plano.md "Detalhamento do Processo 8.")

## 4. Projeto da solução

O documento a seguir apresenta o detalhamento do projeto da solução. São apresentadas cinco seções que descrevem, respectivamente: diagrama de classes, diagrama de componentes, diagrama de entidade-relacionamento, tecnologias utilizadas e guias de estilo.

[Projeto da solução](solution-design.md "Detalhamento do Projeto da solução: classes, componentes, der, tecnologias e guias de estilo.")


## 5. Indicadores de desempenho

O documento a seguir apresenta os indicadores de desempenho dos processos.

[Indicadores de desempenho dos processos](performance-indicators.md)


## 6. Interface do sistema

A sessão a seguir apresenta a descrição do produto de software desenvolvido. 

[Documentação da interface do sistema](interface.md)

## 7. Testes

A sessão a seguir apresenta a descrição dos testes de unidade e testes de usabilidade realizados. 

[Testes do sistema](tests.md)

## 8. Conclusão

_Apresente aqui a conclusão do seu trabalho. Discussão dos resultados obtidos no trabalho, onde se verifica as observações pessoais de cada aluno. Poderá também apresentar sugestões de novas linhas de estudo._

# REFERÊNCIAS

_Como um projeto de software não requer revisão bibliográfica, a inclusão das referências não é obrigatória. No entanto, caso você deseje incluir referências relacionadas às tecnologias, padrões, ou metodologias que serão usadas no seu trabalho, relacione-as de acordo com a ABNT._

_Verifique no link abaixo como devem ser as referências no padrão ABNT:_

http://www.pucminas.br/imagedb/documento/DOC_DSC_NOME_ARQUI20160217102425.pdf


**[1.1]** - _ELMASRI, Ramez; NAVATHE, Sham. **Sistemas de banco de dados**. 7. ed. São Paulo: Pearson, c2019. E-book. ISBN 9788543025001._

**[1.2]** - _COPPIN, Ben. **Inteligência artificial**. Rio de Janeiro, RJ: LTC, c2010. E-book. ISBN 978-85-216-2936-8._

**[1.3]** - _CORMEN, Thomas H. et al. **Algoritmos: teoria e prática**. Rio de Janeiro, RJ: Elsevier, Campus, c2012. xvi, 926 p. ISBN 9788535236996._

**[1.4]** - _SUTHERLAND, Jeffrey Victor. **Scrum: a arte de fazer o dobro do trabalho na metade do tempo**. 2. ed. rev. São Paulo, SP: Leya, 2016. 236, [4] p. ISBN 9788544104514._

**[1.5]** - _RUSSELL, Stuart J.; NORVIG, Peter. **Inteligência artificial**. Rio de Janeiro: Elsevier, c2013. xxi, 988 p. ISBN 9788535237016._



# APÊNDICES


_Atualizar os links e adicionar novos links para que a estrutura do código esteja corretamente documentada._


## Apêndice A - Código fonte

[Código do front-end](../src/front) -- repositório do código do front-end

[Código do back-end](../src/back)  -- repositório do código do back-end


## Apêndice B - Apresentação final


[Slides da apresentação final](presentations/arquivo.pdf)


[Vídeo da apresentação final](video/arquivo.mp4)






