package com.company.discussion.config;

import com.company.discussion.models.User;
import com.company.discussion.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtToken implements Serializable {

    // The secret keyword is retrieved from the application.properties file
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    // serialVersionUID is used to determine of the Class defined in the application is the same one used for serializing/deserializing a JWT
    private static final long serialVersionUID = -2550185165626007488L;

    // jwt tokens accept seconds for token validity
    // 5 hrs * 60 mins * 60 secs
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    // Logic for preparing/creating the JWT
    // claims -> information that we would like to present/show the user
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        // .setClaims includes information to show the recipient -> which is the username
        // .setSubject adds descriptive information about the token
        // .setIssuedAt sets the time and date when the token was created
        // .setExpiration sets the expiration of the token
        // .signWith creates the token using a prebuilt algorithm using the secret keyword
        // .compact build the JWT and serializes it to a COMPACT, URL-safe string
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();

    }

    // Creates the JWT with the user details
    // UserDetails class comes from spring framework containing user information
    public String generateToken(UserDetails userDetails) {

        // Map is an object without duplicate keys
        Map<String, Object> claims = new HashMap<>();
        User user = userRepository.findByEmail(userDetails.getUsername());
        claims.put("user", user.getId());
        return doGenerateToken(claims, userDetails.getUsername());

    }

    // To extract the claims/user info from the JWT
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // Getting the claim from the token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        final Claims CLAIMS = getAllClaimsFromToken(token);
        return claimsResolver.apply(CLAIMS);

    }

    // Get username from JWT
    public String getUsernameFromToken(String token) {
        String claim = getClaimFromToken(token, Claims::getSubject);
        return claim;
    }

    // Get expiration date from JWT
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Check if token is expired
    private Boolean isTokenExpired(String token) {
        final Date EXPIRATION = getExpirationDateFromToken(token);

        return EXPIRATION.before(new Date());
    }

    // Validate JWT
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String USERNAME = getUsernameFromToken(token);
        return (USERNAME.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

}
