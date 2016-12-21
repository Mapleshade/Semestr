﻿DROP TABLE IF EXISTS user_points_in_spec, user_achive, user_points, users, costs, ExtraPoints, points, achivements, specialities, subjects, universities;

CREATE TABLE universities (
  id        SERIAL PRIMARY KEY,
  univ_name VARCHAR     NOT NULL,
  country   VARCHAR(20) NOT NULL,
  city      VARCHAR(20) NOT NULL,
  address   TEXT,
  about     TEXT
);

CREATE TABLE subjects (
  id       INT PRIMARY KEY,
  sub_name VARCHAR(20)
);


CREATE TABLE specialities (
  id        SERIAL PRIMARY KEY,
  spec_name VARCHAR NOT NULL,
  subject1  INT     NOT NULL,
  subject2  INT     NOT NULL,
  subject3  INT     NOT NULL,
  subject4  INT,
  subject5  INT,
  about     TEXT,
  FOREIGN KEY (subject1) REFERENCES subjects (id),
  FOREIGN KEY (subject2) REFERENCES subjects (id),
  FOREIGN KEY (subject3) REFERENCES subjects (id),
  FOREIGN KEY (subject4) REFERENCES subjects (id),
  FOREIGN KEY (subject5) REFERENCES subjects (id)
);

CREATE TABLE achivements (
  id       SERIAL PRIMARY KEY,
  ach_sub  INT REFERENCES subjects (id),
  ach_name TEXT
);


CREATE TABLE points (
  univ_id             INT,
  spec_id             INT,
  budjet              INT CHECK (budjet > 0),
  day_contract        INT CHECK (day_contract > 0),
  evening_form        INT CHECK (evening_form > 0),
  correspondence_form INT CHECK (correspondence_form > 0),
  FOREIGN KEY (univ_id) REFERENCES universities (id) ON DELETE CASCADE,
  FOREIGN KEY (spec_id) REFERENCES specialities (id) ON DELETE CASCADE,
  PRIMARY KEY (univ_id, spec_id)
);


CREATE TABLE ExtraPoints (
  univ_id INT REFERENCES universities (id),
  ach_id  INT REFERENCES achivements (id),
  points  INT CHECK (points > 0),
  PRIMARY KEY (univ_id, ach_id)
);

CREATE TABLE costs (
  univ_id             INT REFERENCES universities (id) ON DELETE CASCADE,
  spec_id             INT REFERENCES specialities (id) ON DELETE CASCADE,
  day_contract        NUMERIC NOT NULL,
  evening_form        NUMERIC,
  correspondence_form NUMERIC,
  PRIMARY KEY (univ_id, spec_id)
);

CREATE TABLE users (
  id           SERIAL PRIMARY KEY,
  user_name    VARCHAR(15),
  user_surname VARCHAR(20),
  country      VARCHAR(20),
  city         VARCHAR(20),
  user_login   VARCHAR(20) NOT NULL,
  user_pasword VARCHAR(20) NOT NULL
);

CREATE TABLE user_points (
  user_id    INT REFERENCES users (id) ON DELETE CASCADE,
  subject_id INT REFERENCES subjects (id),
  points     INT CHECK (points <= 100),
  CONSTRAINT points_right CHECK (points >= 0)
);

CREATE TABLE user_achive (
  user_id INT REFERENCES users (id) ON DELETE CASCADE,
  ach_id  INT REFERENCES achivements (id)
);

CREATE TABLE user_points_in_spec (
  spec_id INT REFERENCES specialities (id) ON DELETE CASCADE,
  user_id INT REFERENCES users (id) ON DELETE CASCADE,
  points  INT
);





INSERT INTO subjects(id,sub_name) VALUES (1,'математика');
INSERT INTO subjects(id,sub_name) VALUES (2,'русский язык');
INSERT INTO subjects(id,sub_name) VALUES (3,'история');
INSERT INTO subjects(id,sub_name) VALUES (4,'информатика');
INSERT INTO subjects(id,sub_name) VALUES (5,'обществознание');
INSERT INTO subjects(id,sub_name) VALUES (6,'физика');
INSERT INTO subjects(id,sub_name) VALUES (7,'литература');
INSERT INTO subjects(id,sub_name) VALUES (8,'химия');
INSERT INTO subjects(id,sub_name) VALUES (9,'английский язык');
INSERT INTO subjects(id,sub_name) VALUES (10,'география');
INSERT INTO subjects(id,sub_name) VALUES (11,'биология');

INSERT into achivements(id, ach_sub, ach_name) VALUES (1,1,'Олимпиада "Золотое сечение"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (2,2,'Олимпиада "Золотое перо"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (3,3,'Олимпиада "Назад в прошлое"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (4,4,'Олимпиада "IT-KOT"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (5,5,'Олимпиада "Мнение"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (6,6,'Олимпиада "Гравицапа"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (7,7,'Олимпиада "Ломоносовские чтения"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (8,8,'Олимпиада "Атомы - основа всего"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (9,9,'Олимпиада "Английский бульдог"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (10,10,'Олимпиада "Вокруг света"');
INSERT into achivements(id, ach_sub, ach_name) VALUES (11,11,'Олимпиада "Мир вокруг нас"');

INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (1,1,5);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (1,2,10);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (1,3,5);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (1,4,15);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (1,5,5);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (2,6,5);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (2,7,10);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (2,8,5);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (2,9,4);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (2,10,9);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (3,11,5);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (3,1,6);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (3,2,9);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (3,3,6);
INSERT INTO extrapoints(univ_id, ach_id, points) VALUES (3,4,15);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3) VALUES (1,'программист',1,2,4);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3) VALUES (2,'переводчик',2,5,9);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3,subject4) VALUES (3,'юрист',1,2,3,5);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3,subject4,subject5) VALUES (4,'врач',1,2,5,8,11);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3) VALUES (5,'учитель',1,2,5);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3,subject4) VALUES (6,'журналист',2,3,5,7);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3) VALUES (7,'гид',2,3,10);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3) VALUES (8,'инженер',1,4,6);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3) VALUES (9,'ветеринар',8,10,11);
INSERT INTO specialities(id, spec_name, subject1, subject2, subject3) VALUES (10,'астроном',1,6,10);

INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (1,1,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (1,2,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (1,3,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (1,4,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (2,5,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (2,6,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (2,7,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (3,8,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (3,9,15,10,5,3);
INSERT INTO points (univ_id, spec_id, budjet, day_contract, evening_form, correspondence_form) VALUES (3,10,15,10,5,3);

INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (1,1,100000,90000,80000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (1,2,90000,80000,70000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (1,3,90000,80000,70000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (1,4,100000,90000,80000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (2,5,80000,70000,60000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (2,6,80000,70000,60000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (2,7,80000,70000,60000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (3,8,90000,80000,70000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (3,9,100000,90000,80000);
INSERT INTO costs (univ_id, spec_id, day_contract, evening_form, correspondence_form) VALUES (3,10,80000,70000,60000);


UPDATE points SET budjet = 200, day_contract = 180, evening_form = 170, correspondence_form = 170 where (univ_id = 1 and spec_id = 1);
UPDATE points SET budjet = 190, day_contract = 180, evening_form = 160, correspondence_form = 160 where (univ_id = 1 and spec_id = 2);
UPDATE points SET budjet = 190, day_contract = 170, evening_form = 170, correspondence_form = 160 where (univ_id = 1 and spec_id = 3);
UPDATE points SET budjet = 200, day_contract = 190, evening_form = 170, correspondence_form = 170 where (univ_id = 1 and spec_id = 4);
UPDATE points SET budjet = 150, day_contract = 130, evening_form = 140, correspondence_form = 130 where (univ_id = 2 and spec_id = 5);
UPDATE points SET budjet = 170, day_contract = 150, evening_form = 150, correspondence_form = 140 where (univ_id = 2 and spec_id = 6);
UPDATE points SET budjet = 200, day_contract = 180, evening_form = 170, correspondence_form = 170 where (univ_id = 2 and spec_id = 7);
UPDATE points SET budjet = 200, day_contract = 190, evening_form = 180, correspondence_form = 170 where (univ_id = 3 and spec_id = 8);
UPDATE points SET budjet = 200, day_contract = 180, evening_form = 170, correspondence_form = 170 where (univ_id = 3 and spec_id = 9);
UPDATE points SET budjet = 160, day_contract = 150, evening_form = 150, correspondence_form = 140 where (univ_id = 3 and spec_id = 10);
