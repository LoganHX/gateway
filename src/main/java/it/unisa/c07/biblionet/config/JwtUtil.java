package it.unisa.c07.biblionet.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultJwtParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {


    @Value("${jwt.secret}")
    private String secret;


    public void validateToken(final String fullToken) throws Exception {

        final String token = fullToken.substring(7);

        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception ex) {
            throw new Exception("Invalid JWT signature");
        }
    }

    public static Claims getClaimsFromTokenWithoutKey(String token){
        token = token.substring(7);
        String[] splitToken = token.split("\\.");
        String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";

        DefaultJwtParser parser = new DefaultJwtParser();
        Jwt<?, ?> jwt = parser.parse(unsignedToken);
        return (Claims) jwt.getBody();
    }





}
