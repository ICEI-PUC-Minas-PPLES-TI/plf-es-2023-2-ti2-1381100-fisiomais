## Descrição do Script

O script Bash fornece uma interface simples para automatizar as tarefas comuns de gerenciamento do contêiner Docker. Aqui estão as funções disponíveis e suas descrições:

1. `build_image`: Esta função constrói a imagem Docker usando o Dockerfile atual.

2. `run_container`: Inicia um contêiner baseado na imagem Docker "fisiomais-mysql", mapeando a porta 3306 e usando um volume chamado "mysql_data" para armazenar os dados do MySQL.

3. `stop_container`: Para o contêiner em execução.

4. `restart_container`: Para e reinicia o contêiner.

5. `remove_container`: Remove o contêiner.

6. `clean_database`: Realiza um backup dos dados do MySQL, remove o contêiner e o volume associado e limpa o diretório de dados do MySQL. Em seguida, inicia um novo contêiner.

7. `recover_initial_dump`: Recupera o dump inicial do banco de dados.

## Como Usar o Script
Primeiramente dê permissão de execução para seu usuário utilizando o comando:
```bash
chmod +x run-local-db.sh
```

Para usar o script de gerenciamento do contêiner Docker, siga as instruções abaixo:

1. **Construir a Imagem Docker:**

   Para construir a imagem Docker "fisiomais-mysql", execute o seguinte comando:

   ```bash
   ./run-local-db.sh build
   ```

2. **Iniciar o Contêiner:**

   Para iniciar o contêiner, execute:

   ```bash
   ./run-local-db.sh run
   ```

3. **Parar o Contêiner:**

   Para parar o contêiner em execução, execute:

   ```bash
   ./run-local-db.sh stop
   ```

4. **Reiniciar o Contêiner:**

   Para parar e reiniciar o contêiner, execute:

   ```bash
   ./run-local-db.sh restart
   ```

5. **Remover o Contêiner:**

   Para remover o contêiner, execute:

   ```bash
   ./run-local-db.sh remove
   ```

6. **Limpar a Base de Dados:**

   Para limpar a base de dados, execute:

   ```bash
   ./run-local-db.sh clean
   ```

7. **Recuperar o Dump Inicial:**

   Para recuperar o dump inicial do banco de dados, execute:

   ```bash
   ./run-local-db.sh initial_dump
   ```

## Exemplo de Uso

Aqui está um exemplo de como usar o script para construir a imagem e iniciar o contêiner:

```bash
# Construir a imagem Docker
./run-local-db.sh build

# Iniciar o contêiner
./run-local-db.sh run

# popula com os dados iniciais
./run-local-db.sh initial_dump
```