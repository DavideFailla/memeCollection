package org.generation.italy.memeCollection.model.services.implementations;

import org.generation.italy.memeCollection.model.data.abstractions.*;
import org.generation.italy.memeCollection.model.entities.*;
import org.generation.italy.memeCollection.model.entities.Rarity;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class GameService implements AbstractGameService {
    private AlbumRepository albumRepo;
    private CardRepository cardRepo;
    private PlayerRepository playerRepo;
    private MemeRepository memeRepo;
    private Random random = new Random();

    @Autowired
    public GameService(AlbumRepository albumRepo, CardRepository cardRepo, PlayerRepository playerRepo, MemeRepository memeRepo) {
        this.albumRepo = albumRepo;
        this.cardRepo = cardRepo;
        this.playerRepo = playerRepo;
        this.memeRepo = memeRepo;
    }

    @Override
    public List<Card> createPack(Edition edition){
        List<Card> pack = new ArrayList<>();
        List<Meme> meme1;
        List<Meme> meme2;
        List<Meme> meme3;
        Pageable pag = randomCard(edition,Rarity.COMMON);
        meme1 = memeRepo.findByEditionAndRarity(edition, Rarity.COMMON,pag);
        pack.add(new Card(0,null,null,meme1.get(0)));
        double random = Math.floor(Math.random()*3);
        if(random == 0 || random == 1){
            pag = randomCard(edition,Rarity.UNCOMMON);
            meme2 = memeRepo.findByEditionAndRarity(edition, Rarity.UNCOMMON,pag);
        }else {
            pag = randomCard(edition,Rarity.COMMON);
            meme2 = memeRepo.findByEditionAndRarity(edition, Rarity.COMMON,pag);
        }
        pack.add(new Card(0,null,null,meme2.get(0)));
        random = Math.floor(Math.random()*6);
        if(random == 0 || random == 1 || random == 2){
            pag = randomCard(edition,Rarity.RARE);
            meme3 = memeRepo.findByEditionAndRarity(edition, Rarity.RARE,pag);
        }else if(random == 3 || random == 4){
            pag = randomCard(edition,Rarity.EPIC);
            meme3 = memeRepo.findByEditionAndRarity(edition, Rarity.EPIC,pag);
        }else {
            pag = randomCard(edition,Rarity.LEGENDARY);
            meme3 = memeRepo.findByEditionAndRarity(edition, Rarity.LEGENDARY,pag);
        }
        pack.add(new Card(0,null,null,meme3.get(0)));
        return pack;
    }

    private Pageable randomCard(Edition e, Rarity r){
        int num = memeRepo.countByEditionAndRarity(e,r);
        int randomRarity = random.nextInt(num);
        return PageRequest.of(randomRarity,1);
    }

    private boolean isCardADuplicate(Album playerAlbum, Card c){
        for(Card card : playerAlbum.getCardSet()){
            if(c.getMeme().getName().equals(card.getMeme().getName())){ // se si verifica questo allora la carta è un duplicato
                playerAlbum.getCardDuplicates().add(c);
                cardRepo.save(c);
                return true;
            }
        }
        return false;
    }

    @Override
    public Player assignCardToPlayer(List<Card> pack, Player player){
        Album playerAlbum = albumRepo.findByEditionAndPlayer(Edition.OG,player);
        for (Card c : pack){
            c.setPlayer(player);
            if(playerAlbum.getCardSet().isEmpty()){ //allora sicuramente non ci sono duplicati
                c.setAlbum(playerAlbum);
                playerAlbum.getCardSet().add(c);
                cardRepo.save(c);
            }else {
                if(isCardADuplicate(playerAlbum,c)){ //allora la carta è un duplicato

                }else {
                    c.setAlbum(playerAlbum);
                    playerAlbum.getCardSet().add(c);
                    cardRepo.save(c);
                }
            }
        }
        return player;
    }
}

//                    else {
//                        c.setAlbum(playerAlbum);
//                        playerAlbum.getCardSet().add(c);
//                        cardRepo.save(c);
//                    }
