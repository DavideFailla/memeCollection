package org.generation.italy.memeCollection.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(generator = "player_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "player_generator", sequenceName = "player_sequence", allocationSize = 1)
    @Column(name = "id_player")
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate dob;
    private String email;
    private String nickname;
    private String password;
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Album album;
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Deck deck;
    private double money;
}
