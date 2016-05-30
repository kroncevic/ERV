package hr.tvz.rome.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String secret = "5PDK3mSSfy21ooPqpHnR";

    private static final Long expiration = 323231L;

    private static final String CLAIM_USERNAME = "username";
    private static final String CLAIM_AUTHORITY = "authority";

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public String generateToken(Authentication authenticatedUser){

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USERNAME, authenticatedUser.getPrincipal());
        claims.put(CLAIM_AUTHORITY, ((GrantedAuthority) authenticatedUser.getAuthorities().toArray()[0]).getAuthority());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }






}