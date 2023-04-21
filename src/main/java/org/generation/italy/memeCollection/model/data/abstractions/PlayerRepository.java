package org.generation.italy.memeCollection.model.data.abstractions;

import org.generation.italy.memeCollection.model.entities.Player;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends GenericRepository<Player>{
    @Query("""
            select p
            from Player p join User u
            on p.user.id = u.id
            where u.email = :email
            """)
    Player findPlayerByEmail(String email);
}
