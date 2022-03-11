create sequence seq_blogger increment by 1 start with 1;

create table blogger201712045 (
                                  id number(11) not null primary key,
                                  email varchar2(30) not null unique,
                                  pw varchar2(20) not null,
                                  name varchar2(30) not null,
                                  phone varchar2(13),
                                  address varchar2(50)
);

INSERT INTO blogger201712045 VALUES(seq_blogger.nextval,'admin201712045@induk.ac.kr', 'cometrue','관리자', '9507620','korea');
INSERT INTO blogger201712045 VALUES(seq_blogger.nextval,'egyou@induk.ac.kr', 'cometrue','유응구', '9507625', 'korea');

select * from blogger201712045;

select pw from blogger201712045 where email = 'egyou@induk.ac.kr';

update blogger201712045 set name='comso', phone='7777', address='nowon, seoul' where email='comso1@induk.ac.kr' and pw='cometrue';

delete from blogger201712045 where id = 2;

drop sequence seq_blogger;

drop table blogger201712045;

