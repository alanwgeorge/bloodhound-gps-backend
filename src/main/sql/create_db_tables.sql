-- Add this to your Tomcat context.xml
--  <Resource name="jdbc/geofence" auth="Container" type="javax.sql.DataSource"
--               maxActive="100" maxIdle="30" maxWait="10000"
--               username="XXXXX" password="XXXXX" driverClassName="com.mysql.jdbc.Driver"
--               url="jdbc:mysql://localhost:3306/geofence"/>


create database if not exists geofence;

CREATE USER 'geofence'@'localhost' IDENTIFIED BY 'geofence';

GRANT ALL PRIVILEGES ON *.* TO 'geofence'@'localhost' WITH GRANT OPTION;

CREATE USER 'geofence'@'%' IDENTIFIED BY 'geofence';

GRANT ALL PRIVILEGES ON *.* TO 'geofence'@'%' WITH GRANT OPTION;

use geofence;

drop table if exists geofence;

CREATE TABLE geofence (
 id int not null auto_increment,
 device_id VARCHAR(255) not null, 
 latitude decimal(10, 8) not null,
 longitude decimal(11, 8) not null,
 radius int not null,
 createtime timestamp not null,
 exittime timestamp,
 version int,
 primary key (id));

drop table if exists location;

CREATE TABLE location (
 id int not null auto_increment,
 device_id VARCHAR(255) not null,
 latitude decimal(10, 8) not null,
 longitude decimal(11, 8) not null,
 accuracy int not null,
 createtime timestamp not null,
 version int,
 primary key (id));

drop table if exists device;

CREATE TABLE device (
 device_id VARCHAR(255) not null,
 name VARCHAR(255) not null,
 version int,
 primary key (device_id));

insert into device (device_id, name, version) values (
	'APA91bGwXJtY0ijFwHokg00jxWDu3NxTmF5yRYdqpqEfyWNTWD1FiSe8KJBGxuuSNjMPXKHTHafc1K_1YeRsNdZ5NI7Oy48IhOawAwDZ1PKvwTchZMhr93GBlmmmiwXIschiEI9DTammkrMg_Oe5LAQAyLuMn_DplPXiTj2bgkokosimyK_I2Qs',
	'Shin Nexus5',
	0);
insert into device (device_id, name, version) values (
	'APA91bFgg__nV3l0TCNM6NDOgMrOcps28jC5xt8mq0tnZgr18kDX7lyfEZ96c6fX9fXMV3xyTN-ZE6Cbi1Oge2m62dXoqAythaQJGxFB1uNh4D3bt-ylEVBP8iuf6aMj8YWEO4AN41qTU2Fri3l3phtr3cTM0a5hBOfokK_7D4ncFNZBY_uGHmw',
	'Alan Nexus5',
	0);
insert into device (device_id, name, version) values (
	'APA91bF8MHCVmaVKShjdsXzRda_FuG0D9hqIqpi8VAHHyaNLglztH-TJuUY-U0YIvPE0cjBk1yDZePuP5K_9WYFoB0cJt5X7c0YMRxT4XA5hJU0niy2Xj0bd6HEanjraFIP8H01ch_eO0OAEK7mFM5G7ig3nl1Dbj9UGp2WABnCGkH_1w9npHLs',
	'WF SamsungS5',
	0);


show tables;
