package org.generation.italy.memeCollection.model.data.abstractions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.generation.italy.memeCollection.model.entities.Card;
import org.generation.italy.memeCollection.model.entities.Edition;
import org.generation.italy.memeCollection.model.entities.Pack;
import org.generation.italy.memeCollection.model.entities.Rarity;

import java.util.List;

public class CardRepositoryCustomImpl implements CardRepositoryCustom{
    @PersistenceContext
    EntityManager em;

}
