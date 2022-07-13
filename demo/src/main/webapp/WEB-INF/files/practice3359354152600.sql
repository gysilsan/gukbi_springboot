commit;
SELECT * FROM BOARD;
CREATE SEQUENCE BOARD_NUM INCREMENT BY 1 START WITH 1 NOCACHE;

SELECT * FROM BOARD ORDER BY NUM;

select max(num) from board WHERE AUTHOR='Smith';

-- pagination
-- ROWNUM: ���ȣ
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

-- page ��ȣ 1 : 1, 3
-- page ��ȣ 2 : 4, 6

-- board?cmd=list%page=2

SELECT num, title, ROWNUM RN FROM board;
-- page:1 -> 1,2,3 ==> 1
--           4,5,6 ==> 2
--           7,8,9 ==> 3

-- (rn-1)/3 , �Ҽ��� ���� ����, +1
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

-- View ����
CREATE OR REPLACE VIEW board_page AS (
    SELECT num, title, FLOOR((RN-1)/3+1) AS page FROM 
    (
        SELECT num, title, ROWNUM RN FROM
        (
            SELECT * FROM board ORDER BY num DESC
            
        )
    )
);

SELECT * FROM board_page WHERE page=1; -- ��û ������ ������ ������

-- ���� board ���̺� ����� �Խñ��� �� �������� 3�྿ ������ ���
-- �� ������ ���� ������ SQL�� ����Ͽ� Ȯ���غ�����.
SELECT CEIL(COUNT(*)/3) AS ttlpages FROM board; -- ��ũ ������ ���� ���� ������ 

-- INNER, OUTER, CROSS JOIN

SELECT * FROM board_page,(SELECT CEIL(COUNT(*)/3) AS ttlpages FROM board) WHERE page=1;

-- page, ttlpages 2�� �Ӽ��� VO�� �����ϰ� ����Ѵ�
-- VO�� ����ϴ� ��� List, Map�� �����

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
-- 'a'�� ���ʿ� 'b'�� �����. ������� �ִ� ���ڰ����� 5���� �Ǿ�� �Ѵ�.

SELECT LPAD(' ', (1-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (2-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (3-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (4-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (5-1)*3) || 'test' AS field1 FROM dual;
SELECT LPAD(' ', (6-1)*3) || 'test' AS field1 FROM dual;
-- �۾���
-- �Խñ� �Ʒ��� ��۾���
SELECT num, title, pcode FROM board
START WITH pcode=0
CONNECT BY PRIOR num=pcode
ORDER SIBLINGS BY wdate DESC;


SELECT num, LPAD(' ', (LEVEL-1)*3)||title AS title, pcode FROM board
START WITH pcode=0
CONNECT BY PRIOR num=pcode
ORDER SIBLINGS BY wdate DESC;

SELECT num, LPAD('��', (LEVEL-1)*3, ' ')||title AS title, pcode FROM board
START WITH pcode=0
CONNECT BY PRIOR num=pcode
ORDER SIBLINGS BY wdate DESC;

CREATE OR REPLACE VIEW board_page AS (
    SELECT t2.*, FLOOR((RN-1)/10+1) AS page FROM 
    (
        SELECT t.*, ROWNUM RN FROM
        (
            SELECT num, LPAD('�� ', (LEVEL-1)*4, '��')||title AS title, author, wdate, pcode FROM board
            START WITH pcode=0 
            CONNECT BY PRIOR num=pcode 
            ORDER SIBLINGS BY wdate DESC
        ) t
    ) t2
);
SELECT * FROM board_page CROSS JOIN (SELECT CEIL(COUNT(*) / 10) AS ttlpages FROM board) WHERE page=1;







SELECT num, LPAD('��', (LEVEL-1)*3, ' ')||title AS title, author, wdate, pcode, page FROM board_page CROSS JOIN
					(SELECT CEIL(COUNT(*) / 10) AS ttlpages FROM board) 
                    WHERE page=1
                    START WITH pcode=0 
				    CONNECT BY PRIOR num=pcode 
                    ORDER SIBLINGS BY wdate DESC;

