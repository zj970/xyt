# 工程简介
# MySql版本
mysql-installer-community-8.0.28.0
0.
```sql
CREATE DATABASE xyt;

/*创建管理员表*/
CREATE TABLE admin
(
    Anu CHAR(11) PRIMARY KEY,
    Apd CHAR(33) NOT NULL 
);

/*创建系表*/
CREATE TABLE department
(
    Dnu CHAR(3) PRIMARY KEY ,/* 列级完整性约束条件，Dnu是主码 */
    Dname CHAR(20) UNIQUE NOT NULL ,
    Dphone CHAR(11) 
);

/* 创建教师表 */
CREATE TABLE teacher
(
    Tnu CHAR(6) PRIMARY KEY ,/* 列级完整性约束条件，Tnu是主码 */
    Tname CHAR(20) NOT NULL ,
    Tsex CHAR(2) NOT NULL ,
    Title CHAR(8) ,
    Tphone CHAR(11) ,
    Teyte DATE NOT NULL,
    Tsnte DATE, 
    Dnu CHAR(3) ,
    Tpd CHAR(33) NOT NULL ,
    FOREIGN KEY (Dnu) REFERENCES department(Dnu) /* 表级完整性的约束条件,Dnu是外码连接department */
);

/* 创建班级表 */
CREATE TABLE class 
(
    Cnu CHAR(6) PRIMARY KEY ,/* 列级完整性约束条件，Cnu是主码 */
    Tnu CHAR(6) ,
    Dnu CHAR(3) ,
    Cname CHAR(30) NOT NULL,
    FOREIGN KEY (Dnu) REFERENCES department(Dnu),/* 表级完整性的约束条件,Dnu是外码连接department */
    FOREIGN KEY (Tnu) REFERENCES teacher(Tnu)/* 表级完整性的约束条件,Tnu是外码连接teacher */
);

/* 创建学生表 */
CREATE TABLE student
(
    Snu CHAR(12) PRIMARY KEY ,/* 列级完整性约束条件，Snu是主码 */
    Sname CHAR(20) NOT NULL,
    Ssex CHAR(2),
    Sbirth DATE,
    Cnu CHAR(8),
    Scredit FLOAT(3),
    Sette DATE,
    Spd CHAR(33) NOT NULL ,
    FOREIGN KEY (Cnu) REFERENCES class(Cnu)/* 表级完整性的约束条件,Cnu是外码连接class */
);
/* 创建课程表 */
CREATE TABLE lesson
(
    Lnu CHAR(6) PRIMARY KEY ,/* 列级完整性约束条件，Lnu是主码 */
    Lname CHAR(20) NOT NULL ,
    Lcredit FLOAT(2) ,
    Las CHAR(80) ,
    Tnu CHAR(6) ,
    FOREIGN KEY (Tnu) REFERENCES teacher(Tnu)/* 表级完整性的约束条件,Tnu是外码连接teacher */
);
/* 创建选课记录表 */
CREATE TABLE sc 
(
    Snu CHAR(12) ,
    Lnu CHAR(6) ,
    Grade FLOAT(2) CHECK(Grade>0 AND Grade <= 150),
    PRIMARY KEY (Snu,Lnu) ,/* 主码由两个属性构成，必须为表级完整性进行定义 */
    /* 表级完整性约束条件，Snu是外码连接student,删除更新student元组sc与同步*/
    FOREIGN KEY (Snu) REFERENCES student(Snu) ON DELETE CASCADE ON UPDATE CASCADE ,
    /* 表级完整性约束条件，Lnu是外码连接lesson,当删除lesson表中的元组造成与sc不一致时拒绝删除，更新lesson元组与sc同步*/
    FOREIGN KEY (Lnu) REFERENCES lesson(Lnu) ON DELETE NO ACTION ON UPDATE CASCADE 
);

/* 创建公告表 */
CREATE TABLE notice
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL ,
  author VARCHAR(30) NOT NULL ,
  content VARCHAR(100) NOT NULL ,
  type INT NOT NULL ,
  releasedate DATE NOT NULL
);

/* 创建角色表 */
CREATE TABLE role
(
    roleID INT PRIMARY KEY ,
    roleName CHAR(20)
);

/* 创建权限角色关系表 */
CREATE TABLE role_permission
(
  id INT PRIMARY KEY ,
  role_id INT ,
  permission_id INT 
);
/* 创建权限资源表 */
CREATE TABLE  permission
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    text CHAR(20) NOT NULL ,
    permission CHAR(20) NOT NULL ,
    url CHAR(50),
    percode CHAR(30),
    parentid INT(10) ,
    sortstring INT,
    available INT 
);

/* 建立索引 */
CREATE UNIQUE INDEX stu_name ON student(Sname);
CREATE UNIQUE INDEX tea_name ON teacher(Tname);
CREATE UNIQUE INDEX sc_nu ON sc(Snu ASC ,Lnu DESC);

/* 使用案例 */

/* CS系为例的学生信息视图 */
CREATE VIEW CS_student AS SELECT * FROM student WHERE Cnu IN (SELECT Cnu FROM department WHERE Dname = 'CS') WITH CHECK OPTION ;
SELECT * FROM CS_student;
SELECT COUNT(*) FROM CS_student;

/* 查询班级的学生信息 */
SELECT Snu,Sname,Ssex,Sbirth FROM student WHERE Cnu = '20181111';
SELECT COUNT(*) FROM student WHERE Cnu = '20181111';

/* 查询学生的成绩、学分 */
SELECT sc.Lnu, Lname, Lcredit,Grade FROM lesson,sc WHERE Snu = '180608501017' AND SC.Lnu = lesson.Lnu;
SELECT SUM(Lcredit) FROM sc,lesson WHERE Snu = '180608501017' AND sc.Lnu = lesson.Lnu;

/* 查询教师授课信息 */
SELECT lesson.* FROM lesson WHERE Tnu = '201809';
SELECT student.Snu,Sname,Cnu,Grade FROM lesson,sc,student WHERE Tnu = '201809' AND lesson.Lnu = sc.Lnu AND sc.Snu = student.Snu;


```

# 延伸阅读

