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

    @Override
    public List<Card> findInAlbumByCardName(Album album, String cardName, Player player) {
        return cardRepo.findInAlbumByCardName(album,cardName,player);
    }

    @Override
    public List<Card> findInAlbumByCardNameAndRarity(Album album, String cardName, Player player, Rarity rarity) {
        return cardRepo.findInAlbumByCardNameAndRarity(album, cardName, player, rarity);
    }

    @Override
    public List<Card> findInAlbumByCardNameAndFunLevel(Album album, String cardName, Player player, FunLevel funLevel) {
        return cardRepo.findInAlbumByCardNameAndFunLevel(album, cardName, player, funLevel);
    }

    @Override
    public List<Card> findInAlbumByCardNameAndFunLevelAndRarity(FunLevel funLevel, String cardName, Rarity rarity, Player player, Album album) {
        return cardRepo.findInAlbumByCardNameAndFunLevelAndRarity(funLevel, cardName, rarity, player, album);
    }

    @Override
    public List<Card> findInAlbumByFunLevel(FunLevel funLevel, Player player, Album album) {
        return cardRepo.findInAlbumByFunLevel(funLevel, player, album);
    }

    @Override
    public List<Card> findInAlbumByRarity(Rarity rarity, Player player, Album album) {
        return cardRepo.findInAlbumByRarity(rarity, player, album);
    }

    @Override
    public List<Card> findInAlbumByFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player, Album album) {
        return cardRepo.findInAlbumByFunLevelAndRarity(funLevel, rarity, player, album);
    }

    @Override
    public List<Card> findInDuplicatesByCardName(String cardName, Player player) {
        return cardRepo.findInDuplicatesByCardName(cardName,player);
    }

    @Override
    public List<Card> findInDuplicatesByCardNameAndEdition(String cardName, Player player, Edition edition) {
        return cardRepo.findInDuplicatesByCardNameAndEdition(cardName, player, edition);
    }

    @Override
    public List<Card> findInDuplicatesByEdition(Player player, Edition edition) {
        return cardRepo.findInDuplicatesByEdition(player, edition);
    }

    @Override
    public List<Card> findInDuplicatesByEditionAndFunLevel(FunLevel funLevel, Player player, Edition edition) {
        return cardRepo.findInDuplicatesByEditionAndFunLevel(funLevel, player, edition);
    }

    @Override
    public List<Card> findInDuplicatesByFunLevel(FunLevel funLevel, Player player) {
        return cardRepo.findInDuplicatesByFunLevel(funLevel, player);
    }

    @Override
    public List<Card> findInDuplicatesByEditionAndCardNameAndFunLevel(String cardName, FunLevel funLevel, Player player, Edition edition) {
        return cardRepo.findInDuplicatesByEditionAndCardNameAndFunLevel(cardName, funLevel, player, edition);
    }

    @Override
    public List<Card> findInDuplicatesByCardNameAndFunLevel(String cardName, FunLevel funLevel, Player player) {
        return cardRepo.findInDuplicatesByCardNameAndFunLevel(cardName, funLevel, player);
    }

    @Override
    public List<Card> findInDuplicatesByEditionAndRarity(Rarity rarity, Player player, Edition edition) {
        return cardRepo.findInDuplicatesByEditionAndRarity(rarity, player, edition);
    }

    @Override
    public List<Card> findInDuplicatesByRarity(Rarity rarity, Player player) {
        return cardRepo.findInDuplicatesByRarity(rarity, player);
    }

    @Override
    public List<Card> findInDuplicatesByEditionAndCardNameAndRarity(String cardName, Rarity rarity, Player player, Edition edition) {
        return cardRepo.findInDuplicatesByEditionAndCardNameAndRarity(cardName, rarity, player, edition);
    }

    @Override
    public List<Card> findInDuplicatesByCardNameAndRarity(String cardName, Rarity rarity, Player player) {
        return cardRepo.findInDuplicatesByCardNameAndRarity(cardName, rarity, player);
    }

    @Override
    public List<Card> findInDuplicatesByEditionAndFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player, Edition edition) {
        return cardRepo.findInDuplicatesByEditionAndFunLevelAndRarity(funLevel, rarity, player, edition);
    }

    @Override
    public List<Card> findInDuplicatesByFunLevelAndRarity(FunLevel funLevel, Rarity rarity, Player player) {
        return cardRepo.findInDuplicatesByFunLevelAndRarity(funLevel, rarity, player);
    }

    @Override
    public List<Card> findInDuplicatesByEditionAndCardNameAndFunLevelAndRarity(String cardName, FunLevel funLevel, Rarity rarity, Player player, Edition edition) {
        return cardRepo.findInDuplicatesByEditionAndCardNameAndFunLevelAndRarity(cardName, funLevel, rarity, player, edition);
    }

    @Override
    public List<Card> findInDuplicatesByCardNameAndFunLevelAndRarity(String cardName, FunLevel funLevel, Rarity rarity, Player player) {
        return cardRepo.findInDuplicatesByCardNameAndFunLevelAndRarity(cardName, funLevel, rarity, player);
    }

    @Override
    public List<Card> findInDuplicatesAllCards(Player player) {
        return cardRepo.findInDuplicatesAllCards(player);
    }

    @Override
    public List<Card> findInAlbumAllCards(Player player, Album album) {
        return cardRepo.findByAlbumAndPlayer(player, album);
    }
}

//                    else {
//                        c.setAlbum(playerAlbum);
//                        playerAlbum.getCardSet().add(c);
//                        cardRepo.save(c);
//                    }
