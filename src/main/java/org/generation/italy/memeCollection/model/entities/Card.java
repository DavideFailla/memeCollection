package org.generation.italy.memeCollection.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private long id;
    private String name;
    private Edition edition;
    private FunLevel funLevel;
    private Rarity rarity;
}

