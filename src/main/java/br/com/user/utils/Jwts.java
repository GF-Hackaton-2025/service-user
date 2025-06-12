package br.com.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

import static br.com.user.config.JwtConfig.algorithm;
import static br.com.user.config.JwtConfig.issuer;

@UtilityClass
public class Jwts {

    public static String create(LocalDateTime expiresAt, String... payload) {
        JWTCreator.Builder builder = JWT.create().withIssuer(issuer);
        if (expiresAt != null)
            builder.withExpiresAt(Dates.parseDate(expiresAt));

        int index = -1;
        String name = null;
        for (String item : payload) {
            index++;
            if ((index % 2) != 0)
                builder.withClaim(name, item);
            else name = item;
        }

      return builder.sign(algorithm);
    }

}
