package com.meetup.meetup.service;

import com.meetup.meetup.security.jwt.SecretKeyProvider;
import com.meetup.meetup.service.vm.MinimalProfile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService {

    private static final String ISSUER = "in.sdqali.jwt";

    public static final String USERNAME = "username";

    @Autowired
    private SecretKeyProvider secretKeyProvider;

    @Autowired
    private ProfileService profileService;

    public MinimalProfile verify(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return profileService.minimal(claims.getBody().getSubject());
    }

    public String tokenFor(MinimalProfile minimalProfile) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject("jwt-demo")
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .claim(USERNAME, minimalProfile.getUsername())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
