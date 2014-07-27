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
 createtime date not null,
 exittime DATE,
 version int,
 primary key (id));

show tables;
