package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.data.exceptions.DataException;
import org.generation.italy.memeCollection.model.dtos.PlayerDto;
import org.generation.italy.memeCollection.model.entities.Album;
import org.generation.italy.memeCollection.model.entities.Player;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/albums")
public class ApiAlbumController {
    private GenericService<Album> service;
    private AbstractGameService gameService;
    public ApiAlbumController(GenericRepository<Album> albumGenericRepository, AbstractGameService gameService){
        this.service = new GenericService<>(albumGenericRepository);
        this.gameService = gameService;
    }

    @GetMapping()
    public ResponseEntity<List<Album>> findAll(){
        try {
            List<Album> albums = this.service.findAll();
            return ResponseEntity.ok().body((albums));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
