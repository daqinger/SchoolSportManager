create datebase SchoolSport

-- 创建日志表
create table logs(
  logContent varchar(200) auto_increment,
  logDate varchar (100)
)
-- 创建运动表
create table sports(
  sportId int primary key not null auto_increment,
  sportName varchar(50),
  sportNumberNeeded int,
  sportNumberNow int
)
--创建中间表
create table sportStudent(
  sportStudentId int primary key not null auto_increment,
  sportId int,
  studentNumber int
)
--创建学生表
create table students(
  studentNumber int primary key not null,
  studentName varchar(50),
  studentAge int,
  studentPassword varchar(50)
  studentClassNumber int
)
--创建老师表
create table teachers(
  teacherId int primary key not null auto_increment,
  teacherName varchar(50)
  teacherPassword varchar(50)
)
--预插入体育老师甲的信息
insert into teachers(teacherName,teacherPassword) values ('体育老师甲','123546')