package org.generation.italy.memeCollection.model.services.abstractions;

import org.generation.italy.memeCollection.model.entities.*;

import java.util.List;

public interface AbstractGameService {
    List<Card> createPack(Edition edition);
    Player assignCardToPlayer(List<Card> pack, Player player);
    List<Card> showAlbum(Player player, Album album);
    Player getPlayerFromId(long playerId);
    Album getAlbumFromId(long albumId);
}
