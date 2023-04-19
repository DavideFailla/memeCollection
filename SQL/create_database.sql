DROP TABLE IF EXISTS token,_user,player,card,album,meme;

DROP TYPE IF EXISTS fun_level, rarity, edition;

CREATE TYPE fun_level AS ENUM ('PATHETIC', 'BASIC', 'LOL', 'LMAO', 'RIP');
CREATE TYPE rarity AS ENUM ('COMMON', 'UNCOMMON', 'RARE', 'EPIC', 'LEGENDARY');
CREATE TYPE edition AS ENUM ('OG','GEN_Z');

CREATE TABLE _user(
	id_user INTEGER NOT NULL,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	dob DATE NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	role VARCHAR(255) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY(id_user)
);

CREATE SEQUENCE user_sequence
	start 1
	increment 1
	OWNED BY _user.id_user;

CREATE TABLE token(
	id_token INTEGER NOT NULL,
	token VARCHAR(255) NOT NULL,
	revoked BOOLEAN NOT NULL,
	expired BOOLEAN NOT NULL,
	token_type VARCHAR(255) NOT NULL,
	id_user INTEGER NOT NULL,
	CONSTRAINT PK_token PRIMARY KEY(id_token),
	CONSTRAINT FK_token_user FOREIGN KEY(id_user)
		REFERENCES _user(id_user)
);

CREATE SEQUENCE token_sequence
	start 1
	increment 1
	OWNED BY token.id_token;

CREATE TABLE player(
	id_player BIGINT NOT NULL,
	nickname VARCHAR(32) NOT NULL,
	money MONEY NOT NULL,
	id_user INTEGER NOT NULL,
	CONSTRAINT PK_player PRIMARY KEY(id_player),
	CONSTRAINT FK_player_user FOREIGN KEY(id_user)
		REFERENCES _user(id_user)
);

CREATE SEQUENCE player_sequence
	start 1
	increment 1
	OWNED BY player.id_player;

CREATE TABLE album
(
	id_album BIGINT NOT NULL,
	title VARCHAR(32) NOT NULL,
	description VARCHAR(32) NOT NULL,
	edition edition NOT NULL,
	id_player BIGINT NOT NULL,
	CONSTRAINT PK_album PRIMARY KEY(id_album),
	CONSTRAINT FK_album_player FOREIGN KEY(id_player)
		REFERENCES player(id_player)
);

CREATE SEQUENCE album_sequence
	start 1
	increment 1
	OWNED BY album.id_album;

CREATE TABLE meme(
    id_meme BIGINT NOT NULL,
    name VARCHAR(32) NOT NULL,
    edition edition NOT NULL,
    fun_level fun_level NOT NULL,
    rarity rarity NOT NULL,
    CONSTRAINT PK_meme PRIMARY KEY (id_meme)
);

CREATE SEQUENCE meme_sequence
	start 1
	increment 1
	OWNED BY meme.id_meme;

CREATE TABLE card(
	id_card BIGINT NOT NULL,
	id_album BIGINT NULL,
	id_player BIGINT NULL,
	id_meme BIGINT NOT NULL,
	CONSTRAINT PK_card PRIMARY KEY(id_card),
	CONSTRAINT FK_card_album FOREIGN KEY (id_album)
		REFERENCES album(id_album),
	CONSTRAINT FK_card_player FOREIGN KEY (id_player)
		REFERENCES player(id_player),
    CONSTRAINT FK_card_meme FOREIGN KEY (id_meme)
        REFERENCES meme(id_meme)
);

CREATE SEQUENCE card_sequence
	start 1
	increment 1
	OWNED BY card.id_card;












