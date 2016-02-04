create database if not exists photoparserdb;
use photoparserdb;

create table if not exists portfolio (
  id int NOT NULL AUTO_INCREMENT ,
    url varchar(255) NOT NULL,
    author varchar(255) NOT NULL,
  primary key (id)
);

create table if not exists album(
  id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    author varchar(255) NOT NULL references portfolio(author),
    portfolio_id int references portfolio(id),
  primary key(id)
);

create table if not exists image(
  id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    author varchar(255) NOT NULL references portfolio(author),
    width int,
    height int,
    alt int,
    portfolio_id int references portfolio(id),
    album_id int references album(id),
  primary key(id)
);
