-- 创建数据库
create database db_example;
create user 'springuser'@'%' identified by 'ThePassword';
grant all on db_example.* to 'springuser'@'%';

-- 创建用户表
create table user
(
    id bigint not null auto_increment comment '用户ID',
    username varchar(50) comment '用户账号',
    password varchar(50) comment '用户密码',
    account_non_expired bool,
    account_non_locked bool,
    credentials_non_expired bool,
    enabled bool,
    primary key (id)
);
alter table user comment '用户表';

-- 创建角色表
create table role
(
    id int not null auto_increment comment '角色ID',
    name varchar(50) comment '角色名',
    name_zh varchar(50) comment '角色中文名',
    primary key (id)
);
alter table user comment '角色表';

-- 创建用户角色关联表
create table user_role
(
    user_id bigint not null comment '用户ID',
    role_id int not null comment '角色ID'
);
alter table user comment '用户角色关联表';

-- 插入用户
insert into user (id,username,password,account_non_expired,account_non_locked,credentials_non_expired, enabled) values (1,"xuyaxiong","123456",true,true,true,true);
insert into user (id,username,password,account_non_expired,account_non_locked,credentials_non_expired, enabled) values (2,"danqiu","123456",true,true,true,true);
insert into user (id,username,password,account_non_expired,account_non_locked,credentials_non_expired, enabled) values (3,"matang","123456",true,true,true,true);
insert into user (id,username,password,account_non_expired,account_non_locked,credentials_non_expired, enabled) values (4,"haha","123456",true,true,true,true);

-- 插入角色
insert into role (id,name,name_zh) values (1,"ROLE_admin","管理员");
insert into role (id,name,name_zh) values (2,"ROLE_user","用户");
insert into role (id,name,name_zh) values (3,"ROLE_manager","经理");
insert into role (id,name,name_zh) values (4,"ROLE_visitor","访客");

-- 关联用户及角色
insert into user_role (user_id,role_id) values (1,1);
insert into user_role (user_id,role_id) values (2,2);
insert into user_role (user_id,role_id) values (3,3);
insert into user_role (user_id,role_id) values (4,4);