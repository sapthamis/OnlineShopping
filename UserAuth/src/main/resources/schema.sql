create table roles (ID BIGINT NOT NULL ,name varchar(60),primary key (id));
create table users (ID BIGINT NOT NULL ,email varchar(255), name varchar(255), password varchar(255), username varchar(255),primary key (id));
create table user_roles (user_id BIGINT NOT NULL , role_id BIGINT NOT NULL , primary key (user_id, role_id))