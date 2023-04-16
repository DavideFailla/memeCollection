package org.generation.italy.memeCollection.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meme")
public class Meme {
    @Id
    @GeneratedValue(generator = "meme_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "meme_generator", sequenceName = "meme_sequence", allocationSize = 1)
    @Column(name = "id_meme")
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "edition")
    @Type(PostgreSQLEnumType.class)
    private Edition edition;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "fun_level")
    @Type(PostgreSQLEnumType.class)
    private FunLevel funLevel;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "rarity")
    @Type(PostgreSQLEnumType.class)
    private Rarity rarity;
}
