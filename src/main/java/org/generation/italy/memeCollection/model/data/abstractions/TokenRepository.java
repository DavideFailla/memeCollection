package org.generation.italy.memeCollection.model.data.abstractions;

import org.generation.italy.memeCollection.model.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);

    @Query("""
            select t.id
            from Token t
            where t.expired = false or t.revoked = false
            order by t.id desc
            limit 1
            """)
    Integer findCurrentToken();

    @Query("""
            select t.user.id
            from Token t
            where t.id = :tokenId
            """)
    Integer findUserByToken(Integer tokenId);
}
