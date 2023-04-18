package org.generation.italy.memeCollection.model.data.abstractions;

import org.generation.italy.memeCollection.model.entities.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends GenericRepository<Card>{
    @Query(
            value = """
                    FROM Card c
                    WHERE c.album.id = :album AND c.player.id = :player AND c IN ( SELECT m.name
                                                                                     FROM Meme m
                                                                                     WHERE m.name = :cardName )
                    """
    )
    Optional<Card> findInAlbumByCardName(Album album, String cardName, Player player);
    @Query(
            value = """
                    FROM Card c
                    WHERE c.album.id = null AND c.player.id = :player AND c IN ( SELECT m.name
                                                                                     FROM Meme m
                                                                                     WHERE m.name = :cardName )
                    """
    )
    List<Card> findInDuplicatesByCardName(String cardName,Player player);
    @Query(
            value = """
                    FROM Card c
                    WHERE c.album.id = :album AND c.player.id = :player AND c IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.funLevel = :funLevel )
                    """
    )
    List<Card> findInAlbumAndPlayerAndFunLevel(FunLevel funLevel,Player player,Album album);
    List<Card> findByAlbumAndPlayer(Album album, Player player);
    @Query(
            value = """
                    FROM Card c
                    WHERE c.album.id = :album AND c.player.id = :player AND c IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity )
                    """
    )
    List<Card> findInAlbumAndPlayerAndRarity(Rarity rarity,Player player,Album album);
    @Query(
            value = """
                    FROM Card c
                    WHERE c.album.id = :album AND c.player.id = :player AND c IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity AND m.funLevel = :funLevel )
                    """
    )
    List<Card> findInAlbumAndPlayerAndFunLevelAndRarity(FunLevel funLevel,Rarity rarity,Player player,Album album);
    @Query(
            value = """
                    FROM Card c
                    WHERE c.album.id = null AND c.player.id = :player AND c IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.funLevel = :funLevel AND m.edition = :edition )
                    """
    )
    List<Card> findInDuplicatesAndPlayerAndFunLevel(FunLevel funLevel,Player player,Edition edition);
    @Query(
            value = """
                    FROM Card c
                    WHERE c.album.id = null AND c.player.id = :player AND c IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity AND m.edition = :edition )
                    """
    )
    List<Card> findInDuplicatesAndPlayerAndRarity(Rarity rarity,Player player,Edition edition);
    @Query(
            value = """
                    FROM Card c
                    WHERE c.album.id = null AND c.player.id = :player AND c IN ( SELECT m.id
                                                                                     FROM Meme m
                                                                                     WHERE m.rarity = :rarity AND m.funLevel = :funLevel AND m.edition = :edition)
                    """
    )
    List<Card> findInDuplicatesAndPlayerAndFunLevelAndRarity(FunLevel funLevel,Rarity rarity,Player player,Edition edition);
}
