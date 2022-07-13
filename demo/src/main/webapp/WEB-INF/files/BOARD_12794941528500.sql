--------------------------------------------------------
--  파일이 생성됨 - 수요일-6월-22-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOARD
--------------------------------------------------------

  CREATE TABLE "SCOTT"."BOARD" 
   (	"NUM" NUMBER, 
	"TITLE" VARCHAR2(100 BYTE), 
	"CONTENTS" VARCHAR2(4000 BYTE), 
	"AUTHOR" VARCHAR2(40 BYTE), 
	"WDATE" DATE, 
	"PCODE" NUMBER DEFAULT 0
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into SCOTT.BOARD
SET DEFINE OFF;
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (12,'test1','test1','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (26,'0621수정','0621테스트','Smith',to_date('22/06/15','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (45,'수정되나','되나?이건되나','TEST',to_date('22/06/21','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (25,'test','test','Smith',to_date('22/06/14','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (16,'1253','1253','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (28,'답답글테스트','잘되나?','Smith',to_date('22/06/14','RR/MM/DD'),27);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (14,'최종테스트','테스트','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (15,'1246','1246','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (18,'1318','1318','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (13,'ttttest','ttttest','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (17,'1312','1312','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (19,'abcd','efgh','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (20,'aa','ss','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (21,'ss','dd','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (22,'vv','cc','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (23,'zxcv','xcv','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (24,'최근순 테스트','테스트','Smith',to_date('22/06/13','RR/MM/DD'),0);
Insert into SCOTT.BOARD (NUM,TITLE,CONTENTS,AUTHOR,WDATE,PCODE) values (27,'답글테스트','잘되나?','Smith',to_date('22/06/14','RR/MM/DD'),26);
