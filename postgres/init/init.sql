-- 作成したDBに接続
\c booksite;
-- テーブル作成
DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK (
	id integer NOT NULL PRIMARY KEY,
	title char(100) NOT NULL,
	author char(100) NOT NULL,
	publish_date timestamp NOT NULL,
	price integer NOT NULL,
	publisher char(100) NOT NULL
);
-- ID用シーケンス作成
CREATE SEQUENCE book_id_seq START 1;
-- サンブルデータの登録
INSERT INTO BOOK (id, title, author, publish_date, price, publisher) VALUES(nextval('book_id_seq'), 'King', 'King.A', '2024-01-02', 2000, 'KingPub');

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
	id integer NOT NULL PRIMARY KEY,
	first_name char(100) NOT NULL,
	last_name char(100) NOT NULL,
	password_bash char(100) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- ID用シーケンス作成
CREATE SEQUENCE users_id_seq START 1;
-- サンブルデータの登録
INSERT INTO USERS (id, first_name, last_name, password_bash) VALUES(nextval('users_id_seq'), 'yamada', 'taro', '86j899ew6qqire70215ou1o080');

DROP TABLE IF EXISTS FAVORITE;
CREATE TABLE FAVORITE (
	user_id integer NOT NULL,
	book_id integer NOT NULL,
    PRIMARY KEY (user_id, book_id)
);
-- サンブルデータの登録
INSERT INTO FAVORITE (user_id, book_id) VALUES(1, 1);
