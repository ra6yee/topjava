DELETE FROM user_roles;
DELETE FROM users;
delete from  meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);


insert into meals (userid, date_time, description, calories)
VALUES (100002, '2015-10-01 12:30', 'обед', 500),
 (100000, '2015-02-01 19:30', 'ужин', 500),
 (100000, '2015-03-01 22:30', ' перекус',500),
 (100001, '2015-04-01 10:30', 'завтрак', 500),
 (100001, '2015-05-01 14:30', 'обед', 1100),
 (100001, '2015-06-01 19:30', 'ужин', 500),
 (100001, '2015-07-01 22:50', 'обжираловка', 1500);