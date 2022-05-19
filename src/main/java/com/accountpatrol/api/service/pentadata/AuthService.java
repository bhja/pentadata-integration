package com.accountpatrol.api.service.pentadata;

import com.accountpatrol.api.pentadata.model.PentadataRequest;
import com.accountpatrol.api.pentadata.model.PentadataResponse;
import com.accountpatrol.api.pentadata.model.Person;

public interface AuthService {

    PentadataResponse getConsentInformation(String personId,String useCase);

    PentadataResponse authenticatePerson(String personId, Person person);

    PentadataResponse verifyPerson(String personId,PentadataRequest request);
}
