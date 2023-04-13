package org.generation.italy.memeCollection.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate dob;
    private String email;
    private String nickname;
    private String password;
    private Album album;
    private Deck deck;
    private double money;


}
