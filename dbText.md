# auth-server
To be integrated to backend repo if needed.

drop database if exists employee;
create database employee;

use employee;

drop table if exists user;
create table user(
	`id` int primary key auto_increment,
    `username` varchar(35) not null unique,
    `password` varchar(35) not null,
    `firstname` varchar(35) not null,
    `laststname` varchar(35) not null,
    `email` varchar(35) not null,
    `rolename` varchar(35) not null
);

insert into user(`id`,`username`,`password`,`firstname`,`lastname`,`email`,`rolename`) values (null, 'admin','admin','Tom','Lee','qwe@123.com','employee');
