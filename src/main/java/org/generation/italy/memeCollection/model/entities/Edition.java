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
@Table(name = "edition")
public class Edition {
    @Id
    @GeneratedValue(generator = "edition_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "edition_generator", sequenceName = "edition_sequence", allocationSize = 1)
    @Column(name = "id_edition")
    private long id;
    private String name;
    private String description;
}
