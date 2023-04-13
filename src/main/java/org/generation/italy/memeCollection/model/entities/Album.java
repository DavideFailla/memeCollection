package org.generation.italy.memeCollection.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    private long id;
    private String title;
    private String description;
    private Set<Card> cardSet;
    private Edition edition;

}
