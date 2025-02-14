create table user(
    id int(50) not null auto_increment primary key,
    email varchar(50) not null,
    password varchar(50) not null,
    nickname varchar(30) not null,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    updatedAt TIMESTAMP,
    deletedAt TIMESTAMP
);

create table movie(
    id int(50) not null auto_increment primary key,
    userId int(50) not null,
    subject varchar(500) not null,
    url varchar(5000) not null,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    deletedAt TIMESTAMP,
    foreign key(userId) references user(id)
);

create table live_streaming(
    id int(50) not null auto_increment primary key,
    userId int(50) not null,
    movieId int(50) not null,
    title varchar(500) not null,
    contents varchar(1000),
    userCount int(10) default 0 not null,
    thumbsUp int(10) default 0 not null,
    reservedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    updatedAt TIMESTAMP,
    deletedAt TIMESTAMP,
    foreign key(userId) references user(id),
    foreign key (movieId) references movie(id)
);

 CREATE TABLE streamingLike(
	id int(50) not null auto_increment primary key,
    userId int(50) not null,
    streamingId int(50) not null,
    foreign key(userId) references user(id),
    foreign key(streamingId) references live_streaming(id)
);