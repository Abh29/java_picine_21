drop table if exists chatters;
drop table if exists chatrooms;
drop table if exists chatter_chatroom_table;

create table chatters(
    id serial,
    login varchar unique not null,
    password varchar not null,
    primary key(id));

create table chatrooms(
    id serial primary key ,
    name varchar unique not null ,
    owner_id integer not null,
    constraint fk_owner
        foreign key (owner_id)
            references chatters(id)
);


create table chatter_chatroom_table(
    id  serial  primary key ,
    chatter_id integer not null,
    chatroom_id integer not null,
    constraint fk_chatter foreign key (chatter_id) references chatters(id),
    constraint fk_chatroom foreign key (chatroom_id) references chatrooms(id)
);


create table messages(
    id serial primary key,
    author integer not null,
    room integer not null,
    text varchar,
    created_at timestamp not null,
    constraint fk_chatter foreign key (author) references chatters(id),
    constraint fk_chatroom foreign key (room) references chatrooms(id)
);