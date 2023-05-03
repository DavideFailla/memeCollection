package org.generation.italy.memeCollection.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.memeCollection.model.entities.Card;
import org.generation.italy.memeCollection.model.entities.Meme;

import java.util.List;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemeDto {
    private long id;
    private String name;

    public static MemeDto fromEntity(Meme m){
        return new MemeDto(m.getId(), m.getName());
    }

    public static List<MemeDto> fromEntityList(List<Meme> memes){
        return StreamSupport.stream(memes.spliterator(), false)
                .map(m -> MemeDto.fromEntity(m)).toList();
    }
}
