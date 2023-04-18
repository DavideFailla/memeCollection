package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.entities.Deck;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiDeckController {
    private GenericService<Deck> service;
    private AbstractGameService gameService;

    public ApiDeckController(GenericRepository<Deck> service, AbstractGameService gameService){
        this.service = new GenericService<>(service);
        this.gameService = gameService;
    }
}
//LAST VERSION
