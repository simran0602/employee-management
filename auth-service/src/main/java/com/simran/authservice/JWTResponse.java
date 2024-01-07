package com.simran.authservice;

public class JWTResponse {

    private String token;

    // Default constructor (required for JSON serialization)
    public JWTResponse() {
    }

    public JWTResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {
        private String token;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public JWTResponse build() {
            return new JWTResponse(token);
        }
    }
    public static Builder builder() {
        return new Builder();
    }

}

