CREATE TABLE games (id BIGINT identity NOT NULL, rounds_won INT NOT NULL, rounds_lost INT NOT NULL,  kills INT NOT NULL, assists INT NOT NULL, deaths INT NOT NULL, map_id BIGINT, user_id BIGINT, PRIMARY KEY (id));

CREATE TABLE maps (id BIGINT identity NOT NULL, file_name VARCHAR(255), name VARCHAR(255), PRIMARY KEY (id));

CREATE TABLE users (id BIGINT identity NOT NULL, username VARCHAR(255), password VARCHAR(255), PRIMARY KEY (id));

ALTER TABLE games ADD CONSTRAINT map_id_fk FOREIGN KEY (map_id) REFERENCES maps;

ALTER TABLE games ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users;