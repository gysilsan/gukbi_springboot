CREATE TABLE tb_upload(
    num NUMBER(4) PRIMARY KEY,
    title   VARCHAR2(100),
    udate   DATE,
    comments VARCHAR2(100)
);
commit;

DROP TABLE tb_upload;

CREATE TABLE tb_attach(
    num NUMBER(4),
    fname VARCHAR2(50),
    fpath VARCHAR2(100)
);

ALTER TABLE tb_attach ADD CONSTRAINTS fk_num FOREIGN KEY(num)REFERENCES tb_upload(num);
