package org.generation.italy.memeCollection.model.data.abstractions;

import org.generation.italy.memeCollection.model.entities.Edition;
import org.generation.italy.memeCollection.model.entities.Meme;
import org.generation.italy.memeCollection.model.entities.Rarity;
import org.springframework.data.jpa.repository.Query;

public interface MemeRepository extends GenericRepository<Meme>{
    @Query(
            value = """
                    SELECT *
                    FROM meme m
                    WHERE m.edition = ? AND m.rarity = ?
                    ORDER BY RANDOM()
                    LIMIT 1
                    """,
            nativeQuery = true)
    Meme findMemeByEditionAndRarity(Edition e, Rarity r);
}
