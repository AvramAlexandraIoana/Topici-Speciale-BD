insert into country(country_name) values('S.U.A');
insert into country(country_name) values('Romania');

INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MANAGER');

INSERT INTO users(email, name, password, username) VALUES('ioana-alexandra.avram@my.fmi.unibuc.ro', 'Admin', '$2a$10$HSvlNaiopJs.K0NmFmUkMuFqRiphlKsmLIDbYilJg97Kd7z1bu9ya', 'Admin');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES (1, 2);
