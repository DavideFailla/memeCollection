package org.generation.italy.memeCollection.model.services.abstractions;

import org.generation.italy.memeCollection.model.entities.Card;
import org.generation.italy.memeCollection.model.entities.Edition;
import org.generation.italy.memeCollection.model.entities.Pack;
import org.generation.italy.memeCollection.model.entities.Player;

import java.util.List;

public interface AbstractGameService {
    List<Card> createPack(Edition edition);
    Player assignCardToPlayer(List<Card> pack, Player player);
}
