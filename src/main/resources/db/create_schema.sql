DROP DATABASE IF EXISTS school_hib;
DROP USER IF EXISTS school_hib_user;

USE mysql;
CREATE DATABASE school_hib DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'school_hib_user'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON school_hib.* TO 'school_hib_user'@'%';
FLUSH PRIVILEGES;
