-- 创建数据库
create database db_example;
create user 'springuser'@'%' identified by 'ThePassword';
grant all on db_example.* to 'springuser'@'%';

-- 创建用户表
create table user
(
    id bigint not null auto_increment comment '用户ID',
    username varchar(50) comment '用户账号',
    password varchar(256) comment '用户密码',
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

-- 创建权限表
create table privilege
(
    id int not null auto_increment comment '权限ID',
    name varchar(50) comment '权限名',
    url varchar(200) comment '权限URL',
    primary key (id)
);
alter table privilege comment '权限表';

-- 创建用户角色关联表
create table user_role
(
    user_id bigint not null comment '用户ID',
    role_id int not null comment '角色ID'
);
alter table user comment '用户角色关联表';

-- 创建角色权限关联表
create table role_privilege
(
    role_id int comment '角色ID',
    privilege_id int comment '权限ID'
);
alter table role_privilege comment '角色权限关联表';

-- 插入用户
-- 密码 123456
insert into user (id,username,password,account_non_expired,account_non_locked,credentials_non_expired, enabled) values (1,'xuyaxiong','$2a$10$ZECz39Ra7ru3xcxqwduvSO1oTyE.oIYJcVn5Jq3OuMtTXiJcfm9D6',true,true,true,true);

-- 插入角色
insert into role (id,name,name_zh) values (1,'admin','管理员');
insert into role (id,name,name_zh) values (2,'user','用户');
insert into role (id,name,name_zh) values (3,'manager','经理');
insert into role (id,name,name_zh) values (4,'visitor','访客');

-- 插入权限
insert into privilege (id,name,url) values (1,'用户管理','/users');
insert into privilege (id,name,url) values (2,'角色管理','/roles');

-- 关联用户和角色
insert into user_role (user_id,role_id) values (1,1);

-- 关联角色和权限
insert into role_privilege (role_id,privilege_id) values (1,1);
insert into role_privilege (role_id,privilege_id) values (1,2);