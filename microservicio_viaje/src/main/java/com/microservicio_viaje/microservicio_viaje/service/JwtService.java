package com.microservicio_viaje.microservicio_viaje.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
@Service
public class JwtService {
    private static final String SECRET_KEY="586E3272357538782F413F4428472B486250655368566B597033700676397924"; //clave secreta para firmar el token

    private Key getKey(){//obtengo la clave de firma desde la clave secreta en Base64
        byte[] keyByte= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
    public String getUsernameFromToken(String token) {//obtengo el nombre de usuario desde el token JWT
        return getClaim(token, Claims::getSubject);
    }
    public boolean isTokenValid(String token) {
        final String username = getUsernameFromToken(token);
        return (!isTokenExpired(token) && username != null);
    }
    private Claims getAllClaims(String token) {//obtengo todas las reclamaciones del token JWT
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {//obtengo una reclamación específica del token JWT
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Date getExpiration(String token) {//obtengo la fecha de expiración del token JWT
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token) {//verifica si el token JWT ha expirado
        return getExpiration(token).before(new Date());
    }
}
