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
    
   INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 12, 214, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 5, 190, 250, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 18, 280, 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 7, 160, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 3, 220, 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 21, 145, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 9, 260, 90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 11, 310, 220, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 14, 230, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 6, 180, 300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 5, 120, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 11, 280, 190, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 7, 200, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 3, 160, 250, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 14, 210, 130, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 2, 260, 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 6, 300, 290, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 19, 220, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 1, 190, 170, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 17, 230, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 9, 140, 300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 12, 250, 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 21, 180, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 10, 160, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 4, 100, 270, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 8, 300, 110, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 13, 90, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 15, 275, 130, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 16, 150, 90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 18, 210, 290, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 20, 100, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 5, 260, 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 6, 180, 270, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 11, 150, 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 14, 230, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 2, 290, 190, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 16, 250, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 1, 270, 300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 3, 120, 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 19, 300, 70, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 7, 110, 130, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 13, 280, 260, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 4, 240, 140, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 8, 300, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 15, 290, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 17, 200, 220, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 20, 180, 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 9, 260, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 12, 90, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 21, 150, 210, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO users (type_id, name, password, mail_address, grade, school_class, trophy_id, statuses_id, created_at, updated_at) VALUES
(1, '佐藤 太郎', 'pass123', 'sato@example.com', 1, 1, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '鈴木 花子', 'pass123', 'suzuki@example.com', 1, 2, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '高橋 次郎', 'pass123', 'takahashi@example.com', 2, 1, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, '田中 美咲', 'pass123', 'tanaka@example.com', 2, 2, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '伊藤 太一', 'pass123', 'ito@example.com', 3, 1, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '山本 彩', 'pass123', 'yamamoto@example.com', 3, 2, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '中村 拓海', 'pass123', 'nakamura@example.com', 1, 3, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, '小林 桃子', 'pass123', 'kobayashi@example.com', 2, 3, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '加藤 慎一', 'pass123', 'kato@example.com', 3, 3, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, '渡辺 愛', 'pass123', 'watanabe@example.com', 1, 4, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);