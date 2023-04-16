package org.generation.italy.memeCollection.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(generator = "card_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "card_generator", sequenceName = "card_sequence", allocationSize = 1)
    @Column(name = "id_card")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;
    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;
    @ManyToOne
    @JoinColumn(name = "id_meme")
    private Meme meme;
}

