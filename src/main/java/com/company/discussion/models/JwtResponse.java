package com.company.discussion.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    // Properties
    private static final long serialVersionUID = -8091879091924046844L;

    private String jwtToken;

    // Constructors
    public JwtResponse() {
    }

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    // Getter
    public String getJwtToken() {
        return jwtToken;
    }
}
