package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.entities.Album;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiAlbumController {
    private GenericService<Album> service;
    private AbstractGameService gameService;
    public ApiAlbumController(GenericRepository<Album> albumGenericRepository, AbstractGameService gameService){
        this.service = new GenericService<>(albumGenericRepository);
        this.gameService = gameService;
    }
}
