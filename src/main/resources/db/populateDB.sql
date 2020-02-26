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

INSERT INTO meals (datetime, description, calories, user_id) VALUES
('20150601 13:00:00', 'Пользователь ланч', '500', 100000),
('20150601 20:00:00', 'Пользователь ужин', '1400', 100000),
('20150601 20:00:00', 'Админ еще ужин', '1400', 100001),
('20150601 14:00:00', 'Админ ланч', '510', 100001),
('20150601 21:00:00', 'Админ ужин', '1500', 100001),
('20150602 13:10:00', 'Пользователь ланч', '300', 100000),
('20150603 20:10:00', 'Пользователь ужин', '2200', 100000);