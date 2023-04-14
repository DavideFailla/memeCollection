package org.generation.italy.memeCollection.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;

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
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_edition")
    private Edition edition;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "funlevel")
    @Type(PostgreSQLEnumType.class)
    private FunLevel funLevel;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "rarity")
    @Type(PostgreSQLEnumType.class)
    private Rarity rarity;
    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;
    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;
}

