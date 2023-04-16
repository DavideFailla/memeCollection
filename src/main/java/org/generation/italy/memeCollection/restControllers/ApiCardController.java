package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.data.exceptions.DataException;
import org.generation.italy.memeCollection.model.dtos.CardDto;
import org.generation.italy.memeCollection.model.entities.*;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/cards")
public class ApiCardController {
    private GenericService<Card> service;
    private AbstractGameService gameService;

    @Autowired
    public ApiCardController(GenericRepository<Card> service, AbstractGameService gameService) {
        this.service = new GenericService<>(service);
        this.gameService = gameService;
    }

//    @PostMapping()
//    public ResponseEntity<CardDto> create(@RequestBody Card c){
//        try {
//            var cardResult = this.service.create(c);
//            CardDto dtoResult = CardDto.fromEntity(c);
//            return ResponseEntity.created(URI.create("/api/cards" + cardResult.getId())).body(dtoResult);
//        } catch (DataException e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().build();
//        }
//    }

    @PostMapping()
    public ResponseEntity<List<Card>> createPack(){
        Pack p = new Pack(20, Edition.OG);
        List<Album> albums = new ArrayList<>();
        Album album = new Album(1,"ogalbum","cool",
                null,Edition.OG,null,null);
        albums.add(album);
        Player player = new Player(1,"davide"
                ,"failla", LocalDate.now(),"dasf","byawa",
                "wilduce",albums,200);
        List<Card>cards = gameService.createPack(p,player);
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping()
    public ResponseEntity<List<CardDto>> findAll(){
        try {
            List<Card> cards = this.service.findAll();
            return ResponseEntity.ok().body(CardDto.fromEntityList(cards));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
