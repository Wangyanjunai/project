-- 创建用户
create user 'project'@'%' identified by 'XueGod!@#123';
-- 创建数据库
create database if not exists project default character set utf8 default collate utf8_general_ci;
-- 授权mysql5.7
grant all on project.* to 'project'@'%' identified by 'XueGod!@#123';
-- 授权mysql8.1
-- grant all privileges on project.* to 'project'@'%' with grant option;
-- alter user 'project'@'%' identified with mysql_native_password by 'XueGod!@#123'; #修改加密规则
-- alter user 'project'@'%' identified by 'XueGod!@#123' password expire never; #更新一下用户的密码且不过期
-- 刷新权限
flush privileges;
