package org.generation.italy.memeCollection.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    private String nickname;
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<Album> album;
    private double money;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
