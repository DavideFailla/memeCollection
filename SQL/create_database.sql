DROP TABLE IF EXISTS player,card,album,deck;

DROP TYPE IF EXISTS fun_level, rarity, edition;

CREATE TYPE fun_level AS ENUM ('PATHETIC', 'BASIC', 'LOL', 'LMAO', 'RIP');
CREATE TYPE rarity AS ENUM ('COMMON', 'UNCOMMON', 'RARE', 'EPIC', 'LEGENDARY');
CREATE TYPE edition AS ENUM ('OG','GEN_Z');


CREATE TABLE player(
	id_player BIGINT NOT NULL,
	firstname VARCHAR(32) NOT NULL,
	lastname VARCHAR(32) NOT NULL,
	dob DATE NOT NULL,
	email VARCHAR(50) NOT NULL,
	nickname VARCHAR(32) NOT NULL,
	password VARCHAR(32) NOT NULL,
	money DECIMAL NOT NULL,
	CONSTRAINT PK_player PRIMARY KEY(id_player)
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
    CONSTRAINT FK_meme PRIMARY KEY (id_meme)
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


	--SELECT setval('sequence_name', 1, FALSE); per resettare la sequenza












