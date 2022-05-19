package com.accountpatrol.api.pentadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private double amount;
    private String transactionId;
    private String description;
    private String merchantName;
    private List<String> category;
    private boolean pending;
    private String currency;
    private Location location;
    private String datetime;
    private String cid;
    private String transactionDateTime;




}
