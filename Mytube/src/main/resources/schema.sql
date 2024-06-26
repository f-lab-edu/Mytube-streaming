create table user(
    id int(50) not null auto_increment primary key,
    email varchar(30) not null,
    password varchar(30) not null,
    nickname varchar(10) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

create table movie(
    id int(50) not null auto_increment primary key,
    user_id int(50) not null,
    subject varchar(20) not null,
    url varchar(100) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    deleted_at TIMESTAMP,
    foreign key(user_id) references user(id)
);

create table live_streaming(
    id int(50) not null auto_increment primary key,
    user_id int(50) not null,
    movie_id int(50) not null,
    title varchar(20) not null,
    contents varchar(20),
    user_count int(10) default 0 not null,
    thumbs_up int(10) default 0 not null,
    reserved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    foreign key(user_id) references user(id),
    foreign key (movie_id) references movie(id)
);