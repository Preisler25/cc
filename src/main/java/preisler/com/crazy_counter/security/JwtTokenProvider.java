package preisler.com.crazy_counter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationInSeconds;

    public String generateToken(String id) {
        System.out.println("--------generateToken---------");
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInSeconds);

        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Extract username from JWT token
    public String getUserIdFromToken(String token) {
        System.out.println("--------getUserIdFromToken---------");
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        System.out.println("Sec: " + jwtSecret);
        System.out.println("Token: " + token);
        try {
            System.out.println("try");
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .setAllowedClockSkewSeconds(60)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT signature");
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token" + e.getMessage());
        }
    }
    // Validate token
    public boolean validateToken(String token) {
        System.out.println("--------validateToken---------");
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            if (jwt.getBody().getExpiration().before(new Date())) {
                System.out.println("Expired");
                return false;
            }
            System.out.println("Not Expired");
            return !jwt.getBody().getExpiration().before(new Date());


        } catch (Exception e) {
            System.out.println("Invalid JWT Token: " + e.getMessage());
        }
        return false;
    }

    // Retrieve a claim from token
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // For retrieving any information from the token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
}

