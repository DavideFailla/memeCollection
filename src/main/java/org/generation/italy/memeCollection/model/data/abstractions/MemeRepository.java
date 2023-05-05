package org.generation.italy.memeCollection.model.data.abstractions;

import org.generation.italy.memeCollection.model.entities.Edition;
import org.generation.italy.memeCollection.model.entities.Meme;
import org.generation.italy.memeCollection.model.entities.Rarity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemeRepository extends GenericRepository<Meme>{
//    @Query(
//            value = """
//                    SELECT *
//                    FROM meme m
//                    WHERE m.edition = ?1 AND m.rarity = ?2
//                    ORDER BY RANDOM()
//                    LIMIT 1
//                    """,
//            nativeQuery = true)
//    @Query(
//            """
//                    FROM Meme m
//                    WHERE m.edition = :e AND m.rarity = :r
//                    """
//    )
    List<Meme> findByEditionAndRarity(Edition e, Rarity r, Pageable p);

    int countByEditionAndRarity(Edition e, Rarity r);

    @Query("""
            FROM Meme m
            """)
    List<Meme> allMemeNames();

    List<Meme> findByEdition(Edition edition);
}
