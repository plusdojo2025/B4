CREATE DATABASE b4;

USE b4;

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
    
   INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 12, 214, 30, '2025-04-02 09:30:00', '2025-04-02 09:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 5, 190, 250, '2025-04-05 15:00:00', '2025-04-05 15:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 18, 280, 60, '2025-04-08 11:45:00', '2025-04-08 11:45:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 7, 160, 10, '2025-04-10 14:20:00', '2025-04-10 14:20:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 3, 220, 180, '2025-04-15 10:00:00', '2025-04-15 10:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 21, 145, 0, '2025-04-18 17:30:00', '2025-04-18 17:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 9, 260, 90, '2025-04-20 13:00:00', '2025-04-20 13:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 11, 310, 220, '2025-04-24 08:40:00', '2025-04-24 08:40:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 14, 230, 15, '2025-04-26 10:15:00', '2025-04-26 10:15:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 6, 180, 300, '2025-04-29 16:45:00', '2025-04-29 16:45:00');

INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 5, 120, 30, '2025-05-02 09:10:00', '2025-05-02 09:10:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 11, 280, 190, '2025-05-04 12:00:00', '2025-05-04 12:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 7, 200, 0, '2025-05-06 10:15:00', '2025-05-06 10:15:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 3, 160, 250, '2025-05-08 14:25:00', '2025-05-08 14:25:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 14, 210, 130, '2025-05-10 16:30:00', '2025-05-10 16:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 2, 260, 80, '2025-05-13 11:50:00', '2025-05-13 11:50:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 6, 300, 290, '2025-05-16 13:10:00', '2025-05-16 13:10:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 19, 220, 20, '2025-05-18 09:40:00', '2025-05-18 09:40:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 1, 190, 170, '2025-05-20 17:00:00', '2025-05-20 17:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 17, 230, 100, '2025-05-23 15:00:00', '2025-05-23 15:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 9, 140, 300, '2025-05-25 14:45:00', '2025-05-25 14:45:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 12, 250, 60, '2025-05-26 10:20:00', '2025-05-26 10:20:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 21, 180, 200, '2025-05-27 11:55:00', '2025-05-27 11:55:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 10, 160, 50, '2025-05-28 14:30:00', '2025-05-28 14:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 4, 100, 270, '2025-05-29 12:30:00', '2025-05-29 12:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 8, 300, 110, '2025-05-30 16:00:00', '2025-05-30 16:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 13, 90, 0, '2025-05-31 09:30:00', '2025-05-31 09:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 15, 275, 130, '2025-05-31 14:10:00', '2025-05-31 14:10:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 16, 150, 90, '2025-05-31 16:00:00', '2025-05-31 16:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 18, 210, 290, '2025-05-31 18:00:00', '2025-05-31 18:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 20, 100, 10, '2025-06-02 09:00:00', '2025-06-02 09:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 5, 260, 180, '2025-06-03 10:45:00', '2025-06-03 10:45:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 6, 180, 270, '2025-06-04 13:30:00', '2025-06-04 13:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 11, 150, 40, '2025-06-05 15:30:00', '2025-06-05 15:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 14, 230, 200, '2025-06-06 16:00:00', '2025-06-06 16:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 2, 290, 190, '2025-06-07 17:10:00', '2025-06-07 17:10:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 16, 250, 30, '2025-06-08 08:00:00', '2025-06-08 08:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 1, 270, 300, '2025-06-10 09:20:00', '2025-06-10 09:20:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 3, 120, 80, '2025-06-12 11:00:00', '2025-06-12 11:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 19, 300, 70, '2025-06-13 12:30:00', '2025-06-13 12:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (1, 7, 110, 130, '2025-06-14 13:00:00', '2025-06-14 13:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (2, 13, 280, 260, '2025-06-15 14:15:00', '2025-06-15 14:15:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (3, 4, 240, 140, '2025-06-16 15:30:00', '2025-06-16 15:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (4, 8, 300, 20, '2025-06-17 16:45:00', '2025-06-17 16:45:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (5, 15, 290, 100, '2025-06-18 17:00:00', '2025-06-18 17:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (6, 17, 200, 220, '2025-06-19 09:30:00', '2025-06-19 09:30:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (7, 20, 180, 150, '2025-06-20 11:10:00', '2025-06-20 11:10:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (8, 9, 260, 50, '2025-06-21 13:45:00', '2025-06-21 13:45:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (9, 12, 90, 0, '2025-06-22 15:00:00', '2025-06-22 15:00:00');
INSERT INTO progress(user_id, book_id, target_page, read_page, created_at, updated_at) VALUES (10, 21, 150, 210, '2025-06-23 16:30:00', '2025-06-23 16:30:00');
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
(1, '渡辺 愛', 'pass123', 'watanabe@example.com', 1, 3, NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
drop table finish_books;

drop table books;

CREATE TABLE finish_books(
    id int auto_increment primary key,
    book_id int ,
    user_id int ,
    type_id int DEFAULT 1,
    created_at timestamp,
    updated_at timestamp);


CREATE TABLE finish_books_type(
    id int auto_increment primary key,
    type varchar(3),
    created_at timestamp,
    updated_at timestamp);

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

INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 4, 2, '2025-05-29 01:01:31', '2025-05-29 01:01:31');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 7, 2, '2025-06-29 06:00:31', '2025-06-29 06:00:31');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 4, 2, '2025-04-19 02:47:25', '2025-04-19 02:47:25');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 5, 2, '2025-05-21 01:04:45', '2025-05-21 01:04:45');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 2, 2, '2025-05-29 16:21:57', '2025-05-29 16:21:57');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 8, 2, '2025-06-01 11:38:46', '2025-06-01 11:38:46');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (12, 7, 2, '2025-04-18 20:57:00', '2025-04-18 20:57:00');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (6, 6, 2, '2025-06-28 08:56:56', '2025-06-28 08:56:56');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (6, 10, 2, '2025-06-09 19:26:02', '2025-06-09 19:26:02');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (10, 1, 2, '2025-04-26 13:58:42', '2025-04-26 13:58:42');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 3, 2, '2025-06-06 18:16:10', '2025-06-06 18:16:10');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 9, 2, '2025-04-05 09:09:08', '2025-04-05 09:09:08');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 6, 2, '2025-06-27 06:40:16', '2025-06-27 06:40:16');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (10, 5, 2, '2025-06-11 01:26:43', '2025-06-11 01:26:43');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (10, 8, 2, '2025-06-07 18:10:36', '2025-06-07 18:10:36');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 4, 2, '2025-06-19 05:38:19', '2025-06-19 05:38:19');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (12, 3, 2, '2025-06-09 20:36:55', '2025-06-09 20:36:55');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 10, 2, '2025-04-02 00:36:00', '2025-04-02 00:36:00');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 2, 2, '2025-05-02 11:57:10', '2025-05-02 11:57:10');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 1, 2, '2025-05-07 19:29:47', '2025-05-07 19:29:47');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 3, 2, '2025-04-19 11:20:41', '2025-04-19 11:20:41');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 5, 2, '2025-06-14 08:22:47', '2025-06-14 08:22:47');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 9, 2, '2025-06-19 07:35:54', '2025-06-19 07:35:54');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 3, 2, '2025-06-26 12:01:34', '2025-06-26 12:01:34');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 6, 2, '2025-06-03 03:28:02', '2025-06-03 03:28:02');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 3, 2, '2025-04-18 10:00:41', '2025-04-18 10:00:41');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 9, 2, '2025-05-21 03:32:46', '2025-05-21 03:32:46');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 10, 2, '2025-06-22 19:18:49', '2025-06-22 19:18:49');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 7, 2, '2025-06-04 14:34:10', '2025-06-04 14:34:10');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (12, 9, 2, '2025-04-03 19:13:38', '2025-04-03 19:13:38');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 5, 2, '2025-04-01 15:14:07', '2025-04-01 15:14:07');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 4, 2, '2025-06-07 02:16:58', '2025-06-07 02:16:58');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 9, 2, '2025-04-02 19:17:32', '2025-04-02 19:17:32');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (7, 5, 2, '2025-05-15 11:51:52', '2025-05-15 11:51:52');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 4, 2, '2025-04-01 21:56:33', '2025-04-01 21:56:33');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 7, 2, '2025-06-23 08:59:14', '2025-06-23 08:59:14');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 2, 2, '2025-06-13 23:45:51', '2025-06-13 23:45:51');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 2, 2, '2025-06-20 22:01:21', '2025-06-20 22:01:21');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 1, 2, '2025-04-12 23:31:52', '2025-04-12 23:31:52');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (10, 10, 2, '2025-06-06 11:32:54', '2025-06-06 11:32:54');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 7, 2, '2025-04-20 17:14:29', '2025-04-20 17:14:29');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 1, 2, '2025-04-05 00:29:38', '2025-04-05 00:29:38');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 1, 2, '2025-04-07 11:22:22', '2025-04-07 11:22:22');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 5, 2, '2025-06-23 02:56:43', '2025-06-23 02:56:43');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 4, 2, '2025-04-19 23:21:14', '2025-04-19 23:21:14');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 3, 2, '2025-05-20 14:59:26', '2025-05-20 14:59:26');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 9, 2, '2025-05-06 09:33:52', '2025-05-06 09:33:52');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 4, 2, '2025-06-06 01:28:58', '2025-06-06 01:28:58');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 2, 2, '2025-05-19 12:26:43', '2025-05-19 12:26:43');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (6, 6, 2, '2025-04-18 18:52:35', '2025-04-18 18:52:35');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 9, 2, '2025-06-15 09:16:05', '2025-06-15 09:16:05');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 10, 2, '2025-06-17 06:59:16', '2025-06-17 06:59:16');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 1, 2, '2025-06-07 12:29:13', '2025-06-07 12:29:13');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 1, 2, '2025-04-16 21:36:14', '2025-04-16 21:36:14');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 9, 2, '2025-04-19 08:56:39', '2025-04-19 08:56:39');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 3, 2, '2025-04-22 03:46:58', '2025-04-22 03:46:58');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 8, 2, '2025-04-26 11:50:35', '2025-04-26 11:50:35');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 3, 2, '2025-05-11 13:23:58', '2025-05-11 13:23:58');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 7, 2, '2025-06-28 01:36:36', '2025-06-28 01:36:36');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 3, 2, '2025-06-12 17:57:19', '2025-06-12 17:57:19');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 6, 2, '2025-05-15 10:56:49', '2025-05-15 10:56:49');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 7, 2, '2025-06-29 09:40:47', '2025-06-29 09:40:47');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 9, 2, '2025-05-15 13:16:16', '2025-05-15 13:16:16');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 1, 2, '2025-05-20 19:14:01', '2025-05-20 19:14:01');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 7, 2, '2025-05-05 15:47:14', '2025-05-05 15:47:14');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 3, 2, '2025-04-08 07:38:29', '2025-04-08 07:38:29');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 4, 2, '2025-05-07 09:21:16', '2025-05-07 09:21:16');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 4, 2, '2025-06-12 03:08:30', '2025-06-12 03:08:30');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 1, 2, '2025-05-06 21:26:55', '2025-05-06 21:26:55');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 6, 2, '2025-04-30 01:13:20', '2025-04-30 01:13:20');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 7, 2, '2025-04-16 07:27:17', '2025-04-16 07:27:17');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 8, 2, '2025-05-30 02:12:55', '2025-05-30 02:12:55');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 1, 2, '2025-05-14 15:39:00', '2025-05-14 15:39:00');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 1, 2, '2025-06-02 02:18:06', '2025-06-02 02:18:06');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 5, 2, '2025-06-18 23:08:02', '2025-06-18 23:08:02');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 8, 2, '2025-05-03 11:01:22', '2025-05-03 11:01:22');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 8, 2, '2025-05-21 14:58:32', '2025-05-21 14:58:32');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (6, 1, 2, '2025-04-14 09:30:19', '2025-04-14 09:30:19');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 8, 2, '2025-05-21 00:42:48', '2025-05-21 00:42:48');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 6, 2, '2025-06-28 00:17:09', '2025-06-28 00:17:09');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 8, 2, '2025-04-05 12:42:05', '2025-04-05 12:42:05');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 2, 2, '2025-06-08 02:42:09', '2025-06-08 02:42:09');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 4, 2, '2025-04-15 14:27:12', '2025-04-15 14:27:12');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 5, 2, '2025-06-01 20:06:21', '2025-06-01 20:06:21');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 3, 2, '2025-04-20 10:02:09', '2025-04-20 10:02:09');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 7, 2, '2025-04-19 01:21:13', '2025-04-19 01:21:13');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 10, 2, '2025-04-28 11:54:07', '2025-04-28 11:54:07');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (12, 4, 2, '2025-05-20 03:26:36', '2025-05-20 03:26:36');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (9, 8, 2, '2025-04-02 16:47:33', '2025-04-02 16:47:33');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 10, 2, '2025-05-26 03:34:27', '2025-05-26 03:34:27');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 3, 2, '2025-06-23 10:09:19', '2025-06-23 10:09:19');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 3, 2, '2025-06-07 07:27:22', '2025-06-07 07:27:22');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 7, 2, '2025-04-03 06:10:43', '2025-04-03 06:10:43');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 5, 2, '2025-06-14 21:56:15', '2025-06-14 21:56:15');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 1, 2, '2025-06-05 08:36:32', '2025-06-05 08:36:32');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (9, 1, 2, '2025-06-18 04:14:16', '2025-06-18 04:14:16');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 1, 2, '2025-04-29 21:20:16', '2025-04-29 21:20:16');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 1, 2, '2025-06-27 05:13:02', '2025-06-27 05:13:02');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 7, 2, '2025-06-20 05:25:50', '2025-06-20 05:25:50');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 8, 2, '2025-04-24 22:57:01', '2025-04-24 22:57:01');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (7, 2, 2, '2025-05-05 17:09:56', '2025-05-05 17:09:56');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 5, 2, '2025-05-16 00:55:46', '2025-05-16 00:55:46');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 5, 2, '2025-05-24 06:28:25', '2025-05-24 06:28:25');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 7, 2, '2025-04-22 11:24:03', '2025-04-22 11:24:03');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (7, 9, 2, '2025-04-07 15:56:04', '2025-04-07 15:56:04');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 1, 2, '2025-06-07 05:14:32', '2025-06-07 05:14:32');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 9, 2, '2025-06-01 23:57:03', '2025-06-01 23:57:03');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 4, 2, '2025-04-22 00:01:40', '2025-04-22 00:01:40');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 8, 2, '2025-05-01 23:47:30', '2025-05-01 23:47:30');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 2, 2, '2025-06-22 13:23:14', '2025-06-22 13:23:14');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 8, 2, '2025-06-25 12:15:42', '2025-06-25 12:15:42');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (17, 9, 2, '2025-04-21 09:42:15', '2025-04-21 09:42:15');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 9, 2, '2025-05-24 03:41:10', '2025-05-24 03:41:10');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 2, 2, '2025-06-22 06:44:59', '2025-06-22 06:44:59');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 6, 2, '2025-05-21 08:51:00', '2025-05-21 08:51:00');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (6, 3, 2, '2025-04-27 07:55:15', '2025-04-27 07:55:15');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 7, 2, '2025-04-06 00:26:21', '2025-04-06 00:26:21');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 6, 2, '2025-05-07 01:23:22', '2025-05-07 01:23:22');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (9, 8, 2, '2025-04-29 06:06:00', '2025-04-29 06:06:00');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 1, 2, '2025-06-06 19:10:42', '2025-06-06 19:10:42');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (6, 1, 2, '2025-06-08 10:11:20', '2025-06-08 10:11:20');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 4, 2, '2025-04-22 01:30:56', '2025-04-22 01:30:56');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 2, 2, '2025-06-11 21:14:12', '2025-06-11 21:14:12');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 6, 2, '2025-06-01 06:01:30', '2025-06-01 06:01:30');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 5, 2, '2025-06-18 12:18:38', '2025-06-18 12:18:38');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 2, 2, '2025-04-12 03:40:53', '2025-04-12 03:40:53');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 1, 2, '2025-05-20 20:13:06', '2025-05-20 20:13:06');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (10, 10, 2, '2025-06-27 18:23:26', '2025-06-27 18:23:26');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 5, 2, '2025-05-29 08:22:12', '2025-05-29 08:22:12');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 2, 2, '2025-05-29 01:58:22', '2025-05-29 01:58:22');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 6, 2, '2025-05-17 01:54:34', '2025-05-17 01:54:34');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 3, 2, '2025-05-13 09:58:55', '2025-05-13 09:58:55');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (11, 5, 2, '2025-04-13 05:27:06', '2025-04-13 05:27:06');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 7, 2, '2025-06-26 17:48:20', '2025-06-26 17:48:20');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 1, 2, '2025-06-01 02:42:59', '2025-06-01 02:42:59');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 5, 2, '2025-06-10 03:09:08', '2025-06-10 03:09:08');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 8, 2, '2025-05-30 12:14:13', '2025-05-30 12:14:13');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (12, 8, 2, '2025-05-26 18:25:48', '2025-05-26 18:25:48');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 1, 2, '2025-06-01 06:48:11', '2025-06-01 06:48:11');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 4, 2, '2025-04-08 16:50:06', '2025-04-08 16:50:06');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 3, 2, '2025-04-07 08:15:29', '2025-04-07 08:15:29');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 3, 2, '2025-06-09 09:56:45', '2025-06-09 09:56:45');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 2, 2, '2025-06-08 00:33:04', '2025-06-08 00:33:04');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 10, 2, '2025-04-22 23:13:28', '2025-04-22 23:13:28');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 5, 2, '2025-04-14 15:48:43', '2025-04-14 15:48:43');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 5, 2, '2025-05-26 11:20:24', '2025-05-26 11:20:24');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (7, 8, 2, '2025-06-10 22:15:23', '2025-06-10 22:15:23');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 5, 2, '2025-05-12 06:31:48', '2025-05-12 06:31:48');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 5, 2, '2025-06-14 02:10:39', '2025-06-14 02:10:39');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 8, 2, '2025-05-19 03:48:28', '2025-05-19 03:48:28');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 6, 2, '2025-06-12 23:39:27', '2025-06-12 23:39:27');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 4, 2, '2025-06-04 00:05:49', '2025-06-04 00:05:49');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (10, 6, 2, '2025-06-23 14:13:29', '2025-06-23 14:13:29');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 4, 2, '2025-06-04 03:54:45', '2025-06-04 03:54:45');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (6, 4, 2, '2025-05-03 12:37:45', '2025-05-03 12:37:45');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 4, 2, '2025-05-18 01:42:31', '2025-05-18 01:42:31');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 3, 2, '2025-06-19 06:32:19', '2025-06-19 06:32:19');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (14, 1, 2, '2025-05-20 20:10:11', '2025-05-20 20:10:11');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 8, 2, '2025-05-19 05:47:58', '2025-05-19 05:47:58');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 1, 2, '2025-04-05 07:35:13', '2025-04-05 07:35:13');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 1, 2, '2025-05-08 05:35:01', '2025-05-08 05:35:01');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 8, 2, '2025-05-05 11:56:59', '2025-05-05 11:56:59');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 3, 2, '2025-04-19 07:53:10', '2025-04-19 07:53:10');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 5, 2, '2025-04-13 22:05:32', '2025-04-13 22:05:32');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 2, 2, '2025-04-11 16:46:35', '2025-04-11 16:46:35');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (7, 5, 2, '2025-05-06 12:31:24', '2025-05-06 12:31:24');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (12, 6, 2, '2025-04-04 19:16:37', '2025-04-04 19:16:37');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 10, 2, '2025-04-30 14:03:35', '2025-04-30 14:03:35');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (9, 2, 2, '2025-06-23 23:05:08', '2025-06-23 23:05:08');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 4, 2, '2025-06-12 12:38:04', '2025-06-12 12:38:04');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (7, 4, 2, '2025-04-23 01:50:31', '2025-04-23 01:50:31');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (11, 5, 2, '2025-04-04 15:56:41', '2025-04-04 15:56:41');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 8, 2, '2025-04-22 15:49:51', '2025-04-22 15:49:51');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 10, 2, '2025-05-14 22:02:50', '2025-05-14 22:02:50');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (15, 9, 2, '2025-06-01 01:36:34', '2025-06-01 01:36:34');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (4, 3, 2, '2025-05-02 10:00:41', '2025-05-02 10:00:41');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (16, 3, 2, '2025-05-13 20:46:13', '2025-05-13 20:46:13');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (21, 3, 2, '2025-05-21 23:04:38', '2025-05-21 23:04:38');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 2, 2, '2025-04-19 18:22:49', '2025-04-19 18:22:49');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 7, 2, '2025-04-13 02:51:04', '2025-04-13 02:51:04');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (10, 2, 2, '2025-05-22 05:50:21', '2025-05-22 05:50:21');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (3, 3, 2, '2025-06-27 13:43:37', '2025-06-27 13:43:37');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (6, 1, 2, '2025-04-01 12:39:22', '2025-04-01 12:39:22');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (11, 6, 2, '2025-05-21 11:44:58', '2025-05-21 11:44:58');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 5, 2, '2025-05-16 23:17:38', '2025-05-16 23:17:38');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (13, 6, 2, '2025-05-10 02:50:06', '2025-05-10 02:50:06');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 5, 2, '2025-06-05 06:50:11', '2025-06-05 06:50:11');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (8, 6, 2, '2025-05-09 02:38:54', '2025-05-09 02:38:54');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (2, 1, 2, '2025-05-18 15:21:55', '2025-05-18 15:21:55');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 6, 2, '2025-06-19 05:44:05', '2025-06-19 05:44:05');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (10, 1, 2, '2025-04-12 12:01:43', '2025-04-12 12:01:43');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (1, 7, 2, '2025-05-25 05:16:27', '2025-05-25 05:16:27');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 3, 2, '2025-05-27 21:20:17', '2025-05-27 21:20:17');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (5, 6, 2, '2025-05-01 09:01:44', '2025-05-01 09:01:44');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (7, 4, 2, '2025-04-13 04:14:00', '2025-04-13 04:14:00');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (18, 4, 2, '2025-05-02 13:14:12', '2025-05-02 13:14:12');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (20, 3, 2, '2025-04-14 04:27:53', '2025-04-14 04:27:53');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (19, 7, 2, '2025-04-24 19:26:23', '2025-04-24 19:26:23');
INSERT INTO finish_books (book_id, user_id, type_id, created_at, updated_at) VALUES (11, 1, 2, '2025-06-15 23:43:45', '2025-06-15 23:43:45');


(1, 1, 'ファンタジーの冒険', '作家1', '出版社A', '図書館', 250, 'book1.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 2, '謎解きの館', '作家2', '出版社B', '書店', 300, 'book2.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 3, '戦いの記録', '作家3', '出版社C', 'プレゼント', 220, 'book3.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 4, '恐怖の森', '作家4', '出版社D', '教室', 180, 'book4.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 5, '笑いの時間', '作家5', '出版社E', '自宅', 160, 'book5.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 6, '平凡な日々', '作家6', '出版社F', '図書館', 210, 'book6.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 7, 'かわいい動物たち', '作家7', '出版社G', '書店', 90, 'book7.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 8, '昔話の世界', '作家8', '出版社H', '教室', 130, 'book8.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 9, '文学への招待', '作家9', '出版社I', '自宅', 340, 'book9.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1,10, 'リアルな物語', '作家10', '出版社J', '図書館', 280, 'book10.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1,11, 'エッセイ日和', '作家11', '出版社K', '書店', 200, 'book11.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1,12, 'わかりやすい学習', '作家12', '出版社L', '教室', 150, 'book12.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1,13, '動物図鑑', '作家13', '出版社M', 'プレゼント', 310, 'book13.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1,14, '知識の泉', '作家14', '出版社N', '自宅', 230, 'book14.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1,15, '不思議な世界', '作家15', '出版社O', '図書館', 270, 'book15.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 1, '冒険ファンタジー2', '作家16', '出版社P', '書店', 240, 'book16.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 2, '続・謎解きの館', '作家17', '出版社Q', '自宅', 190, 'book17.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 3, '新・戦いの記録', '作家18', '出版社R', '教室', 300, 'book18.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 4, 'もう一つの恐怖', '作家19', '出版社S', '図書館', 210, 'book19.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 5, '続・笑いの時間', '作家20', '出版社T', '書店', 170, 'book20.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO books(user_id, genre_id, title, author, publisher, gets, page, cover, created_at, updated_at) VALUES
(2, 6, 'グレッグのダメ日記', 'ジェフ・キニー', 'ポプラ社', '教室', 250, 'book21.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


insert into finish_books_type(type, created_at, updated_at) values('未読了',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into finish_books_type(type, created_at, updated_at) values('読了',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
insert into finish_books(book_id, user_id, type_id, created_at, updated_at) values(21, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO users (Id, type_id, name, users_id, password, mail_address, trophy_id,
statuses_id, grade, school_class, created_at, updated_at)
VALUES
(30, 3, '山本 当麻', ' family7', 'password', 'adminuser@example.com', 3, 1, 3, 2,
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(31,2,'高橋一郎','family5', 'password', 'adminuser@example.com',3,1,2,1,
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(32,2, '伊藤太郎','family6', 'password', 'adminuser@example.com',3,1,3,1,
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(33,2, '中村海斗','family8', 'password', 'adminuser@example.com',3,1,1,3,
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(34,3, '加藤信二','family9', 'password', 'adminuser@example.com',3,1,3,3,
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


