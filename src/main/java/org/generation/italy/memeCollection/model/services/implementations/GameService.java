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
import java.util.Optional;
import java.util.Random;

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
        Pageable pag = drawRandomCard(edition,Rarity.COMMON);
        meme1 = memeRepo.findByEditionAndRarity(edition, Rarity.COMMON,pag);
        pack.add(new Card(0,null,null,meme1.get(0)));
        double random = Math.floor(Math.random()*3);
        if(random == 0 || random == 1){
            pag = drawRandomCard(edition,Rarity.UNCOMMON);
            meme2 = memeRepo.findByEditionAndRarity(edition, Rarity.UNCOMMON,pag);
        }else {
            pag = drawRandomCard(edition,Rarity.COMMON);
            meme2 = memeRepo.findByEditionAndRarity(edition, Rarity.COMMON,pag);
        }
        pack.add(new Card(0,null,null,meme2.get(0)));
        random = Math.floor(Math.random()*6);
        if(random == 0 || random == 1 || random == 2){
            pag = drawRandomCard(edition,Rarity.RARE);
            meme3 = memeRepo.findByEditionAndRarity(edition, Rarity.RARE,pag);
        }else if(random == 3 || random == 4){
            pag = drawRandomCard(edition,Rarity.EPIC);
            meme3 = memeRepo.findByEditionAndRarity(edition, Rarity.EPIC,pag);
        }else {
            pag = drawRandomCard(edition,Rarity.LEGENDARY);
            meme3 = memeRepo.findByEditionAndRarity(edition, Rarity.LEGENDARY,pag);
        }
        pack.add(new Card(0,null,null,meme3.get(0)));
        return pack;
    }

    public int getCountByEditionAndRarity(Edition edition, Rarity rarity){
        return memeRepo.countByEditionAndRarity(edition, rarity);
    }

    private Pageable drawRandomCard(Edition e, Rarity r){
        int num = getCountByEditionAndRarity(e,r);
        int randomRarity = random.nextInt(num);
        return PageRequest.of(randomRarity,1);
    }

    @Override
    public Player assignCardToPlayer(List<Card> pack, Player player){
        Album playerAlbum = albumRepo.findByEditionAndPlayer(Edition.OG,player);
        for (Card c : pack){
            c.setPlayer(player);
                if(playerAlbum.isCardADuplicate(c)){ //allora la carta è un duplicato
                    playerAlbum.addDuplicates(c);
                }else {
                    c.setAlbum(playerAlbum);
                    playerAlbum.addCard(c);
                }
                cardRepo.save(c);
        }
        return player;
    }

    @Override
    public List<Card> showAlbum(Player player, Album album) {
        return cardRepo.findByAlbumAndPlayer(album,player);
    }

    @Override
    public Player getPlayerFromId(long playerId) {
        Optional<Player> player = playerRepo.findById(playerId);
        if(player.isPresent()){
            return player.get();
        }
        return null;
    }

    @Override
    public Album getAlbumFromId(long albumId) {
        Optional<Album> album = albumRepo.findById(albumId);
        if(album.isPresent()){
            return album.get();
        }
        return null;
    }
}

//                    else {
//                        c.setAlbum(playerAlbum);
//                        playerAlbum.getCardSet().add(c);
//                        cardRepo.save(c);
//                    }
