CREATE DATABASE cineprime CHARACTER SET utf8mb4;

CREATE TABLE genre (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    public_id VARCHAR(255) UNIQUE,
    name VARCHAR(500) NOT NULL,
    deleted_at DATETIME
	);
	
CREATE TABLE director (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    public_id VARCHAR(255) UNIQUE,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	age SMALLINT,
	birthdate DATE,
    deleted_at DATETIME
	);
	
CREATE TABLE writer (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    public_id VARCHAR(255) UNIQUE,
    first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	age SMALLINT,
	birthdate DATE,
    deleted_at DATETIME
	);
	
CREATE TABLE star (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    public_id VARCHAR(255) UNIQUE,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	age SMALLINT,
	birthdate DATE,
    deleted_at DATETIME
	);
	
CREATE TABLE movie (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    public_id VARCHAR(255) UNIQUE,
	title VARCHAR(500) NOT NULL,
    description VARCHAR(1024),
	release_date DATE,
	pg TINYINT,
	playtime_min SMALLINT UNSIGNED,
	last_modified_at TIMESTAMP,
    deleted_at DATETIME
	);
	
CREATE TABLE writer_movie (
	writer_id BIGINT NOT NULL,
	movie_id BIGINT NOT NULL,
	FOREIGN KEY (writer_id) REFERENCES writer(id),
	FOREIGN KEY (movie_id) REFERENCES movie(id)
	);
	
CREATE TABLE director_movie (
	director_id BIGINT NOT NULL,
	movie_id BIGINT NOT NULL,
	FOREIGN KEY (director_id) REFERENCES director(id),
	FOREIGN KEY (movie_id) REFERENCES movie(id)
	);
	
CREATE TABLE star_movie (
	star_id BIGINT NOT NULL,
	movie_id BIGINT NOT NULL,
	FOREIGN KEY (star_id) REFERENCES star(id),
	FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE genre_movie (
	genre_id BIGINT NOT NULL,
	movie_id BIGINT NOT NULL,
	FOREIGN KEY (genre_id) REFERENCES genre(id),
	FOREIGN KEY (movie_id) REFERENCES movie(id)
);
	