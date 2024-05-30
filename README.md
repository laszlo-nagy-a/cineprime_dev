# Cineprime_dev Apllication

## System requirements
- Java 17
- SpringBoot 3.2.4 version

## Dependencies
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- lombok
- mariadb-java-client
- jakarta.persistence-api

## Build
1. Create an SQL server with port 3306
2. Create user "root" with "root" password for the SQL server
   1. To create and start SQL server with specified user and password use the docker-compose.yml file
      1. Install Docker desktop app
      2. Run docker yml file via CLI, which is included: ../docker-compose.yml
3. Run structure SQL from ../main/resources/database/structure-sql.sql
4. Start the application
5. Test it VIA Postman
   1. Collection file included: ../src/test/CinePrime.postman_collection.json
