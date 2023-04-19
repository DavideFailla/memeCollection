package org.generation.italy.memeCollection.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(generator = "token_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "token_generator", sequenceName = "token_sequence", allocationSize = 1)
    @Column(name = "id_token")
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_user")
    public User user;
}
