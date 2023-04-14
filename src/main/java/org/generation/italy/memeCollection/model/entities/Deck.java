package org.generation.italy.memeCollection.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deck")
public class Deck {
    @Id
    @GeneratedValue(generator = "deck_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "deck_generator", sequenceName = "deck_sequence", allocationSize = 1)
    @Column(name = "id_deck")
    private long id;
    private String name;
    @OneToMany(mappedBy = "id_card")
    private List<Card> cards;
    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;
}
