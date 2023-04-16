package org.generation.italy.memeCollection.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
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
    @Column(columnDefinition = "edition")
    @Type(PostgreSQLEnumType.class)
    private Edition edition;
    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;
    @OneToMany(mappedBy = "album")
    private List<Card> cardDuplicates;
}
