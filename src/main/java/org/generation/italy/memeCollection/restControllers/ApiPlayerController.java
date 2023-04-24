package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.data.exceptions.DataException;
import org.generation.italy.memeCollection.model.dtos.PlayerDto;
import org.generation.italy.memeCollection.model.entities.Card;
import org.generation.italy.memeCollection.model.entities.Edition;
import org.generation.italy.memeCollection.model.entities.Player;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.AuthenticationService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    ResponseEntity<PlayerDto> assignCardsToPlayer(Principal principal){
        Player player = gameService.findPlayerByEmail(principal.getName());
        List<Card> cards = gameService.createPack(Edition.OG);
        return ResponseEntity.ok().body(PlayerDto.fromEntity(gameService.assignCardToPlayer(cards,player)));
    }

    @GetMapping("/findAllPlayers")
    public ResponseEntity<List<PlayerDto>> findAll(Principal principal){
        try {
            List<Player> players = this.service.findAll();
            return ResponseEntity.ok().body(PlayerDto.fromEntityList(players));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
