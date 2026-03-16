# Backend do projeto Movie-Tracker (Spring Boot)

Backend da aplicação Movie Tracker desenvolvido com Spring Boot, Spring Data JPA e MySQL.

## Pré-requisitos

- Java 17+
- Maven 3.6+
- MySQL 8.0+

## Instalação

1. Configure o MySQL:
CREATE DATABASE IF NOT EXISTS movie_tracker;

2. Configure as credenciais no arquivo `src/main/resources/application.properties`:

spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

3. Instale as dependências e compile:

mvn clean install

## Executar a Aplicação

mvn spring-boot:run

A aplicação estará disponível em: `http://localhost:8080/api`