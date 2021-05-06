--Run these commands to insert some data into your DB

--To add Active Duty Maps
INSERT INTO maps (file_name, name)
VALUES ('de_inferno', 'Inferno'),
       ('de_train', 'Train'),
       ('de_mirage', 'Mirage'),
       ('de_nuke', 'Nuke'),
       ('de_overpass', 'Overpass'),
       ('de_dust2', 'Dust II'),
       ('de_vertigo', 'Vertigo');

--To add users
INSERT INTO users (username, password)
VALUES ('admin', '$2a$10$tcGTwDEo7lEuVhXv2UxhF..LEk4UgueCqL5olWKpE6a5/f79FRs/i'), --adminpw
       ('sipos', '$2a$10$DJ9j8jXljXh..7HWy8.MtOCbL2Pll6gNiYUs4lnRGJ1lgstPLvDTC'), --sipospw
       ('dani', '$2a$10$.B1eUdV1W86fDxgcwdicYu32ilJjA1r6POvYvaNb53lo.LIznj4p6');  --danipw

--To add games
INSERT INTO games (rounds_won, rounds_lost, kills, assists, deaths, map_id, user_id)
VALUES (10, 16, 29, 4, 19, 3, 2),
       (16, 7, 15, 4, 20, 2, 3),
       (16, 5, 25, 4, 13, 3, 2),
       (6, 16, 22, 4, 17, 1, 3),
       (16, 14, 27, 4, 20, 3, 2),
       (16, 8, 23, 4, 22, 6, 3),
       (4, 16, 18, 4, 25, 5, 2),
       (14, 16, 21, 4, 25, 7, 2);


--To remove every data in you database run
DELETE FROM games;
DBCC CHECKIDENT (games, RESEED, 0);
DELETE FROM maps;
DBCC CHECKIDENT (maps, RESEED, 0);
DELETE FROM users;
DBCC CHECKIDENT (users, RESEED, 0);