## Descrição da Imagem Docker

A imagem Docker é construída a partir da imagem oficial do MySQL 8.0. Ela configura um ambiente MySQL com as seguintes características:

### Variáveis de Ambiente do MySQL

Esta imagem define duas variáveis de ambiente para configurar o MySQL:

- `MYSQL_ROOT_PASSWORD`: Define a senha do usuário root do MySQL como "1234".
- `MYSQL_DATABASE`: Cria um banco de dados chamado "fisiomais_db".

### Script de Inicialização da Base de Dados

A imagem copia automaticamente um script chamado `database_creation.sql` para dentro do contêiner. Esse script é executado automaticamente quando o contêiner é iniciado e é usado para criar tabelas e definir dados iniciais no banco de dados.

### Exposição de Porta

A imagem exporta a porta 3306 para o host, permitindo que você se conecte ao banco de dados MySQL externamente.

## Como Usar a Imagem Docker

Aqui estão os passos para usar esta imagem Docker:

1. **Construindo a Imagem Docker:**

   Para construir a imagem Docker, você pode executar o seguinte comando no diretório que contém o Dockerfile:

   ```bash
   docker build -t fisiomais-mysql .
   ```

2. **Executando o Contêiner:**

   Após a construção da imagem, você pode iniciar um contêiner com o seguinte comando:

   ```bash
   docker run -d --name fisiomais-mysql-container -p 3306:3306 -v mysql_data:/var/lib/mysql fisiomais-mysql
   ```

   Isso iniciará o contêiner e mapeará a porta 3306 do contêiner para a porta 3306 do host.
   Caso queria remove-lo após a execução execute:
   ```bash
   docker run --rm -d --name fisiomais-mysql-container -p 3306:3306 -v mysql_data:/var/lib/mysql fisiomais-mysql
   ```

3. **Parando o Contêiner:**

   Para parar o contêiner, você pode usar o comando:

   ```bash
   docker stop fisiomais-mysql-container
   ```

4. **Reiniciando o Contêiner:**

   Para reiniciar o contêiner, você pode usar o comando:

   ```bash
   docker restart fisiomais-mysql-container
   ```

5. **Removendo o Contêiner:**

   Para remover o contêiner, execute:

   ```bash
   docker rm fisiomais-mysql-container
   ```

6. **Limpando a Base de Dados:**

   Se você deseja limpar a base de dados (remover o contêiner e o volume associado), você pode usar o seguinte comando:

   ```bash
   docker stop fisiomais-mysql-container
   docker rm -f fisiomais-mysql-container
   docker volume rm mysql_data
   rm -rf mysql_data/*
   docker run -d --name fisiomais-mysql-container -p 3306:3306 -v mysql_data:/var/lib/mysql fisiomais-mysql
   ```

7. **Recuperando o Dump Inicial:**

   Se precisar recuperar o dump inicial do banco de dados, use o seguinte comando:

   ```bash
   docker exec -i fisiomais-mysql-container mysql -uroot -p1234 < database_data_initialization_script.sql
   ```

## Script de gestão

Para facilitar a gestão dessa imagem foi criado um bash script [`run-local-db.sh`](./Bash-script.md):