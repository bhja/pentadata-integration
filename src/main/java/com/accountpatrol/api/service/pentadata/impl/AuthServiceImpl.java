package com.accountpatrol.api.service.pentadata.impl;

import com.accountpatrol.api.pentadata.bean.UriMappings;
import com.accountpatrol.api.pentadata.model.PentadataRequest;
import com.accountpatrol.api.pentadata.model.PentadataResponse;
import com.accountpatrol.api.pentadata.model.Person;
import com.accountpatrol.api.service.pentadata.AuthService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


public class AuthServiceImpl extends AbstractBaseService implements AuthService
{

    public AuthServiceImpl(Credentials pCredentials, UriMappings mappings) {
        super(pCredentials,mappings);
    }

    /**
     * The signature is valid only for a short span of time.
     * Must be 1-2 minutes. Hence has to be regenerated every time user clicks on the consent button on the UI.
     * It requires POST happening from the UI so pass the Pentadata API endpoint that needs to be invoked in the response.
     * @param personId
     * @param useCase
     * @return
     */
    @Override
    public PentadataResponse getConsentInformation(String personId,String useCase){
            ResponseEntity<Map> response = exchange(String.format("%s%s", getMappings().getBaseUrl(),
                    getMappings().getConsentSignature().replace(":personId", personId).replace(":useCase", useCase)), HttpMethod.GET,
                    getEntity(null), Map.class);
            Map<String,Object> responseBody = new HashMap<>();
            responseBody.put("consent_post_url",String.format("%s%s",getMappings().getBaseUrl(),getMappings().getConsents()));
            if (response.getStatusCode() == HttpStatus.OK) {
                getLogger().debug("Retrieved signature " + response.getBody() + " for personId " + personId + " for usecase " + useCase);
                responseBody.put("signature",response.getBody().get("signature"));
            }
            return  new PentadataResponse(responseBody);

    }



    @Override
    public PentadataResponse authenticatePerson(String personId,Person person){
        ResponseEntity<Map> responseEntity = exchange(String.format("%s%s",getMappings().getBaseUrl(),
                getMappings().getPersonAuthenticate().replace(":id",personId)),HttpMethod.POST,
                getRequestEntity(person),Map.class);
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            getLogger().debug(" User authentication fulfillment retrieved " + responseEntity.getBody());
        }
        return new PentadataResponse(responseEntity.getBody());
    }

    @Override
    public PentadataResponse verifyPerson(String personId,PentadataRequest request){
        ResponseEntity<Map> responseEntity = exchange(String.format("%s%s",getMappings().getBaseUrl(),
                getMappings().getPersonsVerify().replace(":id",personId)),HttpMethod.POST,
                getEntity(request.getRequest()),Map.class);
        if(responseEntity.getStatusCode() == HttpStatus.ACCEPTED){
            getLogger().debug("User verified successfully");
        }
        return new PentadataResponse(responseEntity.getBody());
    }
}
