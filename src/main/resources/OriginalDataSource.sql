/*
 Navicat Premium Data Transfer

 Source Server         : AliYunServer
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : 123.56.220.33:3306
 Source Schema         : examination_system

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 28/05/2025 21:34:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `collegeID` int NOT NULL,
  `collegeName` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程名',
  PRIMARY KEY (`collegeID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '计算机系');
INSERT INTO `college` VALUES (2, '设计系');
INSERT INTO `college` VALUES (3, '财经系');
INSERT INTO `college` VALUES (4, '会计系');
INSERT INTO `college` VALUES (5, '车辆工程系');
INSERT INTO `college` VALUES (6, '社会系');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `courseID` int NOT NULL,
  `courseName` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程名称',
  `teacherID` int NOT NULL,
  `courseTime` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '开课时间',
  `classRoom` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '开课地点',
  `courseWeek` int NULL DEFAULT NULL COMMENT '学时',
  `courseType` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '课程类型',
  `collegeID` int NOT NULL COMMENT '所属院系',
  `score` int NOT NULL COMMENT '学分',
  PRIMARY KEY (`courseID`) USING BTREE,
  INDEX `collegeID`(`collegeID` ASC) USING BTREE,
  INDEX `teacherID`(`teacherID` ASC) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'C语言程序设计', 1001, '周二', 'A401', 18, '必修课', 1, 3);
INSERT INTO `course` VALUES (2, 'Python网络爬虫', 1001, '周四', 'B402', 18, '必修课', 1, 3);
INSERT INTO `course` VALUES (3, '数据结构', 1001, '周四', 'A401', 18, '必修课', 1, 2);
INSERT INTO `course` VALUES (4, 'Java程序设计', 1002, '周五', 'A401', 18, '必修课', 1, 2);
INSERT INTO `course` VALUES (5, '英语', 1002, '周四', 'B302', 18, '必修课', 2, 2);
INSERT INTO `course` VALUES (6, '计算机组成原理', 1003, '周三', 'B401', 18, '专业核心', 2, 4);
INSERT INTO `course` VALUES (7, '软件工程', 1003, '周三', 'C211', 18, '专业核心', 2, 4);
INSERT INTO `course` VALUES (8, '毛概', 1004, '周一', 'C111', 19, '专业必修', 1, 4);
INSERT INTO `course` VALUES (9, 'Compilers: Principles, Techniques, and Tools', 1005, '周三', 'D502', 11, '专业核心', 1, 4);
INSERT INTO `course` VALUES (10, '综合课设', 1006, '周日', 'D422', 3, '集中检查', 1, 1);
INSERT INTO `course` VALUES (11, '中国现代史', 1007, '周一', 'C411', 15, '专业必修', 1, 2);
INSERT INTO `course` VALUES (12, 'Linux', 1007, '周二', 'D201', 15, '专业核心', 1, 4);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleID` int NOT NULL,
  `roleName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `permissions` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`roleID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (0, 'admin', NULL);
INSERT INTO `role` VALUES (1, 'teacher', NULL);
INSERT INTO `role` VALUES (2, 'student', NULL);

-- ----------------------------
-- Table structure for selectedcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectedcourse`;
CREATE TABLE `selectedcourse`  (
  `courseID` int NOT NULL,
  `studentID` int NOT NULL,
  `mark` int NULL DEFAULT NULL COMMENT '成绩',
  INDEX `courseID`(`courseID` ASC) USING BTREE,
  INDEX `studentID`(`studentID` ASC) USING BTREE,
  CONSTRAINT `selectedcourse_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `selectedcourse_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of selectedcourse
-- ----------------------------
INSERT INTO `selectedcourse` VALUES (2, 10001, 12);
INSERT INTO `selectedcourse` VALUES (1, 10001, 95);
INSERT INTO `selectedcourse` VALUES (1, 10002, 66);
INSERT INTO `selectedcourse` VALUES (1, 10003, 100);
INSERT INTO `selectedcourse` VALUES (2, 10003, 99);
INSERT INTO `selectedcourse` VALUES (3, 10001, NULL);
INSERT INTO `selectedcourse` VALUES (4, 10001, NULL);
INSERT INTO `selectedcourse` VALUES (12, 10001, 69);
INSERT INTO `selectedcourse` VALUES (11, 10002, 89);
INSERT INTO `selectedcourse` VALUES (12, 10002, NULL);
INSERT INTO `selectedcourse` VALUES (11, 10003, 82);
INSERT INTO `selectedcourse` VALUES (12, 10003, NULL);
INSERT INTO `selectedcourse` VALUES (11, 10004, 36);
INSERT INTO `selectedcourse` VALUES (12, 10004, NULL);
INSERT INTO `selectedcourse` VALUES (11, 10005, NULL);
INSERT INTO `selectedcourse` VALUES (12, 10005, NULL);
INSERT INTO `selectedcourse` VALUES (11, 10006, NULL);
INSERT INTO `selectedcourse` VALUES (12, 10006, NULL);
INSERT INTO `selectedcourse` VALUES (11, 10007, NULL);
INSERT INTO `selectedcourse` VALUES (6, 10007, NULL);
INSERT INTO `selectedcourse` VALUES (12, 10007, NULL);
INSERT INTO `selectedcourse` VALUES (5, 10001, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `birthYear` date NULL DEFAULT NULL COMMENT '出生日期',
  `grade` date NULL DEFAULT NULL COMMENT '入学时间',
  `collegeID` int NOT NULL COMMENT '院系id',
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `collegeID`(`collegeID` ASC) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (10001, '李志伟', '男', '1996-09-02', '2015-09-02', 4);
INSERT INTO `student` VALUES (10002, '何新越', '男', '1995-09-14', '2015-09-02', 3);
INSERT INTO `student` VALUES (10003, '王诗涵', '女', '1996-09-02', '2015-09-02', 2);
INSERT INTO `student` VALUES (10004, 'XJY', '男', '1996-09-02', '2015-09-02', 5);
INSERT INTO `student` VALUES (10005, '明志鹏', '男', '1998-09-02', '2015-09-02', 6);
INSERT INTO `student` VALUES (10006, '雷超', '男', '1995-09-02', '2015-09-02', 3);
INSERT INTO `student` VALUES (10007, 'JAMES', '男', '2000-09-02', '2015-09-02', 5);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `birthYear` date NOT NULL,
  `degree` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学历',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '职称',
  `grade` date NULL DEFAULT NULL COMMENT '入职时间',
  `collegeID` int NOT NULL COMMENT '院系',
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `collegeID`(`collegeID` ASC) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1008 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1001, 'Edsger Wybe Dijkstra', '男', '1990-03-08', '硕士', '副教授', '2015-09-02', 2);
INSERT INTO `teacher` VALUES (1002, 'Dennis MacAlistair Ritchie', '男', '1996-09-02', '本科', '普通教师', '2015-09-02', 1);
INSERT INTO `teacher` VALUES (1003, 'Edsger Wybe Dijkstra', '男', '1996-09-02', '硕士', '教授', '2015-09-02', 1);
INSERT INTO `teacher` VALUES (1004, 'Linus Benedict Torvalds', '男', '1996-09-02', '本科', '讲师', '2015-09-02', 5);
INSERT INTO `teacher` VALUES (1005, 'Tim Berners Lee', '男', '1996-09-02', '硕士', '副教授', '2015-09-02', 3);
INSERT INTO `teacher` VALUES (1006, 'James Gosling', '男', '1999-09-02', '本科', '助教', '2016-09-02', 5);
INSERT INTO `teacher` VALUES (1007, 'Grace Murray Hopper', '女', '1997-09-02', '本科', '教授', '2015-09-02', 4);

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin`  (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `role` int NOT NULL DEFAULT 2 COMMENT '角色权限',
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `role`(`role` ASC) USING BTREE,
  CONSTRAINT `userlogin_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 0);
INSERT INTO `userlogin` VALUES (8, '10001', 'd89f3a35931c386956c1a402a8e09941', 2);
INSERT INTO `userlogin` VALUES (9, '10002', '9103c8c82514f39d8360c7430c4ee557', 2);
INSERT INTO `userlogin` VALUES (10, '10003', 'f5dffc111454b227fbcdf36178dfe6ac', 2);
INSERT INTO `userlogin` VALUES (14, '1001', 'b8c37e33defde51cf91e1e03e51657da', 1);
INSERT INTO `userlogin` VALUES (15, '1002', 'fba9d88164f3e2d9109ee770223212a0', 1);
INSERT INTO `userlogin` VALUES (20, '1003', 'aa68c75c4a77c87f97fb686b2f068676', 1);
INSERT INTO `userlogin` VALUES (21, '10004', 'd783823cc6284b929c2cd8df2167d212', 2);
INSERT INTO `userlogin` VALUES (22, '10005', '6eb887126d24e8f1cd8ad5033482c781', 2);
INSERT INTO `userlogin` VALUES (23, '10006', '19b1b73d63d4c9ea79f8ca57e9d67095', 2);
INSERT INTO `userlogin` VALUES (24, '10007', '9cdf26568d166bc6793ef8da5afa0846', 2);
INSERT INTO `userlogin` VALUES (25, '1004', 'fed33392d3a48aa149a87a38b875ba4a', 1);
INSERT INTO `userlogin` VALUES (26, '1005', '2387337ba1e0b0249ba90f55b2ba2521', 1);
INSERT INTO `userlogin` VALUES (27, '1006', '9246444d94f081e3549803b928260f56', 1);
INSERT INTO `userlogin` VALUES (28, '1007', 'd7322ed717dedf1eb4e6e52a37ea7bcd', 1);

SET FOREIGN_KEY_CHECKS = 1;
