## 5. Indicadores de desempenho

<!-- _Apresente aqui os principais indicadores de desempenho e algumas metas para o processo. Atenção: as informações necessárias para gerar os indicadores devem estar contempladas no diagrama de classes e no DER. Colocar no mínimo 5 indicadores._

Perspectivas de medidas de avaliação de desempenho de processos:

* Financeira
* Cliente
* Aprendizado e Crescimento
* Processos internos

Veja o link [Heflo - Types of Process Performance Metrics](https://www.heflo.com/blog/business-management/process-performance-metrics/)

_Usar o seguinte modelo:_

| **Indicador**               | **Objetivos**                                                         | **Descrição**                                             | **Fonte dados**     | **Perspectiva**           |
| --------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------- | ------------------- | ------------------------- |
| Percentual de reclamações   | Avaliar quantitativamente as reclamações                              | Percentual de reclamações em relação ao total atendimento | Tabela reclamações  | Aprendizado e Crescimento |
| Taxa de Requisições abertas | Melhorar a prestação de serviços medindo a porcentagem de requisições | Mede % de requisições atendidas na semana                 | Tabela solicitações | Processos internos        |
| Taxa de entrega de material | Manter controle sobre os materiais que estão sendo entregues          | Mede % de material entregue dentro do mês                 | Tabela Pedidos      | Clientes                  |
|                             |                                                                       |                                                           |                     |                           |

Obs.: todas as informações para gerar os indicadores devem estar no diagrama de classes e no DER. -->

Aqui serão apresentados os principais parâmetros de avaliação que permitem mensurar a eficiência, eficácia e qualidade dos processos da plataforma **Fisiomais**. Esses indicadores, também conhecidos como KPIs (Key Performance Indicators), são essenciais para avaliar o progresso dos processos e garantir a consecução dos objetivos estabelecidos. 

### Indicadores de Desempenho para o Processo de Solicitar Agendamento de Consulta - Processo 1


| **Indicador**                               | **Objetivos**                                                           | **Descrição**                                                                                               | **Fonte de Dados** | **Perspectiva**           |
| ------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ------------------ | ------------------------- |
| Taxa de Confirmação de Agendamentos por mês | Mensurar a eficácia na confirmação das consultas agendadas mensalmente. | Calcula a porcentagem de consultas confirmadas em relação ao total de consultas agendadas em escala mensal. | Tabela `consulta`  | Processos internos        |
| Taxa de Agendamentos Cancelados             | Minimizar cancelamentos                                                 | Mede a porcentagem de agendamentos cancelados em relação ao total de agendamentos                           | Tabela `consulta`  | Aprendizado e Crescimento |

### Indicadores de Desempenho para o Processo Controlar Consultas do Fisioterapeuta – Processo 2

| **Indicador** | **Objetivos** | **Descrição** | **Fonte dos Dados** | **Perspectiva** |
|---------------|---------------|---------------|---------------------|-----------------|
| Taxa de Consultas Concluídas | Maximizar a efetividade do tratamento | Mede a porcentagem de consultas realizadas com sucesso em relação ao total de consultas agendadas | Tabelas: `consultas` | Perspectiva do Cliente |
| Taxa de Reagendamentos | Reduzir a necessidade de reagendamento | Mede a frequência com que as consultas são reagendadas, indicando flexibilidade e disponibilidade do serviço | Tabelas: `consultas` | Perspectiva do Cliente | 

### Indicadores de Desempenho para o Processo Cadastrar paciente – Processo 3

| **Indicador** | **Objetivos** | **Descrição** | **Fonte dos Dados** | **Perspectiva** |
|---------------|---------------|---------------|---------------------|-----------------|
| Incremento Mensal de Registros de Pacientes | Analisar a expansão da base de pacientes e a efetividade das táticas de captação | Avalia a variação percentual, a cada mês, no total de pacientes recém-inscritos no sistema | Tabela `paciente` | Processos Internos |

### Indicadores de Desempenho para o Processo Cadastrar Fisioterapeuta –  Processo 4

| **Indicador** | **Objetivos** | **Descrição** | **Fonte dos Dados** | **Perspectiva** |
|---------------|---------------|---------------|---------------------|-----------------|
| Taxa de Crescimento de Cadastros de Fisioterapeutas | Avaliar o crescimento da rede de fisioterapeutas e a eficácia das estratégias de atração | Mede a variação percentual mensal no número de novos fisioterapeutas cadastrados no sistema | Tabela `fisioterapeuta` | Processos Internos |
| Indice de Perfis Completos | Garantir perfis completos dos fisioterapeutas | Percentual de fisioterapeutas com informações de contato e especialidade preenchidas após o cadastro | Tabela `fisioterapeuta` | Processos Internos |

### Indicadores de Desempenho para o Processo Acompanhar Paciente – Processo 6

| **Indicador** | **Objetivos** | **Descrição** | **Fonte dos Dados** | **Perspectiva** |
|---------------|---------------|---------------|---------------------|-----------------|
| Taxa de Satisfação do Paciente com o Acompanhamento Virtual | Melhorar a qualidade e eficácia do acompanhamento virtual | Mede o percentual de pacientes que avaliaram positivamente o acompanhamento virtual, incluindo a comunicação, os recursos fornecidos e o atendimento geral | Tabela: `acompanhamento_virtual` | Perspectiva do Cliente |
| Índice de Atividade de Acompanhamento | Manter a regularidade das sessões de acompanhamento | Mede a frequência média de sessões de acompanhamento realizadas, indicando a regularidade e o engajamento dos pacientes no processo. | Tabela: `acompanhamento_virtual`, coluna `data_sessao` | Processos Internos |




### Indicadores de Desempenho para o Processo Gerenciar Mídias - Processo 7

| **Indicador**                               | **Objetivos**                                                       | **Descrição**                                                                                                                         | **Fonte de Dados**                        | **Perspectiva**    |
| ------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------- | ------------------ |
| Taxa de Utilização de Mídias nos Exercícios | Avaliar a utilização das mídias nos exercícios prescritos.          | Calcula a porcentagem de exercícios que envolvem o uso de mídias (vídeos, imagens, GIFs) em relação ao total de exercícios prescritos | Tabelas: `midia` e `exercicio_has_midias` | Processos Internos |
| Taxa de Uso de Mídias por Tipo              | Identificar preferências de utilização de diferentes tipos de mídia | Mede a porcentagem de uso de cada tipo de mídia (vídeo, imagem, GIF) em relação ao total                                              | Tabelas: `midia` e `exercicio_has_midias` | Processos Internos |


### Indicadores de Desempenho Processo Criar Tratamento - Processo 8

| **Indicador**                               | **Objetivos**                                                       | **Descrição**                                                                                                                         | **Fonte de Dados**                        | **Perspectiva**    |
| ------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------- | ------------------ |
| Taxa de Criação de Tratamentos por Fisioterapeuta | Avaliar a quantidade de tratamentos criados pelos fisioterapeutas cadastrados.          | Calcula a porcentagem de tratamentos criados em relação ao total de cada fisioterapeuta | Tabelas: `fisioterapeuta` e `paciente` | Processos Internos |





---
