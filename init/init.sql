CREATE TABLESPACE userspace
DATAFILE '/myfile/oracle/userspacedata.DBF'  -- 指定数据文件路径和名称
SIZE 100M  -- 指定表空间的大小
AUTOEXTEND ON  -- 启用自动扩展
NEXT 10M  -- 自动扩展时的增量大小
MAXSIZE UNLIMITED;  -- 不限制表空间的最大大小


-- 创建用户并设置默认的表空间
CREATE USER user IDENTIFIED BY user
DEFAULT TABLESPACE userspace;  -- 默认表空间

-- 授权用户权限
GRANT DBA TO user;

/*==============================================================*/
/* Table: "COURSE"                                              */
/* 课程表                                                        */
/*==============================================================*/

create table "COURSE"
(
"CNO"                VARCHAR2(15)           not null, --课程编号
"CNAME"              VARCHAR2(20)         not null,   --课程名称
"CCREDIT"            NUMBER,                          --课程学分
constraint PK_COURSE primary key ("CNO")
);

/*==============================================================*/
/* Table: "FACULTY"                                             */
/* 院系表                                                        */
/*==============================================================*/
create table "FACULTY"
(
"FNO"                NUMBER(11)           not null, --学院或系编号
"FNAME"              VARCHAR2(30)         not null, --学院或系名称
constraint PK_FACULTY primary key ("FNO")
);

/*==============================================================*/
/* Table: "STUDENT"                                             */
/* 学生表                                                        */
/*==============================================================*/
create table "STUDENT"
(
"SNO"                NUMBER(11)        not null,       --学号
"SNAME"              VARCHAR2(16)         not null,    --学生姓名
"GENDER"             CHAR(4),                          --学生性别
"CLASSNO"            NUMBER(11),                       --班级编号
"DATAOFBIRTH"        DATE,                             --出生日期
"NATIVEPLACE"        VARCHAR2(12),                     --户籍所在地
"ADMITDATA"          DATE,                             --入学日期

constraint PK_STUDENT primary key ("SNO")
);
/*==============================================================*/
/* Table: "CLASS"                                               */
/* 行政班级                                                      */
/*==============================================================*/
create table "CLASS"
(
"CLASSNO"            NUMBER(11)           not null,   --班级编号
"FNO"                NUMBER(11),                      --学院或系编号
"CLASSNAME"          VARCHAR2(25)         not null,   --班级名称
constraint PK_CLASS primary key ("CLASSNO")
);
/*==============================================================*/
/* Table: "LESSON"                                              */
/* 教学班                                                        */
/*==============================================================*/
create table "LESSON"
(
"LESSONNO"           NUMBER(11)           not null,   --教学班编号
"TNO"                NUMBER(11),                      --教师编号
"CNO"                VARCHAR2(15),                    --课程编号
"CLASSTIME"          VARCHAR2(20),                    --开课时间
"CLASSROOM"          VARCHAR2(20),                    --开课地点
"MAXNUM"             SMALLINT,                        --最大人数
constraint PK_LESSON primary key ("LESSONNO")
);

/*==============================================================*/
/* Table: "TEACHER"                                             */
/* 教师表                                                        */
/*==============================================================*/
create table "TEACHER"
(
"TNO"                NUMBER(11)           not null,   --教师编号
"FNO"                NUMBER(11),                      --学院或系编号
"TNAME"              VARCHAR2(16)         not null,   --教师姓名
"GENDER"             CHAR(4),                         --性别
"NATIVEPLACE"        VARCHAR2(12),                    --户籍所在地
"TITLE"              VARCHAR2(16),                    --职称
constraint PK_TEACHER primary key ("TNO")
);
/*==============================================================*/
/* Table: "SUSER"                                                */
/* 学生用户                                                       */
/*==============================================================*/
create table "SUSER"
(
"SNO"            NUMBER(11)             	not null,   --学号
"PASSWORD"           VARCHAR2(25)         not null,     --密码
constraint PK_SUSER primary key ("SNO")
);

/*==============================================================*/
/* Table: "TUSER"                                                */
/* 教务处老师用户                                                  */
/*==============================================================*/
create table "TUSER"
(
"TNO"            INTEGER              	not null,     --教师编号
"PASSWORD"           VARCHAR2(25)         not null,   --密码
constraint PK_TUSER primary key ("TNO")
);


/*==============================================================*/
/* Table: "SC"                                                  */
/* 选课关系表                                                     */
/*==============================================================*/
create table "SC"
(
"SNO"                NUMBER(11)       not null,        --学号
"LESSONNO"           NUMBER(11)           not null,    --教学班编号
"DAILYSCORE"         NUMBER(5,2),                      --平时分
"MIDDLESCORE"        NUMBER(5,2),                      --期中成绩
"FINALSCORE"         NUMBER(5,2),                      --期末成绩
"TOTALSCORE"         NUMBER(5,2),                      --最终成绩
constraint PK_SC primary key ("SNO", "LESSONNO")

);

/*==============================================================*/
/* Table: "SC_LOG"                                              */
/* 选课日志表                                                    */
/*==============================================================*/

CREATE TABLE SC_LOG (
SC_LOGID NUMBER(11)  ,                                  --选课日志编号
ACTION VARCHAR2(10) NOT NULL,                           --操作
SNO NUMBER(11),                                         --学号
LESSONNO NUMBER(11),                                    --教学班编号
CHANGE_TIMESTAMP TIMESTAMP,                             --操作时间
CONSTRAINT PK_SC_LOG PRIMARY KEY (SC_LOGID)             --主键
);
/*==============================================================*/
/* Table: "lesson_log"                                          */
/* 教学班日志表                                                  */
/*==============================================================*/
CREATE TABLE lesson_log (
Les_logid NUMBER(11) ,                                  --教学班日志编号
action VARCHAR2(50) NOT NULL,                           --操作
lessonno NUMBER,                                        --教学班编号
tno NUMBER,                                             --教师编号
cno VARCHAR2(15),                                       --课程编号
classtime VARCHAR2(20),                                 --开课时间
classroom VARCHAR2(20),                                 --开课地点
maxnum SMALLINT,                                        --最大人数
change_timestamp TIMESTAMP,                             --操作时间
CONSTRAINT PK_LESSON_LOG PRIMARY KEY (les_logid)        --主键
);


--序列 实现选课信息的自增

--选课日志表自增
--功能：实现选课日志表的自增
create sequence Seq_sc_log
increment by 1
start with 1
nomaxvalue
nocycle;
alter table sc_log modify sc_logid default seq_sc_log.nextval;
--教学班日志表自增
--功能：实现教学班日志表的自增
create sequence Seq_lesson_log
increment by 1
start with 1
nomaxvalue
nocycle;
alter table lesson_log modify les_logid default Seq_lesson_log.nextval;

-- 外键
--功能：为表添加外键约束
-- Alter table "STUDENT" foreign key constraint
ALTER TABLE "STUDENT"
ADD CONSTRAINT "FK_STUDENT_CLASSNO" FOREIGN KEY ("CLASSNO")
REFERENCES "CLASS" ("CLASSNO");

-- Alter table "CLASS" foreign key constraint
ALTER TABLE "CLASS"
ADD CONSTRAINT "FK_CLASS_FNO" FOREIGN KEY ("FNO")
REFERENCES "FACULTY" ("FNO");

-- Alter table "LESSON" foreign key constraints
ALTER TABLE "LESSON"
ADD CONSTRAINT "FK_LESSON_TNO" FOREIGN KEY ("TNO")
REFERENCES "TEACHER" ("TNO");

ALTER TABLE "LESSON"
ADD CONSTRAINT "FK_LESSON_CNO" FOREIGN KEY ("CNO")
REFERENCES "COURSE" ("CNO");

-- Alter table "TEACHER" foreign key constraint
ALTER TABLE "TEACHER"
ADD CONSTRAINT "FK_TEACHER_FNO" FOREIGN KEY ("FNO")
REFERENCES "FACULTY" ("FNO");

-- Alter table "SUSER" foreign key constraint
ALTER TABLE "SUSER"
ADD CONSTRAINT "FK_SUSER_ROLENO" FOREIGN KEY ("SNO")
REFERENCES "STUDENT" ("SNO");

-- Alter table "TUSER" foreign key constraint
ALTER TABLE "TUSER"
ADD CONSTRAINT "FK_TUSER_ROLENO" FOREIGN KEY ("TNO")
REFERENCES "TEACHER" ("TNO");

-- Alter table "SC" foreign key constraints
ALTER TABLE "SC"
ADD CONSTRAINT "FK_SC_SNO" FOREIGN KEY ("SNO")
REFERENCES "STUDENT" ("SNO");

ALTER TABLE "SC"
ADD CONSTRAINT "FK_SC_LESSONO" FOREIGN KEY ("LESSONNO")
REFERENCES "LESSON" ("LESSONNO");

--视图

-- 功能: 该视图用于简化复杂的查询。
create view query_view as
select s.sno,s.sname,c.classname,l.lessonno,co.cname,l.classroom from student s,sc sc,lesson l,class c,course co
where c.classno = s.CLASSNO and s.sno=sc.sno and sc.lessonno=l.lessonno and l.cno=co.cno ;

--存储过程

--存储过程
-- --1.添加学生存储过程，学生注册
-- 功能: 该存储过程用于执行特定操作。
-- 参数: 学生姓名，性别，班级编号，出生日期，户籍所在地，入学日期
CREATE OR REPLACE PROCEDURE P_StuRegister(
S_NAME IN VARCHAR2,
G_ENDER IN CHAR,
CLASS_NO IN NUMBER,
DATAOF_BIRTH IN DATE,
NATIVE_PLACE IN VARCHAR2,
ADMIT_DATA IN DATE,
S_NO OUT NUMBER
) is
BEGIN
-- 插入学生信息并获取新的学号
select Seq_Student.nextval into S_NO from dual;
INSERT INTO STUDENT(SNO, SNAME, GENDER, CLASSNO, DATAOFBIRTH, NATIVEPLACE, ADMITDATA)
VALUES (S_NO, S_NAME, G_ENDER, CLASS_NO, DATAOF_BIRTH, NATIVE_PLACE, ADMIT_DATA);
END P_StuRegister;

--2.更新学生成绩存储过程：
--描述：更新学生的课程成绩，包括每日成绩、期中成绩和期末成绩，并计算总成绩。
-- 功能: 该存储过程用于执行特定操作。
--参数：SNO（学号）、LESSONNO（课程号）、DAILYSCORE（每日成绩）、MIDDLESCORE（期中成绩）、FINALSCORE（期末成绩）
CREATE OR REPLACE PROCEDURE p_UpdateStudentScores(
S_NO IN NUMBER,
LESSON_NO IN NUMBER,
DAILY_SCORE IN NUMBER,
MIDDLE_SCORE IN NUMBER,
FINAL_SCORE IN NUMBER
) AS
BEGIN
-- 更新学生的成绩并计算总成绩
UPDATE SC
SET DAILYSCORE = DAILY_SCORE, MIDDLESCORE = MIDDLE_SCORE, FINALSCORE = FINAL_SCORE,
TOTALSCORE = 0.1*DAILY_SCORE +0.3* MIDDLE_SCORE + 0.6*FINAL_SCORE
WHERE SNO = S_NO AND LESSONNO = LESSON_NO;
END p_UpdateStudentScores;

--3.获取学生课程成绩存储过程：
--描述：根据学生学号和课程号获取该学生在特定课程中的成绩。
--参数：SNO（学号）、CNO（课程号）
--返回值：DAILYSCORE、MIDDLESCORE、FINALSCORE、TOTALSCORE
-- 功能: 该存储过程用于执行特定操作。
-- 参数: ...
create or replace PROCEDURE P_GetStudentCourseScores(
S_NO IN NUMBER,
C_NO IN varchar2,
DAILY_SCORE OUT NUMBER,
MIDDLE_SCORE OUT NUMBER,
FINAL_SCORE OUT NUMBER,
TOTAL_SCORE OUT NUMBER
) AS
BEGIN
SELECT DAILYSCORE, MIDDLESCORE, FINALSCORE, TOTALSCORE
INTO DAILY_SCORE, MIDDLE_SCORE, FINAL_SCORE, TOTAL_SCORE
FROM SC
WHERE SNO = S_NO AND LESSONNO = (SELECT LESSONNO FROM LESSON WHERE CNO = C_NO);

END P_GetStudentCourseScores;

--4.获取特定教师的所有课程存储过程：
--描述：根据教师号（TNO），获取该教师教授的所有课程,教学班信息。
--参数：TNO（教师号）
--返回值：包括课程名称、课程号、教室等信息的记录集合。
--注：使用了sys_refcursor 返回结果集
-- 功能: 该存储过程用于执行特定操作。
create or replace PROCEDURE P_GetTeacherLessons(
T_NO IN NUMBER,
TeacherLesson OUT SYS_REFCURSOR
) AS
BEGIN
OPEN TeacherLesson FOR
SELECT C.CNAME, L.CNO, L.CLASSTIME, L.CLASSROOM
FROM COURSE C
INNER JOIN LESSON L ON C.CNO = L.CNO
WHERE L.TNO = T_NO;
END P_GetTeacherLessons;


--5.学生选课存储过程：
--描述：当学生要选修一门课程时，将其学号和课程号插入到SC表中，并初始化成绩为0。
--参数：SNO（学号）、LESSONNO（课程号）
-- 功能: 该存储过程用于执行特定操作。
create or replace PROCEDURE P_StudentSelectLesson(
S_NO IN NUMBER,
LESSON_NO IN NUMBER
) AS
BEGIN
INSERT INTO SC (SNO, LESSONNO, DAILYSCORE, MIDDLESCORE, FINALSCORE, TOTALSCORE)
VALUES (S_NO, LESSON_NO, 0, 0, 0, 0);

END P_StudentSelectLesson;

--6.删除学生选课存储过程：
--描述：当学生要退选一门课程时，从SC表中删除对应的记录。
--参数：SNO（学号）、LESSONNO（课程号）
-- 功能: 该存储过程用于执行特定操作。

CREATE OR REPLACE PROCEDURE p_StuDropLesson(
S_NO IN NUMBER,
LESSON_NO IN NUMBER
) AS

BEGIN
DELETE FROM SC
WHERE SNO = S_NO AND LESSONNO = LESSON_NO;
commit;
END ;

--添加教学班存储过程：
--描述：添加一个新的教学班
--参数：LESSON_NO, T_NO, C_NO, CLASS_ROOM, CLASS_TIME, MAX_NUM
-- 功能: 该存储过程用于执行特定操作。

create or replace PROCEDURE P_ADDLESSON(
LESSON_NO IN NUMBER,
T_NO IN NUMBER,
C_NO IN VARCHAR2,
CLASS_ROOM IN VARCHAR2,
CLASS_TIME IN VARCHAR2,
MAX_NUM IN NUMBER
) AS
BEGIN
INSERT INTO LESSON (LESSONNO, TNO, CNO, CLASSROOM, CLASSTIME, MAXNUM )
VALUES (LESSON_NO, T_NO, C_NO, CLASS_ROOM, CLASS_TIME, MAX_NUM );
commit;
END ;

--创建课程存储过程：
--描述：添加一个新的课程
--参数：C_NO, C_NAME, C_CREDIT
-- 功能: 该存储过程用于执行特定操作。
create or replace PROCEDURE P_ADDCOURSE(
C_NO IN NUMBER,
C_NAME IN VARCHAR2,
C_CREDIT IN NUMBER
) AS
BEGIN
INSERT INTO COURSE ( CNO, CNAME, CCREDIT )
VALUES (C_NO, C_NAME, C_CREDIT );
commit;
END ;

-- 触发器
--级联删除学生触发器：
--描述：当删除学生时，级联删除与该学生相关的成绩记录。
CREATE OR REPLACE TRIGGER T_CascadeDeleteStudent
BEFORE DELETE ON STUDENT
FOR EACH ROW
BEGIN
DELETE FROM SC WHERE SNO = :OLD.SNO;
END;

--选课表发生改动，将操作和数据写入选课日志表
CREATE OR REPLACE TRIGGER T_SCChangeLog
AFTER INSERT OR UPDATE OR DELETE ON SC
FOR EACH ROW
BEGIN
IF INSERTING THEN
INSERT INTO sc_log (ACTION, SNO, LESSONNO, CHANGE_TIMESTAMP)
VALUES ('INSERT', :NEW.SNO, :NEW.LESSONNO, SYSTIMESTAMP);
ELSIF UPDATING THEN
INSERT INTO sc_log (ACTION, SNO, LESSONNO, CHANGE_TIMESTAMP)
VALUES ('UPDATE', :NEW.SNO, :NEW.LESSONNO, SYSTIMESTAMP);
ELSIF DELETING THEN
INSERT INTO sc_log (ACTION, SNO, LESSONNO, CHANGE_TIMESTAMP)
VALUES ('DELETE', :OLD.SNO, :OLD.LESSONNO, SYSTIMESTAMP);
END IF;
END;

--教学班表发生改动，将操作和数据写入教学班日志表

CREATE OR REPLACE TRIGGER T_LessonChangeLog
AFTER INSERT OR UPDATE OR DELETE ON LESSON
FOR EACH ROW
BEGIN
IF INSERTING THEN
INSERT INTO lesson_log (action, lessonno, tno, cno, classtime, classroom, maxnum, change_timestamp)
VALUES ('INSERT', :NEW.LESSONNO, :NEW.TNO, :NEW.CNO, :NEW.CLASSTIME, :NEW.CLASSROOM, :NEW.MAXNUM, SYSTIMESTAMP);
ELSIF UPDATING THEN
INSERT INTO lesson_log (action, lessonno, tno, cno, classtime, classroom, maxnum, change_timestamp)
VALUES ('UPDATE', :OLD.LESSONNO, :OLD.TNO, :OLD.CNO, :OLD.CLASSTIME, :OLD.CLASSROOM, :OLD.MAXNUM, SYSTIMESTAMP);
ELSIF DELETING THEN
INSERT INTO lesson_log (action, lessonno, tno, cno, classtime, classroom, maxnum, change_timestamp)
VALUES ('DELETE', :OLD.LESSONNO, :OLD.TNO, :OLD.CNO, :OLD.CLASSTIME, :OLD.CLASSROOM, :OLD.MAXNUM, SYSTIMESTAMP);
END IF;
END;

--删除某个 lesson 时, 级联删除 sc 表的记录；
create or replace trigger T_CasecadeDeleteSc
after delete on lesson
for each row
begin
delete from sc where :old.lessonno=sc.lessonno;
end;

--删除 某个course 时，级联删除 lesson 表的记录
create or replace trigger T_CasecadeDeletelesson
after delete on course
for each row
begin
delete from lesson where :old.cno=lesson.lessonno;
end;



--清除初始化
drop table "COURSE" cascade constraints;

drop table "FACULTY" cascade constraints;



drop table "STUDENT" cascade constraints;

drop table "CLASS" cascade constraints;

drop table "LESSON" cascade constraints;


drop table "TEACHER" cascade constraints;

drop table "TUSER" cascade constraints;
drop table "SUSER" cascade constraints;

drop table "SC" cascade constraints;

drop table "LESSON_LOG" cascade constraints ;
drop table "SC_LOG" cascade constraints ;
