package org.generation.italy.memeCollection.model.services.abstractions;

import org.generation.italy.memeCollection.model.entities.Card;
import org.generation.italy.memeCollection.model.entities.Pack;
import org.generation.italy.memeCollection.model.entities.Player;

import java.util.List;

public interface AbstractGameService {
    public List<Card> createPack(Pack p, Player player);
}
