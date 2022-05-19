package com.accountpatrol.api.pentadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

        private String postalCode;
        private String city;
        private String state;
        private String country;
        private String address;
        private String datetime;

}
