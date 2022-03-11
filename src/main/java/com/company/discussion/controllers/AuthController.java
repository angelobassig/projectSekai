package com.company.discussion.controllers;

import com.company.discussion.Constants;
import com.company.discussion.config.JwtToken;
import com.company.discussion.models.JwtRequest;
import com.company.discussion.models.JwtResponse;
import com.company.discussion.models.User;
import com.company.discussion.repositories.UserRepository;
import com.company.discussion.services.JwtUserDetailService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/api/users/authenticate", method= RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//
//        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//
//        // Check the database if username exists
//        // Retrieve the user with the same username
//        final UserDetails USER_DETAILS = jwtUserDetailService.loadUserByUsername(authenticationRequest.getEmail());
//
//        // Generate the jwt including the username and user id
//        final String token = jwtToken.generateToken(USER_DETAILS);
//
//        // Respond with the JWT in the client (e.g. ghd1uikh3iurhi12.f32huilhflku32)
//        return ResponseEntity.ok(new JwtResponse(token));
//    }

    public ResponseEntity<Object> login(@RequestBody HashMap<String, Object> credentials) {
        HashMap<String, String> response = new HashMap<>();
        User matchedUser = userRepository.findByEmail(credentials.get("email").toString());

        if (matchedUser != null) {
            String enteredPasword = credentials.get("password").toString();
            boolean isPasswordMatched = new BCryptPasswordEncoder().matches(enteredPasword, matchedUser.getPassword());

            if (isPasswordMatched) {
                response.put("result", "successful");
                response.put("id", Long.toString(matchedUser.getId()));
                response.put("email", matchedUser.getEmail());
                response.put("profileUrl", matchedUser.getProfileUrl());
                response.put("token", generateToken(matchedUser.getId(), matchedUser.getEmail()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("result", "incorrect_credentials");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.put("result", "user_not_found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    private void authenticate(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    private String generateToken(Long id, String email) {
        long timestamp = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY);

        builder = builder.setIssuedAt(new Date(timestamp));
        builder = builder.setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY));

        builder = builder.claim("id", id);
        builder = builder.claim("email", email);

        return builder.compact();
    }

}
