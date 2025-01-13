package com.ecom.amazon.Jwt;

import com.ecom.amazon.Utility.ApplicationEnvironments;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility {

    public final String customSecretKey = ApplicationEnvironments.JWT_SECRET; //user at least '32 characters' long string
    public final SecretKey SECRET = new SecretKeySpec(customSecretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

    public final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10; // milliseconds * seconds * minutes * hours * days

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return (String) extractClaim(token, claims -> claims.get("role"));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
//        return Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token).getBody();
        return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, String role) {
//        return createToken(Map.of(), username);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        String token = createToken(claims, username);
        return token;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .signWith(SECRET)
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        final String extractUsername = extractUsername(token);
        return (extractUsername.equals(username) && !isTokenExpired(token));
    }
}
