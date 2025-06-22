#!/bin/bash

echo "Contruyendo contenedores"
docker-compose build

echo "Inicializando contenedores"
docker-compose up

