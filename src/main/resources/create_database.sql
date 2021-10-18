-- 创建数据库
create database db_example;
create user 'springuser'@'%' identified by 'ThePassword';
grant all on db_example.* to 'springuser'@'%';


-- 创建用户表
drop table if exists sys_user;
create table sys_user
(
    id bigint not null auto_increment comment '用户ID',
    username varchar(50) not null comment '用户账号',
    password varchar(256) not null comment '用户密码',
    email varchar(50) comment '用户邮箱',
    account_non_expired bool default 1,
    account_non_locked bool default 1,
    credentials_non_expired bool default 1,
    enabled bool default 1,
    created_at datetime default now() comment '创建时间',
    updated_at datetime default now() comment '更新时间',
    deleted_at datetime comment '删除时间',
    primary key (id)
);
alter table sys_user comment '用户表';

-- 创建角色表
drop table if exists sys_role;
create table sys_role
(
    id int not null auto_increment comment '角色ID',
    name varchar(50) not null comment '角色名',
    name_zh varchar(50) not null comment '角色中文名',
    created_at datetime default now() comment '创建时间',
    updated_at datetime default now() comment '更新时间',
    deleted_at datetime comment '删除时间',
    primary key (id)
);
alter table sys_role comment '角色表';

-- 创建资源表
drop table if exists sys_resource;
create table sys_resource
(
    id int not null auto_increment comment '资源ID',
    name varchar(50) not null comment '资源名',
    url varchar(200) not null comment '资源URL',
    created_at datetime default now() comment '创建时间',
    updated_at datetime default now() comment '更新时间',
    deleted_at datetime comment '删除时间',
    primary key (id)
);
alter table sys_resource comment '资源表';

-- 创建菜单表
drop table if exists sys_menu;
create table sys_menu
(
    id int not null auto_increment,
    name varchar(20) not null comment '菜单名',
    parent_id int comment '父级菜单ID',
    `level` int not null comment '菜单级数',
    sort int not null comment '菜单排序',
    icon varchar(100) comment '菜单图标',
    hidden bool default 0 comment '是否隐藏',
    created_at datetime default now() comment '创建时间',
    updated_at datetime default now() comment '更新时间',
    deleted_at datetime comment '删除时间',
    primary key (id)
);
alter table sys_menu comment '菜单表';

-- 创建用户角色关联表
drop table if exists sys_user_role;
create table sys_user_role
(
    id int not null auto_increment,
    user_id bigint not null comment '用户ID',
    role_id int not null comment '角色ID',
    primary key (id)
);
alter table sys_user_role comment '用户角色关联表';

-- 创建角色资源关联表
drop table if exists sys_role_resource;
create table sys_role_resource
(
    id int not null auto_increment,
    role_id int not null comment '角色ID',
    resource_id int not null comment '资源ID',
    primary key (id)

);
alter table sys_role_resource comment '角色资源关联表';

-- 创建角色菜单关联表
drop table if exists sys_role_menu;
create table sys_role_menu
(
    id int not null auto_increment,
    role_id int not null comment '角色ID',
    menu_id int not null comment '菜单ID',
    primary key (id)
);
alter table sys_role_menu comment '角色菜单关联表';

-- 插入用户
-- 密码 123456
insert into sys_user (id,username,password,email,account_non_expired,account_non_locked,credentials_non_expired,enabled) values (1,'admin','$2a$10$ZECz39Ra7ru3xcxqwduvSO1oTyE.oIYJcVn5Jq3OuMtTXiJcfm9D6','xyxlindy@163.com',true,true,true,true);
insert into sys_user (id,username,password,email,account_non_expired,account_non_locked,credentials_non_expired,enabled) values (2,'user1','$2a$10$ZECz39Ra7ru3xcxqwduvSO1oTyE.oIYJcVn5Jq3OuMtTXiJcfm9D6','user1@163.com',true,true,true,true);
insert into sys_user (id,username,password,email,account_non_expired,account_non_locked,credentials_non_expired,enabled) values (3,'manager1','$2a$10$ZECz39Ra7ru3xcxqwduvSO1oTyE.oIYJcVn5Jq3OuMtTXiJcfm9D6','manager1@163.com',true,true,true,true);

-- 插入角色
insert into sys_role (id,name,name_zh) values (1,'admin','管理员');
insert into sys_role (id,name,name_zh) values (2,'user','用户');

-- 插入资源
insert into sys_resource (id,name,url,created_at) values (1,'系统管理','/admin/**',now());

-- 插入菜单
insert into sys_menu(id,name,parent_id,level,sort,icon,hidden) values (1,'权限管理',0,1,0,'http://www.baidu.com',0);
insert into sys_menu(id,name,parent_id,level,sort,icon,hidden) values (2,'用户列表',1,2,0,'http://www.baidu.com',0);
insert into sys_menu(id,name,parent_id,level,sort,icon,hidden) values (3,'菜单列表',1,2,0,'http://www.baidu.com',0);
insert into sys_menu(id,name,parent_id,level,sort,icon,hidden) values (4,'角色列表',1,2,0,'http://www.baidu.com',0);
insert into sys_menu(id,name,parent_id,level,sort,icon,hidden) values (5,'资源列表',1,2,0,'http://www.baidu.com',0);

-- 关联用户和角色
insert into sys_user_role (id,user_id,role_id) values (1,1,1);
insert into sys_user_role (id,user_id,role_id) values (2,2,2);
insert into sys_user_role (id,user_id,role_id) values (3,3,3);

-- 关联角色和资源
insert into sys_role_resource (id,role_id,resource_id) values (1,1,1);

-- 关联角色和菜单
insert into sys_role_menu (id,role_id,menu_id) values (1,1,1);
