drop table if exists users;
drop table if exists players;
drop table if exists games;

create table users(
    id serial primary key ,
    user_name varchar(255) unique not null ,
    password char(255) unique not null ,
    nick varchar(255),
    games_played integer default 0,
    games_won integer default 0
);

create table players(
    id serial primary key ,
    user_id bigint not null ,
    nick varchar(255) not null unique,
    hp double precision not null default 100.0,
    position_x integer not null default 0,
    position_y integer not null default 0,
    attack_power double precision not null default 0.0,
    shots integer not null default 0,
    constraint fk_user foreign key (user_id) references users(id)
);


create table games(
    id serial primary key ,
    player1_id bigint not null,
    player2_id bigint not null,
    initial_hp double precision default 100.0,
    constraint fk_player1 foreign key (player1_id) references players(id),
    constraint fk_player2 foreign key (player2_id) references players(id)
);

