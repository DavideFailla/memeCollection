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

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/cards")
@CrossOrigin
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

//    @PostMapping()
//    public ResponseEntity<List<Card>> createPack(){
//        List<Card>cards = gameService.createPack(Edition.OG);
//        if(cards.size() != 0){
//            return ResponseEntity.ok().body(cards);
//        }
//        return ResponseEntity.notFound().build();
//    }



    @GetMapping("/showAllCards")
    public ResponseEntity<List<CardDto>> findAll(){
        try {
            List<Card> cards = this.service.findAll();
            return ResponseEntity.ok().body(CardDto.fromEntityList(cards));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/album")
    public ResponseEntity<List<CardDto>> showAlbum(Principal principal, @RequestParam String albumEdition){
        Edition edition = Edition.valueOf(albumEdition);
        Player player = gameService.findPlayerByEmail(principal.getName());
        Album album = gameService.findAlbumByEditionAndPlayer(edition,player);
        List<Card> result = gameService.showAlbum(player,album);
        if(result.size() > 0){
            return ResponseEntity.ok().body(CardDto.fromEntityList(result));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findInAlbum")
    public ResponseEntity<List<CardDto>> findInAlbumByCardNameAndFunLevelAndRarity(@RequestParam long albumId,
                                                                                   Principal principal,
                                                                                   @RequestParam(required = false) String cardName,
                                                                                   @RequestParam(required = false) FunLevel funLevel,
                                                                                   @RequestParam(required = false) Rarity rarity){

        Player player = gameService.findPlayerByEmail(principal.getName());
        Album album = gameService.getAlbumFromId(albumId);
        if(funLevel == null && rarity == null && cardName == null ){
            List<Card> lc = gameService.findInAlbumAllCards(player,album);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        } else if(funLevel == null && rarity == null ){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInAlbumByCardName(album,cardName,player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        } else if(funLevel == null && cardName == null ){
            List<Card> lc = gameService.findInAlbumByRarity(rarity, player, album);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(rarity == null && cardName == null ){
            List<Card> lc = gameService.findInAlbumByFunLevel(funLevel, player, album);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(funLevel == null ){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInAlbumByCardNameAndRarity(album, cardName, player, rarity);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        } else if(rarity == null){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInAlbumByCardNameAndFunLevel(album, cardName, player, funLevel);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(cardName == null){
            List<Card> lc = gameService.findInAlbumByFunLevelAndRarity(funLevel, rarity, player, album);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInAlbumByCardNameAndFunLevelAndRarity(funLevel, cardName, rarity, player, album);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));

    }
    @GetMapping("/findInDuplicates")
    public ResponseEntity<List<CardDto>> findInDuplicatesByCardNameAndEditionAndFunLevelAndRarity(Principal principal,
                                                                                                  @RequestParam(required = false) Edition edition,
                                                                                                  @RequestParam(required = false) String cardName,
                                                                                                  @RequestParam(required = false) FunLevel funLevel,
                                                                                                  @RequestParam(required = false) Rarity rarity){
        Player player = gameService.findPlayerByEmail(principal.getName());
        if(funLevel == null && rarity == null && cardName == null && edition == null ){
            List<Card> lc = gameService.findInDuplicatesAllCards(player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if (funLevel == null && rarity == null && edition == null) {
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInDuplicatesByCardName(cardName, player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        } else if(funLevel == null && rarity == null && cardName == null){
            List<Card> lc = gameService.findInDuplicatesByEdition(player, edition);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        } else if(edition == null && rarity == null && cardName == null){
            List<Card> lc = gameService.findInDuplicatesByFunLevel(funLevel, player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        } else if(edition == null && funLevel == null && cardName == null){
            List<Card> lc = gameService.findInDuplicatesByRarity(rarity, player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        } else if(funLevel == null && cardName == null ){
            List<Card> lc = gameService.findInDuplicatesByEditionAndRarity(rarity, player, edition);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(rarity == null && cardName == null ){
            List<Card> lc = gameService.findInDuplicatesByEditionAndFunLevel(funLevel, player,edition);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(rarity == null && edition == null ){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInDuplicatesByCardNameAndFunLevel(cardName, funLevel, player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(edition == null && cardName == null ){
            List<Card> lc = gameService.findInDuplicatesByFunLevelAndRarity(funLevel,rarity,player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(edition == null && funLevel == null ){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInDuplicatesByCardNameAndRarity(cardName, rarity, player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(rarity == null && funLevel == null ){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInDuplicatesByCardNameAndEdition(cardName, player, edition);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(funLevel == null ){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInDuplicatesByEditionAndCardNameAndRarity(cardName, rarity, player, edition);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        } else if(rarity == null){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInDuplicatesByEditionAndCardNameAndFunLevel(cardName, funLevel, player, edition);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(cardName == null){
            List<Card> lc = gameService.findInDuplicatesByEditionAndFunLevelAndRarity(funLevel, rarity, player, edition);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }else if(edition == null){
            cardName = "%" + cardName + "%";
            List<Card> lc = gameService.findInDuplicatesByCardNameAndFunLevelAndRarity(cardName, funLevel, rarity, player);
            return ResponseEntity.ok().body(CardDto.fromEntityList(lc));
        }
        cardName = "%" + cardName + "%";
        List<Card> lc = gameService.findInDuplicatesByEditionAndCardNameAndFunLevelAndRarity(cardName, funLevel, rarity, player, edition);
        return ResponseEntity.ok().body(CardDto.fromEntityList(lc));

    }
}
