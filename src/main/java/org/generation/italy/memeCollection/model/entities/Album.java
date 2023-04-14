package org.generation.italy.memeCollection.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(generator = "album_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "album_generator", sequenceName = "album_sequence", allocationSize = 1)
    @Column(name = "id_album")
    private long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "album")
    private Set<Card> cardSet;
    @ManyToOne
    @JoinColumn(name = "id_edition")
    private Edition edition;
    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;
}
