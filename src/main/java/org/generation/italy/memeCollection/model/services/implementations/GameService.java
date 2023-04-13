package org.generation.italy.memeCollection.model.services.implementations;

import org.generation.italy.memeCollection.model.data.abstractions.*;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements AbstractGameService {
    private AlbumRepository albumRepo;
    private CardRepository cardRepo;
    private DeckRepository deckRepo;
    private PackRepository packRepo;
    private PlayerRepository playerRepo;

    @Autowired
    public GameService(AlbumRepository albumRepo, CardRepository cardRepo, DeckRepository deckRepo, PackRepository packRepo, PlayerRepository playerRepo) {
        this.albumRepo = albumRepo;
        this.cardRepo = cardRepo;
        this.deckRepo = deckRepo;
        this.packRepo = packRepo;
        this.playerRepo = playerRepo;
    }
}
