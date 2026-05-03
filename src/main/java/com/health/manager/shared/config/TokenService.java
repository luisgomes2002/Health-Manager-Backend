package com.health.manager.shared.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.health.manager.auth.model.AuthenticatedUser;
import com.health.manager.users.entity.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Users user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId().toString())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole().name())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("health-manager")
                .sign(algorithm);
    }

    public Optional<AuthenticatedUser> verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decoded = JWT.require(algorithm).build().verify(token);

            UUID userId = UUID.fromString(decoded.getClaim("userId").asString());
            String name = decoded.getClaim("name").asString();
            String email = decoded.getSubject();
            String role = decoded.getClaim("role").asString();

            return Optional.of(new AuthenticatedUser(userId, name, email, role));
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
