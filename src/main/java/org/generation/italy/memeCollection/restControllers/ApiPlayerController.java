package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.data.exceptions.DataException;
import org.generation.italy.memeCollection.model.dtos.CardDto;
import org.generation.italy.memeCollection.model.dtos.PlayerDto;
import org.generation.italy.memeCollection.model.entities.Album;
import org.generation.italy.memeCollection.model.entities.Card;
import org.generation.italy.memeCollection.model.entities.Edition;
import org.generation.italy.memeCollection.model.entities.Player;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/players")
@CrossOrigin
public class ApiPlayerController {
    private GenericService<Player> service;
    private AbstractGameService gameService;

    public ApiPlayerController(GenericRepository<Player> service, AbstractGameService gameService){
        this.service = new GenericService<>(service);
        this.gameService = gameService;
    }

    @PostMapping("/createPack")
    ResponseEntity<PlayerDto> assignCardsToPlayer(@RequestParam long playerId){
        try {
            List<Card> cards = gameService.createPack(Edition.OG);
            Optional<Player> player = service.findById(playerId);
            if(player.isPresent()){
                return ResponseEntity.ok().body(PlayerDto.fromEntity(gameService.assignCardToPlayer(cards,player.get())));
            }
            return ResponseEntity.notFound().build();
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/findAllPlayers")
    public ResponseEntity<List<PlayerDto>> findAll(){
        try {
            List<Player> players = this.service.findAll();
            return ResponseEntity.ok().body(PlayerDto.fromEntityList(players));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
