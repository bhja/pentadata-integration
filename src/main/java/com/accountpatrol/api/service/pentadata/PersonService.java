package com.accountpatrol.api.service.pentadata;

import com.accountpatrol.api.pentadata.model.PentadataResponse;

public interface PersonService {

   PentadataResponse createPerson(String userId);

   PentadataResponse getPersonDetails(String emailId);



}
