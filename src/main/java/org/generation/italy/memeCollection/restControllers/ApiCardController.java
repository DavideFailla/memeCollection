package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.entities.Card;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCardController {
    private GenericService<Card> service;
    private AbstractGameService gameService;

    public ApiCardController(GenericRepository<Card> service, AbstractGameService gameService) {
        this.service = new GenericService<>(service);
        this.gameService = gameService;
    }
}
