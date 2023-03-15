package com.example.Teaching_based_system.JWT;

import com.example.Teaching_based_system.Entity.Token;
import com.example.Teaching_based_system.Repository.TokenRepo;
import io.jsonwebtoken.*;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    @Autowired
    private TokenRepo tokenRepo;
    private final String secret = "efkmkfafkesafef5af16a5f45af421256146845948511561265225";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public String generateRefreshToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(username + ',' + role).setIssuer("Pasidu.com").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60*20))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, role);
    }

    private String createToken(Map<String, Object> claims, String subject,String role) {

        return Jwts.builder().setClaims(claims).setSubject(subject + ',' + role).setIssuer("Pasidu.com").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*20))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        String[] details = username.split(",");
        String name = details[0];
        Token token1 = tokenRepo.findById(name).orElse(null);
        boolean isTokensmatch = (token1!=null)&&(token1.getToken()).equals(token);
        return (name.equals(userDetails.getUsername()) && !isTokenExpired(token) && isTokensmatch);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
