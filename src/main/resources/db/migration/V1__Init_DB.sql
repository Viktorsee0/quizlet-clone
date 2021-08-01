create table cards
(
    id         int8 generated by default as identity,
    definition varchar(255) not null,
    term       varchar(255) not null,
    filename   varchar(255),
    model_id   int8 not null,
    primary key (id)
);

create table folders
(
    id          int8 generated by default as identity,
    title       varchar(255) not null,
    description varchar(255),
    user_id     int8 not null,
    primary key (id)
);

create table models
(
    id          int8 generated by default as identity,
    name        varchar(255) not null,
    description varchar(255),
    folder_id   int8,
    user_id     int8 not null,
    primary key (id)
);

create table roles
(
    user_id int8 not null,
    roles   varchar(255)
);

create table users
(
    id              int8 generated by default as identity,
    activation_code varchar(255),
    active          boolean not null default false,
    email           varchar(255) not null,
    password        varchar(255) not null,
    primary key (id)
);



alter table if exists cards
    add constraint cards_model_fk foreign key (model_id) references models;
alter table if exists folders
    add constraint folders_user_fk foreign key (user_id) references users;
alter table if exists models
    add constraint models_folder_fk foreign key (folder_id) references folders;
alter table if exists models
    add constraint models_user_fk foreign key (user_id) references users;
alter table if exists roles
    add constraint roles_user_fk foreign key (user_id) references users;