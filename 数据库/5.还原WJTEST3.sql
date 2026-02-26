/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 127.0.0.1:1521
 Source Schema         : WJTEST

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 08/05/2025 20:03:08
*/


-- ----------------------------
-- Table structure for AGENTTABLE
-- ----------------------------
DROP TABLE "WJTEST"."AGENTTABLE";
CREATE TABLE "WJTEST"."AGENTTABLE" (
  "TB_ID" NUMBER ,
  "AT_ID" VARCHAR2(200 BYTE) ,
  "AT_NAME" VARCHAR2(200 BYTE) ,
  "AT_QQ" VARCHAR2(200 BYTE) ,
  "AT_WX" VARCHAR2(200 BYTE) ,
  "AT_ALIPAYURL" VARCHAR2(200 BYTE) ,
  "AT_WXURL" VARCHAR2(200 BYTE) ,
  "AT_CRETIME" VARCHAR2(200 BYTE) ,
  "AT_CREMANAGEID" NUMBER ,
  "AT_UPATEMANAGEID" NUMBER ,
  "AT_UPATIME" VARCHAR2(200 BYTE) ,
  "AT_MEMO" VARCHAR2(200 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_ID" IS '代理id';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_NAME" IS '代理名称';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_QQ" IS '代理qq';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_WX" IS '代理微信';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_ALIPAYURL" IS '支付宝url';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_WXURL" IS '微信url';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_CRETIME" IS '创建时间';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_CREMANAGEID" IS '创建者管理员id';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_UPATEMANAGEID" IS '更新管理员id';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_UPATIME" IS '更新时间';
COMMENT ON COLUMN "WJTEST"."AGENTTABLE"."AT_MEMO" IS '备注';

-- ----------------------------
-- Table structure for APPVERSION
-- ----------------------------
DROP TABLE "WJTEST"."APPVERSION";
CREATE TABLE "WJTEST"."APPVERSION" (
  "VER_ID" VARCHAR2(20 BYTE) ,
  "VER_URL" VARCHAR2(20 BYTE) ,
  "VER_SIGN" VARCHAR2(20 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."APPVERSION"."VER_ID" IS '版本号';
COMMENT ON COLUMN "WJTEST"."APPVERSION"."VER_URL" IS '下载路径';
COMMENT ON COLUMN "WJTEST"."APPVERSION"."VER_SIGN" IS '标识(1,pc 2,app)';

-- ----------------------------
-- Table structure for BABY
-- ----------------------------
DROP TABLE "WJTEST"."BABY";
CREATE TABLE "WJTEST"."BABY" (
  "BABYID" NUMBER(32) ,
  "BABYNAME" VARCHAR2(50 BYTE) ,
  "QIZHI" NUMBER(8) DEFAULT 0                         ,
  "NEILI" NUMBER(8) DEFAULT 0                         ,
  "ZHILI" NUMBER(8) DEFAULT 0                         ,
  "NAILI" NUMBER(8) DEFAULT 0                         ,
  "MINGQI" NUMBER(8) DEFAULT 0                         ,
  "DAODE" NUMBER(8) DEFAULT 0                         ,
  "PANNI" NUMBER(8) DEFAULT 0                         ,
  "WANXING" NUMBER(8) DEFAULT 0                         ,
  "QINGMI" NUMBER(8) DEFAULT 0                         ,
  "XIAOXIN" NUMBER(8) DEFAULT 0                         ,
  "WENBAO" NUMBER(8) DEFAULT 0                         ,
  "PILAO" NUMBER(8) DEFAULT 0                         ,
  "YANGYUJIN" NUMBER(8) DEFAULT 0                         ,
  "ROLEID" NUMBER(32) ,
  "BABYAGE" NUMBER(8) DEFAULT 0                         ,
  "CHILDSEX" NUMBER(8) DEFAULT 0                         ,
  "OUTCOME" VARCHAR2(32 BYTE) ,
  "TALENTS" VARCHAR2(150 BYTE) DEFAULT '1=1|2=1|3=1'                         ,
  "PARTS" VARCHAR2(100 BYTE) DEFAULT '-1|-1|-1|-1'                         ,
  "ROLENAME" VARCHAR2(50 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."BABY"."BABYID" IS '宝宝ID';
COMMENT ON COLUMN "WJTEST"."BABY"."BABYNAME" IS '宝宝名字';
COMMENT ON COLUMN "WJTEST"."BABY"."QIZHI" IS '气质';
COMMENT ON COLUMN "WJTEST"."BABY"."NEILI" IS '内力';
COMMENT ON COLUMN "WJTEST"."BABY"."ZHILI" IS '智力';
COMMENT ON COLUMN "WJTEST"."BABY"."NAILI" IS '耐力';
COMMENT ON COLUMN "WJTEST"."BABY"."MINGQI" IS '名气';
COMMENT ON COLUMN "WJTEST"."BABY"."DAODE" IS '道德';
COMMENT ON COLUMN "WJTEST"."BABY"."PANNI" IS '叛逆';
COMMENT ON COLUMN "WJTEST"."BABY"."WANXING" IS '玩性';
COMMENT ON COLUMN "WJTEST"."BABY"."QINGMI" IS '亲密';
COMMENT ON COLUMN "WJTEST"."BABY"."XIAOXIN" IS '孝心';
COMMENT ON COLUMN "WJTEST"."BABY"."WENBAO" IS '温饱';
COMMENT ON COLUMN "WJTEST"."BABY"."PILAO" IS '疲劳';
COMMENT ON COLUMN "WJTEST"."BABY"."YANGYUJIN" IS '养育金';
COMMENT ON COLUMN "WJTEST"."BABY"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."BABY"."BABYAGE" IS '宝宝年龄';
COMMENT ON COLUMN "WJTEST"."BABY"."CHILDSEX" IS '性别';
COMMENT ON COLUMN "WJTEST"."BABY"."OUTCOME" IS '结局';
COMMENT ON COLUMN "WJTEST"."BABY"."TALENTS" IS '天资';
COMMENT ON COLUMN "WJTEST"."BABY"."PARTS" IS '装备';

-- ----------------------------
-- Table structure for BUYCOUNT
-- ----------------------------
DROP TABLE "WJTEST"."BUYCOUNT";
CREATE TABLE "WJTEST"."BUYCOUNT" (
  "BID" NUMBER(32) NOT NULL ,
  "PTYPE" NUMBER(32) ,
  "SHOPID" NUMBER(32) ,
  "SHOPTYPE" NUMBER(32) ,
  "TOTALNUM" NUMBER(32) ,
  "TOTALPRICE" NUMBER(32) ,
  "WEEKNUM" NUMBER(32) ,
  "WEEKPRICE" NUMBER(32) ,
  "DAYNUM" NUMBER(32) ,
  "DAYPRICE" NUMBER(32) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of BUYCOUNT
-- ----------------------------
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('101', '1', '1', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('201', '1', '2', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('301', '1', '3', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('401', '1', '4', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('501', '1', '5', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('601', '1', '6', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('701', '1', '7', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('801', '1', '8', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('901', '1', '9', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1001', '1', '10', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1101', '1', '11', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1201', '1', '12', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1301', '1', '13', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1401', '1', '14', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1501', '1', '15', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1601', '1', '16', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1701', '1', '17', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1801', '1', '18', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1901', '1', '19', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2001', '1', '20', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2101', '1', '21', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2201', '1', '22', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2301', '1', '23', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2401', '1', '24', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2501', '1', '25', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2601', '1', '26', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2701', '1', '27', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2801', '1', '28', '5', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3001', '1', '30', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3101', '1', '31', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3201', '1', '32', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3301', '1', '33', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3401', '1', '34', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3501', '1', '35', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3601', '1', '36', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3701', '1', '37', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3801', '1', '38', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3901', '1', '39', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4001', '1', '40', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4101', '1', '41', '6', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4401', '1', '44', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4501', '1', '45', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4601', '1', '46', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4701', '1', '47', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4801', '1', '48', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4901', '1', '49', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5001', '1', '50', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5101', '1', '51', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5201', '1', '52', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5301', '1', '53', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5401', '1', '54', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5501', '1', '55', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5601', '1', '56', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5701', '1', '57', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5801', '1', '58', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5901', '1', '59', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6001', '1', '60', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6101', '1', '61', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6201', '1', '62', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6301', '1', '63', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6401', '1', '64', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6501', '1', '65', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6601', '1', '66', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6701', '1', '67', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6801', '1', '68', '7', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7101', '1', '71', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7201', '1', '72', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7301', '1', '73', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7401', '1', '74', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7501', '1', '75', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7601', '1', '76', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7701', '1', '77', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7801', '1', '78', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('7901', '1', '79', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('8001', '1', '80', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('8101', '1', '81', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('8201', '1', '82', '8', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('8501', '1', '85', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('8601', '1', '86', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('8701', '1', '87', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('8801', '1', '88', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('8901', '1', '89', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9001', '1', '90', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9101', '1', '91', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9201', '1', '92', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9301', '1', '93', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9401', '1', '94', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9501', '1', '95', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9601', '1', '96', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9701', '1', '97', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9801', '1', '98', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('9901', '1', '99', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10001', '1', '100', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10101', '1', '101', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10201', '1', '102', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10301', '1', '103', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10401', '1', '104', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10501', '1', '105', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10601', '1', '106', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10701', '1', '107', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10801', '1', '108', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('10901', '1', '109', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('11001', '1', '110', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('11101', '1', '111', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('11201', '1', '112', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('11301', '1', '113', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('11401', '1', '114', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('11501', '1', '115', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('11601', '1', '116', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('11901', '1', '119', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12001', '1', '120', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12101', '1', '121', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12201', '1', '122', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12301', '1', '123', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12401', '1', '124', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12501', '1', '125', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12601', '1', '126', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12701', '1', '127', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12801', '1', '128', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('12901', '1', '129', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('13001', '1', '130', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('13101', '1', '131', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('13201', '1', '132', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('13301', '1', '133', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('13401', '1', '134', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('13501', '1', '135', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('13601', '1', '136', '10', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('13901', '1', '139', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14001', '1', '140', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14101', '1', '141', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14201', '1', '142', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14301', '1', '143', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14401', '1', '144', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14501', '1', '145', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14601', '1', '146', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14701', '1', '147', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14801', '1', '148', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('14901', '1', '149', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('15001', '1', '150', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('15101', '1', '151', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('15201', '1', '152', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('15501', '1', '155', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('15601', '1', '156', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('15701', '1', '157', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('15801', '1', '158', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('15901', '1', '159', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16001', '1', '160', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16101', '1', '161', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16201', '1', '162', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16301', '1', '163', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16401', '1', '164', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16501', '1', '165', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16601', '1', '166', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16701', '1', '167', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16801', '1', '168', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('16901', '1', '169', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17001', '1', '170', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17101', '1', '171', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17201', '1', '172', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17301', '1', '173', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17401', '1', '174', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17501', '1', '175', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17601', '1', '176', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17701', '1', '177', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17801', '1', '178', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('17901', '1', '179', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('18001', '1', '180', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('18101', '1', '181', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('18401', '1', '184', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('18501', '1', '185', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('18601', '1', '186', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('18701', '1', '187', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('18801', '1', '188', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('18901', '1', '189', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19001', '1', '190', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19101', '1', '191', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19201', '1', '192', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19301', '1', '193', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19401', '1', '194', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19501', '1', '195', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19601', '1', '196', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19701', '1', '197', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19801', '1', '198', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('19901', '1', '199', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20001', '1', '200', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20101', '1', '201', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20201', '1', '202', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20301', '1', '203', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20401', '1', '204', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20501', '1', '205', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20601', '1', '206', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20701', '1', '207', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20801', '1', '208', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('20901', '1', '209', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21001', '1', '210', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21101', '1', '211', '15', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21301', '1', '213', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21401', '1', '214', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21501', '1', '215', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21601', '1', '216', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21701', '1', '217', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21801', '1', '218', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21901', '1', '219', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22001', '1', '220', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22101', '1', '221', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22201', '1', '222', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22301', '1', '223', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22401', '1', '224', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22501', '1', '225', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22601', '1', '226', '18', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22901', '1', '229', '61', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23001', '1', '230', '61', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23101', '1', '231', '61', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23201', '1', '232', '61', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23301', '1', '233', '61', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23401', '1', '234', '61', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23701', '1', '237', '85', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23801', '1', '238', '85', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23901', '1', '239', '85', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24201', '1', '242', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24301', '1', '243', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24401', '1', '244', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24501', '1', '245', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24601', '1', '246', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24701', '1', '247', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24801', '1', '248', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24901', '1', '249', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25001', '1', '250', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25101', '1', '251', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25201', '1', '252', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25301', '1', '253', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25401', '1', '254', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25501', '1', '255', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25601', '1', '256', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25701', '1', '257', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25801', '1', '258', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25901', '1', '259', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26001', '1', '260', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26101', '1', '261', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26201', '1', '262', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26301', '1', '263', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26401', '1', '264', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26501', '1', '265', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26601', '1', '266', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26701', '1', '267', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26801', '1', '268', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26901', '1', '269', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27001', '1', '270', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27101', '1', '271', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27201', '1', '272', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27301', '1', '273', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27401', '1', '274', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27501', '1', '275', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27601', '1', '276', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27701', '1', '277', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27801', '1', '278', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27901', '1', '279', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28001', '1', '280', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28101', '1', '281', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28201', '1', '282', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28301', '1', '283', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28401', '1', '284', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28501', '1', '285', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28601', '1', '286', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28701', '1', '287', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28801', '1', '288', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28901', '1', '289', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29001', '1', '290', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29101', '1', '291', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29201', '1', '292', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29301', '1', '293', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29401', '1', '294', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29501', '1', '295', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29601', '1', '296', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29701', '1', '297', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29801', '1', '298', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29901', '1', '299', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30001', '1', '300', '88', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30101', '1', '301', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30201', '1', '302', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30301', '1', '303', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30401', '1', '304', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30501', '1', '305', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30601', '1', '306', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30701', '1', '307', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30801', '1', '308', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30901', '1', '309', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31001', '1', '310', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31101', '1', '311', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('312001', '1', '3120', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31201', '1', '312', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31301', '1', '313', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31401', '1', '314', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31501', '1', '315', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31601', '1', '316', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31701', '1', '317', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31801', '1', '318', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31901', '1', '319', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32001', '1', '320', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32101', '1', '321', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32201', '1', '322', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32301', '1', '323', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32401', '1', '324', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32501', '1', '325', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32601', '1', '326', '99', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32901', '1', '329', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33001', '1', '330', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33101', '1', '331', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33201', '1', '332', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33301', '1', '333', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33401', '1', '334', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33501', '1', '335', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33601', '1', '336', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33701', '1', '337', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33801', '1', '338', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33901', '1', '339', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34001', '1', '340', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34101', '1', '341', '120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34201', '1', '342', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34301', '1', '343', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34401', '1', '344', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34501', '1', '345', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34601', '1', '346', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34701', '1', '347', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34801', '1', '348', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34901', '1', '349', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35001', '1', '350', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35101', '1', '351', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('352001', '1', '3520', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('353001', '1', '3530', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('354001', '1', '3540', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('355001', '1', '3550', '121', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35201', '1', '352', '123', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35301', '1', '353', '123', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35401', '1', '354', '123', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35501', '1', '355', '123', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35601', '1', '356', '123', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35801', '1', '358', '124', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35901', '1', '359', '124', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36001', '1', '360', '124', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36101', '1', '361', '124', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36401', '1', '364', '125', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36501', '1', '365', '125', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36601', '1', '366', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36701', '1', '367', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36801', '1', '368', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36901', '1', '369', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37001', '1', '370', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37101', '1', '371', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37201', '1', '372', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37301', '1', '373', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37401', '1', '374', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('375001', '1', '3750', '126', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37501', '1', '375', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37601', '1', '376', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37701', '1', '377', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37801', '1', '378', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37901', '1', '379', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38001', '1', '380', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38101', '1', '381', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38201', '1', '382', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38301', '1', '383', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38401', '1', '384', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38501', '1', '385', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38601', '1', '386', '131', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38901', '1', '389', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39001', '1', '390', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39101', '1', '391', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39201', '1', '392', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39301', '1', '393', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39401', '1', '394', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39501', '1', '395', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39601', '1', '396', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39701', '1', '397', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39801', '1', '398', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39901', '1', '399', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40001', '1', '400', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40101', '1', '401', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40201', '1', '402', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40301', '1', '403', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40401', '1', '404', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40501', '1', '405', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40601', '1', '406', '180', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41701', '1', '417', '601', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41801', '1', '418', '601', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41901', '1', '419', '601', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42001', '1', '420', '601', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42101', '1', '421', '601', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42401', '1', '424', '605', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42501', '1', '425', '605', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42601', '1', '426', '605', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42701', '1', '427', '605', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42801', '1', '428', '605', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43001', '1', '430', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43101', '1', '431', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43201', '1', '432', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43301', '1', '433', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43401', '1', '434', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43501', '1', '435', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43601', '1', '436', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43701', '1', '437', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43801', '1', '438', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43901', '1', '439', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44001', '1', '440', '887', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44401', '1', '444', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44501', '1', '445', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44601', '1', '446', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44701', '1', '447', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44801', '1', '448', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44901', '1', '449', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45001', '1', '450', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45101', '1', '451', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45201', '1', '452', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45301', '1', '453', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45401', '1', '454', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45501', '1', '455', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45601', '1', '456', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45701', '1', '457', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45801', '1', '458', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45901', '1', '459', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46001', '1', '460', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46101', '1', '461', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46201', '1', '462', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46301', '1', '463', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46401', '1', '464', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46501', '1', '465', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46601', '1', '466', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46701', '1', '467', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46801', '1', '468', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('46901', '1', '469', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47001', '1', '470', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47101', '1', '471', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47201', '1', '472', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47301', '1', '473', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47401', '1', '474', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47501', '1', '475', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47601', '1', '476', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47701', '1', '477', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47801', '1', '478', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('47901', '1', '479', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48001', '1', '480', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48101', '1', '481', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48201', '1', '482', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48301', '1', '483', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48401', '1', '484', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48501', '1', '485', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48601', '1', '486', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48701', '1', '487', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48801', '1', '488', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('48901', '1', '489', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49001', '1', '490', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49101', '1', '491', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49201', '1', '492', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49301', '1', '493', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49401', '1', '494', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49501', '1', '495', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34500', '0', '345', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34600', '0', '346', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34700', '0', '347', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34800', '0', '348', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34900', '0', '349', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35000', '0', '350', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35100', '0', '351', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35200', '0', '352', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35300', '0', '353', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35400', '0', '354', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35500', '0', '355', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35600', '0', '356', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35700', '0', '357', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35800', '0', '358', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('35900', '0', '359', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36000', '0', '360', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36100', '0', '361', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36200', '0', '362', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36300', '0', '363', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36400', '0', '364', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36500', '0', '365', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36600', '0', '366', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36700', '0', '367', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36800', '0', '368', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('36900', '0', '369', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37000', '0', '370', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37100', '0', '371', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37200', '0', '372', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37300', '0', '373', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37400', '0', '374', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37500', '0', '375', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37600', '0', '376', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37700', '0', '377', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37800', '0', '378', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('37900', '0', '379', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38000', '0', '380', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38100', '0', '381', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38200', '0', '382', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38300', '0', '383', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38400', '0', '384', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38500', '0', '385', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38600', '0', '386', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38700', '0', '387', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38800', '0', '388', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('38900', '0', '389', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39000', '0', '390', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39100', '0', '391', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39200', '0', '392', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39300', '0', '393', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39400', '0', '394', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39500', '0', '395', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39600', '0', '396', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39700', '0', '397', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39800', '0', '398', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('39900', '0', '399', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40000', '0', '400', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40100', '0', '401', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40200', '0', '402', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40300', '0', '403', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40400', '0', '404', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40500', '0', '405', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40600', '0', '406', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40700', '0', '407', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40800', '0', '408', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('40900', '0', '409', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41000', '0', '410', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41100', '0', '411', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41200', '0', '412', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41300', '0', '413', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41400', '0', '414', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41500', '0', '415', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41600', '0', '416', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41700', '0', '417', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41800', '0', '418', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('41900', '0', '419', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42000', '0', '420', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42100', '0', '421', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42200', '0', '422', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42300', '0', '423', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42400', '0', '424', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42500', '0', '425', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42600', '0', '426', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42700', '0', '427', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42800', '0', '428', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('42900', '0', '429', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43000', '0', '430', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43100', '0', '431', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43200', '0', '432', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43300', '0', '433', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43400', '0', '434', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43500', '0', '435', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43600', '0', '436', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43700', '0', '437', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43800', '0', '438', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('43900', '0', '439', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44000', '0', '440', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44100', '0', '441', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44200', '0', '442', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44300', '0', '443', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44400', '0', '444', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44500', '0', '445', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44600', '0', '446', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44700', '0', '447', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44800', '0', '448', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('44900', '0', '449', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45000', '0', '450', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45100', '0', '451', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45200', '0', '452', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45300', '0', '453', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45400', '0', '454', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('45500', '0', '455', '13', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100000', '0', '1000', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100100', '0', '1001', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100200', '0', '1002', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100300', '0', '1003', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100400', '0', '1004', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100500', '0', '1005', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100600', '0', '1006', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100700', '0', '1007', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100800', '0', '1008', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('100900', '0', '1009', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('101000', '0', '1010', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('101100', '0', '1011', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('101200', '0', '1012', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('101300', '0', '1013', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('101400', '0', '1014', '14', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('103', '3', '1', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('203', '3', '2', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('303', '3', '3', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('403', '3', '4', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('503', '3', '5', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('603', '3', '6', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('703', '3', '7', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('803', '3', '8', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('903', '3', '9', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1003', '3', '10', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1103', '3', '11', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1203', '3', '12', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1303', '3', '13', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1403', '3', '14', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1503', '3', '15', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1603', '3', '16', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1703', '3', '17', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('1803', '3', '18', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2803', '3', '28', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('2903', '3', '29', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3003', '3', '30', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3103', '3', '31', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3203', '3', '32', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3303', '3', '33', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3403', '3', '34', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3503', '3', '35', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3603', '3', '36', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3703', '3', '37', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3803', '3', '38', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('3903', '3', '39', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4003', '3', '40', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4103', '3', '41', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4203', '3', '42', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4303', '3', '43', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4403', '3', '44', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4503', '3', '45', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4603', '3', '46', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4703', '3', '47', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4803', '3', '48', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4903', '3', '49', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49601', '1', '496', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49701', '1', '497', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49801', '1', '498', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('49901', '1', '499', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50001', '1', '500', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50101', '1', '501', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50201', '1', '502', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50301', '1', '503', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50401', '1', '504', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50501', '1', '505', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50601', '1', '506', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50701', '1', '507', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50801', '1', '508', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('50901', '1', '509', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51001', '1', '510', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51101', '1', '511', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51201', '1', '512', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51301', '1', '513', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51401', '1', '514', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51501', '1', '515', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51601', '1', '516', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51701', '1', '517', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51801', '1', '518', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('51901', '1', '519', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52001', '1', '520', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52101', '1', '521', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52201', '1', '522', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52301', '1', '523', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52401', '1', '524', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52501', '1', '525', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52601', '1', '526', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52701', '1', '527', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52801', '1', '528', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('52901', '1', '529', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53001', '1', '530', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53101', '1', '531', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53201', '1', '532', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53301', '1', '533', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53401', '1', '534', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53501', '1', '535', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53601', '1', '536', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53701', '1', '537', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53801', '1', '538', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('53901', '1', '539', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54001', '1', '540', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54101', '1', '541', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54201', '1', '542', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54301', '1', '543', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54401', '1', '544', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54501', '1', '545', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54601', '1', '546', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54701', '1', '547', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54801', '1', '548', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('54901', '1', '549', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('55001', '1', '550', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('55301', '1', '553', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('55401', '1', '554', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('55501', '1', '555', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('55601', '1', '556', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('55701', '1', '557', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('55801', '1', '558', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('55901', '1', '559', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('56001', '1', '560', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('56101', '1', '561', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('56201', '1', '562', '1106', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('57401', '1', '574', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('57501', '1', '575', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('57601', '1', '576', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('57701', '1', '577', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('57801', '1', '578', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('57901', '1', '579', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('58001', '1', '580', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('58101', '1', '581', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('58201', '1', '582', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('58301', '1', '583', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('58401', '1', '584', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('58501', '1', '585', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('58601', '1', '586', '2029', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('58901', '1', '589', '8900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('59001', '1', '590', '8900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('59101', '1', '591', '8900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('59201', '1', '592', '8900', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('59601', '1', '596', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('59701', '1', '597', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('59801', '1', '598', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('59901', '1', '599', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60001', '1', '600', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60101', '1', '601', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60201', '1', '602', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60301', '1', '603', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60401', '1', '604', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60501', '1', '605', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60601', '1', '606', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60701', '1', '607', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60801', '1', '608', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('60901', '1', '609', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61001', '1', '610', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61101', '1', '611', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61201', '1', '612', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61301', '1', '613', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61401', '1', '614', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61501', '1', '615', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61601', '1', '616', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61701', '1', '617', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61801', '1', '618', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('61901', '1', '619', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62001', '1', '620', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62101', '1', '621', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62201', '1', '622', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62301', '1', '623', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62401', '1', '624', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62501', '1', '625', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62601', '1', '626', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62701', '1', '627', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62801', '1', '628', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('62901', '1', '629', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63001', '1', '630', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63101', '1', '631', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63201', '1', '632', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63301', '1', '633', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63401', '1', '634', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63501', '1', '635', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63601', '1', '636', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63701', '1', '637', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63801', '1', '638', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('63901', '1', '639', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64001', '1', '640', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64101', '1', '641', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64201', '1', '642', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64301', '1', '643', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64401', '1', '644', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64501', '1', '645', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64601', '1', '646', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64701', '1', '647', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64801', '1', '648', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('64901', '1', '649', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65001', '1', '650', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65101', '1', '651', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65201', '1', '652', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65301', '1', '653', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65401', '1', '654', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65501', '1', '655', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65601', '1', '656', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65701', '1', '657', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65801', '1', '658', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('65901', '1', '659', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66001', '1', '660', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66101', '1', '661', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66201', '1', '662', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66301', '1', '663', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66401', '1', '664', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66501', '1', '665', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66601', '1', '666', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66701', '1', '667', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66801', '1', '668', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('66901', '1', '669', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('67001', '1', '670', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('67101', '1', '671', '181', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('67501', '1', '675', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('67601', '1', '676', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('67701', '1', '677', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('67801', '1', '678', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('67901', '1', '679', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68001', '1', '680', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68101', '1', '681', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68201', '1', '682', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68301', '1', '683', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68401', '1', '684', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68501', '1', '685', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68601', '1', '686', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68701', '1', '687', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68801', '1', '688', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('68901', '1', '689', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69001', '1', '690', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69101', '1', '691', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69201', '1', '692', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69301', '1', '693', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69401', '1', '694', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69501', '1', '695', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69601', '1', '696', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69701', '1', '697', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69801', '1', '698', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('69901', '1', '699', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70001', '1', '700', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70101', '1', '701', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70201', '1', '702', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70301', '1', '703', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70501', '1', '705', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70601', '1', '706', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70701', '1', '707', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70801', '1', '708', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('70901', '1', '709', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71001', '1', '710', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71101', '1', '711', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71201', '1', '712', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71301', '1', '713', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71401', '1', '714', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71501', '1', '715', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71601', '1', '716', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71701', '1', '717', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71801', '1', '718', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('71901', '1', '719', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72001', '1', '720', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72101', '1', '721', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72201', '1', '722', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72301', '1', '723', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72401', '1', '724', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72501', '1', '725', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72601', '1', '726', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72701', '1', '727', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72801', '1', '728', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('72901', '1', '729', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73001', '1', '730', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73101', '1', '731', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73201', '1', '732', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73301', '1', '733', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73401', '1', '734', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73501', '1', '735', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73601', '1', '736', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73701', '1', '737', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73801', '1', '738', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('73901', '1', '739', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74001', '1', '740', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74101', '1', '741', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74201', '1', '742', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74301', '1', '743', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74401', '1', '744', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74501', '1', '745', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74601', '1', '746', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74701', '1', '747', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74801', '1', '748', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('74901', '1', '749', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75001', '1', '750', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75101', '1', '751', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75201', '1', '752', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75301', '1', '753', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75401', '1', '754', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75501', '1', '755', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75601', '1', '756', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75701', '1', '757', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75801', '1', '758', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('75901', '1', '759', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76001', '1', '760', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76101', '1', '761', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76201', '1', '762', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76301', '1', '763', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76401', '1', '764', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76501', '1', '765', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76601', '1', '766', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76701', '1', '767', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76801', '1', '768', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('76901', '1', '769', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77001', '1', '770', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77101', '1', '771', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77201', '1', '772', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77301', '1', '773', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77401', '1', '774', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77501', '1', '775', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77601', '1', '776', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77701', '1', '777', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77801', '1', '778', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('77901', '1', '779', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78001', '1', '780', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78101', '1', '781', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78201', '1', '782', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78301', '1', '783', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78401', '1', '784', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78501', '1', '785', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78601', '1', '786', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78701', '1', '787', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78801', '1', '788', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('78901', '1', '789', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79001', '1', '790', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79101', '1', '791', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79201', '1', '792', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79301', '1', '793', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79401', '1', '794', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79501', '1', '795', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79601', '1', '796', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79701', '1', '797', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79801', '1', '798', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('79901', '1', '799', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80001', '1', '800', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80101', '1', '801', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80201', '1', '802', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80301', '1', '803', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80401', '1', '804', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80501', '1', '805', '182', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('806001', '1', '8060', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('807001', '1', '8070', '89', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80601', '1', '806', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80701', '1', '807', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80801', '1', '808', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('80901', '1', '809', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81001', '1', '810', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81101', '1', '811', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81201', '1', '812', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81301', '1', '813', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81401', '1', '814', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81501', '1', '815', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81601', '1', '816', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81701', '1', '817', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81801', '1', '818', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('81901', '1', '819', '7120', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82001', '1', '820', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82101', '1', '821', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82201', '1', '822', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82301', '1', '823', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82401', '1', '824', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82501', '1', '825', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82601', '1', '826', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82701', '1', '827', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('82801', '1', '828', '8901', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('200', '0', '2', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('300', '0', '3', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('400', '0', '4', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('500', '0', '5', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('600', '0', '6', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('800', '0', '8', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('900', '0', '9', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('501000', '0', '5010', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('501100', '0', '5011', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4600', '0', '46', '3', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4700', '0', '47', '3', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4800', '0', '48', '3', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('4900', '0', '49', '3', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5000', '0', '50', '3', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('5100', '0', '51', '3', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('6700', '0', '67', '4', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('21700', '0', '217', '9', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22000', '0', '220', '21', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22100', '0', '221', '21', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22200', '0', '222', '20', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22300', '0', '223', '20', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22400', '0', '224', '20', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22500', '0', '225', '20', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22600', '0', '226', '20', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22700', '0', '227', '20', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22800', '0', '228', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('22900', '0', '229', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23000', '0', '230', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23100', '0', '231', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23200', '0', '232', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23300', '0', '233', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23400', '0', '234', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23500', '0', '235', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23600', '0', '236', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23700', '0', '237', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23800', '0', '238', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('23900', '0', '239', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24000', '0', '240', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24100', '0', '241', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24200', '0', '242', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24300', '0', '243', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24400', '0', '244', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24500', '0', '245', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24600', '0', '246', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24700', '0', '247', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24800', '0', '248', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('24900', '0', '249', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25000', '0', '250', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25100', '0', '251', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25200', '0', '252', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25300', '0', '253', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25400', '0', '254', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25500', '0', '255', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25600', '0', '256', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25700', '0', '257', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25800', '0', '258', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('25900', '0', '259', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26000', '0', '260', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26100', '0', '261', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26200', '0', '262', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26300', '0', '263', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26400', '0', '264', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26500', '0', '265', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26600', '0', '266', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26700', '0', '267', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26800', '0', '268', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('26900', '0', '269', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27000', '0', '270', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27100', '0', '271', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27200', '0', '272', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27300', '0', '273', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27400', '0', '274', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27500', '0', '275', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27600', '0', '276', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27700', '0', '277', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27800', '0', '278', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('27900', '0', '279', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28000', '0', '280', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28100', '0', '281', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28200', '0', '282', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28300', '0', '283', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28400', '0', '284', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28500', '0', '285', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28600', '0', '286', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28700', '0', '287', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28800', '0', '288', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('28900', '0', '289', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29000', '0', '290', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29100', '0', '291', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29200', '0', '292', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29300', '0', '293', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29400', '0', '294', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29500', '0', '295', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29600', '0', '296', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29700', '0', '297', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29800', '0', '298', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('29900', '0', '299', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30000', '0', '300', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30100', '0', '301', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30200', '0', '302', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30300', '0', '303', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30400', '0', '304', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30500', '0', '305', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30600', '0', '306', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30700', '0', '307', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30800', '0', '308', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('30900', '0', '309', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31000', '0', '310', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31100', '0', '311', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31200', '0', '312', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31300', '0', '313', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31400', '0', '314', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31500', '0', '315', '11', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31600', '0', '316', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31700', '0', '317', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31800', '0', '318', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('31900', '0', '319', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32000', '0', '320', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32100', '0', '321', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32200', '0', '322', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32300', '0', '323', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32400', '0', '324', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32500', '0', '325', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32600', '0', '326', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32700', '0', '327', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32800', '0', '328', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('32900', '0', '329', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33000', '0', '330', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33100', '0', '331', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33200', '0', '332', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33300', '0', '333', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33400', '0', '334', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33500', '0', '335', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33600', '0', '336', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33700', '0', '337', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33800', '0', '338', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('33900', '0', '339', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34000', '0', '340', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34100', '0', '341', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34200', '0', '342', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34300', '0', '343', '12', '0', '0', '0', '0', '0', '0');
INSERT INTO "WJTEST"."BUYCOUNT" VALUES ('34400', '0', '344', '12', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for BUYTYPE
-- ----------------------------
DROP TABLE "WJTEST"."BUYTYPE";
CREATE TABLE "WJTEST"."BUYTYPE" (
  "TID" NUMBER ,
  "BUYTYPE" NUMBER ,
  "TYPENAME" VARCHAR2(100 BYTE) ,
  "TIME" VARCHAR2(100 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."BUYTYPE"."TID" IS '表id';
COMMENT ON COLUMN "WJTEST"."BUYTYPE"."BUYTYPE" IS '类型';
COMMENT ON COLUMN "WJTEST"."BUYTYPE"."TYPENAME" IS '类型名称仙玉/大话币/积分';
COMMENT ON COLUMN "WJTEST"."BUYTYPE"."TIME" IS '记录时间';

-- ----------------------------
-- Table structure for CHONGJIPACK
-- ----------------------------
DROP TABLE "WJTEST"."CHONGJIPACK";
CREATE TABLE "WJTEST"."CHONGJIPACK" (
  "ID" NUMBER(20) ,
  "PACKTYPE" NUMBER ,
  "PACKGRADETYPE" NUMBER ,
  "PACKGRADE" VARCHAR2(2000 BYTE) ,
  "PACKGOODS" VARCHAR2(2000 BYTE) ,
  "GETNUMBER" NUMBER(20) ,
  "DATETIME" VARCHAR2(20 BYTE) ,
  "CANPAYMONEY" NUMBER ,
  "HUITIME" VARCHAR2(20 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."CHONGJIPACK"."ID" IS '礼包 id';
COMMENT ON COLUMN "WJTEST"."CHONGJIPACK"."PACKTYPE" IS '礼包类型';
COMMENT ON COLUMN "WJTEST"."CHONGJIPACK"."PACKGRADETYPE" IS '礼包级别';
COMMENT ON COLUMN "WJTEST"."CHONGJIPACK"."PACKGRADE" IS '领取等级';
COMMENT ON COLUMN "WJTEST"."CHONGJIPACK"."PACKGOODS" IS '领取的物品';
COMMENT ON COLUMN "WJTEST"."CHONGJIPACK"."GETNUMBER" IS '当前礼包领取次数';
COMMENT ON COLUMN "WJTEST"."CHONGJIPACK"."DATETIME" IS '异动时间';
COMMENT ON COLUMN "WJTEST"."CHONGJIPACK"."CANPAYMONEY" IS '获取需要支付的金额';

-- ----------------------------
-- Table structure for COLLECTION
-- ----------------------------
DROP TABLE "WJTEST"."COLLECTION";
CREATE TABLE "WJTEST"."COLLECTION" (
  "COLID" NUMBER(32) ,
  "SALEID" NUMBER(32) ,
  "ROLEID" NUMBER(32) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."COLLECTION"."COLID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."COLLECTION"."SALEID" IS '商品ID';
COMMENT ON COLUMN "WJTEST"."COLLECTION"."ROLEID" IS '角色ID';

-- ----------------------------
-- Table structure for CONFIGURE
-- ----------------------------
DROP TABLE "WJTEST"."CONFIGURE";
CREATE TABLE "WJTEST"."CONFIGURE" (
  "fsdnum" VARCHAR2(1000 CHAR) DEFAULT 3                         ,
  "cjlzgnum" VARCHAR2(1000 CHAR) DEFAULT 2                         ,
  "con_id" VARCHAR2(1000 CHAR) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."CONFIGURE"."fsdnum" IS '飞升丹';
COMMENT ON COLUMN "WJTEST"."CONFIGURE"."cjlzgnum" IS '超级龙之骨';
COMMENT ON TABLE "WJTEST"."CONFIGURE" IS '配置';

-- ----------------------------
-- Records of CONFIGURE
-- ----------------------------
INSERT INTO "WJTEST"."CONFIGURE" VALUES ('3', '2', '1');

-- ----------------------------
-- Table structure for EXPENSESRECEIPTS
-- ----------------------------
DROP TABLE "WJTEST"."EXPENSESRECEIPTS";
CREATE TABLE "WJTEST"."EXPENSESRECEIPTS" (
  "ERID" NUMBER(30) ,
  "PLAYERACC" VARCHAR2(50 BYTE) ,
  "RECHARGE" NUMBER(20,2) ,
  "PLAYERPAY" NUMBER(30) ,
  "YUANBAO" NUMBER(38) ,
  "PAYTIME" VARCHAR2(200 BYTE) ,
  "SID" NUMBER ,
  "TYPE" NUMBER ,
  "ROLEID" NUMBER ,
  "RETURNTYPE" NUMBER ,
  "APPID" NUMBER ,
  "MANAGERID" NUMBER ,
  "GOODSID" NUMBER ,
  "BUYROLE" NUMBER ,
  "SELLROLE" NUMBER ,
  "BUYUSERID" NUMBER ,
  "BUYROLEBALANCE" VARCHAR2(20 BYTE) ,
  "PAYOFPROFITS" FLOAT(126) ,
  "GONGSHISIGN" VARCHAR2(20 BYTE) ,
  "GSPAYOFPROFITS" FLOAT(126) ,
  "BUYROLENAME" VARCHAR2(20 BYTE) ,
  "SELLROLENAME" VARCHAR2(20 BYTE) ,
  "BUYUSERIDNAME" VARCHAR2(20 BYTE) ,
  "GOODSSOMETHING" VARCHAR2(1000 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."ERID" IS '收支ID(订单号)';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."PLAYERACC" IS '玩家账号';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."RECHARGE" IS '充值金额';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."PLAYERPAY" IS '1表示微信2支付宝';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."YUANBAO" IS '订单号';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."PAYTIME" IS '充值时间';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."SID" IS '服务区ID';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."TYPE" IS '//支付类型 1仙玉充值 2周月卡充值 3小资冲级礼包充值 4土豪冲级礼包字段 5折扣卡
';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."ROLEID" IS '角色id';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."RETURNTYPE" IS '支付的状态，默认0表示回调失败';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."APPID" IS '收款的商户ID';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."MANAGERID" IS '管理员Id,负责管理的';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."GOODSID" IS '商品id';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."BUYROLE" IS '买家角色id';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."SELLROLE" IS '卖家角色id';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."BUYUSERID" IS ' 买家账号id';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."BUYROLEBALANCE" IS '买家余额';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."PAYOFPROFITS" IS '支付利润';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."GONGSHISIGN" IS '公示期标志(1、公示期 2、正常)';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."GSPAYOFPROFITS" IS '公示期利润';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."BUYROLENAME" IS '买家角色名';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."SELLROLENAME" IS '卖家角色名';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."BUYUSERIDNAME" IS '买家账号';
COMMENT ON COLUMN "WJTEST"."EXPENSESRECEIPTS"."GOODSSOMETHING" IS '商品信息';
COMMENT ON TABLE "WJTEST"."EXPENSESRECEIPTS" IS '收支表';

-- ----------------------------
-- Table structure for FRIEND
-- ----------------------------
DROP TABLE "WJTEST"."FRIEND";
CREATE TABLE "WJTEST"."FRIEND" (
  "FID" NUMBER(32) ,
  "ROLEID" NUMBER(32) ,
  "FRIENDID" NUMBER(32) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."FRIEND"."FID" IS '主键ID';
COMMENT ON COLUMN "WJTEST"."FRIEND"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."FRIEND"."FRIENDID" IS '好友ID';

-- ----------------------------
-- Table structure for GAME_RACE
-- ----------------------------
DROP TABLE "WJTEST"."GAME_RACE";
CREATE TABLE "WJTEST"."GAME_RACE" (
  "RACE_ID" NUMBER(32) ,
  "RACE_NAME" VARCHAR2(20 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of GAME_RACE
-- ----------------------------
INSERT INTO "WJTEST"."GAME_RACE" VALUES ('10005', '龙族');
INSERT INTO "WJTEST"."GAME_RACE" VALUES ('10001', '人族');
INSERT INTO "WJTEST"."GAME_RACE" VALUES ('10002', '魔族');
INSERT INTO "WJTEST"."GAME_RACE" VALUES ('10003', '仙族');
INSERT INTO "WJTEST"."GAME_RACE" VALUES ('10004', '鬼族');

-- ----------------------------
-- Table structure for GANG
-- ----------------------------
DROP TABLE "WJTEST"."GANG";
CREATE TABLE "WJTEST"."GANG" (
  "GANGID" NUMBER(32) ,
  "GANGNAME" VARCHAR2(100 BYTE) ,
  "GANGNUMBER" NUMBER(32) ,
  "PKVALUE" NUMBER(32) ,
  "BUILDER" NUMBER(32) ,
  "PROPERTY" NUMBER(32) ,
  "GANGGRADE" NUMBER(32) ,
  "FOUNDER" VARCHAR2(100 BYTE) ,
  "GANGBELONG" VARCHAR2(100 BYTE) ,
  "INTRODUCTION" VARCHAR2(1000 BYTE) ,
  "GANGTXT" VARCHAR2(1000 BYTE) ,
  "QID" VARCHAR2(20 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GANG"."GANGID" IS '帮派ID';
COMMENT ON COLUMN "WJTEST"."GANG"."GANGNAME" IS '帮派名称';
COMMENT ON COLUMN "WJTEST"."GANG"."GANGNUMBER" IS '成员数量';
COMMENT ON COLUMN "WJTEST"."GANG"."PKVALUE" IS '战绩值';
COMMENT ON COLUMN "WJTEST"."GANG"."BUILDER" IS '建设值';
COMMENT ON COLUMN "WJTEST"."GANG"."PROPERTY" IS '财产值';
COMMENT ON COLUMN "WJTEST"."GANG"."GANGGRADE" IS '等级';
COMMENT ON COLUMN "WJTEST"."GANG"."FOUNDER" IS '创始人';
COMMENT ON COLUMN "WJTEST"."GANG"."GANGBELONG" IS '帮主';
COMMENT ON COLUMN "WJTEST"."GANG"."INTRODUCTION" IS '帮派宗旨';
COMMENT ON COLUMN "WJTEST"."GANG"."QID" IS '区域id';

-- ----------------------------
-- Table structure for GANGAPPLY
-- ----------------------------
DROP TABLE "WJTEST"."GANGAPPLY";
CREATE TABLE "WJTEST"."GANGAPPLY" (
  "GAID" NUMBER(32) ,
  "GANGID" NUMBER(32) ,
  "ROLEID" NUMBER(32) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GANGAPPLY"."GAID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."GANGAPPLY"."GANGID" IS '帮派ID';
COMMENT ON COLUMN "WJTEST"."GANGAPPLY"."ROLEID" IS '角色ID';

-- ----------------------------
-- Table structure for GANGBATTLE
-- ----------------------------
DROP TABLE "WJTEST"."GANGBATTLE";
CREATE TABLE "WJTEST"."GANGBATTLE" (
  "WEEK" NUMBER DEFAULT 0                         ,
  "VALUE" VARCHAR2(3000 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GANGBATTLE"."WEEK" IS '周';

-- ----------------------------
-- Table structure for GOODSBUYRECORD
-- ----------------------------
DROP TABLE "WJTEST"."GOODSBUYRECORD";
CREATE TABLE "WJTEST"."GOODSBUYRECORD" (
  "BID" NUMBER ,
  "GID" NUMBER ,
  "PRICE" NUMBER ,
  "BUYTYPE" NUMBER ,
  "GOODNUMBER" NUMBER ,
  "NUMBERMONEY" NUMBER ,
  "RECORDTIME" VARCHAR2(100 BYTE) ,
  "USERID" NUMBER ,
  "ROLEID" NUMBER ,
  "SID" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."BID" IS '表id';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."GID" IS '销售的物品id';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."PRICE" IS '销售的单价';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."BUYTYPE" IS '购买类型';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."GOODNUMBER" IS '物品数量';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."NUMBERMONEY" IS '总消耗';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."RECORDTIME" IS '记录时间';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."USERID" IS '用户id';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."ROLEID" IS '角色id';
COMMENT ON COLUMN "WJTEST"."GOODSBUYRECORD"."SID" IS '区域id';

-- ----------------------------
-- Records of GOODSBUYRECORD
-- ----------------------------
INSERT INTO "WJTEST"."GOODSBUYRECORD" VALUES ('9951', '32302', '1', '1', '4', '4', '2025-01-04 13:04:46', '710001', '1000300', '0');
INSERT INTO "WJTEST"."GOODSBUYRECORD" VALUES ('9952', '32303', '1', '1', '1', '1', '2025-01-04 13:05:08', '710001', '1000300', '0');

-- ----------------------------
-- Table structure for GOODSEXCHANGE
-- ----------------------------
DROP TABLE "WJTEST"."GOODSEXCHANGE";
CREATE TABLE "WJTEST"."GOODSEXCHANGE" (
  "GOODSGUID" VARCHAR2(500 BYTE) ,
  "FLAG" NUMBER(8) DEFAULT 0                         ,
  "GOODSID" VARCHAR2(100 BYTE) DEFAULT 80046                         ,
  "OUTTIME" TIMESTAMP(6) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GOODSEXCHANGE"."GOODSGUID" IS '兑换码';
COMMENT ON COLUMN "WJTEST"."GOODSEXCHANGE"."FLAG" IS '标志 （1兑换，0表示没有兑换）';
COMMENT ON COLUMN "WJTEST"."GOODSEXCHANGE"."GOODSID" IS '物品(推广礼盒)';
COMMENT ON COLUMN "WJTEST"."GOODSEXCHANGE"."OUTTIME" IS '兑换时间 ';

-- ----------------------------
-- Table structure for GOODSRECORD
-- ----------------------------
DROP TABLE "WJTEST"."GOODSRECORD";
CREATE TABLE "WJTEST"."GOODSRECORD" (
  "GRID" NUMBER ,
  "RECORDTYPE" NUMBER ,
  "ROLEID" NUMBER ,
  "OTHERROLE" NUMBER ,
  "GOODS" VARCHAR2(200 BYTE) ,
  "RECORDTIME" DATE ,
  "GOODSNUM" NUMBER ,
  "ROLENAME" VARCHAR2(38 BYTE) ,
  "OTHERNAME" VARCHAR2(38 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."GRID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."RECORDTYPE" IS '记录类型（0：商店或商城购买、1:摆摊购买、2、给与、3、礼包获得、4、其他获得、5:交易,6、合成消耗,7、合成符石，8、合成修改，9、使用,10:典当，11：取回典当,12:炼妖消耗';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."ROLEID" IS ' 角色ID';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."OTHERROLE" IS '对方角色id';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."GOODS" IS '操作物品';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."RECORDTIME" IS '记录时间';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."GOODSNUM" IS '物品数量';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."ROLENAME" IS '角色名';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD"."OTHERNAME" IS '对方角色名';

-- ----------------------------
-- Records of GOODSRECORD
-- ----------------------------
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('45', '2', '1000212', '1000214', '81108,紫烟石', TO_DATE('2024-12-10 16:15:00', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('46', '2', '1000212', '1000214', '81108,落星石', TO_DATE('2024-12-10 16:15:01', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('47', '2', '1000212', '1000214', '81108,紫烟石', TO_DATE('2024-12-10 16:15:02', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('48', '2', '1000212', '1000214', '81108,孔雀石', TO_DATE('2024-12-10 16:15:03', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('49', '2', '1000212', '1000214', '81108,寒山石', TO_DATE('2024-12-10 16:15:04', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('50', '2', '1000212', '1000214', '81108,琉璃石', TO_DATE('2024-12-10 16:15:06', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('51', '2', '1000212', '1000214', '81108,琉璃石', TO_DATE('2024-12-10 16:15:08', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('52', '2', '1000212', '1000214', '81108,芙蓉石', TO_DATE('2024-12-10 16:15:10', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('53', '2', '1000212', '1000214', '81108,紫烟石', TO_DATE('2024-12-10 16:15:11', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('54', '2', '1000212', '1000214', '81108,琉璃石', TO_DATE('2024-12-10 16:15:13', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('55', '2', '1000212', '1000214', '32368,落霞', TO_DATE('2024-12-10 17:46:46', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('56', '2', '1000212', '1000214', '32368,落霞', TO_DATE('2024-12-10 17:46:47', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('57', '2', '1000212', '1000214', '32368,落霞', TO_DATE('2024-12-10 17:46:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('58', '2', '1000212', '1000214', '32368,落霞', TO_DATE('2024-12-10 17:46:49', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('59', '2', '1000212', '1000214', '32368,落霞', TO_DATE('2024-12-10 17:46:51', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('60', '2', '1000212', '1000214', '32367,长生', TO_DATE('2024-12-10 17:46:52', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('61', '2', '1000212', '1000214', '32367,长生', TO_DATE('2024-12-10 17:46:53', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('62', '2', '1000212', '1000214', '32367,长生', TO_DATE('2024-12-10 17:46:55', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('63', '2', '1000212', '1000214', '32367,长生', TO_DATE('2024-12-10 17:46:56', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('64', '2', '1000212', '1000214', '32317,卧龙', TO_DATE('2024-12-10 17:46:59', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('65', '2', '1000212', '1000214', '32318,长生', TO_DATE('2024-12-10 17:47:01', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('66', '2', '1000212', '1000214', '32319,落霞', TO_DATE('2024-12-10 17:47:02', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('67', '2', '1000212', '1000214', '32366,卧龙', TO_DATE('2024-12-10 17:47:03', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('68', '2', '1000212', '1000214', '32366,卧龙', TO_DATE('2024-12-10 17:47:05', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('69', '2', '1000212', '1000214', '32367,长生', TO_DATE('2024-12-10 17:47:06', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('70', '2', '1000212', '1000214', '32366,卧龙', TO_DATE('2024-12-10 17:47:08', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('71', '2', '1000212', '1000214', '32366,卧龙', TO_DATE('2024-12-10 17:47:09', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('72', '2', '1000212', '1000214', '32366,卧龙', TO_DATE('2024-12-10 17:47:10', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('73', '2', '1000213', '1000226', '615,琼浆玉液', TO_DATE('2024-12-10 18:35:30', 'SYYYY-MM-DD HH24:MI:SS'), '2900', '龍逐', '鳯虞');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('74', '2', '1000226', '1000227', '615,琼浆玉液', TO_DATE('2024-12-10 18:36:12', 'SYYYY-MM-DD HH24:MI:SS'), '2849', '鳯虞', '孤尘');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('75', '2', '1000227', '1000228', '615,琼浆玉液', TO_DATE('2024-12-10 18:36:46', 'SYYYY-MM-DD HH24:MI:SS'), '2766', '孤尘', '北幕');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('76', '2', '1000228', '1000229', '615,琼浆玉液', TO_DATE('2024-12-10 18:37:24', 'SYYYY-MM-DD HH24:MI:SS'), '2690', '北幕', '南鸢');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('130', '2', '1000214', '1000212', '20026,玄铁晶石', TO_DATE('2024-12-11 12:01:47', 'SYYYY-MM-DD HH24:MI:SS'), '11111', '莫欺中年穷', '莫欺少年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('131', '2', '1000214', '1000212', '206,九彩云龙珠', TO_DATE('2024-12-11 12:01:51', 'SYYYY-MM-DD HH24:MI:SS'), '33333', '莫欺中年穷', '莫欺少年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('132', '2', '1000214', '1000212', '205,血玲珑', TO_DATE('2024-12-11 12:01:54', 'SYYYY-MM-DD HH24:MI:SS'), '11111', '莫欺中年穷', '莫欺少年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('133', '2', '1000214', '1000212', '204,内丹精华', TO_DATE('2024-12-11 12:01:57', 'SYYYY-MM-DD HH24:MI:SS'), '11111', '莫欺中年穷', '莫欺少年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('134', '2', '1000254', '1000255', '899,五十亿银票', TO_DATE('2024-12-11 12:08:07', 'SYYYY-MM-DD HH24:MI:SS'), '1', '大聪明', '奔波儿灞');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('135', '2', '1000254', '1000256', '899,五十亿银票', TO_DATE('2024-12-11 12:08:13', 'SYYYY-MM-DD HH24:MI:SS'), '1', '大聪明', '灞波儿奔');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('136', '2', '1000254', '1000258', '899,五十亿银票', TO_DATE('2024-12-11 12:08:15', 'SYYYY-MM-DD HH24:MI:SS'), '1', '大聪明', '大宝贝');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('137', '2', '1000254', '1000257', '899,五十亿银票', TO_DATE('2024-12-11 12:08:31', 'SYYYY-MM-DD HH24:MI:SS'), '1', '大聪明', '大明白');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('138', '2', '1000212', '1000214', '9938914,卧龙', TO_DATE('2024-12-11 12:33:40', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('139', '2', '1000212', '1000214', '9938915,长生', TO_DATE('2024-12-11 12:33:41', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('140', '2', '1000212', '1000214', '9938916,落霞', TO_DATE('2024-12-11 12:33:43', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('141', '2', '1000212', '1000214', '90754,卧龙', TO_DATE('2024-12-11 12:33:44', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('142', '2', '1000212', '1000214', '90755,长生', TO_DATE('2024-12-11 12:33:45', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('143', '2', '1000212', '1000214', '90756,落霞', TO_DATE('2024-12-11 12:33:46', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('144', '2', '1000212', '1000214', '990726,卧龙', TO_DATE('2024-12-11 12:33:47', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('145', '2', '1000212', '1000214', '990727,长生', TO_DATE('2024-12-11 12:33:49', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('146', '2', '1000212', '1000214', '990728,落霞', TO_DATE('2024-12-11 12:33:51', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('147', '2', '1000212', '1000214', '990740,落霞', TO_DATE('2024-12-11 12:33:52', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('148', '2', '1000212', '1000214', '990740,落霞', TO_DATE('2024-12-11 12:33:53', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('149', '2', '1000212', '1000214', '990740,落霞', TO_DATE('2024-12-11 12:33:55', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('150', '2', '1000212', '1000214', '990740,落霞', TO_DATE('2024-12-11 12:33:56', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('151', '2', '1000212', '1000214', '990740,落霞', TO_DATE('2024-12-11 12:33:57', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('152', '2', '1000212', '1000214', '990740,落霞', TO_DATE('2024-12-11 12:33:58', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('153', '2', '1000212', '1000214', '990739,长生', TO_DATE('2024-12-11 12:33:59', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('154', '2', '1000212', '1000214', '990739,长生', TO_DATE('2024-12-11 12:34:00', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('155', '2', '1000212', '1000214', '990739,长生', TO_DATE('2024-12-11 12:34:01', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('156', '2', '1000212', '1000214', '990738,卧龙', TO_DATE('2024-12-11 12:34:04', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('157', '2', '1000212', '1000214', '990738,卧龙', TO_DATE('2024-12-11 12:34:05', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('158', '2', '1000212', '1000214', '990738,卧龙', TO_DATE('2024-12-11 12:34:06', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('159', '2', '1000212', '1000214', '990738,卧龙', TO_DATE('2024-12-11 12:34:08', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('160', '2', '1000212', '1000214', '990738,卧龙', TO_DATE('2024-12-11 12:34:09', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('161', '2', '1000212', '1000214', '990738,卧龙', TO_DATE('2024-12-11 12:34:10', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('162', '2', '1000212', '1000214', '990739,长生', TO_DATE('2024-12-11 12:34:11', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('163', '2', '1000212', '1000214', '990739,长生', TO_DATE('2024-12-11 12:34:13', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('164', '2', '1000212', '1000214', '990739,长生', TO_DATE('2024-12-11 12:34:15', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('165', '2', '1000188', '1000192', '80067,红日当空', TO_DATE('2024-12-11 12:42:12', 'SYYYY-MM-DD HH24:MI:SS'), '1', '霸道V', '流氓V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('9', '2', '1000213', '1000227', '80497,九玄仙玉', TO_DATE('2024-12-08 21:33:32', 'SYYYY-MM-DD HH24:MI:SS'), '168', '龍逐', '孤尘');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('10', '2', '1000227', '1000226', '80497,九玄仙玉', TO_DATE('2024-12-08 21:34:26', 'SYYYY-MM-DD HH24:MI:SS'), '139', '孤尘', '鳯虞');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('11', '2', '1000226', '1000228', '80497,九玄仙玉', TO_DATE('2024-12-08 21:35:13', 'SYYYY-MM-DD HH24:MI:SS'), '106', '鳯虞', '北幕');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('12', '2', '1000228', '1000229', '80497,九玄仙玉', TO_DATE('2024-12-08 21:42:54', 'SYYYY-MM-DD HH24:MI:SS'), '106', '北幕', '南鸢');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('13', '5', '1000215', '1000216', '999974,MAXplus+（野怪或者天书宝宝）封印卡', TO_DATE('2024-12-08 22:28:23', 'SYYYY-MM-DD HH24:MI:SS'), '10', '明月清风', '明月清疯');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('14', '2', '1000222', NULL, '309,补天神石', TO_DATE('2024-12-09 09:42:40', 'SYYYY-MM-DD HH24:MI:SS'), '1', '壹壹壹', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('15', '2', '1000222', NULL, '80033,超级坐骑经验丹', TO_DATE('2024-12-09 09:42:40', 'SYYYY-MM-DD HH24:MI:SS'), '10', '壹壹壹', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('16', '2', '1000222', NULL, '80035,超级坐骑熟练丹', TO_DATE('2024-12-09 09:42:40', 'SYYYY-MM-DD HH24:MI:SS'), '10', '壹壹壹', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('17', '2', '1000222', NULL, '80125,配饰精华', TO_DATE('2024-12-09 09:42:40', 'SYYYY-MM-DD HH24:MI:SS'), '10032', '壹壹壹', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('18', '2', '1000222', NULL, '80168,鎏金宝鉴碎片', TO_DATE('2024-12-09 09:42:40', 'SYYYY-MM-DD HH24:MI:SS'), '3', '壹壹壹', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('19', '2', '1000223', NULL, '80033,超级坐骑经验丹', TO_DATE('2024-12-09 10:51:47', 'SYYYY-MM-DD HH24:MI:SS'), '10', '贰贰贰', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('20', '2', '1000223', NULL, '80035,超级坐骑熟练丹', TO_DATE('2024-12-09 10:51:47', 'SYYYY-MM-DD HH24:MI:SS'), '10', '贰贰贰', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('21', '2', '1000223', NULL, '309,补天神石', TO_DATE('2024-12-09 10:51:47', 'SYYYY-MM-DD HH24:MI:SS'), '2', '贰贰贰', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('22', '2', '1000223', NULL, '80125,配饰精华', TO_DATE('2024-12-09 10:51:49', 'SYYYY-MM-DD HH24:MI:SS'), '8840', '贰贰贰', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('23', '2', '1000223', NULL, '80168,鎏金宝鉴碎片', TO_DATE('2024-12-09 10:51:49', 'SYYYY-MM-DD HH24:MI:SS'), '9', '贰贰贰', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('24', '5', '1000216', '1000215', '200116,年', TO_DATE('2024-12-09 14:20:22', 'SYYYY-MM-DD HH24:MI:SS'), '1', '明月清疯', '明月清风');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('25', '5', '1000223', '1000224', '7008,回梦', TO_DATE('2024-12-09 16:54:35', 'SYYYY-MM-DD HH24:MI:SS'), '1', '贰贰贰', '叁叁叁');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('26', '2', '1000214', '1000212', '81188,地魁星', TO_DATE('2024-12-09 17:32:32', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺中年穷', '莫欺少年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('166', '2', '1000189', '1000191', '80067,红日当空', TO_DATE('2024-12-11 12:56:44', 'SYYYY-MM-DD HH24:MI:SS'), '1', '混混V', '痞子V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('167', '2', '1000212', '1000214', '990728,落霞', TO_DATE('2024-12-11 13:27:32', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('168', '2', '1000212', '1000214', '990728,落霞', TO_DATE('2024-12-11 13:27:33', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('169', '2', '1000212', '1000214', '990728,落霞', TO_DATE('2024-12-11 13:27:35', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('170', '2', '1000212', '1000214', '990727,长生', TO_DATE('2024-12-11 13:27:36', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('171', '2', '1000212', '1000214', '990727,长生', TO_DATE('2024-12-11 13:27:37', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('172', '2', '1000212', '1000214', '990727,长生', TO_DATE('2024-12-11 13:27:39', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('173', '2', '1000212', '1000214', '990726,卧龙', TO_DATE('2024-12-11 13:27:41', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('174', '2', '1000212', '1000214', '990726,卧龙', TO_DATE('2024-12-11 13:27:43', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('175', '2', '1000212', '1000214', '990726,卧龙', TO_DATE('2024-12-11 13:27:44', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('176', '2', '1000212', '1000214', '30028,藕丝步云履', TO_DATE('2024-12-11 13:32:30', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('177', '2', '1000212', '1000214', '7004,化魄', TO_DATE('2024-12-11 13:32:32', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('178', '2', '1000212', '1000214', '7007,红尘', TO_DATE('2024-12-11 13:32:33', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('179', '2', '1000212', '1000214', '7001,舞雪', TO_DATE('2024-12-11 13:32:34', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('180', '2', '1000212', '1000214', '80067,红日当空', TO_DATE('2024-12-11 13:32:35', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('181', '2', '1000212', '1000214', '30036,凤翅瑶仙簪', TO_DATE('2024-12-11 13:32:37', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('1', '2', '1000105', NULL, '901,神兽碎片', TO_DATE('2024-12-24 19:03:58', 'SYYYY-MM-DD HH24:MI:SS'), '30', '老虎', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('2', '2', '1000105', NULL, '80033,超级坐骑经验丹', TO_DATE('2024-12-24 19:03:58', 'SYYYY-MM-DD HH24:MI:SS'), '15', '老虎', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('3', '2', '1000105', NULL, '80035,超级坐骑熟练丹', TO_DATE('2024-12-24 19:03:58', 'SYYYY-MM-DD HH24:MI:SS'), '15', '老虎', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('4', '2', '1000105', NULL, '80033,超级坐骑经验丹', TO_DATE('2024-12-24 19:04:22', 'SYYYY-MM-DD HH24:MI:SS'), '15', '老虎', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('20', '2', '1000219', '1000224', '899,五十亿银票', TO_DATE('2025-01-02 19:24:51', 'SYYYY-MM-DD HH24:MI:SS'), '2', '4444', '33');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('21', '2', '1000222', '1000189', '6525,枯骨刀', TO_DATE('2025-01-02 23:39:10', 'SYYYY-MM-DD HH24:MI:SS'), '1', '烦死了o', '周鹰漂');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('22', '2', '1000227', NULL, '309,补天神石', TO_DATE('2025-01-03 01:03:09', 'SYYYY-MM-DD HH24:MI:SS'), '1', '大道至简丶', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('23', '2', '1000228', NULL, '309,补天神石', TO_DATE('2025-01-03 02:10:26', 'SYYYY-MM-DD HH24:MI:SS'), '3', '窦沁痪', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('24', '2', '1000228', NULL, '80125,配饰精华', TO_DATE('2025-01-03 02:10:26', 'SYYYY-MM-DD HH24:MI:SS'), '16', '窦沁痪', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('25', '2', '1000228', NULL, '309,补天神石', TO_DATE('2025-01-03 02:17:05', 'SYYYY-MM-DD HH24:MI:SS'), '1', '窦沁痪', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('26', '2', '1000228', NULL, '309,补天神石', TO_DATE('2025-01-03 02:17:07', 'SYYYY-MM-DD HH24:MI:SS'), '2', '窦沁痪', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('27', '2', '1000229', NULL, '309,补天神石', TO_DATE('2025-01-03 07:23:22', 'SYYYY-MM-DD HH24:MI:SS'), '2', '金跌储', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('28', '2', '1000229', NULL, '80033,超级坐骑经验丹', TO_DATE('2025-01-03 07:23:22', 'SYYYY-MM-DD HH24:MI:SS'), '10', '金跌储', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('29', '2', '1000229', NULL, '80035,超级坐骑熟练丹', TO_DATE('2025-01-03 07:23:22', 'SYYYY-MM-DD HH24:MI:SS'), '10', '金跌储', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('30', '2', '1000229', NULL, '80125,配饰精华', TO_DATE('2025-01-03 07:23:22', 'SYYYY-MM-DD HH24:MI:SS'), '29', '金跌储', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('34', '2', '1000238', '1000235', '2233,黄琅如意', TO_DATE('2025-01-03 12:52:22', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('35', '2', '1000238', '1000235', '2233,黄琅如意', TO_DATE('2025-01-03 12:52:24', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('38', '2', '1000238', '1000235', '2237,伽蓝碎雨', TO_DATE('2025-01-03 13:08:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('39', '2', '1000238', '1000235', '2257,琅邪梦断', TO_DATE('2025-01-03 13:08:23', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('40', '2', '1000238', '1000235', '2277,流火披风', TO_DATE('2025-01-03 13:08:29', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('41', '2', '1000238', '1000235', '2227,翠玉扳指', TO_DATE('2025-01-03 13:08:31', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('42', '2', '1000238', '1000235', '2227,翠玉扳指', TO_DATE('2025-01-03 13:08:32', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('43', '2', '1000238', '1000235', '2247,祥云流带', TO_DATE('2025-01-03 13:08:50', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('44', '2', '1000240', '1000238', '32231,水苍玉佩', TO_DATE('2025-01-03 13:28:32', 'SYYYY-MM-DD HH24:MI:SS'), '1', '004', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('45', '2', '1000242', NULL, '309,补天神石', TO_DATE('2025-01-03 13:35:27', 'SYYYY-MM-DD HH24:MI:SS'), '20', '李清照', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('46', '2', '1000242', NULL, '80125,配饰精华', TO_DATE('2025-01-03 13:35:27', 'SYYYY-MM-DD HH24:MI:SS'), '103', '李清照', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('47', '2', '1000242', NULL, '310,六魂之玉', TO_DATE('2025-01-03 13:35:27', 'SYYYY-MM-DD HH24:MI:SS'), '20', '李清照', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('48', '2', '1000242', NULL, '311,无量琉璃', TO_DATE('2025-01-03 13:35:27', 'SYYYY-MM-DD HH24:MI:SS'), '20', '李清照', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('49', '2', '1000238', '1000240', '2227,翠玉扳指', TO_DATE('2025-01-03 13:54:02', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '004');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('50', '2', '1000238', '1000240', '2227,翠玉扳指', TO_DATE('2025-01-03 13:54:04', 'SYYYY-MM-DD HH24:MI:SS'), '1', '002', '004');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('51', '2', '1000235', '1000238', '220,超级乾灵聚元丹', TO_DATE('2025-01-03 14:14:18', 'SYYYY-MM-DD HH24:MI:SS'), '103', '001', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('52', '2', '1000235', '1000238', '80489,灵宝诸天印', TO_DATE('2025-01-03 14:14:20', 'SYYYY-MM-DD HH24:MI:SS'), '1654', '001', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('53', '2', '1000235', '1000238', '10079,灵宝天威印', TO_DATE('2025-01-03 14:14:55', 'SYYYY-MM-DD HH24:MI:SS'), '267', '001', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('61', '2', '1000239', '1000248', '30028,藕丝步云履', TO_DATE('2025-01-03 18:16:46', 'SYYYY-MM-DD HH24:MI:SS'), '1', '003', '005');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('62', '2', '1000240', '1000239', '7002,幻羽', TO_DATE('2025-01-03 18:18:06', 'SYYYY-MM-DD HH24:MI:SS'), '1', '004', '003');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('63', '2', '1000240', '1000239', '7000,轩辕', TO_DATE('2025-01-03 18:18:08', 'SYYYY-MM-DD HH24:MI:SS'), '1', '004', '003');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('64', '2', '1000240', '1000239', '7005,魅影', TO_DATE('2025-01-03 18:18:11', 'SYYYY-MM-DD HH24:MI:SS'), '1', '004', '003');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('65', '2', '1000240', '1000239', '7011,苍暮', TO_DATE('2025-01-03 18:18:14', 'SYYYY-MM-DD HH24:MI:SS'), '1', '004', '003');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('66', '2', '1000248', '1000239', '92361,超级悔梦石', TO_DATE('2025-01-03 18:18:34', 'SYYYY-MM-DD HH24:MI:SS'), '143', '005', '003');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('67', '2', '1000248', '1000239', '212,悔梦石', TO_DATE('2025-01-03 18:18:49', 'SYYYY-MM-DD HH24:MI:SS'), '1947', '005', '003');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('68', '2', '1000248', '1000239', '92667,仙器重铸石', TO_DATE('2025-01-03 18:20:52', 'SYYYY-MM-DD HH24:MI:SS'), '1143', '005', '003');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('75', '2', '1000285', '1000281', '60069,龙兔', TO_DATE('2025-01-04 08:28:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '美', '文');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('76', '2', '1000281', '1000280', '60069,龙兔', TO_DATE('2025-01-04 08:31:07', 'SYYYY-MM-DD HH24:MI:SS'), '1', '文', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('77', '2', '1000287', '1000280', '892,两千万银票', TO_DATE('2025-01-04 08:32:07', 'SYYYY-MM-DD HH24:MI:SS'), '1', '丽', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('78', '2', '1000287', '1000280', '60066,北冥龙君', TO_DATE('2025-01-04 08:32:10', 'SYYYY-MM-DD HH24:MI:SS'), '1', '丽', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('79', '2', '1000292', '1000280', '60068,妙音鸾女', TO_DATE('2025-01-04 08:41:32', 'SYYYY-MM-DD HH24:MI:SS'), '1', '酆太讶', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('80', '2', '1000292', '1000280', '892,两千万银票', TO_DATE('2025-01-04 08:41:34', 'SYYYY-MM-DD HH24:MI:SS'), '1', '酆太讶', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('81', '2', '1000291', '1000280', '60069,龙兔', TO_DATE('2025-01-04 08:41:55', 'SYYYY-MM-DD HH24:MI:SS'), '1', '奚某斧', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('82', '2', '1000290', '1000280', '60069,龙兔', TO_DATE('2025-01-04 08:42:43', 'SYYYY-MM-DD HH24:MI:SS'), '1', '花食释', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('83', '2', '1000290', '1000280', '892,两千万银票', TO_DATE('2025-01-04 08:42:52', 'SYYYY-MM-DD HH24:MI:SS'), '1', '花食释', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('84', '2', '1000289', '1000280', '892,两千万银票', TO_DATE('2025-01-04 08:43:10', 'SYYYY-MM-DD HH24:MI:SS'), '1', '苏淳寸', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('85', '2', '1000289', '1000280', '60067,水月镜花', TO_DATE('2025-01-04 08:43:13', 'SYYYY-MM-DD HH24:MI:SS'), '1', '苏淳寸', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('86', '2', '1000288', '1000280', '60066,北冥龙君', TO_DATE('2025-01-04 08:43:34', 'SYYYY-MM-DD HH24:MI:SS'), '1', '柏殴嚼', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('87', '2', '1000288', '1000280', '892,两千万银票', TO_DATE('2025-01-04 08:43:37', 'SYYYY-MM-DD HH24:MI:SS'), '1', '柏殴嚼', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('88', '2', '1000297', '1000280', '60062,龙马', TO_DATE('2025-01-04 08:55:29', 'SYYYY-MM-DD HH24:MI:SS'), '1', '滕些开', '陈蓦');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('89', '2', '1000279', NULL, '80033,超级坐骑经验丹', TO_DATE('2025-01-04 10:46:08', 'SYYYY-MM-DD HH24:MI:SS'), '15', '1', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('90', '2', '1000279', NULL, '80035,超级坐骑熟练丹', TO_DATE('2025-01-04 10:46:08', 'SYYYY-MM-DD HH24:MI:SS'), '45', '1', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('91', '2', '1000279', NULL, '309,补天神石', TO_DATE('2025-01-04 10:46:13', 'SYYYY-MM-DD HH24:MI:SS'), '20', '1', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('92', '2', '1000279', NULL, '310,六魂之玉', TO_DATE('2025-01-04 10:46:13', 'SYYYY-MM-DD HH24:MI:SS'), '20', '1', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('93', '2', '1000279', NULL, '311,无量琉璃', TO_DATE('2025-01-04 10:46:13', 'SYYYY-MM-DD HH24:MI:SS'), '35', '1', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('8', '5', '1000193', '1000199', '80679,鎏金宝鉴-心猿', TO_DATE('2024-12-28 23:24:27', 'SYYYY-MM-DD HH24:MI:SS'), '1', '沈画分', '金袱再');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('9', '2', '1000193', NULL, '309,补天神石', TO_DATE('2024-12-28 23:51:41', 'SYYYY-MM-DD HH24:MI:SS'), '6', '沈画分', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('10', '2', '1000193', NULL, '310,六魂之玉', TO_DATE('2024-12-28 23:51:41', 'SYYYY-MM-DD HH24:MI:SS'), '105', '沈画分', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('11', '2', '1000193', NULL, '311,无量琉璃', TO_DATE('2024-12-28 23:51:41', 'SYYYY-MM-DD HH24:MI:SS'), '120', '沈画分', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('12', '2', '1000193', NULL, '901,神兽碎片', TO_DATE('2024-12-28 23:51:41', 'SYYYY-MM-DD HH24:MI:SS'), '165', '沈画分', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('13', '2', '1000193', NULL, '310,六魂之玉', TO_DATE('2024-12-29 21:27:16', 'SYYYY-MM-DD HH24:MI:SS'), '96', '沈画分', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('14', '2', '1000193', NULL, '311,无量琉璃', TO_DATE('2024-12-29 21:27:16', 'SYYYY-MM-DD HH24:MI:SS'), '47', '沈画分', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('15', '2', '1000193', NULL, '901,神兽碎片', TO_DATE('2024-12-29 21:27:16', 'SYYYY-MM-DD HH24:MI:SS'), '136', '沈画分', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('17', '2', '1000213', NULL, '309,补天神石', TO_DATE('2025-01-02 15:15:33', 'SYYYY-MM-DD HH24:MI:SS'), '5', '墨涙丶', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('18', '2', '1000213', NULL, '80125,配饰精华', TO_DATE('2025-01-02 15:15:33', 'SYYYY-MM-DD HH24:MI:SS'), '2952', '墨涙丶', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('19', '2', '1000218', '1000219', '899,五十亿银票', TO_DATE('2025-01-02 15:59:14', 'SYYYY-MM-DD HH24:MI:SS'), '111', '3333', '4444');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('31', '2', '1000230', NULL, '383,云织锦', TO_DATE('2025-01-03 09:49:30', 'SYYYY-MM-DD HH24:MI:SS'), '2955', '李白', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('32', '2', '1000230', NULL, '309,补天神石', TO_DATE('2025-01-03 09:49:30', 'SYYYY-MM-DD HH24:MI:SS'), '16', '李白', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('33', '2', '1000230', NULL, '311,无量琉璃', TO_DATE('2025-01-03 09:49:30', 'SYYYY-MM-DD HH24:MI:SS'), '957', '李白', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('36', '2', '1000235', '1000238', '2233,黄琅如意', TO_DATE('2025-01-03 12:56:36', 'SYYYY-MM-DD HH24:MI:SS'), '1', '001', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('37', '2', '1000235', '1000238', '2233,黄琅如意', TO_DATE('2025-01-03 12:56:38', 'SYYYY-MM-DD HH24:MI:SS'), '1', '001', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('69', '2', '1000105', '1000255', '30041,斩妖剑', TO_DATE('2025-01-03 20:11:50', 'SYYYY-MM-DD HH24:MI:SS'), '1', '老虎', '测试');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('70', '2', '1000105', '1000255', '30037,枯骨刀', TO_DATE('2025-01-03 20:11:56', 'SYYYY-MM-DD HH24:MI:SS'), '1', '老虎', '测试');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('71', '2', '1000105', '1000255', '30032,毁天灭地', TO_DATE('2025-01-03 20:11:58', 'SYYYY-MM-DD HH24:MI:SS'), '1', '老虎', '测试');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('72', '2', '1000105', '1000255', '6538,震天戟', TO_DATE('2025-01-03 20:12:00', 'SYYYY-MM-DD HH24:MI:SS'), '1', '老虎', '测试');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('73', '2', '1000105', '1000255', '32328,震天戟', TO_DATE('2025-01-03 20:12:03', 'SYYYY-MM-DD HH24:MI:SS'), '1', '老虎', '测试');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('74', '2', '1000105', '1000255', '32439,风熊礼包', TO_DATE('2025-01-03 20:24:27', 'SYYYY-MM-DD HH24:MI:SS'), '1', '老虎', '测试');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('5', '99999', '1000001', NULL, '面值1000', TO_DATE('2024-12-27 15:06:17', 'SYYYY-MM-DD HH24:MI:SS'), '1', '何露驻', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('6', '99999', '1000189', NULL, '面值1000', TO_DATE('2024-12-27 20:49:29', 'SYYYY-MM-DD HH24:MI:SS'), '1', '周鹰漂', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('7', '99999', '1000197', NULL, '面值1000', TO_DATE('2024-12-28 22:01:10', 'SYYYY-MM-DD HH24:MI:SS'), '1', '桃子姐姐', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('16', '2', '1000001', '1000048', '7050,一阶仙器礼盒', TO_DATE('2024-12-30 18:37:04', 'SYYYY-MM-DD HH24:MI:SS'), '1', '何露驻', '灬吴镇宇');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('54', '2', '1000239', '1000235', '81188,地魁星', TO_DATE('2025-01-03 16:58:21', 'SYYYY-MM-DD HH24:MI:SS'), '1', '003', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('55', '2', '1000239', '1000235', '81188,地魁星', TO_DATE('2025-01-03 16:58:23', 'SYYYY-MM-DD HH24:MI:SS'), '1', '003', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('56', '2', '1000239', '1000240', '81188,地魁星', TO_DATE('2025-01-03 16:59:05', 'SYYYY-MM-DD HH24:MI:SS'), '1', '003', '004');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('57', '5', '1000235', '1000238', '200106,超级蝙蝠', TO_DATE('2025-01-03 17:32:56', 'SYYYY-MM-DD HH24:MI:SS'), '1', '001', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('58', '2', '1000248', '1000235', '7009,傲天', TO_DATE('2025-01-03 17:54:28', 'SYYYY-MM-DD HH24:MI:SS'), '1', '005', '001');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('59', '2', '1000239', '1000238', '81188,地魁星', TO_DATE('2025-01-03 18:07:47', 'SYYYY-MM-DD HH24:MI:SS'), '1', '003', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('60', '2', '1000239', '1000238', '81173,易象符', TO_DATE('2025-01-03 18:08:09', 'SYYYY-MM-DD HH24:MI:SS'), '1089', '003', '002');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('77', '2', '1000191', '1000188', '80067,红日当空', TO_DATE('2024-12-10 21:40:41', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('78', '2', '1000191', '1000189', '80067,红日当空', TO_DATE('2024-12-10 21:43:34', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '混混V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('79', '2', '1000191', '1000189', '80067,红日当空', TO_DATE('2024-12-10 21:56:55', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '混混V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('80', '2', '1000191', '1000188', '80067,红日当空', TO_DATE('2024-12-10 22:01:10', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('81', '2', '1000189', '1000191', '80067,红日当空', TO_DATE('2024-12-10 22:02:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '混混V', '痞子V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('82', '2', '1000191', '1000189', '80067,红日当空', TO_DATE('2024-12-10 22:09:29', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '混混V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('83', '2', '1000191', '1000188', '80067,红日当空', TO_DATE('2024-12-10 22:11:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('84', '2', '1000191', '1000192', '80067,红日当空', TO_DATE('2024-12-10 22:14:07', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '流氓V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('85', '2', '1000191', '1000189', '80067,红日当空', TO_DATE('2024-12-10 22:27:27', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '混混V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('86', '2', '1000228', '1000227', '30029,步定乾坤履', TO_DATE('2024-12-10 22:32:34', 'SYYYY-MM-DD HH24:MI:SS'), '1', '北幕', '孤尘');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('87', '2', '1000227', '1000228', '30029,步定乾坤履', TO_DATE('2024-12-10 22:33:05', 'SYYYY-MM-DD HH24:MI:SS'), '1', '孤尘', '北幕');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('88', '2', '1000228', '1000227', '30029,步定乾坤履', TO_DATE('2024-12-10 22:33:40', 'SYYYY-MM-DD HH24:MI:SS'), '1', '北幕', '孤尘');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('89', '2', '1000243', '1000247', '7000,轩辕', TO_DATE('2024-12-10 22:47:31', 'SYYYY-MM-DD HH24:MI:SS'), '1', '小姐别乱跑', '炼化专用');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('90', '2', '1000243', '1000247', '7002,幻羽', TO_DATE('2024-12-10 22:47:35', 'SYYYY-MM-DD HH24:MI:SS'), '1', '小姐别乱跑', '炼化专用');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('91', '2', '1000247', '1000243', '7002,幻羽', TO_DATE('2024-12-10 22:58:15', 'SYYYY-MM-DD HH24:MI:SS'), '1', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('92', '2', '1000247', '1000243', '7000,轩辕', TO_DATE('2024-12-10 22:58:16', 'SYYYY-MM-DD HH24:MI:SS'), '1', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('93', '2', '1000243', '1000247', '7011,苍暮', TO_DATE('2024-12-10 23:01:02', 'SYYYY-MM-DD HH24:MI:SS'), '1', '小姐别乱跑', '炼化专用');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('94', '2', '1000247', '1000243', '7011,苍暮', TO_DATE('2024-12-10 23:02:36', 'SYYYY-MM-DD HH24:MI:SS'), '1', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('95', '2', '1000243', '1000247', '7011,苍暮', TO_DATE('2024-12-10 23:03:45', 'SYYYY-MM-DD HH24:MI:SS'), '1', '小姐别乱跑', '炼化专用');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('96', '2', '1000213', '1000249', '80682,射·莲生', TO_DATE('2024-12-10 23:19:01', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '渔歌丶');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('97', '2', '1000213', '1000249', '80683,乐·大吕', TO_DATE('2024-12-10 23:19:03', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '渔歌丶');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('98', '5', '1000213', '1000249', '30216,幻方', TO_DATE('2024-12-10 23:20:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '渔歌丶');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('99', '5', '1000213', '1000249', '30112,白虎符石', TO_DATE('2024-12-10 23:20:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '渔歌丶');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('100', '5', '1000213', '1000249', '30106,朱雀符石', TO_DATE('2024-12-10 23:20:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '渔歌丶');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('101', '5', '1000213', '1000249', '32350,青龙符石', TO_DATE('2024-12-10 23:20:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '渔歌丶');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('102', '5', '1000213', '1000249', '30113,玄武符石', TO_DATE('2024-12-10 23:20:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '渔歌丶');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('103', '5', '1000213', '1000249', '30029,步定乾坤履', TO_DATE('2024-12-10 23:20:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '渔歌丶');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('104', '2', '1000213', '1000188', '32352,白虎符石', TO_DATE('2024-12-10 23:23:31', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('105', '5', '1000213', '1000188', '32352,白虎符石', TO_DATE('2024-12-10 23:23:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('106', '5', '1000213', '1000188', '32350,青龙符石', TO_DATE('2024-12-10 23:23:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('107', '5', '1000213', '1000188', '32351,朱雀符石', TO_DATE('2024-12-10 23:23:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('108', '5', '1000213', '1000188', '32353,玄武符石', TO_DATE('2024-12-10 23:23:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('109', '5', '1000213', '1000188', '30110,青龙符石', TO_DATE('2024-12-10 23:23:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('110', '5', '1000213', '1000188', '30111,朱雀符石', TO_DATE('2024-12-10 23:23:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('111', '5', '1000213', '1000188', '30107,白虎符石', TO_DATE('2024-12-10 23:23:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('112', '5', '1000213', '1000188', '32351,朱雀符石', TO_DATE('2024-12-10 23:23:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '龍逐', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('113', '2', '1000189', '1000188', '80067,红日当空', TO_DATE('2024-12-11 00:06:23', 'SYYYY-MM-DD HH24:MI:SS'), '1', '混混V', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('114', '2', '1000191', '1000204', '3316,汉广', TO_DATE('2024-12-11 00:14:36', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '天涯的爱');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('115', '2', '1000247', '1000243', '7011,苍暮', TO_DATE('2024-12-11 00:24:17', 'SYYYY-MM-DD HH24:MI:SS'), '1', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('116', '2', '1000247', '1000243', '81239,仙器精华', TO_DATE('2024-12-11 00:25:05', 'SYYYY-MM-DD HH24:MI:SS'), '100000', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('117', '2', '1000191', '1000204', '3116,古川', TO_DATE('2024-12-11 00:26:24', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '天涯的爱');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('118', '2', '1000204', '1000191', '8211,霁月古镜', TO_DATE('2024-12-11 00:30:59', 'SYYYY-MM-DD HH24:MI:SS'), '1', '天涯的爱', '痞子V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('119', '2', '1000204', '1000191', '8235,冰河披风', TO_DATE('2024-12-11 00:31:28', 'SYYYY-MM-DD HH24:MI:SS'), '1', '天涯的爱', '痞子V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('120', '2', '1000190', '1000204', '81188,地魁星', TO_DATE('2024-12-11 00:45:28', 'SYYYY-MM-DD HH24:MI:SS'), '1', '无赖V', '天涯的爱');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('121', '2', '1000191', '1000204', '8211,霁月古镜', TO_DATE('2024-12-11 00:49:34', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '天涯的爱');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('122', '2', '1000191', '1000204', '8235,冰河披风', TO_DATE('2024-12-11 00:49:35', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '天涯的爱');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('123', '2', '1000188', '1000204', '80067,红日当空', TO_DATE('2024-12-11 01:00:03', 'SYYYY-MM-DD HH24:MI:SS'), '1', '霸道V', '天涯的爱');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('124', '2', '1000247', '1000243', '81188,地魁星', TO_DATE('2024-12-11 01:19:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('125', '2', '1000247', '1000243', '81188,地魁星', TO_DATE('2024-12-11 01:19:21', 'SYYYY-MM-DD HH24:MI:SS'), '1', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('126', '2', '1000247', '1000243', '81174,浑天石', TO_DATE('2024-12-11 01:19:27', 'SYYYY-MM-DD HH24:MI:SS'), '16208', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('127', '2', '1000247', '1000243', '81239,仙器精华', TO_DATE('2024-12-11 01:19:30', 'SYYYY-MM-DD HH24:MI:SS'), '15176', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('128', '2', '1000247', '1000243', '80511,落魄砂', TO_DATE('2024-12-11 01:19:37', 'SYYYY-MM-DD HH24:MI:SS'), '401574', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('129', '2', '1000247', '1000243', '80497,九玄仙玉', TO_DATE('2024-12-11 01:19:40', 'SYYYY-MM-DD HH24:MI:SS'), '304674', '炼化专用', '小姐别乱跑');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('182', '2', '1000212', '1000214', '81108,紫烟石', TO_DATE('2024-12-11 14:08:45', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('183', '2', '1000212', '1000214', '81108,琉璃石', TO_DATE('2024-12-11 14:08:46', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('184', '2', '1000212', '1000214', '81108,寒山石', TO_DATE('2024-12-11 14:08:47', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('185', '2', '1000212', '1000214', '81108,落星石', TO_DATE('2024-12-11 14:08:48', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('186', '2', '1000212', '1000214', '81108,孔雀石', TO_DATE('2024-12-11 14:08:50', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('187', '2', '1000212', '1000214', '81108,芙蓉石', TO_DATE('2024-12-11 14:08:51', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('188', '2', '1000212', '1000214', '81108,赤焰石', TO_DATE('2024-12-11 14:08:54', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('189', '2', '1000212', '1000214', '81108,落星石', TO_DATE('2024-12-11 14:08:55', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('190', '2', '1000212', '1000214', '81108,沐阳石', TO_DATE('2024-12-11 14:08:57', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('191', '2', '1000212', '1000214', '81108,芙蓉石', TO_DATE('2024-12-11 14:08:58', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('192', '2', '1000212', '1000214', '81108,紫烟石', TO_DATE('2024-12-11 14:09:00', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('193', '2', '1000212', '1000214', '32366,卧龙', TO_DATE('2024-12-11 14:09:01', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('194', '2', '1000212', '1000214', '32366,卧龙', TO_DATE('2024-12-11 14:09:02', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('195', '2', '1000212', '1000214', '32367,长生', TO_DATE('2024-12-11 14:09:03', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('196', '2', '1000212', '1000214', '32367,长生', TO_DATE('2024-12-11 14:09:05', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('197', '2', '1000212', '1000214', '32368,落霞', TO_DATE('2024-12-11 14:09:06', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('198', '2', '1000212', '1000214', '32368,落霞', TO_DATE('2024-12-11 14:09:07', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('199', '2', '1000255', '1000254', '900,五十亿银票', TO_DATE('2024-12-11 14:43:59', 'SYYYY-MM-DD HH24:MI:SS'), '1', '奔波儿灞', '大聪明');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('200', '2', '1000259', '1000260', '899,五十亿银票', TO_DATE('2024-12-11 15:51:56', 'SYYYY-MM-DD HH24:MI:SS'), '1', '章鱼哥', '蟹老板');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('2', '2', '1000188', '1000191', '7006,情殇', TO_DATE('2024-12-07 17:19:56', 'SYYYY-MM-DD HH24:MI:SS'), '1', '霸道V', '痞子V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('3', '2', '1000191', '1000188', '7006,情殇', TO_DATE('2024-12-07 17:30:53', 'SYYYY-MM-DD HH24:MI:SS'), '1', '痞子V', '霸道V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('4', '2', '1000188', '1000189', '7006,情殇', TO_DATE('2024-12-07 18:27:40', 'SYYYY-MM-DD HH24:MI:SS'), '1', '霸道V', '混混V');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('5', '2', '1000196', NULL, '309,补天神石', TO_DATE('2024-12-07 18:28:09', 'SYYYY-MM-DD HH24:MI:SS'), '20', '神阶丿醉人间', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('6', '2', '1000196', NULL, '310,六魂之玉', TO_DATE('2024-12-07 18:28:09', 'SYYYY-MM-DD HH24:MI:SS'), '1019', '神阶丿醉人间', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('7', '2', '1000193', '1000210', '899,五十亿银票', TO_DATE('2024-12-07 19:37:50', 'SYYYY-MM-DD HH24:MI:SS'), '1', '夜未央', '1111');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('8', '2', '1000002', NULL, '80033,超级坐骑经验丹', TO_DATE('2024-12-07 19:39:42', 'SYYYY-MM-DD HH24:MI:SS'), '30', '低调点', NULL);
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('27', '2', '1000215', '1000237', '80679,鎏金宝鉴-心猿', TO_DATE('2024-12-09 19:29:32', 'SYYYY-MM-DD HH24:MI:SS'), '1', '明月清风', '吕拥倔');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('28', '2', '1000193', '1000237', '891,一千万银票', TO_DATE('2024-12-09 19:30:22', 'SYYYY-MM-DD HH24:MI:SS'), '1', '夜未央', '吕拥倔');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('29', '2', '1000230', '1000238', '899,五十亿银票', TO_DATE('2024-12-10 00:34:26', 'SYYYY-MM-DD HH24:MI:SS'), '1999', 'qwe', '凤凰花');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('30', '5', '1000230', '1000238', '999,风熊', TO_DATE('2024-12-10 00:35:17', 'SYYYY-MM-DD HH24:MI:SS'), '1', 'qwe', '凤凰花');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('31', '5', '1000230', '1000238', '200116,年', TO_DATE('2024-12-10 00:35:17', 'SYYYY-MM-DD HH24:MI:SS'), '1', 'qwe', '凤凰花');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('32', '2', '1000230', '1000238', '80035,超级坐骑熟练丹', TO_DATE('2024-12-10 00:45:46', 'SYYYY-MM-DD HH24:MI:SS'), '20', 'qwe', '凤凰花');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('33', '2', '1000230', '1000238', '80033,超级坐骑经验丹', TO_DATE('2024-12-10 00:45:54', 'SYYYY-MM-DD HH24:MI:SS'), '20', 'qwe', '凤凰花');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('34', '2', '1000230', '1000238', '80034,高级坐骑熟练丹', TO_DATE('2024-12-10 00:45:59', 'SYYYY-MM-DD HH24:MI:SS'), '9', 'qwe', '凤凰花');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('35', '2', '1000214', '1000212', '7002,幻羽', TO_DATE('2024-12-10 15:08:52', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺中年穷', '莫欺少年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('36', '2', '1000214', '1000212', '7004,化魄', TO_DATE('2024-12-10 15:08:53', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺中年穷', '莫欺少年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('37', '2', '1000214', '1000212', '7000,轩辕', TO_DATE('2024-12-10 15:08:54', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺中年穷', '莫欺少年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('38', '2', '1000212', '1000214', '7000,轩辕', TO_DATE('2024-12-10 15:45:20', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('39', '2', '1000212', '1000214', '30034,紫金七星冠', TO_DATE('2024-12-10 15:45:21', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('40', '2', '1000212', '1000214', '30029,步定乾坤履', TO_DATE('2024-12-10 15:45:23', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('41', '2', '1000212', '1000214', '30032,毁天灭地', TO_DATE('2024-12-10 15:45:25', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('42', '2', '1000212', '1000214', '80067,红日当空', TO_DATE('2024-12-10 15:45:26', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('43', '2', '1000212', '1000214', '7004,化魄', TO_DATE('2024-12-10 15:45:28', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');
INSERT INTO "WJTEST"."GOODSRECORD" VALUES ('44', '2', '1000212', '1000214', '81108,紫烟石', TO_DATE('2024-12-10 16:14:59', 'SYYYY-MM-DD HH24:MI:SS'), '1', '莫欺少年穷', '莫欺中年穷');

-- ----------------------------
-- Table structure for GOODSRECORD2
-- ----------------------------
DROP TABLE "WJTEST"."GOODSRECORD2";
CREATE TABLE "WJTEST"."GOODSRECORD2" (
  "GRID" NUMBER ,
  "RECORDTYPE" NUMBER ,
  "ROLEID" NUMBER ,
  "OTHERROLE" NUMBER ,
  "GOODS" VARCHAR2(200 BYTE) ,
  "RECORDTIME" VARCHAR2(200 BYTE) ,
  "GOODSNUM" NUMBER ,
  "SID" NUMBER ,
  "GOODSNAME" VARCHAR2(200 BYTE) ,
  "VALUE" VARCHAR2(200 BYTE) ,
  "USETIME" VARCHAR2(20 BYTE) ,
  "GOODSID" NUMBER ,
  "SKIN" VARCHAR2(20 BYTE) ,
  "TYPE" NUMBER ,
  "QUALITY" LONG ,
  "INSTRUCTION" VARCHAR2(200 BYTE) ,
  "RGID" NUMBER ,
  "STATUS" NUMBER ,
  "DEFINEPRICE" NUMBER ,
  "GOODLOCK" NUMBER DEFAULT 0   
 
 
 

 
 
 
 
 
 
 
 
 
 
 
 
 


 
 
 
 
 
 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."RECORDTYPE" IS '记录类型';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."OTHERROLE" IS '对方角色ID';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."GOODS" IS '物品信息';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."RECORDTIME" IS '记录时间';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."GOODSNUM" IS '物品数量';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."SID" IS '区域ID';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."GOODSNAME" IS '物品名称';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."VALUE" IS '物品加成';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."USETIME" IS '使用次数';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."GOODSID" IS '物品标识';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."SKIN" IS '皮肤';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."TYPE" IS '物品类型';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."QUALITY" IS '物品贵重';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."INSTRUCTION" IS '物品说明';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."RGID" IS '物品ID';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."STATUS" IS '物品状态';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."DEFINEPRICE" IS '自定义价格/仙玉/大话币/积分';
COMMENT ON COLUMN "WJTEST"."GOODSRECORD2"."GOODLOCK" IS '加锁';

-- ----------------------------
-- Table structure for GOODSSALEDAYRECORD
-- ----------------------------
DROP TABLE "WJTEST"."GOODSSALEDAYRECORD";
CREATE TABLE "WJTEST"."GOODSSALEDAYRECORD" (
  "ID" NUMBER ,
  "GID" NUMBER ,
  "BUYSUM" NUMBER ,
  "PAYSUM" NUMBER ,
  "DATETIME" VARCHAR2(100 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GOODSSALEDAYRECORD"."ID" IS '表id';
COMMENT ON COLUMN "WJTEST"."GOODSSALEDAYRECORD"."GID" IS '物品id';
COMMENT ON COLUMN "WJTEST"."GOODSSALEDAYRECORD"."BUYSUM" IS '销售总量';
COMMENT ON COLUMN "WJTEST"."GOODSSALEDAYRECORD"."PAYSUM" IS '总消耗';
COMMENT ON COLUMN "WJTEST"."GOODSSALEDAYRECORD"."DATETIME" IS '记录时间';

-- ----------------------------
-- Table structure for GOODSTABLE
-- ----------------------------
DROP TABLE "WJTEST"."GOODSTABLE";
CREATE TABLE "WJTEST"."GOODSTABLE" (
  "GOODSID" NUMBER(32) ,
  "GOODSNAME" VARCHAR2(100 BYTE) ,
  "SKIN" VARCHAR2(50 BYTE) ,
  "TYPE" NUMBER(10) ,
  "QUALITY" NUMBER(10) ,
  "VALUE" VARCHAR2(1000 BYTE) ,
  "INSTRUCTION" VARCHAR2(1000 BYTE) ,
  "RGID" NUMBER(32) ,
  "ROLE_ID" NUMBER(32) ,
  "STATUS" NUMBER(8) DEFAULT 0                         ,
  "USETIME" NUMBER(8) DEFAULT 0                         ,
  "DEFINEPRICE" NUMBER(32) ,
  "MAPNAME" VARCHAR2(20 BYTE) ,
  "MAPX" NUMBER ,
  "MAPY" NUMBER ,
  "PRICE" NUMBER ,
  "CODECARD" NUMBER ,
  "GOODLOCK" NUMBER(8) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."GOODSID" IS '物品标识';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."GOODSNAME" IS '物品名称';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."SKIN" IS '物品皮肤';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."TYPE" IS '物品类型';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."QUALITY" IS '物品贵重';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."VALUE" IS '物品加成';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."INSTRUCTION" IS '物品说明';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."RGID" IS '物品ID';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."ROLE_ID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."STATUS" IS '物品状态';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."USETIME" IS '使用次数';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."DEFINEPRICE" IS '自定义价格';
COMMENT ON COLUMN "WJTEST"."GOODSTABLE"."GOODLOCK" IS '加锁';

-- ----------------------------
-- Table structure for GOODSTABLE_FAULT
-- ----------------------------
DROP TABLE "WJTEST"."GOODSTABLE_FAULT";
CREATE TABLE "WJTEST"."GOODSTABLE_FAULT" (
  "GOODSID" NUMBER(32) ,
  "GOODSNAME" VARCHAR2(100 BYTE) ,
  "SKIN" VARCHAR2(50 BYTE) ,
  "TYPE" NUMBER(10) ,
  "QUALITY" NUMBER(10) ,
  "VALUE" VARCHAR2(1000 BYTE) ,
  "INSTRUCTION" VARCHAR2(1000 BYTE) ,
  "RGID" NUMBER(32) ,
  "ROLE_ID" NUMBER(32) ,
  "STATUS" NUMBER(8) ,
  "USETIME" NUMBER(8) ,
  "DEFINEPRICE" NUMBER(32) ,
  "MAPNAME" VARCHAR2(20 BYTE) ,
  "MAPX" NUMBER ,
  "MAPY" NUMBER ,
  "PRICE" NUMBER ,
  "CODECARD" NUMBER ,
  "GOODLOCK" NUMBER(8) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for HATERS
-- ----------------------------
DROP TABLE "WJTEST"."HATERS";
CREATE TABLE "WJTEST"."HATERS" (
  "ROLEID" NUMBER(32) ,
  "UNKNOWN" VARCHAR2(20 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for IMPORTANTGOODSSUMRECORD
-- ----------------------------
DROP TABLE "WJTEST"."IMPORTANTGOODSSUMRECORD";
CREATE TABLE "WJTEST"."IMPORTANTGOODSSUMRECORD" (
  "ID" NUMBER ,
  "GID" NUMBER ,
  "GOODNUMBER" NUMBER ,
  "DATETIME" VARCHAR2(100 BYTE) ,
  "SID" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."IMPORTANTGOODSSUMRECORD"."ID" IS '表id';
COMMENT ON COLUMN "WJTEST"."IMPORTANTGOODSSUMRECORD"."GID" IS '物品id';
COMMENT ON COLUMN "WJTEST"."IMPORTANTGOODSSUMRECORD"."GOODNUMBER" IS '数量';
COMMENT ON COLUMN "WJTEST"."IMPORTANTGOODSSUMRECORD"."DATETIME" IS '记录时间';
COMMENT ON COLUMN "WJTEST"."IMPORTANTGOODSSUMRECORD"."SID" IS '区id';

-- ----------------------------
-- Table structure for IMPORTANTRECORDGOODS
-- ----------------------------
DROP TABLE "WJTEST"."IMPORTANTRECORDGOODS";
CREATE TABLE "WJTEST"."IMPORTANTRECORDGOODS" (
  "IID" NUMBER(32) ,
  "GOODSID" NUMBER ,
  "GOODSNUMBER" NUMBER ,
  "ROCORDTYPE" NUMBER ,
  "USERID" NUMBER ,
  "ROLEID" NUMBER ,
  "SID" NUMBER ,
  "DATETIME" VARCHAR2(100 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."IMPORTANTRECORDGOODS"."IID" IS '表id';
COMMENT ON COLUMN "WJTEST"."IMPORTANTRECORDGOODS"."GOODSID" IS '记录物品id';
COMMENT ON COLUMN "WJTEST"."IMPORTANTRECORDGOODS"."GOODSNUMBER" IS '当前数量';
COMMENT ON COLUMN "WJTEST"."IMPORTANTRECORDGOODS"."ROCORDTYPE" IS '记录类型';
COMMENT ON COLUMN "WJTEST"."IMPORTANTRECORDGOODS"."USERID" IS '用户id';
COMMENT ON COLUMN "WJTEST"."IMPORTANTRECORDGOODS"."ROLEID" IS '角色id';
COMMENT ON COLUMN "WJTEST"."IMPORTANTRECORDGOODS"."SID" IS '区域id';
COMMENT ON COLUMN "WJTEST"."IMPORTANTRECORDGOODS"."DATETIME" IS '异动时间';

-- ----------------------------
-- Table structure for IPADDRESSMAC
-- ----------------------------
DROP TABLE "WJTEST"."IPADDRESSMAC";
CREATE TABLE "WJTEST"."IPADDRESSMAC" (
  "IPID" NUMBER(20) ,
  "ADDRESSKEY" VARCHAR2(100 BYTE) ,
  "CTIME" VARCHAR2(100 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."IPADDRESSMAC"."IPID" IS '编号';
COMMENT ON COLUMN "WJTEST"."IPADDRESSMAC"."ADDRESSKEY" IS 'ip地址';
COMMENT ON COLUMN "WJTEST"."IPADDRESSMAC"."CTIME" IS '插入的时间';

-- ----------------------------
-- Table structure for LINGBAO
-- ----------------------------
DROP TABLE "WJTEST"."LINGBAO";
CREATE TABLE "WJTEST"."LINGBAO" (
  "BAOID" NUMBER(32) ,
  "BAONAME" VARCHAR2(50 BYTE) ,
  "GETHARD" VARCHAR2(20 BYTE) ,
  "BAOTYPE" VARCHAR2(20 BYTE) ,
  "BAOACTIVE" NUMBER(8) ,
  "BAOSPEED" VARCHAR2(20 BYTE) ,
  "BAOREPLY" VARCHAR2(20 BYTE) ,
  "BAOSHOT" VARCHAR2(20 BYTE) ,
  "BAOAP" VARCHAR2(20 BYTE) ,
  "RESISTSHOT" VARCHAR2(20 BYTE) ,
  "ASSISTANCE" VARCHAR2(20 BYTE) ,
  "GOODSKILL" VARCHAR2(100 BYTE) ,
  "ROLEID" NUMBER(32) ,
  "SKIN" VARCHAR2(20 BYTE) ,
  "SKILLSUM" NUMBER(8) DEFAULT 0                         ,
  "FUSUM" NUMBER(8) DEFAULT 0                         ,
  "LINGBAOLVL" NUMBER(32) DEFAULT 1                         ,
  "LINGBAOEXE" NUMBER(32) DEFAULT 0                         ,
  "LINGBAOQIHE" NUMBER(9) DEFAULT 0                         ,
  "SKILLS" VARCHAR2(500 BYTE) ,
  "KANGXING" VARCHAR2(100 BYTE) ,
  "EQUIPMENT" NUMBER(8) ,
  "BAOQUALITY" VARCHAR2(50 BYTE) ,
  "TIANFUSKILL" VARCHAR2(100 BYTE) ,
  "FUSHIS" VARCHAR2(200 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAOID" IS '灵宝ID';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAONAME" IS '灵宝名称';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."GETHARD" IS '获得难度';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAOTYPE" IS '类型';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAOACTIVE" IS '活跃';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAOSPEED" IS '速度';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAOREPLY" IS '法宝回复';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAOSHOT" IS '落宝几率';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAOAP" IS '法宝伤害';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."RESISTSHOT" IS '抗落宝';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."ASSISTANCE" IS '援助几率';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."GOODSKILL" IS '擅长技能';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."SKIN" IS '皮肤';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."SKILLSUM" IS '技能开启数';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."FUSUM" IS '符石开启数';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."LINGBAOLVL" IS '灵宝道行';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."LINGBAOEXE" IS '灵宝当前进度经验';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."LINGBAOQIHE" IS '灵宝契合度';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."SKILLS" IS '灵宝技能集合';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."KANGXING" IS '灵宝附加抗性';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."EQUIPMENT" IS '是否装备';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."BAOQUALITY" IS '灵宝品质';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."TIANFUSKILL" IS '天赋技能';
COMMENT ON COLUMN "WJTEST"."LINGBAO"."FUSHIS" IS '灵宝符石';

-- ----------------------------
-- Table structure for MANAGERTABLE
-- ----------------------------
DROP TABLE "WJTEST"."MANAGERTABLE";
CREATE TABLE "WJTEST"."MANAGERTABLE" (
  "MANAEID" NUMBER ,
  "USERNAME" VARCHAR2(20 BYTE) ,
  "PWD" VARCHAR2(50 BYTE) ,
  "RELNAME" VARCHAR2(50 BYTE) ,
  "CREATETIME" DATE ,
  "FLAG" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for MESSAGE
-- ----------------------------
DROP TABLE "WJTEST"."MESSAGE";
CREATE TABLE "WJTEST"."MESSAGE" (
  "MESID" NUMBER(32) ,
  "ROLEID" NUMBER(32) ,
  "SALEID" NUMBER(32) ,
  "MESCONTENT" VARCHAR2(2000 BYTE) ,
  "GETTIME" TIMESTAMP(6) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."MESSAGE"."MESID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."MESSAGE"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."MESSAGE"."SALEID" IS '商品ID';
COMMENT ON COLUMN "WJTEST"."MESSAGE"."MESCONTENT" IS '消息内容';
COMMENT ON COLUMN "WJTEST"."MESSAGE"."GETTIME" IS '收到时间';

-- ----------------------------
-- Table structure for MOUNT
-- ----------------------------
DROP TABLE "WJTEST"."MOUNT";
CREATE TABLE "WJTEST"."MOUNT" (
  "MID" NUMBER(32) ,
  "MOUNTID" VARCHAR2(50 BYTE) ,
  "MOUNTNAME" VARCHAR2(100 BYTE) ,
  "MOUNTLVL" VARCHAR2(50 BYTE) ,
  "LIVE" VARCHAR2(50 BYTE) ,
  "SPRI" VARCHAR2(50 BYTE) ,
  "POWER" VARCHAR2(50 BYTE) ,
  "BONE" VARCHAR2(50 BYTE) ,
  "EXP" VARCHAR2(50 BYTE) DEFAULT 0                         ,
  "ROLEID" NUMBER(32) ,
  "SID" NUMBER(32) ,
  "OTHRERSID" NUMBER(32) ,
  "USENUMBER" NUMBER(8) DEFAULT 0                         ,
  "PROFICIENCY" NUMBER(8) DEFAULT 0                         ,
  "SID3" NUMBER(32) ,
  "MOVEGRADE" NUMBER(8) DEFAULT 0   
 
 
 

 
 
 
 
 
 
 
 
 
 
 
 
 
      ,
  "SID4" NUMBER(32) ,
  "SID5" NUMBER(32) ,
  "SHOUHU" NUMBER(32) ,
  "SH" NUMBER(8) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."MOUNT"."MID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."MOUNT"."MOUNTID" IS '坐骑ID';
COMMENT ON COLUMN "WJTEST"."MOUNT"."MOUNTNAME" IS '坐骑名称';
COMMENT ON COLUMN "WJTEST"."MOUNT"."MOUNTLVL" IS '坐骑等级';
COMMENT ON COLUMN "WJTEST"."MOUNT"."LIVE" IS '体力';
COMMENT ON COLUMN "WJTEST"."MOUNT"."SPRI" IS '灵性';
COMMENT ON COLUMN "WJTEST"."MOUNT"."POWER" IS '力量';
COMMENT ON COLUMN "WJTEST"."MOUNT"."BONE" IS '根骨';
COMMENT ON COLUMN "WJTEST"."MOUNT"."EXP" IS '经验';
COMMENT ON COLUMN "WJTEST"."MOUNT"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."MOUNT"."SID" IS '管制的召唤兽ID';
COMMENT ON COLUMN "WJTEST"."MOUNT"."OTHRERSID" IS '管制的召唤兽ID';
COMMENT ON COLUMN "WJTEST"."MOUNT"."USENUMBER" IS '使用次数';
COMMENT ON COLUMN "WJTEST"."MOUNT"."PROFICIENCY" IS '熟练度';
COMMENT ON COLUMN "WJTEST"."MOUNT"."SID3" IS '管制id';
COMMENT ON COLUMN "WJTEST"."MOUNT"."MOVEGRADE" IS '移动等级';

-- ----------------------------
-- Table structure for MOUNT_SKILL
-- ----------------------------
DROP TABLE "WJTEST"."MOUNT_SKILL";
CREATE TABLE "WJTEST"."MOUNT_SKILL" (
  "SKILLID" NUMBER(32) ,
  "SKILLNAME" VARCHAR2(100 BYTE) ,
  "REMARK" VARCHAR2(1000 BYTE) ,
  "MID" NUMBER(32) ,
  "SKILLED" NUMBER(8) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for ONEARENANOTES
-- ----------------------------
DROP TABLE "WJTEST"."ONEARENANOTES";
CREATE TABLE "WJTEST"."ONEARENANOTES" (
  "ID" NUMBER ,
  "ROLE1" NUMBER ,
  "NAME1" VARCHAR2(200 BYTE) ,
  "SKIN1" VARCHAR2(200 BYTE) ,
  "LVL1" NUMBER ,
  "ROLE2" NUMBER ,
  "NAME2" VARCHAR2(200 BYTE) ,
  "SKIN2" VARCHAR2(200 BYTE) ,
  "LVL2" NUMBER ,
  "ISV" NUMBER ,
  "PLACE" NUMBER ,
  "TIME" VARCHAR2(200 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ONEARENANOTES"."ROLE1" IS '玩家一   发起方';
COMMENT ON COLUMN "WJTEST"."ONEARENANOTES"."NAME1" IS '名称等级图标';
COMMENT ON COLUMN "WJTEST"."ONEARENANOTES"."ROLE2" IS '玩家二   被动方';
COMMENT ON COLUMN "WJTEST"."ONEARENANOTES"."NAME2" IS '名称等级图标';
COMMENT ON COLUMN "WJTEST"."ONEARENANOTES"."TIME" IS '发生时间';

-- ----------------------------
-- Table structure for ONEARENAROLE
-- ----------------------------
DROP TABLE "WJTEST"."ONEARENAROLE";
CREATE TABLE "WJTEST"."ONEARENAROLE" (
  "ROLEID" NUMBER ,
  "PLACE" NUMBER ,
  "SKIN" VARCHAR2(200 BYTE) ,
  "NAME" VARCHAR2(200 BYTE) ,
  "LVL" NUMBER ,
  "ISAWARD" NUMBER ,
  "PLACEPAST" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ONEARENAROLE"."ROLEID" IS '角色id';
COMMENT ON COLUMN "WJTEST"."ONEARENAROLE"."PLACE" IS '名次';
COMMENT ON COLUMN "WJTEST"."ONEARENAROLE"."SKIN" IS '头像路径';
COMMENT ON COLUMN "WJTEST"."ONEARENAROLE"."NAME" IS '名称';
COMMENT ON COLUMN "WJTEST"."ONEARENAROLE"."LVL" IS '等级';

-- ----------------------------
-- Table structure for ONE_ROL
-- ----------------------------
DROP TABLE "WJTEST"."ONE_ROL";
CREATE TABLE "WJTEST"."ONE_ROL" (
  "ID" NUMBER ,
  "ROLEID" NUMBER ,
  "PLACE" NUMBER ,
  "PLACEPAST" NUMBER ,
  "ISAWARD" VARCHAR2(200 BYTE) ,
  "SKIN" VARCHAR2(200 BYTE) ,
  "NAME" VARCHAR2(200 BYTE) ,
  "LVL" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ONE_ROL"."ROLEID" IS '角色id';
COMMENT ON COLUMN "WJTEST"."ONE_ROL"."PLACE" IS '名次';
COMMENT ON COLUMN "WJTEST"."ONE_ROL"."PLACEPAST" IS '旧名次';
COMMENT ON COLUMN "WJTEST"."ONE_ROL"."ISAWARD" IS '奖励';
COMMENT ON COLUMN "WJTEST"."ONE_ROL"."SKIN" IS '头像路径';
COMMENT ON COLUMN "WJTEST"."ONE_ROL"."NAME" IS '名称';
COMMENT ON COLUMN "WJTEST"."ONE_ROL"."LVL" IS '等级';

-- ----------------------------
-- Table structure for OPENAREATABLE
-- ----------------------------
DROP TABLE "WJTEST"."OPENAREATABLE";
CREATE TABLE "WJTEST"."OPENAREATABLE" (
  "TB_ID" NUMBER ,
  "OT_AREAID" NUMBER ,
  "OT_AREANAME" VARCHAR2(200 BYTE) ,
  "OT_BELONG" NUMBER ,
  "OT_DISTRIBUTION" VARCHAR2(20 BYTE) ,
  "OT_ATID" VARCHAR2(20 BYTE) ,
  "OT_CRETIME" VARCHAR2(20 BYTE) ,
  "OT_CTREMANAGEID" NUMBER ,
  "OT_UPATEMANAGEID" NUMBER ,
  "OT_UPDATETIME" VARCHAR2(20 BYTE) ,
  "OT_MEMO" VARCHAR2(20 BYTE) ,
  "MAXONLINE" NUMBER ,
  "NOWONLINE" NUMBER ,
  "TODAYMAXONLINE" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_AREAID" IS '区域id';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_AREANAME" IS '区域名称';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_BELONG" IS '区域归属id';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_DISTRIBUTION" IS '分成模式(格式5-5)';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_ATID" IS '代理id';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_CRETIME" IS '创建时间';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_CTREMANAGEID" IS '创建者管理员id';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_UPATEMANAGEID" IS '更新管理员id';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_UPDATETIME" IS ' 更新时间(同步更新到游戏服务器)';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."OT_MEMO" IS '备注';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."MAXONLINE" IS '历史最高人数';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."NOWONLINE" IS '当前人数';
COMMENT ON COLUMN "WJTEST"."OPENAREATABLE"."TODAYMAXONLINE" IS ' 今日最高人数';

-- ----------------------------
-- Records of OPENAREATABLE
-- ----------------------------
INSERT INTO "WJTEST"."OPENAREATABLE" VALUES ('1', '100', '元旦', '1001', '5-5', '8888', NULL, '1', '1', '02-1月 -25', NULL, '0', '0', '0');

-- ----------------------------
-- Table structure for PACK_RECORD
-- ----------------------------
DROP TABLE "WJTEST"."PACK_RECORD";
CREATE TABLE "WJTEST"."PACK_RECORD" (
  "ROLE_ID" NUMBER(32) ,
  "RECORD" VARCHAR2(2500 BYTE) ,
  "HELPBB" VARCHAR2(1000 BYTE) ,
  "HELPLING" VARCHAR2(1000 BYTE) ,
  "SUITNUM" NUMBER(8) DEFAULT 5                         ,
  "COLLECT" VARCHAR2(1500 BYTE) ,
  "SUIT1" VARCHAR2(1300 BYTE) ,
  "SUIT2" VARCHAR2(1300 BYTE) ,
  "SUIT3" VARCHAR2(1300 BYTE) ,
  "SUIT4" VARCHAR2(1300 BYTE) ,
  "SUIT5" VARCHAR2(1300 BYTE) ,
  "SUIT6" VARCHAR2(1300 BYTE) ,
  "SUIT7" VARCHAR2(1300 BYTE) ,
  "SUIT8" VARCHAR2(1300 BYTE) ,
  "SUIT9" VARCHAR2(1300 BYTE) ,
  "SUIT10" VARCHAR2(1300 BYTE) ,
  "SUIT11" VARCHAR2(1300 BYTE) ,
  "TX" VARCHAR2(1300 BYTE) ,
  "SLDH" VARCHAR2(1300 BYTE) ,
  "OTHER" VARCHAR2(1300 BYTE) ,
  "LCARD" VARCHAR2(1300 BYTE) ,
  "PETORDER" VARCHAR2(1000 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of PACK_RECORD
-- ----------------------------
INSERT INTO "WJTEST"."PACK_RECORD" VALUES ('1000001', '0-0', NULL, NULL, '5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for PAYVIP
-- ----------------------------
DROP TABLE "WJTEST"."PAYVIP";
CREATE TABLE "WJTEST"."PAYVIP" (
  "ID" NUMBER(20) ,
  "PAYNUM" NUMBER(20) ,
  "GIVEGOODS" VARCHAR2(2000 BYTE) ,
  "GRADE" NUMBER ,
  "INSTRUCTIONTEXT" VARCHAR2(2000 BYTE) ,
  "INCREATIONTEXT" VARCHAR2(2000 BYTE) ,
  "DATETIME" VARCHAR2(20 BYTE) ,
  "GETNUMBER" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."PAYVIP"."PAYNUM" IS '充值金额';
COMMENT ON COLUMN "WJTEST"."PAYVIP"."GIVEGOODS" IS '奖励格式';
COMMENT ON COLUMN "WJTEST"."PAYVIP"."GRADE" IS '等级';
COMMENT ON COLUMN "WJTEST"."PAYVIP"."INSTRUCTIONTEXT" IS '描述';
COMMENT ON COLUMN "WJTEST"."PAYVIP"."INCREATIONTEXT" IS '持续加成';
COMMENT ON COLUMN "WJTEST"."PAYVIP"."DATETIME" IS '异动时间';
COMMENT ON COLUMN "WJTEST"."PAYVIP"."GETNUMBER" IS '领取次数';

-- ----------------------------
-- Table structure for PET_DEFAULT
-- ----------------------------
DROP TABLE "WJTEST"."PET_DEFAULT";
CREATE TABLE "WJTEST"."PET_DEFAULT" (
  "SUMMONINGID" VARCHAR2(100 BYTE) ,
  "SUMMONINGSKIN" VARCHAR2(100 BYTE) ,
  "STYE" VARCHAR2(100 BYTE) ,
  "HP" NUMBER(8) ,
  "MP" NUMBER(8) ,
  "AP" NUMBER(8) ,
  "SP" NUMBER(8) ,
  "GROWLEVEL" VARCHAR2(100 BYTE) ,
  "RESISTANCE" VARCHAR2(1000 BYTE) ,
  "SKILL" VARCHAR2(100 BYTE) ,
  "GOLD" VARCHAR2(100 BYTE) ,
  "WOOD" VARCHAR2(100 BYTE) ,
  "SOIL" VARCHAR2(100 BYTE) ,
  "WATER" VARCHAR2(100 BYTE) ,
  "FIRE" VARCHAR2(100 BYTE) ,
  "SUMMONINGNAME" VARCHAR2(500 BYTE) ,
  "SID" NUMBER(32) ,
  "SSN" VARCHAR2(10 BYTE) ,
  "ROLEID" NUMBER(32) ,
  "BONE" NUMBER(8) ,
  "SPIR" NUMBER(8) ,
  "POWER" NUMBER(8) ,
  "SPEED" NUMBER(8) ,
  "CANPOINT" NUMBER(8) ,
  "GRADE" NUMBER(8) ,
  "EXP" NUMBER(32) ,
  "FAITHFUL" NUMBER(8) ,
  "FRIENDLINESS" NUMBER(8) ,
  "PRICE" NUMBER(8) ,
  "BASISHP" NUMBER(8) ,
  "BASISMP" NUMBER(8) ,
  "BASISAP" NUMBER(8) ,
  "BASISSP" NUMBER(8) ,
  "OPENSEAL" NUMBER(8) ,
  "INNERGOODS" VARCHAR2(50 BYTE) ,
  "DRAGON" NUMBER(8) ,
  "EXTRAHP" NUMBER(8) ,
  "EXTRAMP" NUMBER(8) ,
  "EXTRAAP" NUMBER(8) ,
  "EXTRASP" NUMBER(8) ,
  "PETSKILLS" VARCHAR2(50 BYTE) ,
  "TURNROUNT" NUMBER(8) ,
  "GRADEHP" NUMBER(8) ,
  "GRADEMP" NUMBER(8) ,
  "NEDANRESISTANCE" VARCHAR2(1000 BYTE) ,
  "REVEALNUM" NUMBER(8) ,
  "FLYUPNUM" NUMBER(8) ,
  "BEASTSKILLS" VARCHAR2(10 BYTE) ,
  "FOURATTRIBUTES" VARCHAR2(500 BYTE) ,
  "SKILLDATA" VARCHAR2(2000 BYTE) ,
  "LYK" VARCHAR2(2000 BYTE) ,
  "ZQK" VARCHAR2(2000 BYTE) ,
  "ALCHEMYNUM" NUMBER(8) ,
  "GROWUPDANNUM" NUMBER(8) ,
  "COLORSCHEME" VARCHAR2(300 BYTE) ,
  "DRAC" NUMBER ,
  "TRAINNUM" NUMBER(8) ,
  "PETLOCK" NUMBER(8) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for RECORD
-- ----------------------------
DROP TABLE "WJTEST"."RECORD";
CREATE TABLE "WJTEST"."RECORD" (
  "RECORDID" NUMBER(32) ,
  "RECORDTYPE" NUMBER(8) DEFAULT 0                         ,
  "TEXT" VARCHAR2(2000 BYTE) ,
  "RECORDTIME" VARCHAR2(100 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."RECORD"."RECORDID" IS '日志ID';
COMMENT ON COLUMN "WJTEST"."RECORD"."RECORDTYPE" IS '日志类型';
COMMENT ON COLUMN "WJTEST"."RECORD"."TEXT" IS '日志描述
';
COMMENT ON COLUMN "WJTEST"."RECORD"."RECORDTIME" IS '日志时间';

-- ----------------------------
-- Table structure for REWARD_HALL
-- ----------------------------
DROP TABLE "WJTEST"."REWARD_HALL";
CREATE TABLE "WJTEST"."REWARD_HALL" (
  "ID" NUMBER(32) ,
  "GOODSTABLE" VARCHAR2(3000 BYTE) ,
  "GOODNUM" NUMBER DEFAULT 1                         ,
  "GOODPRICE" NUMBER ,
  "ROLE_ID" NUMBER(32) ,
  "THROWTIME" TIMESTAMP(6) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."REWARD_HALL"."ID" IS 'ID';
COMMENT ON COLUMN "WJTEST"."REWARD_HALL"."GOODSTABLE" IS '物品';
COMMENT ON COLUMN "WJTEST"."REWARD_HALL"."GOODNUM" IS '数量';
COMMENT ON COLUMN "WJTEST"."REWARD_HALL"."GOODPRICE" IS '价格';
COMMENT ON COLUMN "WJTEST"."REWARD_HALL"."ROLE_ID" IS '玩家ID';
COMMENT ON COLUMN "WJTEST"."REWARD_HALL"."THROWTIME" IS '投放时间';
COMMENT ON TABLE "WJTEST"."REWARD_HALL" IS '赏功堂';

-- ----------------------------
-- Table structure for ROLEORDER
-- ----------------------------
DROP TABLE "WJTEST"."ROLEORDER";
CREATE TABLE "WJTEST"."ROLEORDER" (
  "ORDERID" NUMBER(32) ,
  "SALEID" NUMBER(32) ,
  "BUYTIME" TIMESTAMP(6) ,
  "STATUS" NUMBER(8) ,
  "ROLEID" NUMBER(32) ,
  "ORDERNUMBER" VARCHAR2(1000 BYTE) ,
  "buy_role_ids" NUMBER(32) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ROLEORDER"."ORDERID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."ROLEORDER"."SALEID" IS '商品ID';
COMMENT ON COLUMN "WJTEST"."ROLEORDER"."BUYTIME" IS '下单时间';
COMMENT ON COLUMN "WJTEST"."ROLEORDER"."STATUS" IS '状态（1未付钱  2超时 3已付钱 4已取货）';
COMMENT ON COLUMN "WJTEST"."ROLEORDER"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."ROLEORDER"."ORDERNUMBER" IS '订单编号';

-- ----------------------------
-- Table structure for ROLESHOPCART
-- ----------------------------
DROP TABLE "WJTEST"."ROLESHOPCART";
CREATE TABLE "WJTEST"."ROLESHOPCART" (
  "S_CART_ID" NUMBER ,
  "GOODS_ID" NUMBER ,
  "GOODS_NAME" VARCHAR2(200 BYTE) ,
  "SKIN" VARCHAR2(20 BYTE) ,
  "PRICE" VARCHAR2(20 BYTE) ,
  "GNUMBER" NUMBER ,
  "ROLE_ID" NUMBER ,
  "TYPE" NUMBER ,
  "INTIME" VARCHAR2(200 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."S_CART_ID" IS '购物车表ID';
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."GOODS_ID" IS '物品表ID';
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."GOODS_NAME" IS ' 物品名称';
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."SKIN" IS '皮肤';
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."PRICE" IS '物品单价';
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."GNUMBER" IS '物品数量';
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."ROLE_ID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."TYPE" IS ' 购买类型 0商城 1npc商店 2积分 3限购';
COMMENT ON COLUMN "WJTEST"."ROLESHOPCART"."INTIME" IS '插入时间';

-- ----------------------------
-- Table structure for ROLE_ATTRIBUTE
-- ----------------------------
DROP TABLE "WJTEST"."ROLE_ATTRIBUTE";
CREATE TABLE "WJTEST"."ROLE_ATTRIBUTE" (
  "ROLE_ID" NUMBER(8) NOT NULL ,
  "ATTRIBUTENAMEONE" VARCHAR2(255 BYTE) ,
  "BONEONE" NUMBER(8) ,
  "SPIRONE" NUMBER(8) ,
  "POWERONE" NUMBER(8) ,
  "SPEEDONE" NUMBER(8) ,
  "CALMONE" NUMBER(8) ,
  "LABPOINTNUMBERONE" NUMBER(8) ,
  "ATTRIBUTENAMETWO" VARCHAR2(255 BYTE) ,
  "BONETWO" NUMBER(8) ,
  "SPIRTWO" NUMBER(8) ,
  "POWERTWO" NUMBER(8) ,
  "SPEEDTWO" NUMBER(8) ,
  "CALMTWO" NUMBER(8) ,
  "LABPOINTNUMBERTWO" NUMBER(8) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of ROLE_ATTRIBUTE
-- ----------------------------
INSERT INTO "WJTEST"."ROLE_ATTRIBUTE" VALUES ('1000189', '属性一', '905', '145', '145', '145', '145', NULL, '属性二', '145', '905', '145', '145', '145', NULL);
INSERT INTO "WJTEST"."ROLE_ATTRIBUTE" VALUES ('1000447', '属性一', '880', '140', '140', '140', '140', NULL, '属性二', '140', '140', '140', '140', '140', NULL);
INSERT INTO "WJTEST"."ROLE_ATTRIBUTE" VALUES ('1000032', '属性一', '140', '140', '140', '1780', '140', NULL, '属性二', '140', '140', '140', '140', '140', NULL);
INSERT INTO "WJTEST"."ROLE_ATTRIBUTE" VALUES ('1000033', '属性一', '127', '127', '127', '127', '0', NULL, '属性二', '127', '127', '127', '127', '0', NULL);
INSERT INTO "WJTEST"."ROLE_ATTRIBUTE" VALUES ('1000052', '属性一', '125', '126', '125', '125', '0', NULL, '属性二', '125', '125', '125', '125', '0', NULL);
INSERT INTO "WJTEST"."ROLE_ATTRIBUTE" VALUES ('1000271', '属性一', '886', '141', '141', '141', '141', NULL, '属性二', '601', '141', '141', '426', '141', NULL);

-- ----------------------------
-- Table structure for ROLE_PAL
-- ----------------------------
DROP TABLE "WJTEST"."ROLE_PAL";
CREATE TABLE "WJTEST"."ROLE_PAL" (
  "ID" NUMBER ,
  "PID" NUMBER ,
  "GROW" FLOAT(126) ,
  "LVL" NUMBER ,
  "EXP" LONG ,
  "PARTS" VARCHAR2(20 BYTE) ,
  "ROLEID" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ROLE_PAL"."PID" IS '伙伴id';
COMMENT ON COLUMN "WJTEST"."ROLE_PAL"."GROW" IS '成长 ';
COMMENT ON COLUMN "WJTEST"."ROLE_PAL"."LVL" IS '等级';
COMMENT ON COLUMN "WJTEST"."ROLE_PAL"."EXP" IS '经验';
COMMENT ON COLUMN "WJTEST"."ROLE_PAL"."PARTS" IS '装备id';
COMMENT ON COLUMN "WJTEST"."ROLE_PAL"."ROLEID" IS '角色id';

-- ----------------------------
-- Table structure for ROLE_SUMMONING
-- ----------------------------
DROP TABLE "WJTEST"."ROLE_SUMMONING";
CREATE TABLE "WJTEST"."ROLE_SUMMONING" (
  "SUMMONINGID" VARCHAR2(100 BYTE) ,
  "SUMMONINGSKIN" VARCHAR2(100 BYTE) ,
  "STYE" VARCHAR2(100 BYTE) ,
  "HP" NUMBER(8) ,
  "MP" NUMBER(8) ,
  "AP" NUMBER(8) ,
  "SP" NUMBER(8) ,
  "GROWLEVEL" VARCHAR2(100 BYTE) ,
  "RESISTANCE" VARCHAR2(1000 BYTE) ,
  "SKILL" VARCHAR2(100 BYTE) ,
  "GOLD" VARCHAR2(100 BYTE) ,
  "WOOD" VARCHAR2(100 BYTE) ,
  "SOIL" VARCHAR2(100 BYTE) ,
  "WATER" VARCHAR2(100 BYTE) ,
  "FIRE" VARCHAR2(100 BYTE) ,
  "SUMMONINGNAME" VARCHAR2(500 BYTE) ,
  "SID" NUMBER(32) ,
  "SSN" VARCHAR2(10 BYTE) ,
  "ROLEID" NUMBER(32) ,
  "BONE" NUMBER(8) DEFAULT 0                         ,
  "SPIR" NUMBER(8) DEFAULT 0                         ,
  "POWER" NUMBER(8) DEFAULT 0                         ,
  "SPEED" NUMBER(8) DEFAULT 0                         ,
  "CANPOINT" NUMBER(8) DEFAULT 0                         ,
  "GRADE" NUMBER(8) DEFAULT 0                         ,
  "EXP" NUMBER(32) DEFAULT 0                         ,
  "FAITHFUL" NUMBER(8) DEFAULT 100                         ,
  "FRIENDLINESS" NUMBER(8) DEFAULT 0                         ,
  "PRICE" NUMBER(8) ,
  "BASISHP" NUMBER(8) ,
  "BASISMP" NUMBER(8) ,
  "BASISAP" NUMBER(8) ,
  "BASISSP" NUMBER(8) ,
  "OPENSEAL" NUMBER(8) DEFAULT 1                         ,
  "INNERGOODS" VARCHAR2(50 BYTE) ,
  "DRAGON" NUMBER(8) DEFAULT 0                         ,
  "EXTRAHP" NUMBER(8) ,
  "EXTRAMP" NUMBER(8) ,
  "EXTRAAP" NUMBER(8) ,
  "EXTRASP" NUMBER(8) ,
  "PETSKILLS" VARCHAR2(50 BYTE) ,
  "TURNROUNT" NUMBER(8) ,
  "GRADEHP" NUMBER(8) ,
  "GRADEMP" NUMBER(8) ,
  "NEDANRESISTANCE" VARCHAR2(1000 BYTE) ,
  "REVEALNUM" NUMBER(8) DEFAULT 0                         ,
  "FLYUPNUM" NUMBER(8) DEFAULT 0                         ,
  "BEASTSKILLS" VARCHAR2(10 BYTE) ,
  "FOURATTRIBUTES" VARCHAR2(500 BYTE) ,
  "SKILLDATA" VARCHAR2(2000 BYTE) ,
  "LYK" VARCHAR2(2000 BYTE) ,
  "ZQK" VARCHAR2(2000 BYTE) ,
  "ALCHEMYNUM" NUMBER(8) DEFAULT 0                         ,
  "GROWUPDANNUM" NUMBER(8) DEFAULT 0                         ,
  "COLORSCHEME" VARCHAR2(300 BYTE) ,
  "DRAC" NUMBER DEFAULT 0                         ,
  "TRAINNUM" NUMBER(8) ,
  "PETLOCK" NUMBER(8) ,
  "CALM" NUMBER(8) ,
  "czjjd" NUMBER(8) DEFAULT 0   
                      ,
  "XY" VARCHAR2(2000 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SUMMONINGID" IS '召唤兽id';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SUMMONINGSKIN" IS '皮肤';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."STYE" IS '是否物理怪';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."HP" IS '最高HP';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."MP" IS '最高mp';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."AP" IS '最高ap';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SP" IS '最高sp';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."GROWLEVEL" IS '最高成长率
';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."RESISTANCE" IS '抗性';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SKILL" IS '技能';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."GOLD" IS '金';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."WOOD" IS '木';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SOIL" IS '土';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."WATER" IS '水';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."FIRE" IS '火';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SUMMONINGNAME" IS '召唤兽名称';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SSN" IS '宝宝类型';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."BONE" IS '根骨';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SPIR" IS '灵性';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."POWER" IS '力量';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SPEED" IS '敏捷';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."CANPOINT" IS '可分配点数';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."GRADE" IS '等级';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."EXP" IS '经验';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."FAITHFUL" IS '忠诚度';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."FRIENDLINESS" IS '亲密值';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."PRICE" IS '单价';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."BASISHP" IS '当前血量';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."BASISMP" IS '当前蓝量';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."BASISAP" IS '当前伤害';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."BASISSP" IS '当前敏捷';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."OPENSEAL" IS '封印';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."INNERGOODS" IS '内丹';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."DRAGON" IS '龙骨';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."EXTRAHP" IS '额外血量';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."EXTRAMP" IS '额外蓝量';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."EXTRAAP" IS '额外伤害';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."EXTRASP" IS '额外敏捷';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."PETSKILLS" IS '技能';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."TURNROUNT" IS '转身';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."GRADEHP" IS '等级血量';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."GRADEMP" IS '等级蓝量';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."NEDANRESISTANCE" IS '内丹抗性';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."REVEALNUM" IS '被点化次数';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."FLYUPNUM" IS '使用神兽飞升丹的次数';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."BEASTSKILLS" IS '神兽技能id';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."FOURATTRIBUTES" IS '召唤兽增加四种属性几率字段';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."SKILLDATA" IS '召唤兽技能属性';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."LYK" IS '炼妖抗性';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."ZQK" IS '坐骑抗性';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."ALCHEMYNUM" IS '炼妖次数';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."GROWUPDANNUM" IS '元气丹增加的属性';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."COLORSCHEME" IS '变色方案';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."TRAINNUM" IS '培养值';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."PETLOCK" IS '加锁';
COMMENT ON COLUMN "WJTEST"."ROLE_SUMMONING"."XY" IS '心猿';

-- ----------------------------
-- Table structure for ROLE_TABLE
-- ----------------------------
DROP TABLE "WJTEST"."ROLE_TABLE";
CREATE TABLE "WJTEST"."ROLE_TABLE" (
  "ROLE_ID" NUMBER(32) NOT NULL ,
  "USER_ID" NUMBER(32) ,
  "SPECIES_ID" NUMBER(32) ,
  "SUMMONING_ID" NUMBER(32) ,
  "GANG_ID" NUMBER(32) DEFAULT 0                         ,
  "MOUNT_ID" NUMBER(8) ,
  "TROOP_ID" NUMBER(32) ,
  "RACE_ID" NUMBER(32) ,
  "SKILL_ID" NUMBER(32) ,
  "BOOTH_ID" NUMBER(32) ,
  "TASK_ID" NUMBER(32) ,
  "HP" NUMBER(32,2) ,
  "MP" NUMBER(32,2) ,
  "GOLD" NUMBER(20) DEFAULT 0                         ,
  "CODECARD" NUMBER(38) DEFAULT 0                         ,
  "EXPERIENCE" NUMBER(20) DEFAULT 0                         ,
  "GRADE" NUMBER(8) DEFAULT 0                         ,
  "PRESTIGE" NUMBER(20) DEFAULT 0                         ,
  "PKRECORD" NUMBER(20) DEFAULT 0                         ,
  "ROLENAME" VARCHAR2(21 BYTE) ,
  "TITLE" VARCHAR2(100 BYTE) DEFAULT NULL ,
  "PATH" VARCHAR2(1000 BYTE) ,
  "SEX" VARCHAR2(20 BYTE) ,
  "LOCALNAME" VARCHAR2(20 BYTE) ,
  "SKILL" VARCHAR2(20 BYTE) ,
  "X" NUMBER(10) DEFAULT 700                         ,
  "Y" NUMBER(10) DEFAULT 500                         ,
  "MAPID" NUMBER(10) DEFAULT 1208                         ,
  "UPTIME" VARCHAR2(50 BYTE) ,
  "GANGPOST" VARCHAR2(10 BYTE) ,
  "ACHIEVEMENT" NUMBER(32) DEFAULT 0                         ,
  "CONTRIBUTION" NUMBER(32) DEFAULT 0                         ,
  "BONE" NUMBER(8) DEFAULT 0                         ,
  "SPIR" NUMBER(8) DEFAULT 0                         ,
  "POWER" NUMBER(8) DEFAULT 0                         ,
  "SPEED" NUMBER(8) DEFAULT 0                         ,
  "CANPOINT" NUMBER(8) DEFAULT 0                         ,
  "CAPTAIN" NUMBER(8) ,
  "SAVEGOLD" NUMBER(20) DEFAULT 0                         ,
  "PASSWORD" VARCHAR2(50 BYTE) ,
  "GANGNAME" VARCHAR2(50 BYTE) ,
  "HAVEBABY" NUMBER(8) ,
  "NEWROLE" NUMBER(8) DEFAULT 0                         ,
  "RACENAME" VARCHAR2(100 BYTE) ,
  "MAXEXP" NUMBER(20) ,
  "MARRYOBJECT" VARCHAR2(2000 BYTE) ,
  "SKILLS" VARCHAR2(2000 BYTE) ,
  "TIMINGGOOD" VARCHAR2(2000 BYTE) ,
  "TURNAROUND" NUMBER(8) DEFAULT 0                         ,
  "TASKDAILY" VARCHAR2(2000 BYTE) DEFAULT '0|0|0|0'                         ,
  "BORN" VARCHAR2(2000 BYTE) ,
  "RESISTANCE" VARCHAR2(2000 BYTE) DEFAULT '主-|副-'                         ,
  "SERVERMESTRING" VARCHAR2(20 BYTE) ,
  "TASKRECEIVE" VARCHAR2(2000 BYTE) ,
  "TASKCOMPLETE" VARCHAR2(2000 BYTE) ,
  "TASKDATA" VARCHAR2(2000 BYTE) ,
  "FIGHTING" NUMBER(8) DEFAULT 0                         ,
  "DBEXP" NUMBER(8) DEFAULT 0                         ,
  "SCORE" VARCHAR2(2000 BYTE) ,
  "KILL" VARCHAR2(2000 BYTE) ,
  "BABYID" VARCHAR2(32 BYTE) ,
  "DRAWING" DATE ,
  "CALM" NUMBER(8) DEFAULT 0                         ,
  "XIUWEI" NUMBER(8) DEFAULT 0                         ,
  "EXTRAPOINT" VARCHAR2(100 BYTE) ,
  "PAYSUM" NUMBER(32) DEFAULT 0                         ,
  "DAYPAYSUM" NUMBER(32) DEFAULT 0                         ,
  "DAYANDPAYORNO" NUMBER(32) DEFAULT 0                         ,
  "DAYFIRSTINORNO" NUMBER(32) DEFAULT 0                         ,
  "DAYGETORNO" NUMBER(32) DEFAULT 0                         ,
  "VIPGET" VARCHAR2(255 BYTE) ,
  "LOWORHIHTPACK" NUMBER(32) ,
  "PALS" VARCHAR2(255 BYTE) ,
  "FMSLD" NUMBER(8) DEFAULT 0                         ,
  "ATTACHPACK" NUMBER(1) DEFAULT 0                         ,
  "MERIDIANS" VARCHAR2(2000 BYTE) ,
  "HJMAX" VARCHAR2(2000 BYTE) ,
  "QIAN_DAO" VARCHAR2(2000 BYTE) ,
  "ONLINE_TIME" VARCHAR2(2000 BYTE) ,
  "XINGPANS" VARCHAR2(2000 BYTE) ,
  "TTVICTORY" NUMBER(8) DEFAULT 0                         ,
  "TTFAIL" NUMBER(8) DEFAULT 0                         ,
  "TTRECORD" NUMBER(8) DEFAULT 0                         ,
  "TTJIANGLI" VARCHAR2(255 BYTE) ,
  "GMSHOPTYPE" VARCHAR2(1000 BYTE) ,
  "GRADEINCREASE" NUMBER(8) DEFAULT 0                         ,
  "CURRENTATTRIBUTE" NUMBER(8) ,
  "GAMETIMEREMAINING" NUMBER(8) DEFAULT 6000                         ,
  "EXT_POINT" NUMBER(8) DEFAULT 0       ,
  "EQUIPMENTS" VARCHAR2(2000 BYTE) ,
  "LIANGHAO" VARCHAR2(64 BYTE) ,
  "LIANGHAOTYPE" NUMBER(32) DEFAULT 0                  ,
  "LIANGHAOVALUE" VARCHAR2(2000 BYTE) ,
  "LIANGHAOEXPIRE" VARCHAR2(64 BYTE) ,
  "CONTINUEPRICE" NUMBER DEFAULT 0           ,
  "BORN1" VARCHAR2(2000 BYTE) ,
  "TRANSFERGOLD" NUMBER(38) ,
  "ZHONGTIAN" VARCHAR2(255 BYTE) ,
  "QINGLONG" VARCHAR2(255 BYTE) ,
  "ZHUQUE" VARCHAR2(255 BYTE) ,
  "XUANWU" VARCHAR2(255 BYTE) ,
  "BAIHU" VARCHAR2(255 BYTE) ,
  "JIESUO" VARCHAR2(255 BYTE) ,
  "SHOUHU" NUMBER ,
  "SH" VARCHAR2(255 BYTE) ,
  "FLY_ID" NUMBER(11) ,
  "PRESTIGE1" NUMBER(20) DEFAULT 0                          ,
  "PRESTIGE2" NUMBER(20) DEFAULT 0                          ,
  "PRESTIGE3" NUMBER(20) DEFAULT 0                          ,
  "PRESTIGE4" NUMBER(20) DEFAULT 0                     
 
    ,
  "MONEYSHOP" NUMBER(38) DEFAULT 0  
 
  ,
  "DAYDRAW" VARCHAR2(255 BYTE) ,
  "ACHIEVERECORD" VARCHAR2(4000 BYTE) ,
  "ZUIER" VARCHAR2(255 BYTE) ,
  "GROWTHFUND" VARCHAR2(255 BYTE) ,
  "DIFFICULTRECORD" VARCHAR2(255 BYTE) ,
  "DIFFICULTLEVEL" VARCHAR2(255 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."DBEXP" IS '记录已经使用的双倍时间精确到秒';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."SCORE" IS '记录积分';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."KILL" IS '击杀记录';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."BABYID" IS '宝宝id';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."DRAWING" IS '抽奖时间';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."CALM" IS '定力';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."XIUWEI" IS '修为点';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."EXTRAPOINT" IS '额外属性点';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."FMSLD" IS '法门熟练度';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."ATTACHPACK" IS '附加背包';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."MERIDIANS" IS '经脉';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."HJMAX" IS '幻境式练';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."QIAN_DAO" IS '签到';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."XINGPANS" IS '星盘';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."GRADEINCREASE" IS '最大等级增长丹';
COMMENT ON COLUMN "WJTEST"."ROLE_TABLE"."MONEYSHOP" IS '钱庄';

-- ----------------------------
-- Records of ROLE_TABLE
-- ----------------------------
INSERT INTO "WJTEST"."ROLE_TABLE" VALUES ('1000001', '1001', '20001', '2001', '0', NULL, NULL, '10001', NULL, NULL, NULL, '0', '0', '2000000', '0', '34325', '24', '0', '0', '尤谆私', '御武盟小虾米', NULL, '男', '逍遥生', NULL, '460', '1740', '1208', '1746703714341', NULL, '0', '0', '24', '24', '24', '24', '0', NULL, '0', NULL, NULL, NULL, '0', '人族', NULL, NULL, NULL, NULL, '0', '0|0|0|0', NULL, '主-|副-', '1001', NULL, NULL, NULL, '0', NULL, '功绩=15', NULL, NULL, NULL, '0', '0', NULL, '0', '0', '0', '0', '2', NULL, '0', NULL, '0', '0', NULL, '0', NULL, NULL, NULL, '0', '0', '0', '0|0|0|0|0|0', NULL, '0', NULL, '6000', '0', NULL, NULL, '0', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '1-0-0|2-0-0|3-0-0|4-0-0', NULL, '0', '0', '0', '0', '0', NULL, '第一次穿装备=1|第一次升级=1|等级20=1', '0', NULL, NULL, '0');

-- ----------------------------
-- Table structure for ROLR_FLY
-- ----------------------------
DROP TABLE "WJTEST"."ROLR_FLY";
CREATE TABLE "WJTEST"."ROLR_FLY" (
  "flyId" NUMBER(11) ,
  "flyName" VARCHAR2(255 BYTE) ,
  "flyLevel" VARCHAR2(255 BYTE) ,
  "ROLE_ID" NUMBER(11) ,
  "moveLevel" NUMBER(11) ,
  "fuel" NUMBER(11) DEFAULT 0                         ,
  "skin" VARCHAR2(255 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."ROLR_FLY"."flyId" IS '飞行器ID';
COMMENT ON COLUMN "WJTEST"."ROLR_FLY"."flyName" IS '飞行器名称';
COMMENT ON COLUMN "WJTEST"."ROLR_FLY"."flyLevel" IS '飞行器等级';
COMMENT ON COLUMN "WJTEST"."ROLR_FLY"."ROLE_ID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."ROLR_FLY"."moveLevel" IS '移动等级';
COMMENT ON COLUMN "WJTEST"."ROLR_FLY"."fuel" IS '燃料';
COMMENT ON COLUMN "WJTEST"."ROLR_FLY"."skin" IS '皮肤';

-- ----------------------------
-- Table structure for RUFENGHAOCONTROL
-- ----------------------------
DROP TABLE "WJTEST"."RUFENGHAOCONTROL";
CREATE TABLE "WJTEST"."RUFENGHAOCONTROL" (
  "ID" NUMBER ,
  "USERNAME" VARCHAR2(100 BYTE) ,
  "ROLENAME" VARCHAR2(100 BYTE) ,
  "REASON" VARCHAR2(1000 BYTE) ,
  "TYPE" NUMBER ,
  "REGISTERIP" VARCHAR2(100 BYTE) ,
  "LASSTLOGINIP" VARCHAR2(100 BYTE) ,
  "CONTROLOBJECT" VARCHAR2(100 BYTE) ,
  "DAILIID" NUMBER ,
  "TOTALSUM" NUMBER ,
  "DATETIME" VARCHAR2(11 BYTE) ,
  "QUID" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."ID" IS '表id';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."USERNAME" IS '用户名';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."ROLENAME" IS '角色名';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."REASON" IS '原因';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."TYPE" IS '封号状态，1表示封号，2表示解封';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."REGISTERIP" IS '账号注册ip';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."LASSTLOGINIP" IS '最后登录ip';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."CONTROLOBJECT" IS '操作对象';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."DAILIID" IS '代理编号';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."TOTALSUM" IS '总充值积分';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."DATETIME" IS '时间';
COMMENT ON COLUMN "WJTEST"."RUFENGHAOCONTROL"."QUID" IS '区域id';

-- ----------------------------
-- Table structure for RUNE
-- ----------------------------
DROP TABLE "WJTEST"."RUNE";
CREATE TABLE "WJTEST"."RUNE" (
  "RUNEID" NUMBER(8) ,
  "RUNENAME" VARCHAR2(50 BYTE) ,
  "RUNESKIN" NUMBER(8) ,
  "RUNETYPE" NUMBER(8) ,
  "RUNEQUALITY" NUMBER(4) ,
  "RUNEVALUE" VARCHAR2(100 BYTE) ,
  "RUNETEXT" VARCHAR2(1000 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."RUNE"."RUNEID" IS '符文id';
COMMENT ON COLUMN "WJTEST"."RUNE"."RUNENAME" IS '符文名称';
COMMENT ON COLUMN "WJTEST"."RUNE"."RUNESKIN" IS '符文皮肤';
COMMENT ON COLUMN "WJTEST"."RUNE"."RUNETYPE" IS '符文类型';
COMMENT ON COLUMN "WJTEST"."RUNE"."RUNEQUALITY" IS '符文贵重';
COMMENT ON COLUMN "WJTEST"."RUNE"."RUNEVALUE" IS '符文值';
COMMENT ON COLUMN "WJTEST"."RUNE"."RUNETEXT" IS '符文说明';

-- ----------------------------
-- Table structure for SALEGOODS
-- ----------------------------
DROP TABLE "WJTEST"."SALEGOODS";
CREATE TABLE "WJTEST"."SALEGOODS" (
  "SALEID" NUMBER(32) ,
  "SALENAME" VARCHAR2(1000 BYTE) ,
  "SALETYPE" NUMBER(8) ,
  "OTHERID" NUMBER(32) ,
  "CONTIONTYPE" VARCHAR2(200 BYTE) ,
  "FLAG" NUMBER(8) ,
  "UPTIME" TIMESTAMP(6) ,
  "ROLEID" NUMBER(32) ,
  "BUYROLE" NUMBER(32) ,
  "SALEPRICE" NUMBER(32) ,
  "SALESKIN" VARCHAR2(500 BYTE) ,
  "BUY_ROLE_IDS" NUMBER(32) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."SALEID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."SALENAME" IS '商品名称';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."SALETYPE" IS '类型';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."OTHERID" IS '对应数据库表ID';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."CONTIONTYPE" IS '条件分类';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."FLAG" IS '上下架标识';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."UPTIME" IS '上架时间';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."ROLEID" IS '上架角色ID';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."BUYROLE" IS '绑定买家ID';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."SALEPRICE" IS '价格';
COMMENT ON COLUMN "WJTEST"."SALEGOODS"."SALESKIN" IS '皮肤';

-- ----------------------------
-- Table structure for SALEGOODS2
-- ----------------------------
DROP TABLE "WJTEST"."SALEGOODS2";
CREATE TABLE "WJTEST"."SALEGOODS2" (
  "SALEID" NUMBER(32) ,
  "SALENAME" VARCHAR2(1000 BYTE) ,
  "SALETYPE" NUMBER(8) ,
  "OTHERID" NUMBER(32) ,
  "CONTIONTYPE" VARCHAR2(200 BYTE) ,
  "FLAG" NUMBER(8) ,
  "UPTIME" TIMESTAMP(6) ,
  "ROLEID" NUMBER(32) ,
  "BUYROLE" NUMBER(32) ,
  "SALEPRICE" NUMBER(32) ,
  "SALESKIN" VARCHAR2(500 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."SALEID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."SALENAME" IS '商品名称';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."SALETYPE" IS '类型';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."OTHERID" IS '对应数据库表ID';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."CONTIONTYPE" IS '条件分类';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."FLAG" IS '上下架标识';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."UPTIME" IS '上架时间';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."ROLEID" IS '上架角色ID';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."BUYROLE" IS '绑定买家ID';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."SALEPRICE" IS '价格';
COMMENT ON COLUMN "WJTEST"."SALEGOODS2"."SALESKIN" IS '皮肤';

-- ----------------------------
-- Table structure for SELL_LIANGHAO_AUC
-- ----------------------------
DROP TABLE "WJTEST"."SELL_LIANGHAO_AUC";
CREATE TABLE "WJTEST"."SELL_LIANGHAO_AUC" (
  "ID" NUMBER(32) NOT NULL ,
  "BUY_ROLE_ID" NUMBER(32) ,
  "AUC_POINT" NUMBER(32) ,
  "LIANGHAO" VARCHAR2(256 BYTE) ,
  "BUY_TIME" VARCHAR2(256 BYTE) ,
  "AUC_END_TIME" VARCHAR2(256 BYTE) ,
  "STATUS" NUMBER(2) ,
  "ORIGINALPRICE" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."SELL_LIANGHAO_AUC"."AUC_POINT" IS '竟价';
COMMENT ON COLUMN "WJTEST"."SELL_LIANGHAO_AUC"."LIANGHAO" IS '总价';
COMMENT ON COLUMN "WJTEST"."SELL_LIANGHAO_AUC"."BUY_TIME" IS '竞价时间';
COMMENT ON COLUMN "WJTEST"."SELL_LIANGHAO_AUC"."AUC_END_TIME" IS '购买时间';
COMMENT ON COLUMN "WJTEST"."SELL_LIANGHAO_AUC"."STATUS" IS '状态 1 未结束 正常进行  2 已经结束 竞价成功 3 已经结束 竞价失败 已退款 4 未结束 自行取消 已退款 5 竞价结束 失败 未退款';

-- ----------------------------
-- Records of SELL_LIANGHAO_AUC
-- ----------------------------
INSERT INTO "WJTEST"."SELL_LIANGHAO_AUC" VALUES ('1', '1000001', '50000', '111111', '2024-12-26', '2024-12-30', '1', '68');
INSERT INTO "WJTEST"."SELL_LIANGHAO_AUC" VALUES ('21', '1000404', '69', '666666', '2024-11-07', '2024-12-30', '1', '68');
INSERT INTO "WJTEST"."SELL_LIANGHAO_AUC" VALUES ('2', '1000193', '44972', '111111', '2024-10-18', '2024-12-30', '3', '68');

-- ----------------------------
-- Table structure for SELL_XIAN_YU_ORDER
-- ----------------------------
DROP TABLE "WJTEST"."SELL_XIAN_YU_ORDER";
CREATE TABLE "WJTEST"."SELL_XIAN_YU_ORDER" (
  "ID" NUMBER(32) NOT NULL ,
  "BUY_ROLE_ID" NUMBER(32) ,
  "SELL_ROLE_ID" NUMBER(32) ,
  "XIAN_YU_POINT" NUMBER(38) ,
  "PRICE_POINT" NUMBER(38) DEFAULT 0                          ,
  "TOTAL_PRICE" NUMBER(38) ,
  "BUY_TIME" VARCHAR2(1000 BYTE) ,
  "ADD_SELL_TIME" VARCHAR2(1000 BYTE) ,
  "DEPOSIT" NUMBER(38) ,
  "SELL_ID" NUMBER(32) ,
  "ORDER_STATUS" NUMBER(2) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."SELL_XIAN_YU_ORDER"."XIAN_YU_POINT" IS '仙玉数';
COMMENT ON COLUMN "WJTEST"."SELL_XIAN_YU_ORDER"."PRICE_POINT" IS '每点售价';
COMMENT ON COLUMN "WJTEST"."SELL_XIAN_YU_ORDER"."TOTAL_PRICE" IS '总价';
COMMENT ON COLUMN "WJTEST"."SELL_XIAN_YU_ORDER"."BUY_TIME" IS '购买时间';
COMMENT ON COLUMN "WJTEST"."SELL_XIAN_YU_ORDER"."ADD_SELL_TIME" IS '上架时间';
COMMENT ON COLUMN "WJTEST"."SELL_XIAN_YU_ORDER"."DEPOSIT" IS '押金';
COMMENT ON COLUMN "WJTEST"."SELL_XIAN_YU_ORDER"."SELL_ID" IS '售卖记录ID';

-- ----------------------------
-- Records of SELL_XIAN_YU_ORDER
-- ----------------------------
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('21', '1000444', '1000448', '1000000', '1', '1000000', NULL, NULL, NULL, '2001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('22', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('23', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('24', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('25', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('26', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('27', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('28', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('29', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('30', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('31', '1000301', '1000297', '9999', '1', '9999', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('32', '1000301', '1000297', '10', '1', '10', NULL, NULL, NULL, '4001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('41', '1000528', '1000515', '1', '100000', '100000', NULL, NULL, '5000', '3001', '1');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('2', '1000301', '1000297', '11', '1', '11', NULL, NULL, NULL, '2001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('61', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('62', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('63', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('64', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('65', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('66', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('67', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('68', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('69', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('70', '1000540', '1000444', '9999', '10', '99990', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('71', '1000540', '1000444', '10', '10', '100', NULL, NULL, NULL, '5001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('72', '1000539', '1000540', '2222', '222', '493284', NULL, NULL, NULL, '6001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('73', '1000536', '1000540', '1111', '222', '246642', NULL, NULL, NULL, '6001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('74', '1000538', '1000540', '1111', '222', '246642', NULL, NULL, NULL, '6001', '2');
INSERT INTO "WJTEST"."SELL_XIAN_YU_ORDER" VALUES ('75', '1000537', '1000540', '1111', '222', '246642', NULL, NULL, NULL, '6001', '2');

-- ----------------------------
-- Table structure for SERVICEAREA
-- ----------------------------
DROP TABLE "WJTEST"."SERVICEAREA";
CREATE TABLE "WJTEST"."SERVICEAREA" (
  "SID" NUMBER ,
  "Sname" VARCHAR2(50 BYTE) ,
  "SDATE" DATE ,
  "AGENTS" VARCHAR2(50 BYTE) ,
  "DIVIDEDINTO" VARCHAR2(50 BYTE) ,
  "MANAEID" NUMBER(32) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."SERVICEAREA"."SID" IS '服务区ID';
COMMENT ON COLUMN "WJTEST"."SERVICEAREA"."Sname" IS '服务区名称';
COMMENT ON COLUMN "WJTEST"."SERVICEAREA"."SDATE" IS '创建时间';
COMMENT ON COLUMN "WJTEST"."SERVICEAREA"."AGENTS" IS '代理者';
COMMENT ON COLUMN "WJTEST"."SERVICEAREA"."DIVIDEDINTO" IS '分成比例';
COMMENT ON COLUMN "WJTEST"."SERVICEAREA"."MANAEID" IS '管理员Id';
COMMENT ON TABLE "WJTEST"."SERVICEAREA" IS '服务区表';

-- ----------------------------
-- Table structure for SHANGCHENGSHOP
-- ----------------------------
DROP TABLE "WJTEST"."SHANGCHENGSHOP";
CREATE TABLE "WJTEST"."SHANGCHENGSHOP" (
  "GID" NUMBER ,
  "GOODSNAME" VARCHAR2(100 BYTE) ,
  "GOODTYPE" NUMBER ,
  "GOODSPRICE" NUMBER ,
  "SKIN" VARCHAR2(20 BYTE) ,
  "TEXT" VARCHAR2(100 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."SHANGCHENGSHOP"."GID" IS '物品id';
COMMENT ON COLUMN "WJTEST"."SHANGCHENGSHOP"."GOODSNAME" IS '物品名称';
COMMENT ON COLUMN "WJTEST"."SHANGCHENGSHOP"."GOODTYPE" IS '物品类型';
COMMENT ON COLUMN "WJTEST"."SHANGCHENGSHOP"."GOODSPRICE" IS '物品销售价格';
COMMENT ON COLUMN "WJTEST"."SHANGCHENGSHOP"."SKIN" IS '皮肤';
COMMENT ON COLUMN "WJTEST"."SHANGCHENGSHOP"."TEXT" IS '物品说明';

-- ----------------------------
-- Table structure for SPECIES
-- ----------------------------
DROP TABLE "WJTEST"."SPECIES";
CREATE TABLE "WJTEST"."SPECIES" (
  "SPECIES_ID" NUMBER(32) ,
  "RACE_ID" NUMBER(32) ,
  "SEX" VARCHAR2(4 BYTE) ,
  "LOCALNAME" VARCHAR2(10 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of SPECIES
-- ----------------------------
INSERT INTO "WJTEST"."SPECIES" VALUES ('24001', '10005', '男', '沧浪君');
INSERT INTO "WJTEST"."SPECIES" VALUES ('24002', '10005', '男', '龙渊客');
INSERT INTO "WJTEST"."SPECIES" VALUES ('24003', '10005', '男', '忘忧子');
INSERT INTO "WJTEST"."SPECIES" VALUES ('24004', '10005', '女', '骊珠儿');
INSERT INTO "WJTEST"."SPECIES" VALUES ('24005', '10005', '女', '木兰行');
INSERT INTO "WJTEST"."SPECIES" VALUES ('24006', '10005', '女', '莫解语');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20001', '10001', '男', '逍遥生');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20002', '10001', '男', '剑侠客');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20003', '10001', '男', '猛壮士');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20004', '10001', '女', '飞燕女');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20005', '10001', '女', '英女侠');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20006', '10001', '女', '俏千金');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21001', '10002', '男', '虎头怪');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21002', '10002', '男', '夺命妖');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21003', '10002', '男', '巨魔王');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21004', '10002', '女', '小蛮妖');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21005', '10002', '女', '骨精灵');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21006', '10002', '女', '狐美人');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22001', '10003', '男', '神天兵');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22002', '10003', '男', '智圣仙');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22003', '10003', '男', '龙战将');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22004', '10003', '女', '精灵仙');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22005', '10003', '女', '舞天姬');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22006', '10003', '女', '玄剑娥');
INSERT INTO "WJTEST"."SPECIES" VALUES ('23001', '10004', '男', '祭剑魂');
INSERT INTO "WJTEST"."SPECIES" VALUES ('23002', '10004', '男', '猎魂引');
INSERT INTO "WJTEST"."SPECIES" VALUES ('23003', '10004', '男', '无崖子');
INSERT INTO "WJTEST"."SPECIES" VALUES ('23004', '10004', '女', '墨衣行');
INSERT INTO "WJTEST"."SPECIES" VALUES ('23005', '10004', '女', '夜溪灵');
INSERT INTO "WJTEST"."SPECIES" VALUES ('23006', '10004', '女', '幽梦影');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20007', '10001', '男', '飞剑侠');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20008', '10001', '女', '燕山雪');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20009', '10001', '男', '纯阳子');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20010', '10001', '女', '红拂女');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21007', '10002', '男', '逆天魔');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21008', '10002', '女', '媚灵狐');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21009', '10002', '男', '混天魔');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21010', '10002', '女', '九尾狐');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22007', '10003', '男', '武尊神');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22008', '10003', '女', '玄天姬');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22009', '10003', '男', '紫薇神');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22010', '10003', '女', '霓裳仙');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20011', '10001', '男', '神秀生');
INSERT INTO "WJTEST"."SPECIES" VALUES ('20012', '10001', '女', '玲珑女');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21011', '10002', '男', '绝影魔');
INSERT INTO "WJTEST"."SPECIES" VALUES ('21012', '10002', '女', '霜月灵');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22011', '10003', '男', '青阳使');
INSERT INTO "WJTEST"."SPECIES" VALUES ('22012', '10003', '女', '云中君');
INSERT INTO "WJTEST"."SPECIES" VALUES ('23007', '10004', '男', '南冠客');
INSERT INTO "WJTEST"."SPECIES" VALUES ('23008', '10004', '女', '镜花影');
INSERT INTO "WJTEST"."SPECIES" VALUES ('24007', '10005', '男', '游无极');
INSERT INTO "WJTEST"."SPECIES" VALUES ('24008', '10005', '女', '临九渊');

-- ----------------------------
-- Table structure for SUMMONING_SKILL
-- ----------------------------
DROP TABLE "WJTEST"."SUMMONING_SKILL";
CREATE TABLE "WJTEST"."SUMMONING_SKILL" (
  "SKILLID" NUMBER(8) ,
  "SKILLNAME" VARCHAR2(50 BYTE) ,
  "SKILLTYPE" NUMBER(8) ,
  "SKILLLEVEL" NUMBER(8) ,
  "GROW" BINARY_DOUBLE ,
  "DIELECTRIC" NUMBER(8) ,
  "VALUE" BINARY_DOUBLE ,
  "CAMP" NUMBER(8) ,
  "SKILLRALATION" VARCHAR2(500 BYTE) ,
  "REMARK" VARCHAR2(1000 BYTE) ,
  "SKIID" NUMBER(32) ,
  "SUMMONINGID" NUMBER(32) ,
  "SKILLED" NUMBER(8) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for TITLETABLE
-- ----------------------------
DROP TABLE "WJTEST"."TITLETABLE";
CREATE TABLE "WJTEST"."TITLETABLE" (
  "TITLEID" NUMBER(32) ,
  "ROLEID" NUMBER(32) ,
  "TITLENAME" VARCHAR2(50 BYTE) ,
  "RECORDTIME" DATE ,
  "LIMITTIME" NUMBER(8) DEFAULT -1 
 
 
 

 
 
 
 
 
 
 
 
 
 
 
 
 


 
 
 
 
 
 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."TITLETABLE"."TITLEID" IS '表ID';
COMMENT ON COLUMN "WJTEST"."TITLETABLE"."ROLEID" IS '角色ID';
COMMENT ON COLUMN "WJTEST"."TITLETABLE"."TITLENAME" IS '称谓名称';

-- ----------------------------
-- Table structure for TTMODEL
-- ----------------------------
DROP TABLE "WJTEST"."TTMODEL";
CREATE TABLE "WJTEST"."TTMODEL" (
  "STARTHOUR" NUMBER ,
  "ENDHOUR" NUMBER ,
  "STARTMINUTE" NUMBER ,
  "ENDMINUTE" NUMBER ,
  "SEASONSTARTTIME" DATE ,
  "SEASONENDTIME" DATE ,
  "CURRENTSEASON" NUMBER ,
  "ISOPEN" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of TTMODEL
-- ----------------------------
INSERT INTO "WJTEST"."TTMODEL" VALUES ('21', '0', '0', '0', TO_DATE('2023-09-30 12:00:00', 'SYYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-10-31 13:00:00', 'SYYYY-MM-DD HH24:MI:SS'), '1', '0');

-- ----------------------------
-- Table structure for USERTABLE
-- ----------------------------
DROP TABLE "WJTEST"."USERTABLE";
CREATE TABLE "WJTEST"."USERTABLE" (
  "USER_ID" NUMBER(32) ,
  "USERNAME" VARCHAR2(200 BYTE) ,
  "USERPWD" VARCHAR2(200 BYTE) ,
  "ACTIVITY" NUMBER(2) DEFAULT 0                         ,
  "VIP" NUMBER(10) DEFAULT 0                         ,
  "FRIENT_ID" NUMBER(32) ,
  "SAFETY" VARCHAR2(200 BYTE) ,
  "IDCARD" NUMBER(18) ,
  "CODECARD" NUMBER(32) DEFAULT 0                    ,
  "MONEY" VARCHAR2(8 BYTE) DEFAULT 0                         ,
  "QID" NUMBER(8) ,
  "USERMONEY" FLOAT(126) DEFAULT 0.00                         ,
  "USERLASTLOGIN" VARCHAR2(255 BYTE) ,
  "PHONENUMBER" VARCHAR2(255 BYTE) ,
  "PHONETIME" VARCHAR2(255 BYTE) ,
  "LOGINIP" VARCHAR2(255 BYTE) ,
  "REGISTERIP" VARCHAR2(255 BYTE) ,
  "FLAG" VARCHAR2(255 BYTE) ,
  "USERREGIDTSERTIME" VARCHAR2(255 BYTE) ,
  "PAYINTEGRATION" NUMBER(32) ,
  "TYPE" NUMBER(32) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
ENABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."USERTABLE"."CODECARD" IS '点卡';
COMMENT ON COLUMN "WJTEST"."USERTABLE"."MONEY" IS '充值总金额';
COMMENT ON COLUMN "WJTEST"."USERTABLE"."QID" IS '区域ID';
COMMENT ON COLUMN "WJTEST"."USERTABLE"."USERMONEY" IS '藏宝阁金钱';

-- ----------------------------
-- Records of USERTABLE
-- ----------------------------
INSERT INTO "WJTEST"."USERTABLE" VALUES ('1001', '121245QQQ', '121245QQQ', '0', '0', NULL, '121245QQQ', NULL, '0', '0', '100', '0.0000000000000000', NULL, NULL, NULL, NULL, '120.219.77.215', NULL, '2025-05-08 19:22:36', NULL, '0');

-- ----------------------------
-- Table structure for USERXYANDROLEDHBCR
-- ----------------------------
DROP TABLE "WJTEST"."USERXYANDROLEDHBCR";
CREATE TABLE "WJTEST"."USERXYANDROLEDHBCR" (
  "ID" NUMBER ,
  "USERID" NUMBER ,
  "USERNAME" VARCHAR2(100 BYTE) ,
  "ROLEID" NUMBER ,
  "ROLENAME" VARCHAR2(100 BYTE) ,
  "TYPE" NUMBER ,
  "XSUM" NUMBER ,
  "XDSUM" NUMBER ,
  "DSUM" NUMBER ,
  "SSSUM" NUMBER ,
  "TIME" VARCHAR2(100 BYTE) ,
  "SID" NUMBER ,
  "XNOW" NUMBER 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."ID" IS '表id';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."USERID" IS '用户id';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."USERNAME" IS '用户名称';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."ROLEID" IS '角色id';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."ROLENAME" IS '角色名称';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."TYPE" IS '类型名称仙玉/大话币/积分';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."XSUM" IS '仙玉总';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."XDSUM" IS '仙玉消耗';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."DSUM" IS '大话币总';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."SSSUM" IS '大话币消耗';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."TIME" IS '记录时间';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."SID" IS '区域id(当前区域id为空)';
COMMENT ON COLUMN "WJTEST"."USERXYANDROLEDHBCR"."XNOW" IS '当前仙玉';

-- ----------------------------
-- Table structure for WECHATRECORD
-- ----------------------------
DROP TABLE "WJTEST"."WECHATRECORD";
CREATE TABLE "WJTEST"."WECHATRECORD" (
  "CHAT_ID" NUMBER(32) ,
  "CHAT_MES" VARCHAR2(4000 BYTE) ,
  "CHAT_SENDID" NUMBER(32) ,
  "CHAT_GETID" NUMBER(32) ,
  "TIME" VARCHAR2(1000 BYTE) 
)
TABLESPACE "USER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- View structure for FRIENDTABLE
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."FRIENDTABLE" AS select f.fid, f.roleid,r.role_id,r.species_id, r.rolename,g.race_name,r.grade from role_table r,species s,game_race g,friend f
where f.friendid=r.role_id and r.species_id = s.species_id and s.race_id=g.race_id;

-- ----------------------------
-- View structure for GANGAPPLYTABLE
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."GANGAPPLYTABLE" AS select ga.gaid, r.role_id,r.rolename,g.race_name,r.grade,ga.gangid from gangapply ga,role_table r,game_race g
where ga.roleid = r.role_id and r.race_id = g.race_id;

-- ----------------------------
-- View structure for GOODSRECORD_VIEW
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."GOODSRECORD_VIEW" AS select g."GRID",g."RECORDTYPE",g."ROLEID",g."OTHERROLE",g."GOODS",g."RECORDTIME",g."GOODSNUM",(select rolename from role_table where role_id = g.roleid) rolename,(select rolename from role_table where role_id = g.otherrole) othername from goodsrecord g;

-- ----------------------------
-- View structure for GOODSTABLE_ROLE_USERTABLE
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."GOODSTABLE_ROLE_USERTABLE" AS select g."GOODSID",g."GOODSNAME",g."SKIN",g."TYPE",g."QUALITY",g."VALUE",g."INSTRUCTION",g."RGID",g."ROLE_ID",g."STATUS",g."USETIME",g."DEFINEPRICE",g."MAPNAME",g."MAPX",g."MAPY",g."PRICE",g."CODECARD",g."GOODLOCK",t.rolename,t.user_id,t.username from(
  select r.role_id,r.rolename,u.user_id,u.username from role_table r,usertable u where r.user_id=u.user_id ) t,goodstable g where t.role_id=g.role_id;

-- ----------------------------
-- View structure for LINGBAO_ROLE_USERTABLE
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."LINGBAO_ROLE_USERTABLE" AS select g."BAOID",g."BAONAME",g."GETHARD",g."BAOTYPE",g."BAOACTIVE",g."BAOSPEED",g."BAOREPLY",g."BAOSHOT",g."BAOAP",g."RESISTSHOT",g."ASSISTANCE",g."GOODSKILL",g."ROLEID",g."SKIN",g."SKILLSUM",g."FUSUM",g."LINGBAOLVL",g."LINGBAOEXE",g."LINGBAOQIHE",g."SKILLS",g."KANGXING",g."EQUIPMENT",g."BAOQUALITY",g."TIANFUSKILL",g."FUSHIS",t.rolename,t.user_id,t.username from(
  select r.role_id,r.rolename,u.user_id,u.username from role_table r,usertable u where r.user_id=u.user_id ) t,lingbao g where t.role_id=g.ROLEID;

-- ----------------------------
-- View structure for LOGINTABLE
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."LOGINTABLE" AS SELECT
  r.bone,
  r.spir,
  r. POWER,
  r.speed,
  r.gangname,
  r. PASSWORD,
  r.savegold,
  r.GANGPOST,
  r.CONTRIBUTION,
  r.ACHIEVEMENT,
  r.newrole,
  r.fighting,
  r.taskcomplete,
  r.taskdata,
  r.taskreceive,
  r.servermestring,
  r.resistance,
  r.born,
	r.born1,
  r.taskDaily,
  r.uptime,
  r.timinggood,
  r.turnaround,
  r.marryobject,
  r.skills,
  r.captain,
  s.localname,
  s.sex,
  G .race_name,
  r.user_id,
  r.x,
  r.y,
  r.mapid,
  U .username,
  U .userpwd,
  U .activity,
  U .money,
  r.species_id,
  r.gang_id,
  r.booth_id,
  U .codecard,
  r.experience,
  r.gold,
  r.grade,
  r.hp,
		r."EQUIPMENTS",
  r.mount_id,
  r.mp,
  r.pkrecord,
  r.prestige, r.prestige1, r.prestige2, r.prestige3, r.prestige4,
  r.race_id,
  r.role_id,
  r.rolename,
  r.summoning_id,
  r.title,
  r.troop_id,
  r. PATH,
  r. KILL,
  r.DBEXP,
  r.SCORE,
  r.BABYID,
  r.skill_id,
  r.drawing,
  r.calm,
  r.xiuwei,
  r.fmsld,
  r.extraPoint,
  r.PAYSUM,
  r.DAYPAYSUM,
  r.DAYANDPAYORNO,
  r.DAYFIRSTINORNO,
  r.DAYGETORNO,
  r.VIPGET,
  r.LOWORHIHTPACK,
  r.PALS,
  r.meridians,
  r.ATTACHPACK,
  r.HJMAX,
  r.online_time,
  r.QIAN_DAO,
  r.xingpans,
  r.GMSHOPTYPE,
  r.TTVICTORY,
  r.TTFAIL,
  r.TTRECORD,
  r.TTJIANGLI,
	r.FLY_ID,
	r.LIANGHAOVALUE,
	r.zhongtian,
r.zhuque,
r.xuanwu,
r.baihu,
r.jiesuo,
r.shouhu,
r.qinglong,
r.sh,
r.GRADEINCREASE,r.CURRENTATTRIBUTE,r.gameTimeRemaining,r.ext_point,r.LIANGHAO,r.LIANGHAOTYPE,r.LIANGHAOEXPIRE,r.CONTINUEPRICE,r.TRANSFERGOLD,r.MONEYSHOP,r.DAYDRAW,r.ACHIEVERECORD,r.ZUIER,r.GROWTHFUND,r.DIFFICULTRECORD,r.DIFFICULTLEVEL
FROM
  role_table r,
  species s,
  usertable U,
  game_race G
WHERE
  U .user_id = r.user_id
AND r.species_id = s.species_id
AND s.race_id = G .race_id;

-- ----------------------------
-- View structure for MOUNT_ROLE_USERTABLE
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."MOUNT_ROLE_USERTABLE" AS SELECT
	G ."MID",
	G ."MOUNTID",
	G ."MOUNTNAME",
	G ."MOUNTLVL",
	G ."LIVE",
	G ."SPRI",
	G ."POWER",
	G ."BONE",
	G ."EXP",
	G ."ROLEID",
	G ."SID",
	G."SHOUHU",
	G ."OTHRERSID",
	G ."USENUMBER",
	G ."PROFICIENCY",
	G ."SID3",
	G ."SID4",
	G ."SID5",
	G."SH",
	G ."MOVEGRADE",
	T .rolename,
	T .user_id,
	T .username
FROM
	(
		SELECT
			r.role_id,
			r.rolename,
			U .user_id,
			U .username
		FROM
			role_table r,
			usertable U
		WHERE
			r.user_id = U .user_id
	) T,
	MOUNT G
WHERE
	T .role_id = G .ROLEID;

-- ----------------------------
-- View structure for ROLESUMMONING_ROLE_USERTABLE
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."ROLESUMMONING_ROLE_USERTABLE" AS SELECT
  G ."SUMMONINGID",
  G ."SUMMONINGSKIN",
  G ."STYE",
  G ."HP",
  G ."MP",
  G ."AP",
  G ."SP",
  G ."GROWLEVEL",
  G ."RESISTANCE",
  G ."SKILL",
  G ."GOLD",
  G ."WOOD",
  G ."SOIL",
  G ."WATER",
  G ."FIRE",
  G ."SUMMONINGNAME",
  G ."SID",
  G ."SSN",
  G ."ROLEID",
  G ."BONE",
  G ."SPIR",
  G ."POWER",
  G ."SPEED",
  G ."CALM",
  G ."GRADE",
  G ."EXP",
  G ."FAITHFUL",
  G ."FRIENDLINESS",
  G ."PRICE",
  G ."BASISHP",
  G ."BASISMP",
  G ."BASISAP",
  G ."BASISSP",
  G ."OPENSEAL",
  G ."INNERGOODS",
  G ."DRAGON",
  G ."EXTRAHP",
  G ."EXTRAMP",
  G ."EXTRAAP",
  G ."EXTRASP",
  G ."PETSKILLS",
  G ."TURNROUNT",
  G ."GRADEHP",
  G ."GRADEMP",
  G ."NEDANRESISTANCE",
  G ."REVEALNUM",
  G ."FLYUPNUM",
  G ."BEASTSKILLS",
  G ."FOURATTRIBUTES",
  G ."SKILLDATA",
  G ."LYK",
  G ."ZQK",
  G ."ALCHEMYNUM",
  G ."GROWUPDANNUM",
  G ."COLORSCHEME",
  G ."DRAC",
  G ."TRAINNUM",
  G ."PETLOCK",
  T .rolename,
  T .user_id,
  T .username,
  G ."czjjd"
FROM
  (
    SELECT
      r.role_id,
      r.rolename,
      U .user_id,
      U .username
    FROM
      role_table r,
      usertable U
    WHERE
      r.user_id = U .user_id
  ) T,
  role_summoning G
WHERE
  T .role_id = G .ROLEID;

-- ----------------------------
-- View structure for ROLE_USETER_HATER
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."ROLE_USETER_HATER" AS select m. ROLENAME,m.gold,m.grade,m.TURNAROUND,m.hp,m.mp, m.born,m.score,  m.USER_ID  ,m.password,m.  ROLE_ID  ,m.  UNKNOWN  ,u.activity,u.qid,u.username as localname,u.PAYINTEGRATION,u.USERREGIDTSERTIME,u.codecard
  from (
  select r.*,h.unknown from role_table r  left join haters h on r.ROLE_ID=h.roleid) m ,usertable  u  where m.USER_ID=u.user_id;

-- ----------------------------
-- View structure for SALE_SHOPPING_RECORD
-- ----------------------------
CREATE OR REPLACE VIEW "WJTEST"."SALE_SHOPPING_RECORD" AS SELECT sh.gid,sh.goodsname,sh.goodsprice,go.buysum,go.paysum,go.datetime FROM GOODSSALEDAYRECORD GO JOIN  SHANGCHENGSHOP SH ON GO.GID = SH.GID;

-- ----------------------------
-- Function structure for FIND_IN_SET
-- ----------------------------
CREATE OR REPLACE
FUNCTION "WJTEST"."FIND_IN_SET" AS
begin
select instr(','||arg2||',' , ','||arg1||',') into Result from dual;
return(Result);
end find_in_set;
/

-- ----------------------------
-- Function structure for SPLIT
-- ----------------------------
CREATE OR REPLACE
FUNCTION "WJTEST"."SPLIT" AS
BEGIN
LOOP
l_idx := INSTR (v_list, p_sep);
IF l_idx > 0
THEN
PIPE ROW (SUBSTR (v_list, 1, l_idx - 1));
v_list := SUBSTR (v_list, l_idx + LENGTH (p_sep));
ELSE
PIPE ROW (v_list);
EXIT;
END IF;
/

-- ----------------------------
-- Function structure for SPLITSTR
-- ----------------------------
CREATE OR REPLACE
FUNCTION "WJTEST"."SPLITSTR" AS
BEGIN
if str is NULL
then
t_internal :=0;
elsIF INSTR (str, inter) = 0
THEN
t_internal   := 0;
ELSE
SELECT sstr
INTO t_str
FROM (SELECT ROWNUM AS item, COLUMN_VALUE AS sstr
FROM table (split (str, '|')))
WHERE instr(sstr,inter) <> 0;
t_internal := to_number(substr(t_str,instr(t_str,'=')+1));
END IF;

RETURN t_internal;
END;
/

-- ----------------------------
-- Function structure for SPLITTASK
-- ----------------------------
CREATE OR REPLACE
FUNCTION "WJTEST"."SPLITTASK" AS
BEGIN
if str is NOT NULL AND INSTR (str, inter) <> 0 THEN
  lv_str:=str;
  lv_srtNum:=instr(lv_str,'|');
  while lv_srtNum<>0 or is_head loop
     if lv_srtNum<>0 THEN
       lv_value:=substr(lv_str,0,lv_srtNum-1);
     ELSE
       is_head:=FALSE;
       lv_value:=lv_str;
     END IF;
     if length(lv_value)>length(inter)+1 AND substr(lv_value,0,length(inter)+1)=CONCAT(inter,'-') THEN
        lv_valueNum:=0;
        while instr(lv_value,'-')<>0 loop
          lv_valueNum:=lv_valueNum+1;
          lv_value:=substr(lv_value,instr(lv_value,'-')+1,length(lv_value));
        end loop;
        if lv_valueNum=3 THEN
             t_internal :=to_number(lv_value);
             RETURN t_internal;
        END IF;
     END IF;
     if lv_srtNum<>0 THEN
       lv_str:=substr(lv_str,lv_srtNum+1,length(lv_str));
       lv_srtNum:=instr(lv_str,'|');
     END IF;
  end loop;
END IF;

RETURN t_internal;
END;
/

-- ----------------------------
-- Sequence structure for ADDRESS_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."ADDRESS_ID";
CREATE SEQUENCE "WJTEST"."ADDRESS_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for FUWUQIQU
-- ----------------------------
DROP SEQUENCE "WJTEST"."FUWUQIQU";
CREATE SEQUENCE "WJTEST"."FUWUQIQU" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for MANAGEID
-- ----------------------------
DROP SEQUENCE "WJTEST"."MANAGEID";
CREATE SEQUENCE "WJTEST"."MANAGEID" MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for PAY_INCREATEMENT
-- ----------------------------
DROP SEQUENCE "WJTEST"."PAY_INCREATEMENT";
CREATE SEQUENCE "WJTEST"."PAY_INCREATEMENT" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 NOCACHE;

-- ----------------------------
-- Sequence structure for SELL_LIANGHAO_AUC_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SELL_LIANGHAO_AUC_ID";
CREATE SEQUENCE "WJTEST"."SELL_LIANGHAO_AUC_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SELL_XIAN_YU_ORDER_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SELL_XIAN_YU_ORDER_ID";
CREATE SEQUENCE "WJTEST"."SELL_XIAN_YU_ORDER_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_AGENT_INFO
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_AGENT_INFO";
CREATE SEQUENCE "WJTEST"."SEQ_AGENT_INFO" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_ATABLE
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_ATABLE";
CREATE SEQUENCE "WJTEST"."SEQ_ATABLE" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_BABYBORN_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_BABYBORN_ID";
CREATE SEQUENCE "WJTEST"."SEQ_BABYBORN_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_BABY_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_BABY_ID";
CREATE SEQUENCE "WJTEST"."SEQ_BABY_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_BUYTYPE_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_BUYTYPE_ID";
CREATE SEQUENCE "WJTEST"."SEQ_BUYTYPE_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_CHONGJIPACK_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_CHONGJIPACK_ID";
CREATE SEQUENCE "WJTEST"."SEQ_CHONGJIPACK_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_COLLECTION_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_COLLECTION_ID";
CREATE SEQUENCE "WJTEST"."SEQ_COLLECTION_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_FRIEND_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_FRIEND_ID";
CREATE SEQUENCE "WJTEST"."SEQ_FRIEND_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_GANGAPPLY_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_GANGAPPLY_ID";
CREATE SEQUENCE "WJTEST"."SEQ_GANGAPPLY_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_GANG_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_GANG_ID";
CREATE SEQUENCE "WJTEST"."SEQ_GANG_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_GEN_TABLE
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_GEN_TABLE";
CREATE SEQUENCE "WJTEST"."SEQ_GEN_TABLE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_GEN_TABLE_COLUMN
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_GEN_TABLE_COLUMN";
CREATE SEQUENCE "WJTEST"."SEQ_GEN_TABLE_COLUMN" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_GOODSBUYRECORD_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_GOODSBUYRECORD_ID";
CREATE SEQUENCE "WJTEST"."SEQ_GOODSBUYRECORD_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_GOODSRECORD_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_GOODSRECORD_ID";
CREATE SEQUENCE "WJTEST"."SEQ_GOODSRECORD_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_GOODSSALEDAYRECORD_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_GOODSSALEDAYRECORD_ID";
CREATE SEQUENCE "WJTEST"."SEQ_GOODSSALEDAYRECORD_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_GOODSTABLE_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_GOODSTABLE_ID";
CREATE SEQUENCE "WJTEST"."SEQ_GOODSTABLE_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_IMPORTANTGOODSLU_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_IMPORTANTGOODSLU_ID";
CREATE SEQUENCE "WJTEST"."SEQ_IMPORTANTGOODSLU_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_IMPORTANTGOODSSUMRECORD_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_IMPORTANTGOODSSUMRECORD_ID";
CREATE SEQUENCE "WJTEST"."SEQ_IMPORTANTGOODSSUMRECORD_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_IMPORTANTRECORDGOODS_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_IMPORTANTRECORDGOODS_ID";
CREATE SEQUENCE "WJTEST"."SEQ_IMPORTANTRECORDGOODS_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_LINGBAO_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_LINGBAO_ID";
CREATE SEQUENCE "WJTEST"."SEQ_LINGBAO_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_MESSAGE_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_MESSAGE_ID";
CREATE SEQUENCE "WJTEST"."SEQ_MESSAGE_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_MOUNTSKILL_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_MOUNTSKILL_ID";
CREATE SEQUENCE "WJTEST"."SEQ_MOUNTSKILL_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_MOUNT_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_MOUNT_ID";
CREATE SEQUENCE "WJTEST"."SEQ_MOUNT_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_PAYVIP_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_PAYVIP_ID";
CREATE SEQUENCE "WJTEST"."SEQ_PAYVIP_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_PRODUCTION_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_PRODUCTION_ID";
CREATE SEQUENCE "WJTEST"."SEQ_PRODUCTION_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_PROPERTIES
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_PROPERTIES";
CREATE SEQUENCE "WJTEST"."SEQ_PROPERTIES" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_RECORD_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_RECORD_ID";
CREATE SEQUENCE "WJTEST"."SEQ_RECORD_ID" MINVALUE 1 MAXVALUE 99999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_REWARD_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_REWARD_ID";
CREATE SEQUENCE "WJTEST"."SEQ_REWARD_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_ROLEGOODS_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_ROLEGOODS_ID";
CREATE SEQUENCE "WJTEST"."SEQ_ROLEGOODS_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_ROLEORDER_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_ROLEORDER_ID";
CREATE SEQUENCE "WJTEST"."SEQ_ROLEORDER_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_ROLETABLE_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_ROLETABLE_ID";
CREATE SEQUENCE "WJTEST"."SEQ_ROLETABLE_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_RUFENGHAOCONTROL_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_RUFENGHAOCONTROL_ID";
CREATE SEQUENCE "WJTEST"."SEQ_RUFENGHAOCONTROL_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_SALEGOODS_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SALEGOODS_ID";
CREATE SEQUENCE "WJTEST"."SEQ_SALEGOODS_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_SALE_CARD_ORDER
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SALE_CARD_ORDER";
CREATE SEQUENCE "WJTEST"."SEQ_SALE_CARD_ORDER" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_SHANGCHENGSHOP_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SHANGCHENGSHOP_ID";
CREATE SEQUENCE "WJTEST"."SEQ_SHANGCHENGSHOP_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_SOWING_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SOWING_ID";
CREATE SEQUENCE "WJTEST"."SEQ_SOWING_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_SUMMONING_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SUMMONING_ID";
CREATE SEQUENCE "WJTEST"."SEQ_SUMMONING_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_SYS_CONFIG
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_CONFIG";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_CONFIG" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_DEPT
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_DEPT";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_DEPT" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_DICT_DATA
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_DICT_DATA";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_DICT_DATA" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_DICT_TYPE
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_DICT_TYPE";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_DICT_TYPE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_JOB
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_JOB";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_JOB" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_JOB_LOG
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_JOB_LOG";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_JOB_LOG" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_LOGININFOR
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_LOGININFOR";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_LOGININFOR" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_MENU
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_MENU";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_MENU" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_NOTICE
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_NOTICE";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_NOTICE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_OPER_LOG
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_OPER_LOG";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_OPER_LOG" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_POST
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_POST";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_POST" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_ROLE
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_ROLE";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_ROLE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_USER
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_SYS_USER";
CREATE SEQUENCE "WJTEST"."SEQ_SYS_USER" MINVALUE 2 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_TB_AGENT_GOODS
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_TB_AGENT_GOODS";
CREATE SEQUENCE "WJTEST"."SEQ_TB_AGENT_GOODS" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_TITLETABLE_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_TITLETABLE_ID";
CREATE SEQUENCE "WJTEST"."SEQ_TITLETABLE_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_USERSAPPLY_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_USERSAPPLY_ID";
CREATE SEQUENCE "WJTEST"."SEQ_USERSAPPLY_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_USERS_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_USERS_ID";
CREATE SEQUENCE "WJTEST"."SEQ_USERS_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_USERTABLE
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_USERTABLE";
CREATE SEQUENCE "WJTEST"."SEQ_USERTABLE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_USERTABLE_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_USERTABLE_ID";
CREATE SEQUENCE "WJTEST"."SEQ_USERTABLE_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_USERXYANDROLEDHBCR_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_USERXYANDROLEDHBCR_ID";
CREATE SEQUENCE "WJTEST"."SEQ_USERXYANDROLEDHBCR_ID" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for SEQ_WECHAT_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."SEQ_WECHAT_ID";
CREATE SEQUENCE "WJTEST"."SEQ_WECHAT_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 ORDER NOCACHE;

-- ----------------------------
-- Sequence structure for TB_SALE_CARD_ORDER_ID
-- ----------------------------
DROP SEQUENCE "WJTEST"."TB_SALE_CARD_ORDER_ID";
CREATE SEQUENCE "WJTEST"."TB_SALE_CARD_ORDER_ID" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Checks structure for table BUYCOUNT
-- ----------------------------
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011053" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011063" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011090" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011099" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011102" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011103" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011104" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011277" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011360" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011413" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011506" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011620" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011780" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0011863" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0012119" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0012236" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0013015" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0013062" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0013293" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0013538" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0014026" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0015539" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0018127" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0018300" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0018454" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0021948" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0029912" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."BUYCOUNT" ADD CONSTRAINT "SYS_C0030524" CHECK ("BID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table ROLE_ATTRIBUTE
-- ----------------------------
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011106" PRIMARY KEY ("ROLE_ID");

-- ----------------------------
-- Checks structure for table ROLE_ATTRIBUTE
-- ----------------------------
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011054" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011064" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011091" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011100" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011105" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011138" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011314" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011361" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011414" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011507" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011621" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011781" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0011864" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0012120" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0012237" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0013016" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0013063" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0013294" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0013539" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0014035" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0015540" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0018168" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0018301" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0018455" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0021953" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0029934" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_ATTRIBUTE" ADD CONSTRAINT "SYS_C0030525" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table ROLE_TABLE
-- ----------------------------
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011108" PRIMARY KEY ("ROLE_ID");

-- ----------------------------
-- Checks structure for table ROLE_TABLE
-- ----------------------------
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011055" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011065" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011092" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011101" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011107" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011139" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011315" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011362" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011415" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011508" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011622" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011782" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0011865" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0012121" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0012238" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0013017" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0013064" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0013295" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0013540" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0014036" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0015541" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0018169" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0018302" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0018456" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0021962" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0029935" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."ROLE_TABLE" ADD CONSTRAINT "SYS_C0030526" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SELL_LIANGHAO_AUC
-- ----------------------------
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SELL_LIANGHAO_AUC_CONST" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table SELL_LIANGHAO_AUC
-- ----------------------------
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0011056" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0011093" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0011363" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0011509" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0011623" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0011866" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0012122" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0013065" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0013296" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0014358" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0015542" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0029936" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_LIANGHAO_AUC" ADD CONSTRAINT "SYS_C0030527" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SELL_XIAN_YU_ORDER
-- ----------------------------
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SELL_XIAN_YU_ORDER_CONST" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table SELL_XIAN_YU_ORDER
-- ----------------------------
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011057" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011066" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011094" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011109" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011140" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011316" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011364" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011416" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011510" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011624" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011783" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0011867" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0012123" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0012239" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0013018" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0013066" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0013297" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0013541" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0014037" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0015543" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0018170" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0018303" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0018457" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0021963" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0029937" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "WJTEST"."SELL_XIAN_YU_ORDER" ADD CONSTRAINT "SYS_C0030528" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
