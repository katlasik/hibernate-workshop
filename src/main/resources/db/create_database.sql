use school_hib;

drop table if exists SchoolClass;

drop table if exists SchoolClassStudent;

drop table if exists Student;

drop table if exists StudentNote;

drop table if exists Teacher;

drop table if exists Test;

create table SchoolClass
(
    id         bigint not null auto_increment,
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
    id        bigint not null auto_increment,
    birthdate date,
    firstName varchar(255),
    lastName  varchar(255),
    primary key (id)
);

create table StudentNote
(
    id             bigint not null auto_increment,
    createdAt      date,
    value          integer,
    weight         integer default 1,
    schoolClass_id bigint,
    student_id     bigint,
    primary key (id)
);

create table Test
(
    id             bigint      not null auto_increment,
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
    id        bigint not null auto_increment,
    firstName varchar(255),
    lastName  varchar(255),
    primary key (id)
);

create table LessonTopics (
    schoolClass_id bigint not null,
    topic varchar(255)
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

alter table LessonTopics
    add constraint LessonTopics_fk
        foreign key (schoolClass_id)
            references SchoolClass (id)
