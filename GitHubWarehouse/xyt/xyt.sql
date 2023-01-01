/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 100611 (10.6.11-MariaDB-1)
 Source Host           : localhost:3306
 Source Schema         : xyt

 Target Server Type    : MySQL
 Target Server Version : 100611 (10.6.11-MariaDB-1)
 File Encoding         : 65001

 Date: 01/01/2023 20:26:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Anu` char(11) NOT NULL,
  `Apd` char(33) NOT NULL,
  PRIMARY KEY (`Anu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` (`Anu`, `Apd`) VALUES ('admin', '038bdaf98f2037b31f1e75b5b4c9b26e');
COMMIT;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `Cnu` char(6) NOT NULL,
  `Tnu` char(6) DEFAULT NULL,
  `Dnu` char(3) DEFAULT NULL,
  `Cname` char(30) NOT NULL,
  PRIMARY KEY (`Cnu`),
  KEY `Dnu` (`Dnu`),
  KEY `Tnu` (`Tnu`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`Dnu`) REFERENCES `department` (`Dnu`),
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`Tnu`) REFERENCES `teacher` (`Tnu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of class
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `Dnu` char(3) NOT NULL,
  `Dname` char(20) NOT NULL,
  `Dphone` char(11) DEFAULT NULL,
  PRIMARY KEY (`Dnu`),
  UNIQUE KEY `Dname` (`Dname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
  `Lnu` char(6) NOT NULL,
  `Lname` char(20) NOT NULL,
  `Lcredit` float DEFAULT NULL,
  `Las` char(80) DEFAULT NULL,
  `Tnu` char(6) DEFAULT NULL,
  PRIMARY KEY (`Lnu`),
  KEY `Tnu` (`Tnu`),
  CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`Tnu`) REFERENCES `teacher` (`Tnu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of lesson
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(30) NOT NULL,
  `content` varchar(100) NOT NULL,
  `type` int(11) NOT NULL,
  `releasedate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` char(20) NOT NULL,
  `permission` char(20) NOT NULL,
  `url` char(50) DEFAULT NULL,
  `percode` char(30) DEFAULT NULL,
  `parentid` int(10) DEFAULT NULL,
  `sortstring` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (1, '系统管理', 'menu', 'user', 'system:*', 1, 0, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (2, '用户管理', 'menu', 'user/index', 'user:query', 1, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (3, '添加用户', 'function', 'user/add', 'user:add', 2, 2, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (4, '删除用户', 'function', 'user/delete', 'user:delete', 2, 2, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (5, '修改用户', 'function', 'user/update', 'user:update', 2, 2, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (6, '角色管理', 'menu', 'role/index', 'role:query', 1, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (7, '添加角色', 'function', 'role/add', 'role:add', 6, 3, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (8, '删除角色', 'function', 'role/add', 'role:delete', 6, 3, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (9, '修改角色', 'function', 'role/update', 'role:update', 6, 3, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (10, '学生信息', 'menu', 'student/index', 'student:query', 18, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (11, '添加学生', 'function', 'student/add', 'student:add', 10, 4, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (12, '删除学生', 'function', 'student/delete', 'student:delete', 10, 4, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (13, '修改学生', 'function', 'student/update', 'student:update', 10, 4, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (14, '权限管理', 'menu', 'permission/index', 'permission:query', 1, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (15, '添加权限', 'function', 'permission/add', 'permission:add', 14, 2, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (16, '修改权限', 'function', 'permission/update', 'permission:update', 14, 2, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (17, '删除权限', 'function', 'permission/delete', 'permission:delete', 14, 2, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (18, '信息管理', 'menu', 'message', 'message:*', NULL, 0, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (19, '教师信息', 'menu', 'teacher/index', 'teacher:query', 18, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (20, '查看教师', 'function', 'teacher/index', 'teacher:query', 19, 5, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (21, '添加教师', 'function', 'teacher/add', 'teacher:add', 19, 5, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (22, '删除教师', 'function', 'teacher/delete', 'teacher:delete', 19, 5, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (23, '修改教师', 'function', 'teacher/update', 'teacher:update', 19, 5, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (24, '查看学生', 'function', 'student/index', 'student:query', 10, 4, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (25, '基本课程管理', 'menu', 'course/index', 'basecourse:query', 18, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (26, '查看基本课程', 'function', 'course/index', 'basecourse:query', 25, 6, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (27, '添加基本课程', 'function', 'course/add', 'basecourse:add', 25, 6, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (28, '删除基本课程', 'function', 'course/delete', 'basecourse:delete', 25, 6, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (29, '修改基本课程', 'function', 'course/update', 'basecourse:update', 25, 6, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (30, '课程管理', 'menu', 'course', 'curriculum:*', 30, 0, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (31, '课程信息(管理员)', 'menu', 'course/adminIndex', 'course:adminIndex', 30, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (32, '查看课程信息', 'function', 'course/adminIndex', 'course:adminIndex', 31, 7, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (33, '添加课程信息', 'function', 'course/adminAdd', 'course:adminAdd', 31, 7, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (34, '删除课程信息', 'function', 'course/adminDelete', 'course:adminDelete', 31, 7, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (35, '修改课程信息', 'function', 'course/adminUpdate', 'course:adminUpdate', 31, 7, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (36, '课程信息(学生)', 'menu', 'course/studentIndex', 'course:studentIndex', 30, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (37, '选择课程', 'function', 'course/studentSelect', 'course:studentSelect', 36, 8, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (38, '退选课程', 'function', 'course/studentDrop', 'course:studentDrop', 36, 8, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (39, '查看课程', 'function', 'course/studentIndex', 'course:studentIndex', 36, 8, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (40, '课程信息(教师)', 'menu', 'course/teacherIndex', 'course:teacherIndex', 30, 11, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (41, '查看课程', 'function', 'course/teacherIndex', 'course:teacherIndex', 40, 9, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (42, '结束课程', 'function', 'course/teacherEndCourse', 'course:teacherEndCourse', 40, 9, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (43, '成绩管理', 'menu', 'score', 'result:*', 43, 0, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (44, '学生成绩列表', 'function', 'score/scoreIndex', 'score:scoreIndex', 43, 10, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (45, '我的成绩', 'function', 'score/myScoreIndex', 'score:myScoreIndex', 43, 10, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (46, '我的学生选课信息', 'menu', 'score/myStudentIndex', 'score:myStudentIndex', 30, 11, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (47, '通知管理', 'menu', 'notice/index', 'notice:query', 1, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (48, '添加通知', 'function', 'notice/add', 'notice:add', 47, 12, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (49, '删除通知', 'function', 'notice/delete', 'notice:delete', 47, 12, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (50, '修改通知', 'function', 'notice/update', 'notice:update', 47, 12, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (51, '信息报表', 'menu', 'echart', 'echarts:*', 51, 0, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (52, '成绩报表', 'function', 'echart/scoreEchart', 'echart:score', 51, 13, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (53, '人数报表', 'function', 'echart/peopleEchart', 'echart:people', 51, 13, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (54, '班级信息', 'menu', 'class/index', 'class:query', 18, 1, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (55, '查看班级信息', 'function', 'class/index', 'class:query', 54, 14, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (56, '添加班级信息', 'function', 'class/add', 'class:add', 54, 14, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (57, '删除班级信息', 'function', 'class/delete', 'class:delete', 54, 14, 1);
INSERT INTO `permission` (`id`, `text`, `permission`, `url`, `percode`, `parentid`, `sortstring`, `available`) VALUES (58, '修改班级信息', 'function', 'class/update', 'class:update', 54, 14, 1);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `roleName` char(20) DEFAULT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`roleID`, `roleName`) VALUES (1, 'admin');
INSERT INTO `role` (`roleID`, `roleName`) VALUES (2, 'student');
INSERT INTO `role` (`roleID`, `roleName`) VALUES (3, 'teacher');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (404, 2, 18);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (405, 2, 10);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (406, 2, 13);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (407, 2, 24);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (408, 2, 19);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (409, 2, 20);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (410, 2, 25);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (411, 2, 26);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (412, 2, 30);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (413, 2, 36);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (414, 2, 37);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (415, 2, 38);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (416, 2, 39);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (417, 2, 43);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (418, 2, 45);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (419, 2, 51);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (420, 2, 53);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (421, 3, 18);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (422, 3, 10);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (423, 3, 24);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (424, 3, 19);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (425, 3, 20);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (426, 3, 23);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (427, 3, 25);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (428, 3, 26);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (429, 3, 30);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (430, 3, 40);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (431, 3, 41);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (432, 3, 42);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (433, 3, 46);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (434, 3, 43);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (435, 3, 44);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (436, 3, 51);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (437, 3, 52);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (438, 3, 53);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (461, 1, 1);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (462, 1, 2);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (463, 1, 3);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (464, 1, 4);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (465, 1, 5);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (466, 1, 6);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (467, 1, 7);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (468, 1, 8);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (469, 1, 9);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (470, 1, 14);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (471, 1, 15);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (472, 1, 16);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (473, 1, 17);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (474, 1, 47);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (475, 1, 48);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (476, 1, 49);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (477, 1, 50);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (478, 1, 18);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (479, 1, 10);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (480, 1, 11);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (481, 1, 12);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (482, 1, 13);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (483, 1, 24);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (484, 1, 19);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (485, 1, 20);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (486, 1, 21);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (487, 1, 22);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (488, 1, 23);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (489, 1, 25);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (490, 1, 26);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (491, 1, 27);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (492, 1, 28);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (493, 1, 29);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (494, 1, 54);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (495, 1, 55);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (496, 1, 56);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (497, 1, 57);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (498, 1, 58);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (499, 1, 30);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (500, 1, 31);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (501, 1, 32);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (502, 1, 33);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (503, 1, 34);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (504, 1, 35);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (505, 1, 51);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (506, 1, 52);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES (507, 1, 53);
COMMIT;

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `Snu` char(12) NOT NULL,
  `Lnu` char(6) NOT NULL,
  `Grade` float DEFAULT NULL CHECK (`Grade` > 0 and `Grade` <= 150),
  PRIMARY KEY (`Snu`,`Lnu`),
  UNIQUE KEY `sc_nu` (`Snu`,`Lnu`),
  KEY `Lnu` (`Lnu`),
  CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`Snu`) REFERENCES `student` (`Snu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sc_ibfk_2` FOREIGN KEY (`Lnu`) REFERENCES `lesson` (`Lnu`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sc
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Snu` char(12) NOT NULL,
  `Sname` char(20) NOT NULL,
  `Ssex` char(2) DEFAULT NULL,
  `Sbirth` date DEFAULT NULL,
  `Cnu` char(8) DEFAULT NULL,
  `Scredit` float DEFAULT NULL,
  `Sette` date DEFAULT NULL,
  `Spd` char(33) NOT NULL,
  PRIMARY KEY (`Snu`),
  UNIQUE KEY `stu_name` (`Sname`),
  KEY `Cnu` (`Cnu`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Cnu`) REFERENCES `class` (`Cnu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `Tnu` char(6) NOT NULL,
  `Tname` char(20) NOT NULL,
  `Tsex` char(2) NOT NULL,
  `Title` char(8) DEFAULT NULL,
  `Tphone` char(11) DEFAULT NULL,
  `Teyte` date NOT NULL,
  `Tsnte` date DEFAULT NULL,
  `Dnu` char(3) DEFAULT NULL,
  `Tpd` char(33) NOT NULL,
  PRIMARY KEY (`Tnu`),
  UNIQUE KEY `tea_name` (`Tname`),
  KEY `Dnu` (`Dnu`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`Dnu`) REFERENCES `department` (`Dnu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- View structure for CS_student
-- ----------------------------
DROP VIEW IF EXISTS `CS_student`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `CS_student` AS select `student`.`Snu` AS `Snu`,`student`.`Sname` AS `Sname`,`student`.`Ssex` AS `Ssex`,`student`.`Sbirth` AS `Sbirth`,`student`.`Cnu` AS `Cnu`,`student`.`Scredit` AS `Scredit`,`student`.`Sette` AS `Sette`,`student`.`Spd` AS `Spd` from `student` where `student`.`Cnu` in (select `student`.`Cnu` from `department` where `department`.`Dname` = 'CS')  WITH CASCADED CHECK OPTION;

SET FOREIGN_KEY_CHECKS = 1;
