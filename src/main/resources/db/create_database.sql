DROP DATABASE IF EXISTS school;
DROP USER IF EXISTS school_user;

USE mysql;
CREATE DATABASE school DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'school_user'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON school.* TO 'school_user'@'%';
FLUSH PRIVILEGES;

USE school;

drop table if exists SchoolClass;

drop table if exists SchoolClassStudent;

drop table if exists Student;

drop table if exists StudentNote;

drop table if exists Teacher;

drop table if exists Test;

create table SchoolClass
(
    id         bigint not null AUTO_INCREMENT,
    name       varchar(255),
    teacher_id bigint,
    primary key (id)
);

create table SchoolClassStudent
(
    student_id     bigint not null,
    schoolClass_id bigint not null
);

create table Student
(
    id        bigint not null AUTO_INCREMENT,
    birthdate date,
    firstName varchar(255),
    lastName  varchar(255),
    primary key (id)
);

create table StudentNote
(
    id             bigint not null AUTO_INCREMENT,
    createdAt      date,
    value          integer,
    weight         integer default 1,
    schoolClass_id bigint,
    student_id     bigint,
    primary key (id)
);

create table Test
(
    id             bigint      not null AUTO_INCREMENT,
    type           varchar(31) not null,
    date           date,
    subject        varchar(255),
    isPresentation bit,
    duration       bigint,
    schoolClass_id bigint,
    primary key (id)
);

create table Teacher
(
    id        bigint not null AUTO_INCREMENT,
    firstName varchar(255),
    lastName  varchar(255),
    primary key (id)
);

create table SchoolClass_lessonTopics (
    SchoolClass_id bigint not null,
    lessonTopics varchar(255)
);

alter table SchoolClass
    add constraint SchoolClass_Teacher_fk
        foreign key (teacher_id)
            references Teacher (id);

alter table SchoolClassStudent
    add constraint SchoolClassStudent_SchoolClass_fk
        foreign key (schoolClass_id)
            references SchoolClass (id);

alter table SchoolClassStudent
    add constraint SchoolClassStudent_Student_fk
        foreign key (student_id)
            references Student (id);

alter table StudentNote
    add constraint StudentNote_SchoolClass_fk
        foreign key (schoolClass_id)
            references SchoolClass (id);

alter table StudentNote
    add constraint StudentNote_Student_fk
        foreign key (student_id)
            references Student (id);

alter table Test
    add constraint Test_SchoolClass_fk
        foreign key (schoolClass_id)
            references SchoolClass (id);

alter table SchoolClass_lessonTopics
    add constraint SchoolClass_lessonTopics_fk
        foreign key (SchoolClass_id)
            references SchoolClass (id)
