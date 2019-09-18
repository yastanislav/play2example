-- Users schema

-- !Ups

CREATE TABLE brand (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    country varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `model` (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    year_prod_start varchar(4) NOT NULL,
    year_prod_end varchar(4) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE car (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    brand_id bigint(20) NOT NULL,
    model_id bigint(20) NOT NULL,
    year_prod varchar(4) NOT NULL,
    cost integer NOT NULL,
    PRIMARY KEY (id)
);

-- !Downs

DROP TABLE brand;
DROP TABLE `model`;
DROP TABLE car;