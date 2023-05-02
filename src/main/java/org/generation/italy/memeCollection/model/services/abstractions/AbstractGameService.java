package org.generation.italy.memeCollection.model.services.abstractions;

import org.generation.italy.memeCollection.model.entities.*;

import java.math.BigDecimal;
import java.util.List;

public interface AbstractGameService {
    Album findAlbumByEditionAndPlayer(Edition edition, Player player);
    Player findPlayerByEmail(String email);
    List<Card> createPack(Edition edition);
    Player assignCardToPlayer(List<Card> pack, Player player, BigDecimal packCost);
    List<Card> showAlbum(Player player, Album album);
    Player getPlayerFromId(long playerId);
    Album getAlbumFromId(long albumId);
    List<Card> findInAlbumByCardName(Album album, String cardName, Player player);
    List<Card> findInAlbumByCardNameAndRarity(Album album, String cardName, Player player,Rarity rarity);
    List<Card> findInAlbumByCardNameAndFunLevel(Album album, String cardName, Player player,FunLevel funLevel);
    List<Card> findInAlbumByCardNameAndFunLevelAndRarity(FunLevel funLevel, String cardName, Rarity rarity, Player player, Album album);
    List<Card> findInAlbumByFunLevel(FunLevel funLevel, Player player, Album album);
    List<Card> findInAlbumByRarity(Rarity rarity, Player player, Album album);
    List<Card> findInAlbumByFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player, Album album);
    List<Card> findInDuplicatesByCardName(String cardName,Player player);

    List<Card> findInDuplicatesByCardNameAndEdition(String cardName,Player player,Edition edition);

    List<Card> findInDuplicatesByEdition(Player player,Edition edition);

    List<Card> findInDuplicatesByEditionAndFunLevel(FunLevel funLevel, Player player, Edition edition);

    List<Card> findInDuplicatesByFunLevel(FunLevel funLevel, Player player);

    List<Card> findInDuplicatesByEditionAndCardNameAndFunLevel(String cardName, FunLevel funLevel, Player player, Edition edition);

    List<Card> findInDuplicatesByCardNameAndFunLevel(String cardName, FunLevel funLevel, Player player);

    List<Card> findInDuplicatesByEditionAndRarity(Rarity rarity, Player player, Edition edition);

    List<Card> findInDuplicatesByRarity(Rarity rarity, Player player);

    List<Card> findInDuplicatesByEditionAndCardNameAndRarity(String cardName, Rarity rarity, Player player, Edition edition);

    List<Card> findInDuplicatesByCardNameAndRarity(String cardName, Rarity rarity, Player player);

    List<Card> findInDuplicatesByEditionAndFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player, Edition edition);

    List<Card> findInDuplicatesByFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player);

    List<Card> findInDuplicatesByEditionAndCardNameAndFunLevelAndRarity(String cardName, FunLevel funLevel, Rarity rarity, Player player, Edition edition);

    List<Card> findInDuplicatesByCardNameAndFunLevelAndRarity(String cardName, FunLevel funLevel, Rarity rarity, Player player);

    List<Card> findInDuplicatesAllCards(Player player);


    List<Card> findInAlbumAllCards(Player player, Album album);
}
