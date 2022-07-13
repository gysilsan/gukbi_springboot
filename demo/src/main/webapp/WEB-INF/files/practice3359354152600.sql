commit;
SELECT * FROM BOARD;
CREATE SEQUENCE BOARD_NUM INCREMENT BY 1 START WITH 1 NOCACHE;

SELECT * FROM BOARD ORDER BY NUM;

select max(num) from board WHERE AUTHOR='Smith';

-- pagination
-- ROWNUM: 행번호
SELECT num, title FROM board;
SELECT num, title, ROWNUM FROM board;
SELECT num, title, ROWNUM RN FROM board;
SELECT * FROM 
(
    SELECT num, title, ROWNUM RN FROM board
);
SELECT * FROM 
(
    SELECT num, title, ROWNUM RN FROM board
) 
WHERE RN BETWEEN 1 AND 3;
SELECT * FROM 
(
    SELECT num, title, ROWNUM RN FROM board
) 
WHERE RN BETWEEN 4 AND 6;

-- page 번호 1 : 1, 3
-- page 번호 2 : 4, 6

-- board?cmd=list%page=2

SELECT num, title, ROWNUM RN FROM board;
-- page:1 -> 1,2,3 ==> 1
--           4,5,6 ==> 2
--           7,8,9 ==> 3

-- (rn-1)/3 , 소수점 이하 버림, +1
SELECT num, title, (RN-1)/3+1 AS page FROM 
(
    SELECT num, title, ROWNUM RN FROM board
);

SELECT num, title, FLOOR((RN-1)/3+1) AS page FROM 
(
    SELECT num, title, ROWNUM RN FROM board
);

SELECT * FROM
(
    SELECT num, title, FLOOR((RN-1)/3+1) AS page FROM 
    (
        SELECT num, title, ROWNUM RN FROM board
    )
)
WHERE page=2;

SELECT * FROM
(
    SELECT num, title, FLOOR((RN-1)/3+1) AS page FROM 
    (
        SELECT num, title, ROWNUM RN FROM
        (
            SELECT * FROM board ORDER BY num DESC
            
        )
    )
)
WHERE page=2;

-- View 생성
CREATE OR REPLACE VIEW board_page AS (
    SELECT num, title, FLOOR((RN-1)/3+1) AS page FROM 
    (
        SELECT num, title, ROWNUM RN FROM
        (
            SELECT * FROM board ORDER BY num DESC
            
        )
    )
);

SELECT * FROM board_page WHERE page=1; -- 요청 페이지 컨텐츠 가져옴

-- 현재 board 테이블에 저장된 게시글이 한 페이지당 3행씩 보여줄 경우
-- 총 페이지 수는 얼마인지 SQL을 사용하여 확인해보세요.
SELECT CEIL(COUNT(*)/3) AS ttlpages FROM board; -- 링크 생성을 위한 기초 데이터 

-- INNER, OUTER, CROSS JOIN

SELECT * FROM board_page,(SELECT CEIL(COUNT(*)/3) AS ttlpages FROM board) WHERE page=1;

-- page, ttlpages 2개 속성을 VO에 선언하고 사용한다
-- VO를 사용하는 대신 List, Map을 사용함

CREATE OR REPLACE VIEW board_page AS (
    SELECT t2.*, FLOOR((RN-1)/3+1) AS page FROM 
    (
        SELECT t.*, ROWNUM RN FROM
        (
            SELECT num, title, author, wdate, pcode FROM board ORDER BY num DESC
        ) t
    ) t2
);
SELECT * FROM board_page WHERE page=1;
commit;

SELECT * FROM board_page,(SELECT CEIL(COUNT(*) / 3) AS ttlpages FROM board) WHERE page=1;
SELECT * FROM board_page CROSS JOIN (SELECT CEIL(COUNT(*) / 3) AS ttlpages FROM board) WHERE page=1;

SELECT num, title, pcode FROM board
START WITH pcode=0
CONNECT BY PRIOR num=pcode
ORDER SIBLINGS BY wdate DESC;

SELECT LPAD('a', 5, 'b') || 'test' AS field1 FROM dual;
-- 'a'의 왼쪽에 'b'를 덧댄다. 결과값의 최대 문자갯수는 5개가 되어야 한다.

SELECT LPAD(' ', (1-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (2-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (3-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (4-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (5-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (6-1)*3) || 'test' AS field1 FROM dual;
-- 글쓰기
-- 게시글 아래에 답글쓰기
SELECT num, title, pcode FROM board
START WITH pcode=0
CONNECT BY PRIOR num=pcode
ORDER SIBLINGS BY wdate DESC;


SELECT num, LPAD(' ', (LEVEL-1)*3)||title AS title, pcode FROM board
START WITH pcode=0
CONNECT BY PRIOR num=pcode
ORDER SIBLINGS BY wdate DESC;

SELECT num, LPAD('└', (LEVEL-1)*3, ' ')||title AS title, pcode FROM board
START WITH pcode=0
CONNECT BY PRIOR num=pcode
ORDER SIBLINGS BY wdate DESC;

CREATE OR REPLACE VIEW board_page AS (
    SELECT t2.*, FLOOR((RN-1)/10+1) AS page FROM 
    (
        SELECT t.*, ROWNUM RN FROM
        (
            SELECT num, LPAD('└ ', (LEVEL-1)*4, '　')||title AS title, author, wdate, pcode FROM board
            START WITH pcode=0 
            CONNECT BY PRIOR num=pcode 
            ORDER SIBLINGS BY wdate DESC
        ) t
    ) t2
);
SELECT * FROM board_page CROSS JOIN (SELECT CEIL(COUNT(*) / 10) AS ttlpages FROM board) WHERE page=1;







SELECT num, LPAD('└', (LEVEL-1)*3, ' ')||title AS title, author, wdate, pcode, page FROM board_page CROSS JOIN
					(SELECT CEIL(COUNT(*) / 10) AS ttlpages FROM board) 
                    WHERE page=1
                    START WITH pcode=0 
				    CONNECT BY PRIOR num=pcode 
                    ORDER SIBLINGS BY wdate DESC;

