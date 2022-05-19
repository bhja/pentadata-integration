package com.accountpatrol.api.pentadata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;
    private long expires;

    @JsonProperty("refresh_expires")
    private long refreshExpires;
    private String userId;


}
