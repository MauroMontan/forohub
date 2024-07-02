create table users (
  id BIGINT not null AUTO_INCREMENT,
  name varchar(255) not null,
  email varchar(255) not null unique,
  password varchar(255) not null,
  PRIMARY KEY (id)
);
