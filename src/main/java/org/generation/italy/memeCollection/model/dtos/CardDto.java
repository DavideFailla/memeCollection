package org.generation.italy.memeCollection.model.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.memeCollection.model.entities.Card;
import org.generation.italy.memeCollection.model.entities.Edition;
import org.generation.italy.memeCollection.model.entities.FunLevel;
import org.generation.italy.memeCollection.model.entities.Rarity;

import java.util.List;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
public class CardDto {
    private long id;
    private String name;
    private Edition edition;
    private FunLevel funLevel;
    private Rarity rarity;

    public CardDto(long id, String name, Edition edition, FunLevel funLevel, Rarity rarity) {
        this.id = id;
        this.name = name;
        this.edition = edition;
        this.funLevel = funLevel;
        this.rarity = rarity;
    }

    public static CardDto fromEntity(Card c){
        return new CardDto(c.getId(),c.getMeme().getName(),c.getMeme().getEdition(), c.getMeme().getFunLevel(), c.getMeme().getRarity());
    }

    public static List<CardDto> fromEntityList(List<Card> cards){
        return StreamSupport.stream(cards.spliterator(), false)
                .map(c -> CardDto.fromEntity(c)).toList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public FunLevel getFunLevel() {
        return funLevel;
    }

    public void setFunLevel(FunLevel funLevel) {
        this.funLevel = funLevel;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
