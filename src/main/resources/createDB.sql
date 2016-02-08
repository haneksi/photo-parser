create database if not exists photoparserdb;
use photoparserdb;

create table if not exists portfolio (
    portfolio_id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    author varchar(255),
    primary key(portfolio_id)
)ENGINE=InnoDB CHARACTER SET=UTF8;

create table if not exists album(
    album_id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    author varchar(255),
    title varchar(255),
    portfolio_id int not null,
    CONSTRAINT album_portfolio_fk
    FOREIGN KEY (portfolio_id)
    REFERENCES portfolio (portfolio_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    primary key(album_id)
)ENGINE=InnoDB CHARACTER SET=UTF8;

create table if not exists image(
    image_id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    author varchar(255),
    width int,
    height int,
    alt varchar(255),
    portfolio_id int not null,
    album_id int not null,
    CONSTRAINT image_portfolio_fk
    FOREIGN KEY (portfolio_id)
    REFERENCES portfolio (portfolio_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT image_album_fk
    FOREIGN KEY (album_id)
    REFERENCES album (album_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    primary key(image_id)
)ENGINE=InnoDB CHARACTER SET=UTF8;