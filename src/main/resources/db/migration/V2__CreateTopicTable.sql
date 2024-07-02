create table topics (
  id BIGINT not null AUTO_INCREMENT,
  title varchar(255) not null,
  message varchar(255) not null,
  creation_date DATE not null,
  status varchar(255) not null,
  PRIMARY KEY (id)
);