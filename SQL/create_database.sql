DROP TABLE IF EXISTS player,card,album,edition;

DROP TYPE IF EXISTS funlevel, rarity;

CREATE TYPE funlevel AS ENUM ('PATHETIC', 'BASIC', 'LOL', 'LMAO', 'RIP');
CREATE TYPE rarity AS ENUM ('COMMON', 'UNCOMMON', 'RARE', 'EPIC', 'LEGENDARY');

CREATE TABLE edition(
	id_edition BIGINT NOT NULL,
	name VARCHAR(32) NOT NULL,
	description VARCHAR(32) NOT NULL,
	CONSTRAINT PK_edition PRIMARY KEY(id_edition)
);

CREATE SEQUENCE edition_sequence
	start 1
	increment 1
	OWNED BY edition.id_edition;

CREATE TABLE album
(
	id_album BIGINT NOT NULL,
	title VARCHAR(32) NOT NULL,
	description VARCHAR(32) NOT NULL,
	id_edition BIGINT NOT NULL,
	CONSTRAINT PK_album PRIMARY KEY(id_album),
	CONSTRAINT FK_album_edition FOREIGN KEY (id_edition)
		REFERENCES edition(id_edition)
);

CREATE SEQUENCE album_sequence
	start 1
	increment 1
	OWNED BY album.id_album;

CREATE TABLE card(
	id_card BIGINT NOT NULL,
	name VARCHAR(32) NOT NULL,
	id_edition BIGINT NOT NULL,
	funlevel funlevel NOT NULL,
	rarity rarity NOT NULL,
	CONSTRAINT PK_card PRIMARY KEY(id_card),
	CONSTRAINT FK_card_edition FOREIGN KEY (id_edition)
		REFERENCES edition(id_edition)
);

CREATE SEQUENCE card_sequence
	start 1
	increment 1
	OWNED BY card.id_card;

CREATE TABLE player(
	id_player BIGINT NOT NULL,
	firstname VARCHAR(32) NOT NULL,
	lastname VARCHAR(32) NOT NULL,
	dob DATE NOT NULL,
	email VARCHAR(50) NOT NULL,
	nickname VARCHAR(32) NOT NULL,
	password VARCHAR(32) NOT NULL,
	id_album BIGINT NOT NULL,
	money DECIMAL NOT NULL,
	CONSTRAINT PK_player PRIMARY KEY(id_player),
	CONSTRAINT FK_player_album FOREIGN KEY(id_album)
		REFERENCES album(id_album)
);

CREATE SEQUENCE player_sequence
	start 1
	increment 1
	OWNED BY player.id_player;
