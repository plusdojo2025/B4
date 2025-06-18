CREATE DATABASE B4;

USE B4;

CREATE TABLE users(
    id int  auto_increment primary key,
    type_id int not null,
    name varchar(100) not null,
    password varchar(32),
    mail_address varchar(100),
    grade int,
    school_class int,
    trophy_id int,
    statuses_id int,
    created_at timestamp,
    updated_at timestamp );

CREATE TABLE user_types(
    id int auto_increment primary key,
    type varchar(50),
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE familys(
    id int auto_increment primary key,
    user1_id int ,
    user2_id int ,
    created_at timestamp,
    updated_at timestamp );

CREATE TABLE books(
    id int auto_increment primary key,
    title varchar(100) not null,
    author varchar(100),
    publisher varchar(100),
    user_id int not null,
    gets varchar(100),
    page int not null,
    genre_id int not null,
    cover varchar(100),
    created_at timestamp,
    updated_at timestamp );

CREATE TABLE recommends(
    id int auto_increment primary key,
    book_id int ,
    user_id int ,
    comment varchar(255),
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE progress(
    id int auto_increment primary key,
    user_id int,
    book_id int,
    target_page int,
    read_page int,
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE genres(
    id int auto_increment primary key,
    genre_name varchar(100),
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE opinions(
    id int auto_increment primary key,
    user_id int ,
    comment varchar(255) not null,
    reaction_id int,
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE reactions(
    id int auto_increment primary key,
    reaction varchar(5),
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE finish_books(
    id int auto_increment primary key,
    book_id int ,
    user_id int ,
    type varchar(3) not null,
    created_at timestamp,
    updated_at timestamp);



 CREATE TABLE rankings(
    id int auto_increment primary key,
    ranking_kind int not null,
    user_id int ,
    rank_value int,
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE ranking_kinds(
    id int auto_increment primary key,
    name varchar(255) not null,
    type varchar(255) not null,
    genre_id int ,
    term varchar(255) not null,
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE trophys(
    id int auto_increment primary key,
    trophy_photo varchar(255),
    ranking_id int ,
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE statuses(
    id int auto_increment primary key,
    name varchar(255) not null,
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE trophy_logs(
    id int auto_increment primary key,
    user_id int ,
    trophy_id int ,
    created_at timestamp,
    updated_at timestamp);

CREATE TABLE statuses_logs(
    id int auto_increment primary key,
    user_id int ,
    statuses_id   int ,
    created_at timestamp,
    updated_at timestamp);

INSERT INTO genres(id, genre_name,created_at,updated_at)
VALUES
    (1,'ファンタジー',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (2,'ミステリー',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (3,'バトル',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (4,'ホラー',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (5,'コメディ',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (6,'日常',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (7,'絵本',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (8,'昔ばなし',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (9,'文学',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (10,'ノンフィクション',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (11,'エッセイ',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (12,'学習',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (13,'図鑑',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (14,'雑学',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
	(15,'その他',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO user_types (id, type, created_at, updated_at)
VALUES 
	(1, '管理者', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	(2,'保護者', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	(3,'生徒', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO reactions(id, reaction,created_at,updated_at)
VALUES
    (1,'承認',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (2,'却下',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    (3,'保留',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
    
INSERT INTO rankings VALUES('100', '9', '01', '1', '2025/04/08', '2025/06/10');
INSERT INTO ranking_kinds VALUES('01', 'クラス', 'クラスごとの読書量', '8', '一か月','2025/04/08', '2025/06/10');    