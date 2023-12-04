CREATE TABLE IF NOt EXISTS lecture (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (250) NOT NULL,
    qualifications VARCHAR (600) NOT NULL,
    picture VARCHAR (200) DEFAULT NULL,
    linkdin VARCHAR (2000) DEFAULT NULL
    );

CREATE TABLE IF NOt EXISTS full_time_rank (
   lecturer_id INT NOT NULL,
   `rank` INT NOT NULL,
    CONSTRAINT pk_full PRIMARY KEY (lecturer_id , `rank`),
    CONSTRAINT fk_full FOREIGN KEY (lecturer_id) REFERENCES lecture (id)
    );

CREATE TABLE IF NOt EXISTS part_time_rank (
   lecturer_id INT NOT NULL,
   `rank` INT NOT NULL,
    CONSTRAINT pk_part PRIMARY KEY (lecturer_id , `rank`),
    CONSTRAINT fk_part FOREIGN KEY (lecturer_id) REFERENCES lecture (id)
    );