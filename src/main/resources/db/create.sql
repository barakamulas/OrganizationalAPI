SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
 id int PRIMARY KEY auto_increment,
 u_name VARCHAR,
 role VARCHAR,
 department_id INTEGER,
);

CREATE TABLE IF NOT EXISTS articles (
 id int PRIMARY KEY auto_increment,
 title VARCHAR,
 content VARCHAR
);

CREATE TABLE IF NOT EXISTS scoped_articles (
 id int PRIMARY KEY auto_increment,
 title VARCHAR,
 content VARCHAR,
 department_id INTEGER
);

CREATE TABLE IF NOT EXISTS departments (
 id int PRIMARY KEY auto_increment,
 d_name VARCHAR,
 description VARCHAR,
 no_of_employees INTEGER
);


