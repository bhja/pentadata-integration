package com.accountpatrol.api.pentadata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person extends PentadataRequest {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String address;
    private String city;
    @JsonProperty("postal_code")
    private String postalCode;
    private String state;
    private String country;
    private String ssn; //  Last 4 digits of the social security number.
    private String dob; // YYYY-MM-DD.
    private String phone;
    @JsonProperty("email_id")
    private String emailId;

    
}
