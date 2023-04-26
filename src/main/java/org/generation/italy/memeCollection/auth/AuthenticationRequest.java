package org.generation.italy.memeCollection.auth;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
public class AuthenticationRequest {
    private String email;
    private String password;
}
