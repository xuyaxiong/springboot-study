-- 创建数据库
create database db_example;
create user 'springuser'@'%' identified by 'ThePassword';
grant all on db_example.* to 'springuser'@'%';

-- 创建用户表
create table user
(
    id bigint not null auto_increment comment '用户ID',
    name varchar(50) comment '用户名',
    email varchar(50) comment '用户邮箱',
    primary key (id)
);
alter table user comment '用户表';