--------------------------------------------------------
--  파일이 생성됨 - 목요일-6월-23-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for View BOARD_PAGE
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "SCOTT"."BOARD_PAGE" ("NUM", "TITLE", "AUTHOR", "WDATE", "PCODE", "RN", "PAGE") AS 
  (
    SELECT t2."NUM",t2."TITLE",t2."AUTHOR",t2."WDATE",t2."PCODE",t2."RN", FLOOR((RN-1)/10+1) AS page FROM 
    (
        SELECT t.*, ROWNUM RN FROM
        (
            SELECT num, LPAD('└ ', (LEVEL-1)*4, '　')||title AS title, author, wdate, pcode FROM board
            START WITH pcode=0 
            CONNECT BY PRIOR num=pcode 
            ORDER SIBLINGS BY wdate DESC
        ) t
    ) t2
)
;
REM INSERTING into SCOTT.BOARD_PAGE
SET DEFINE OFF;
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (56,'ㄷㄷㄷ','TEST',to_date('22/06/23','RR/MM/DD'),0,1,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (57,'　└ ㄷㄷ','TEST',to_date('22/06/23','RR/MM/DD'),56,2,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (58,'　　　└ 답답','TEST',to_date('22/06/23','RR/MM/DD'),57,3,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (55,'답답','TEST',to_date('22/06/23','RR/MM/DD'),0,4,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (54,'답답도','TEST',to_date('22/06/23','RR/MM/DD'),0,5,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (53,'수정되나요','TEST',to_date('22/06/23','RR/MM/DD'),0,6,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (51,'되나','TEST',to_date('22/06/23','RR/MM/DD'),0,7,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (52,'　└ 답글인데','TEST',to_date('22/06/23','RR/MM/DD'),51,8,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (45,'수정되나','TEST',to_date('22/06/21','RR/MM/DD'),0,9,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (26,'0621수정','Smith',to_date('22/06/15','RR/MM/DD'),0,10,1);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (27,'　└ 답글테스트','Smith',to_date('22/06/14','RR/MM/DD'),26,11,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (28,'　　　└ 답답글테스트','Smith',to_date('22/06/14','RR/MM/DD'),27,12,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (25,'test','Smith',to_date('22/06/14','RR/MM/DD'),0,13,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (24,'최근순 테스트','Smith',to_date('22/06/13','RR/MM/DD'),0,14,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (23,'zxcv','Smith',to_date('22/06/13','RR/MM/DD'),0,15,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (22,'vv','Smith',to_date('22/06/13','RR/MM/DD'),0,16,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (21,'ss','Smith',to_date('22/06/13','RR/MM/DD'),0,17,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (20,'aa','Smith',to_date('22/06/13','RR/MM/DD'),0,18,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (19,'abcd','Smith',to_date('22/06/13','RR/MM/DD'),0,19,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (18,'1318','Smith',to_date('22/06/13','RR/MM/DD'),0,20,2);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (17,'1312','Smith',to_date('22/06/13','RR/MM/DD'),0,21,3);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (16,'1253','Smith',to_date('22/06/13','RR/MM/DD'),0,22,3);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (15,'1246','Smith',to_date('22/06/13','RR/MM/DD'),0,23,3);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (14,'최종테스트','Smith',to_date('22/06/13','RR/MM/DD'),0,24,3);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (13,'ttttest','Smith',to_date('22/06/13','RR/MM/DD'),0,25,3);
Insert into SCOTT.BOARD_PAGE (NUM,TITLE,AUTHOR,WDATE,PCODE,RN,PAGE) values (12,'test1','Smith',to_date('22/06/13','RR/MM/DD'),0,26,3);
