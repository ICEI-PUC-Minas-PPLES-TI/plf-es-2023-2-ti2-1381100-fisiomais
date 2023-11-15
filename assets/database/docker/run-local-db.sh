#!/bin/bash

# Define the Docker image and container name
IMAGE_NAME="fisiomais-mysql"
CONTAINER_NAME="fisiomais-mysql-container"
NETWORK_NAME='fisiomais-network'

# Function to build the Docker image
build_image() {
    docker build -t $IMAGE_NAME .
}

# Function to run the Docker container with a volume
run_container() {
    docker run -d --name $CONTAINER_NAME -p 3306:3306 \
        --network $NETWORK_NAME \
        -v mysql_data:/var/lib/mysql \
        $IMAGE_NAME
    # recover_initial_dump
}

# Function to stop the Docker container
stop_container() {
    docker stop $CONTAINER_NAME
}

# Function to restart the Docker container
restart_container() {
    stop_container
    run_container
}

# Function to remove the Docker container
remove_container() {
    stop_container
    docker rm $CONTAINER_NAME
}

# Function to clean the database (remove container and volume)
clean_database() {
    stop_container

    # Check if the mysql_data_backups directory exists; if not, create it
    if [ ! -d "mysql_data_backups" ]; then
        mkdir mysql_data_backups
    fi

    # Create a timestamp for the backup filename
    timestamp=$(date +"%Y%m%d%H%M%S")

    # Check if there are any files or directories in the mysql_data directory
    if [ "$(ls -A mysql_data)" ]; then
        # Backup the MySQL data
        tar -zcvf "mysql_data_backups/mysql_data_backup_$timestamp.tar.gz" -C mysql_data .
    else
        echo "No data to backup in mysql_data directory."
    fi

    # Remove the container
    docker rm -f $CONTAINER_NAME

    # Remove the associated volume
    docker volume rm mysql_data

    # Clear the mysql_data directory
    rm -rf mysql_data/*

    run_container

    # recover_initial_dump
}

recover_initial_dump(){
    docker exec -i $CONTAINER_NAME mysql -uroot -p1234 < database_data_initialization_script.sql
}

create_db(){
    docker exec -i $CONTAINER_NAME mysql -uroot -p1234 < database_creation.sql
}

kill_all_fisiomais_containers(){
    docker stop -f $(docker ps -a | grep fisiomais | awk '{print $1}')
    docker rm -f $(docker ps -a | grep fisiomais | awk '{print $1}')
}

prune_all_fisiomais_images(){
    docker rmi -f $(docker images | grep fisiomais | awk '{print $3}')
}

kill_all_fisiomais_networks(){
    docker network stop $(docker network ls | grep fisiomais | awk '{print $1}')
    docker network rm $(docker network ls | grep fisiomais | awk '{print $1}')
}

create_fisiomais_network(){
    docker network create $NETWORK_NAME
}

start_all(){
    echo "Removendo containers e imagens antigos"
    kill_all_fisiomais_containers > /dev/null 2>&1
    sleep 1
    echo "Removendo imagens antigas"
    prune_all_fisiomais_images > /dev/null 2>&1
    sleep 1
    echo "Buildando imagem"
    build_image > /dev/null 2>&1
    sleep 2
    echo "Criando rede"
    kill_all_fisiomais_networks > /dev/null 2>&1
    sleep 1
    create_fisiomais_network > /dev/null 2>&1
    sleep 2
    echo "Rodando container"
    run_container > /dev/null 2>&1
    sleep 2
    echo "Criando banco de dados"
    create_db > /dev/null 2>&1
    sleep 2
    echo "Recuperando dump inicial"
    recover_initial_dump > /dev/null 2>&1
    echo -e "\e[32mFinalizado\e[0m"
}


# Display usage instructions
usage() {
    echo "Usage: $0 [build|run|stop|restart|remove|clean|initial_dump|create_db|start_all]"
    echo "Start_all: Build the Docker image, run the Docker container, create the database and recover the initial dump"
    echo "Build: Build the Docker image"
    echo "Run: Run the Docker container"
    echo "Stop: Stop the Docker container"
    echo "Restart: Restart the Docker container"
    echo "Remove: Remove the Docker container"
    echo "Clean: Clean the database (remove container and volume)"
    echo "Initial_dump: Recover the initial dump"
    echo "Create_db: Create the database"
    exit 1
}

# Main script
case "$1" in
    start_all) start_all ;;
    build) build_image ;;
    run) run_container ;;
    stop) stop_container ;;
    restart) restart_container ;;
    remove) remove_container ;;
    clean) clean_database ;;
    initial_dump) recover_initial_dump ;;
    create_db) create_db ;;
    *) usage ;;
esac

exit 0