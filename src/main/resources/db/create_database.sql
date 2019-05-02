DROP DATABASE IF EXISTS school;
DROP USER IF EXISTS school_user;

USE mysql;
CREATE DATABASE school DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'school_user'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON school.* TO 'school_user'@'%';
FLUSH PRIVILEGES;

USE school;

drop table if exists hibernate_sequence;

drop table if exists SchoolClass;

drop table if exists SchoolClassStudent;

drop table if exists Student;

drop table if exists StudentNote;

drop table if exists Teacher;

drop table if exists Test;

create table hibernate_sequence
(
    next_val bigint
);

insert into hibernate_sequence
values (1);

insert into hibernate_sequence
values (1);

insert into hibernate_sequence
values (1);

insert into hibernate_sequence
values (1);

insert into hibernate_sequence
values (1);

create table SchoolClass
(
    id         bigint not null,
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
    id        bigint not null,
    birthdate date,
    firstName varchar(255),
    lastName  varchar(255),
    primary key (id)
);

create table StudentNote
(
    id             bigint not null,
    createdAt      date,
    value          integer,
    weight         integer default 1,
    schoolClass_id bigint,
    student_id     bigint,
    primary key (id)
);

create table Teacher
(
    id        bigint not null,
    firstName varchar(255),
    lastName  varchar(255),
    primary key (id)
);

create table Test
(
    type           varchar(31) not null,
    id             bigint      not null,
    date           date,
    subject        varchar(255),
    isPresentation bit,
    duration       bigint,
    schoolClass_id bigint,
    primary key (id)
);

alter table SchoolClass
    add constraint FK3h2qnhgmn5qgqwf32ud1r0729
        foreign key (teacher_id)
            references Teacher (id);

alter table SchoolClassStudent
    add constraint FKqxx8oju4b81qmn29x6ii08jos
        foreign key (schoolClass_id)
            references SchoolClass (id);

alter table SchoolClassStudent
    add constraint FK61gclkck6ai5h358o0htxgigl
        foreign key (student_id)
            references Student (id);

alter table StudentNote
    add constraint FKlwfjcwlx8pjujgsinxsylotq4
        foreign key (schoolClass_id)
            references SchoolClass (id);

alter table StudentNote
    add constraint FKg0jeamfibv1x2nfx89yv0y7to
        foreign key (student_id)
            references Student (id);

alter table Test
    add constraint FK8at9linrrt5j6e80m64957x3h
        foreign key (schoolClass_id)
            references SchoolClass (id);
