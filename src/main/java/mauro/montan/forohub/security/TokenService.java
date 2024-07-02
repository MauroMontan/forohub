package mauro.montan.forohub.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import mauro.montan.forohub.users.LoginDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String issuer = "mauromontano";
    @Value("${secretKey}")
    String secret;

    public String generateToken(LoginDto user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withIssuer(issuer)
                .withExpiresAt(generateExpiration())
                .sign(getAlgorithm());

    }

    public String getSubject(String token) {

        if (token == null) {
            throw new RuntimeException("el token es nulo");
        }

        DecodedJWT decodedJWT;

        try {
            JWTVerifier verifier = JWT.require(getAlgorithm())
                    // specify any specific claim validations
                    .withIssuer(issuer)
                    // reusable verifier instance
                    .build();

            decodedJWT = verifier.verify(token);


            return decodedJWT.getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("error al validar token");
        }
    }


    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant generateExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));

    }
}

