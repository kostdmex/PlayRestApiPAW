# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE c_fk_name CHAR(255);
  DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
      AND REFERENCED_TABLE_NAME IS NOT NULL;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN curs;

  read_loop: LOOP
    FETCH curs INTO c_fk_name;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
  END LOOP;

  CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
  SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
  PREPARE stmt FROM @sql;
  EXECUTE stmt;
END
$$
create table activity (
  id                            integer auto_increment not null,
  add_date                      datetime(6),
  text_activity                 varchar(255),
  card_id                       integer not null,
  user_id                       integer not null,
  constraint pk_activity primary key (id)
);

create table board (
  id                            integer auto_increment not null,
  name                          varchar(255),
  team_id                       integer,
  background                    varchar(255),
  ispublic                      tinyint(1) default 0 not null,
  constraint pk_board primary key (id)
);

create table card (
  id                            integer auto_increment not null,
  title                         varchar(255),
  description                   varchar(255),
  label_id                      integer,
  number_on_list                integer,
  add_date                      datetime(6),
  list_id                       integer not null,
  constraint pk_card primary key (id)
);

create table comment (
  id                            integer auto_increment not null,
  add_date                      datetime(6),
  content                       varchar(255),
  user_id                       integer not null,
  card_id                       integer not null,
  constraint pk_comment primary key (id)
);

create table list (
  id                            integer auto_increment not null,
  name                          varchar(255),
  number_on_board               integer,
  board_id                      integer not null,
  constraint pk_list primary key (id)
);

create table team (
  id                            integer auto_increment not null,
  name                          varchar(255),
  name_short                    varchar(255),
  description                   varchar(255),
  site                          varchar(255),
  logo                          varchar(255),
  constraint pk_team primary key (id)
);

create table user (
  id                            integer auto_increment not null,
  name                          varchar(255),
  surname                       varchar(255),
  nickname                      varchar(255),
  password                      varchar(255),
  email                         varchar(255),
  constraint pk_user primary key (id)
);

create table user_board (
  id                            integer auto_increment not null,
  user_id                       integer,
  board_id                      integer,
  constraint pk_user_board primary key (id)
);

create table user_team (
  id                            integer auto_increment not null,
  user_id                       integer,
  team_id                       integer,
  constraint pk_user_team primary key (id)
);

create index ix_activity_card_id on activity (card_id);
alter table activity add constraint fk_activity_card_id foreign key (card_id) references card (id) on delete restrict on update restrict;

create index ix_activity_user_id on activity (user_id);
alter table activity add constraint fk_activity_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

create index ix_card_list_id on card (list_id);
alter table card add constraint fk_card_list_id foreign key (list_id) references list (id) on delete restrict on update restrict;

create index ix_comment_user_id on comment (user_id);
alter table comment add constraint fk_comment_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

create index ix_comment_card_id on comment (card_id);
alter table comment add constraint fk_comment_card_id foreign key (card_id) references card (id) on delete restrict on update restrict;

create index ix_list_board_id on list (board_id);
alter table list add constraint fk_list_board_id foreign key (board_id) references board (id) on delete restrict on update restrict;


# --- !Downs

alter table activity drop foreign key fk_activity_card_id;
drop index ix_activity_card_id on activity;

alter table activity drop foreign key fk_activity_user_id;
drop index ix_activity_user_id on activity;

alter table card drop foreign key fk_card_list_id;
drop index ix_card_list_id on card;

alter table comment drop foreign key fk_comment_user_id;
drop index ix_comment_user_id on comment;

alter table comment drop foreign key fk_comment_card_id;
drop index ix_comment_card_id on comment;

alter table list drop foreign key fk_list_board_id;
drop index ix_list_board_id on list;

drop table if exists activity;

drop table if exists board;

drop table if exists card;

drop table if exists comment;

drop table if exists list;

drop table if exists team;

drop table if exists user;

drop table if exists user_board;

drop table if exists user_team;

