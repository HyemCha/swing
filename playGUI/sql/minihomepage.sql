create database minihomepage;
use minihomepage;

create table user(
                     id int auto_increment primary key,
                     email varchar(50) not null,
                     nickname varchar(50),
                     pwd varchar(50)
);
create table diary(
                      id int auto_increment primary key,
                      user_id int,
                      title varchar(50),
                      content varchar(1000),
                      img_url varchar(1000),
                      create_at timestamp DEFAULT CURRENT_TIMESTAMP ,
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      foreign key(user_id) references user(id)
);
create table guest_book(
                           id int auto_increment primary key,
                           user_id int,
                           content varchar(1000),
                           create_at timestamp DEFAULT CURRENT_TIMESTAMP ,
                           updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           foreign key(user_id) references user(id)
);