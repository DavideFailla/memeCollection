package org.generation.italy.memeCollection.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.memeCollection.model.entities.Album;
import org.generation.italy.memeCollection.model.entities.Player;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private long id;
    private String firstname;
    private String lastname;
    private String dob;
    private String email;
    private String nickname;
    private String password;
    private double money;

    public static PlayerDto fromEntity(Player p){
        return new PlayerDto(p.getId(),p.getFirstname(),p.getLastname(),p.getDob().toString(),p.getEmail(),
                p.getNickname(),p.getPassword(),p.getMoney());
    }

    public static List<PlayerDto> fromEntityList(List<Player> players){
        return StreamSupport.stream(players.spliterator(), false)
                .map(p -> PlayerDto.fromEntity(p)).toList();
    }
}