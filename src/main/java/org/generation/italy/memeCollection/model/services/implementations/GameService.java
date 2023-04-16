package org.generation.italy.memeCollection.model.services.implementations;

import org.generation.italy.memeCollection.model.data.abstractions.*;
import org.generation.italy.memeCollection.model.entities.*;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService implements AbstractGameService {
    private AlbumRepository albumRepo;
    private CardRepository cardRepo;
    private PlayerRepository playerRepo;
    private MemeRepository memeRepo;

    @Autowired
    public GameService(AlbumRepository albumRepo, CardRepository cardRepo, PlayerRepository playerRepo, MemeRepository memeRepo) {
        this.albumRepo = albumRepo;
        this.cardRepo = cardRepo;
        this.playerRepo = playerRepo;
        this.memeRepo = memeRepo;
    }

    @Override
    public List<Card> createPack(Pack p, Player player){
        List<Card> pack = new ArrayList<>();
        Meme meme1;
        Meme meme2;
        Meme meme3;
            meme1 = memeRepo.findMemeByEditionAndRarity(p.getEdition(), Rarity.COMMON);
            double random = Math.floor(Math.random()*3);
            if(random == 0 || random == 1){
                meme2 = memeRepo.findMemeByEditionAndRarity(p.getEdition(), Rarity.UNCOMMON);
            }else {
                meme2 = memeRepo.findMemeByEditionAndRarity(p.getEdition(), Rarity.COMMON);
            }
            random = Math.floor(Math.random()*6);
            if(random == 0 || random == 1 || random == 2){
                meme3 = memeRepo.findMemeByEditionAndRarity(p.getEdition(), Rarity.RARE);
            }else if(random == 3 || random == 4){
                meme3 = memeRepo.findMemeByEditionAndRarity(p.getEdition(), Rarity.EPIC);
            }else {
                meme3 = memeRepo.findMemeByEditionAndRarity(p.getEdition(), Rarity.LEGENDARY);
            }
            Card c1 = cardRepo.save(new Card(1,player.getAlbumFromEdition(p.getEdition()),player,meme1));
            Card c2 = cardRepo.save(new Card(2,player.getAlbumFromEdition(p.getEdition()),player,meme2));
            Card c3 = cardRepo.save(new Card(3,player.getAlbumFromEdition(p.getEdition()),player,meme3));
            pack.add(c1);
            pack.add(c2);
            pack.add(c3);
            return pack;
    }
}
