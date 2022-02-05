create sequence hibernate_sequence start with 1 increment by 1;

create table site
(
    id int8 not null,
    name text not null,
    primary key (id)
);

create table words_statistic
(
    id      int8 not null,
    count   int8,
    word    varchar(255),
    site_id int8,
    primary key (id)

);

alter table if exists words_statistic add constraint FKmm8di1blbos2qt2hkr8gu4wbq foreign key (site_id) references site;