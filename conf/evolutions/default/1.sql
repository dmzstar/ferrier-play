# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table article (
  id                            bigint auto_increment not null,
  code                          varchar(255),
  title                         varchar(255),
  content                       clob,
  description                   varchar(255),
  status                        integer,
  constraint ck_article_status check ( status in (0,1,2)),
  constraint pk_article primary key (id)
);

create table article_comment (
  id                            bigint auto_increment not null,
  content                       clob,
  description                   varchar(255),
  status                        integer,
  article_id                    bigint not null,
  constraint ck_article_comment_status check ( status in (0,1,2)),
  constraint pk_article_comment primary key (id)
);

create table cms_file (
  id                            bigint auto_increment not null,
  code                          varchar(255),
  title                         varchar(255),
  content                       clob,
  description                   varchar(255),
  status                        integer,
  constraint ck_cms_file_status check ( status in (0,1,2)),
  constraint pk_cms_file primary key (id)
);

create table sec_group (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_sec_group primary key (id)
);

create table home (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_home primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  done                          boolean default false not null,
  due_date                      timestamp,
  constraint pk_user primary key (id)
);

create index ix_article_comment_article_id on article_comment (article_id);
alter table article_comment add constraint fk_article_comment_article_id foreign key (article_id) references article (id) on delete restrict on update restrict;


# --- !Downs

alter table article_comment drop constraint if exists fk_article_comment_article_id;
drop index if exists ix_article_comment_article_id;

drop table if exists article;

drop table if exists article_comment;

drop table if exists cms_file;

drop table if exists sec_group;

drop table if exists home;

drop table if exists user;

