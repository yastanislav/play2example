-- Users schema

-- !Ups

ALTER TABLE car DROP COLUMN brand_id;
ALTER TABLE model add COLUMN brand_id bigint(20) NOT NULL default 3;

-- !Downs

ALTER TABLE car add COLUMN brand_id bigint(20) NOT NULL default 3;
ALTER TABLE model DROP COLUMN brand_id;