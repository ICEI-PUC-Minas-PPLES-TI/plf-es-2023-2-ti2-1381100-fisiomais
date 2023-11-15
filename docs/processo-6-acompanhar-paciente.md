### 3.3.6 Processo 6 – Acompanhar Paciente

O acompanhamento virtual é um processo essencial para a comunicação entre o fisioterapeuta e o paciente em um cenário onde as consultas presenciais não são sempre possíveis ou práticas. Este processo envolve a realização de sessões de videoconferência, fornecimento de recursos educacionais e coleta de feedback e avaliação do atendimento virtual. As oportunidades de melhoria aqui focam na otimização da interface, conexão com outras plataformas de "telessaúde" e aprimoramento da experiência do usuário.

![Imagem](../assets/processes/processo-6-acompanhar-paciente.png)

#### Detalhamento das atividades

---

#### **Atividade: Verificar se há consultas agendadas**

**Objetivo:** Permitir ao fisioterapeuta uma rápida visualização das consultas que estão programadas para determinados dias e horários.

| **Campo**              | **Tipo**               | **Restrições**                     | **Valor default** |
| ---------------------- | ----------------------  | ---------------------------------- | ----------------- |
| Data da Verificação    | Data                   | Deve ser uma data válida           | Data atual        |
| Consultas do Dia       | Tabela                 | -                                  | -                 |
| Status da Consulta     | Seleção única          | Confirmada, Pendente, Cancelada    | -                 |

| **Comandos**           | **Destino**                      | **Tipo** |
| ---------------------- | -------------------------------  | -------- |
| Detalhar               | Tela de Detalhes da Consulta     | link     |
| Atualizar              | Atualização da lista de consultas| button   |

---

#### **Atividade: Realizar e registrar acompanhamento das consultas**

**Objetivo:** Facilitar o acompanhamento de pacientes, permitindo que o profissional visualize informações detalhadas.

| **Campo**       | **Tipo**       | **Restrições** | **Valor default** |
| --------------- | -------------- | ---------------| ----------------- |
| Lista de Pacientes| Tabela       | -              | -                 |

| **Comandos**    | **Destino**               | **Tipo** |
| --------------- | ------------------------- | -------- |
| Selecionar      | Detalhes do Acompanhamento| link     |

---

#### **Atividade: Realizar ajustes necessarios**

**Objetivo:** Permitir que o profissional faça ajustes conforme necessário após o acompanhamento.

| **Campo**       | **Tipo**       | **Restrições** | **Valor default** |
| --------------- | -------------- | ---------------| ----------------- |
| Ajustes         | Área de texto  | -              | -                 |

| **Comandos**    | **Destino**               | **Tipo** |
| --------------- | ------------------------- | -------- |
| Confirmar       | Confirmar Ajustes         | default  |

---

#### **Atividade: Enviar notificação de ajustes para o cliente**

**Objetivo:** Comunicar ao paciente qualquer ajuste feito pelo profissional.

| **Campo**       | **Tipo**       | **Restrições** | **Valor default** |
| --------------- | -------------- | ---------------| ----------------- |
| Mensagem        | Área de texto  | -              | -                 |

| **Comandos**    | **Destino**               | **Tipo** |
| --------------- | ------------------------- | -------- |
| Enviar          | Enviar Ajustes            | default  |

---

#### **Atividade: Receber notificação sobre ajustes feitos**

**Objetivo:** Garantir que o paciente seja informado sobre quaisquer ajustes realizados pelo profissional.

| **Campo**       | **Tipo**       | **Restrições** | **Valor default** |
| --------------- | -------------- | ---------------| ----------------- |
| Notificação     | Área de texto  | -              | -                 |

| **Comandos**    | **Destino**               | **Tipo** |
| --------------- | ------------------------- | -------- |
| Visualizar      | Visualizar Notificação    | link     |

---

#### **Atividade: Confirmar Recebimento de ajustes**

**Objetivo:** Assegurar que o paciente confirmou o recebimento das notificações de ajustes.

| **Campo**       | **Tipo**       | **Restrições** | **Valor default** |
| --------------- | -------------- | ---------------| ----------------- |
| Confirmar       | Botão          | -              | -                 |

| **Comandos**    | **Destino**               | **Tipo** |
| --------------- | ------------------------- | -------- |
| Confirmar       | Confirmação do Recebimento| default  |
|

---
