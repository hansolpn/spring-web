CREATE TABLE jdbc_board (
    board_no NUMBER PRIMARY KEY,
    writer VARCHAR2(30) NOT NULL,
    title VARCHAR2(100) NOT NULL,
    content VARCHAR2(2000),
    reg_date DATE DEFAULT sysdate
);

CREATE SEQUENCE board_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE
    NOCACHE;
