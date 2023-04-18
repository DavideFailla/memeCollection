package org.generation.italy.memeCollection.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "edition")
    @Type(PostgreSQLEnumType.class)
    private Edition edition;
    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;
    @OneToMany(mappedBy = "album")
    private List<Card> cardDuplicates;

    public boolean isCardADuplicate(Card c){
        for(Card card : cardSet){
            if(c.equals(card)){ // se si verifica questo allora la carta è un duplicato
                return true;
            }
        }
        return false;
    }

    public void addDuplicates(Card c){
        cardDuplicates.add(c);
    }

    public void addCard(Card c){
        cardSet.add(c);
    }
}
