package com.accountpatrol.api.pentadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    private String bank;
    @JsonProperty("account_id")
    private int accountId;
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("last_opt_in")
    private String lastOptIn;
    @JsonProperty("account_type")
    private String accountType;

    private Integer routing;



}
