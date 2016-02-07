create database if not exists photoparserdb;
use photoparserdb;

create table if not exists portfolio (
    id int NOT NULL AUTO_INCREMENT ,
    url varchar(255) NOT NULL,
    author varchar(255),
    index(id),
    index(author),
    primary key (id)
)ENGINE=InnoDB CHARACTER SET=UTF8;

create table if not exists album(
    id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    author varchar(255),
    title varchar(255),
    portfolio_id int,
    index(portfolio_id),
    index(author),
    foreign key (author) references portfolio(author),
    foreign key (portfolio_id) references portfolio(id),
    primary key(id)
)ENGINE=InnoDB CHARACTER SET=UTF8;

create table if not exists image(
    id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    author varchar(255),
    width int,
    height int,
    alt varchar(255),
    portfolio_id int,
    album_id int,
    index(portfolio_id),
    index(album_id),
    index(author),
    foreign key(author) references portfolio(author),
    foreign key(portfolio_id) references portfolio(id),
    foreign key(album_id) references album(id),
    primary key(id)
)ENGINE=InnoDB CHARACTER SET=UTF8;

