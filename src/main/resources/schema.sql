CREATE TABLE POKEMON (ID INT PRIMARY KEY,
                    NAME VARCHAR(25),
                    TYPE VARCHAR(25) );


-- CREATE TABLE trainer (ID INT PRIMARY KEY,
--                     NAME VARCHAR(25),
--                     LEVEL INT,
--                     pokemon_id INT,
--                     FOREIGN KEY (pokemon_id)
--                     REFERENCES POKEMON(ID)
--                     ON DELETE CASCADE)


CREATE TABLE trainer (NAME VARCHAR(25) PRIMARY KEY,
                    LEVEL INT,
                    first_pokemon INT,
                    second_pokemon INT,
                    third_pokemon INT,
                     FOREIGN KEY (first_pokemon)
                    REFERENCES POKEMON(ID),
                    FOREIGN KEY (second_pokemon)
                    REFERENCES POKEMON(ID),
                    FOREIGN KEY (third_pokemon)
                    REFERENCES POKEMON(ID))