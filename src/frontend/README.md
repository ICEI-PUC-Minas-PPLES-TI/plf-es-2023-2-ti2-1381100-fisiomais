# Frontend - Guia de Desenvolvimento

Este repositório contém o projeto frontend da plataforma ***Fisio+*** desenvolvido com React, Vite, e outras bibliotecas. Siga as instruções abaixo para configurar seu ambiente de desenvolvimento.

## Desenvolvimento Local

### Pré-requisitos

Certifique-se de ter o seguinte software instalado em seu sistema:

- [Node.js](https://nodejs.org/) (preferencialmente a versão 18.2.0)
- [Visual Studio Code](https://code.visualstudio.com/)
- Extensão "Remote - Containers" para o Visual Studio Code (opcional, apenas se você desejar usar o Dev Container)

Para desenvolver localmente, siga os passos abaixo:

1. Instale as dependências do projeto:

   ```bash
   npm install
   ```

2. Inicie o servidor de desenvolvimento:

   ```bash
   npm run dev
   ```

   Isso iniciará o servidor de desenvolvimento Vite. Abra seu navegador e acesse [http://localhost:5173](http://localhost:5173) para visualizar o aplicativo em desenvolvimento.


## Desenvolvimento com Dev Container (Visual Studio Code)

Se preferir, você pode usar um Dev Container do Visual Studio Code para configurar um ambiente de desenvolvimento isolado. Siga os passos abaixo:

1. Abra o Visual Studio Code na pasta do projeto.

2. Se você possui a extensão "Remote - Containers" instalada, uma notificação será exibida, sugerindo a abertura do projeto no Dev Container. Clique em "Reopen in Container".

3. O Visual Studio Code criará e abrirá um ambiente de desenvolvimento isolado usando Docker. Todas as ferramentas e dependências necessárias já estarão configuradas.

4. Dentro do Dev Container, você pode executar os mesmos comandos listados na seção "Desenvolvimento Local" para construir, executar e depurar seu aplicativo.