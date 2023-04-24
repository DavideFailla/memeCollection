package org.generation.italy.memeCollection.model.data.abstractions;

import org.generation.italy.memeCollection.model.entities.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends GenericRepository<Card>{
    List<Card> findByAlbumAndPlayer(Album album, Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = :album AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                    FROM Meme m
                                                                                    WHERE m.name LIKE :cardName )
                    """
    ) //da array vuoto
    List<Card> findInAlbumByCardName(Album album, String cardName, Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = :album AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName AND m.rarity = :rarity )
                    """
    )
    List<Card> findInAlbumByCardNameAndRarity(Album album, String cardName, Player player,Rarity rarity);
    @Query(
           """
                    FROM Card c
                    WHERE c.album = :album AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName AND m.funLevel = :funLevel )
                    """
    )
    List<Card> findInAlbumByCardNameAndFunLevel(Album album, String cardName, Player player,FunLevel funLevel);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = :album AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName AND m.rarity = :rarity AND m.funLevel = :funLevel )
                    """
    )
    List<Card> findInAlbumByCardNameAndFunLevelAndRarity(FunLevel funLevel, String cardName, Rarity rarity, Player player, Album album);


    @Query(
             """
                    FROM Card c
                    WHERE c.album = :album AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.funLevel = :funLevel )
                    """
    )
    List<Card> findInAlbumByFunLevel(FunLevel funLevel, Player player, Album album);

    @Query(
            """
                    FROM Card c
                    WHERE c.album = :album AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity )
                    """
    )
    List<Card> findInAlbumByRarity(Rarity rarity, Player player, Album album);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = :album AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity AND m.funLevel = :funLevel )
                    """
    )
    List<Card> findInAlbumByFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player, Album album);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName )
                    """
    )
    List<Card> findInDuplicatesByCardName(String cardName,Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName AND m.edition = :edition )
                    """
    )
    List<Card> findInDuplicatesByCardNameAndEdition(String cardName,Player player,Edition edition);
    @Query(
            """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE  m.edition = :edition )
                    """
    )
    List<Card> findInDuplicatesByEdition(Player player,Edition edition);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.funLevel = :funLevel AND m.edition = :edition )
                    """
    )
    List<Card> findInDuplicatesByEditionAndFunLevel(FunLevel funLevel, Player player, Edition edition);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.funLevel = :funLevel )
                    """
    )
    List<Card> findInDuplicatesByFunLevel(FunLevel funLevel, Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName AND m.funLevel = :funLevel AND m.edition = :edition )
                    """
    )
    List<Card> findInDuplicatesByEditionAndCardNameAndFunLevel(String cardName, FunLevel funLevel, Player player, Edition edition);
    @Query(
            """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName AND m.funLevel = :funLevel )
                    """
    )
    List<Card> findInDuplicatesByCardNameAndFunLevel(String cardName, FunLevel funLevel, Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity AND m.edition = :edition )
                    """
    )
    List<Card> findInDuplicatesByEditionAndRarity(Rarity rarity, Player player, Edition edition);
    @Query(
            """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity )
                    """
    )
    List<Card> findInDuplicatesByRarity(Rarity rarity, Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE  m.name LIKE :cardName AND m.rarity = :rarity AND m.edition = :edition )
                    """
    )
    List<Card> findInDuplicatesByEditionAndCardNameAndRarity(String cardName, Rarity rarity, Player player, Edition edition);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE  m.name LIKE :cardName AND m.rarity = :rarity )
                    """
    )
    List<Card> findInDuplicatesByCardNameAndRarity(String cardName, Rarity rarity, Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity AND m.funLevel = :funLevel AND m.edition = :edition)
                    """
    )
    List<Card> findInDuplicatesByEditionAndFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player, Edition edition);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity AND m.funLevel = :funLevel)
                    """
    )
    List<Card> findInDuplicatesByFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName AND m.rarity = :rarity AND m.funLevel = :funLevel AND m.edition = :edition)
                    """
    )
    List<Card> findInDuplicatesByEditionAndCardNameAndFunLevelAndRarity(String cardName, FunLevel funLevel, Rarity rarity, Player player, Edition edition);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player AND c.meme.id IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.name LIKE :cardName AND m.rarity = :rarity AND m.funLevel = :funLevel)
                    """
    )
    List<Card> findInDuplicatesByCardNameAndFunLevelAndRarity(String cardName, FunLevel funLevel, Rarity rarity, Player player);
    @Query(
             """
                    FROM Card c
                    WHERE c.album = null AND c.player = :player 
                    """
    )
    List<Card> findInDuplicatesAllCards(Player player);

    @Query(
             """
                FROM Card c
                WHERE c.album = :album AND c.player = :player
             """
    )
    List<Card> findByAlbumAndPlayer(Player player, Album album);
}
