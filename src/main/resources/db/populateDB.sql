DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2015-01-01 01:00:01', 'one', 201, 100000),
  ('2015-01-01 02:00:01', 'two', 202, 100000),
  ('2015-01-01 03:00:01', 'three', 203, 100000),
  ('2015-01-02 04:00:01', 'four', 2004, 100000),
  ('2015-01-02 05:00:01', 'five', 205, 100000),
  ('2015-01-02 06:00:01', 'six', 206, 100000),

  ('2015-01-02 00:00:01', 'admin1', 200, 100001),
  ('2015-01-02 00:00:01', 'admin2', 350, 100001),
  ('2015-01-02 00:00:01', 'admin3', 240, 100001),
  ('2015-01-03 00:00:01', 'admin4', 560, 100001),
  ('2015-01-03 00:00:01', 'admin5', 840, 100001),
  ('2015-01-03 00:00:01', 'admin6', 720, 100001);


