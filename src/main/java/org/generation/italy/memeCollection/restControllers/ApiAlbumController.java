package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.entities.Album;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;

public class ApiAlbumController {
    private GenericService<Album> service;
    private AbstractGameService gameService;
    public ApiAlbumController(GenericRepository<Album> albumGenericRepository, AbstractGameService gameService){
        this.gameService = gameService;
        this.service = new GenericService<>(albumGenericRepository);
    }
}
