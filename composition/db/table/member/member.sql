create table member
(
    id        bigint primary key auto_increment comment 'ID',
    username  varchar(100) unique not null comment '회원 ID',
    password  varchar(500)        not null comment '회원 PWD (암호화)',
    createdAt datetime            not null default now() comment '생성일',
    updatedAt datetime            not null default now() comment '수정일'
) comment '회원 테이블';

