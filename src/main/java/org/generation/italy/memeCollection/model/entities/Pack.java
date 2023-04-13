package org.generation.italy.memeCollection.model.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pack {
    private List<Card> packCards;
    private double cost;
    private Edition edition;
}
