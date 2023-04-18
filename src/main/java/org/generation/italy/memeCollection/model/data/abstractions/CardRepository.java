package org.generation.italy.memeCollection.model.data.abstractions;

import org.generation.italy.memeCollection.model.entities.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends GenericRepository<Card>{
    List<Card> findByAlbumAndPlayer(Album album, Player player);
}
