DROP DATABASE IF EXISTS school;
DROP USER IF EXISTS school_user;

USE mysql;
CREATE DATABASE school DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'school_user'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON school.* TO 'school_user'@'%';
FLUSH PRIVILEGES;
