CREATE DATABASE IF NOT EXISTS movies;

USE movies;

CREATE TABLE IF NOT EXISTS movies(
    `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `genre` VARCHAR(255) NOT NULL,
    `url_stream` VARCHAR(255) NOT NULL
);

CREATE USER 'superuser'@'%' IDENTIFIED BY 'superuser';
GRANT ALL ON movies.* TO 'superuser'@'%';

CREATE USER 'movies_db'@'%' IDENTIFIED BY 'movies_db';
GRANT SELECT, UPDATE, INSERT, DELETE ON movies.* TO 'movies_db'@'%';

FLUSH PRIVILEGES;