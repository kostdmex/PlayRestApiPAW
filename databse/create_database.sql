create database TrelloPAW;
use TrelloPAW;

create table team(
	id int(10) unsigned not null auto_increment,
    name text not null,
    name_short text,
    description text,
    site text,
    logo longblob,
    primary key (id));
    
create table board(
	id int(10) unsigned not null auto_increment,
    name text not null,
    team_id int(10) unsigned,
    background longblob,
    primary key (id),
    CONSTRAINT id_team foreign key (team_id) references team (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
    
create table user(
	id int(10) unsigned not null auto_increment,
    name text not null,
    surname text not null,
    nickname text not null,
    password text not null,
    email text,
    avatar longblob,
    primary key (id));

create table user_team(
	id int(10) unsigned not null auto_increment,
    team_id int(10) unsigned,
    user_id int (10) unsigned,
    primary key (id),
      CONSTRAINT id_user_user_team foreign key (user_id) references user (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT id_team_user_team foreign key (team_id) references team (id) ON UPDATE NO ACTION ON DELETE NO ACTION);    
    
create table user_board(
	id int(10) unsigned not null auto_increment,
    user_id int(10) unsigned,
    board_id int(10) unsigned,
	primary key (id),
      CONSTRAINT id_user_user_board foreign key (user_id) references user (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT id_board_user_board foreign key (board_id) references board (id) ON UPDATE NO ACTION ON DELETE NO ACTION);


create table list(
	id int(10) unsigned not null auto_increment,
    name text not null,
    board_id int(10) unsigned not null,
    number_on_board int(10) unsigned not null,
	primary key (id),
		CONSTRAINT id_board_list foreign key (board_id) references board (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
    

create table card(
	id int(10) unsigned not null auto_increment,
    list_id int(10) unsigned not null,
    title text not null,
    description text,
    label_id int(10) unsigned,
    number_on_list int(10) unsigned,
    add_date timestamp not null default current_timestamp,
    primary key (id),
		CONSTRAINT id_list_card foreign key (list_id) references list (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
        
create table card_user(
	id int(10) unsigned not null auto_increment,
    user_id int(10) unsigned,
    card_id int(10) unsigned,
	primary key (id),
      CONSTRAINT id_user_card_user foreign key (user_id) references user (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT id_card_card_user foreign key (card_id) references card (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
      
create table activity(
	id int(10) unsigned not null auto_increment,
    card_id int(10) unsigned not null,
    user_id int(10) unsigned not null,
    add_date timestamp not null default current_timestamp,
    text_activity text not null,
    primary key (id),
      CONSTRAINT id_user_activity foreign key (user_id) references user (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT id_card_activity foreign key (card_id) references card (id) ON UPDATE NO ACTION ON DELETE NO ACTION);  
      
create table comment(
	id int(10) unsigned not null auto_increment,
	user_id int(10) unsigned,
    card_id int(10) unsigned,
    add_date timestamp not null default current_timestamp,
    attachment longblob,
	primary key (id),
      CONSTRAINT id_user_comment foreign key (user_id) references user (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT id_card_comment foreign key (card_id) references card (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
      
create table task_list(
	id int(10) unsigned not null auto_increment,
    card_id int(10) unsigned not null,
	primary key (id),
      CONSTRAINT id_card_task_list foreign key (card_id) references card (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
      
create table task(
	id int(10) unsigned not null auto_increment,
    task_list_id int(10) unsigned not null,
    name text not null,
    is_done bool default false,
	primary key (id),
      CONSTRAINT id_task_list foreign key (task_list_id) references task_list (id) ON UPDATE NO ACTION ON DELETE NO ACTION);

  alter table board add column `isPublic` boolean not null default true;
  alter table list add unique(number_on_board, board_id);
  alter table comment add column content longtext;
  alter table list modify number_on_board int(10) unsigned;
