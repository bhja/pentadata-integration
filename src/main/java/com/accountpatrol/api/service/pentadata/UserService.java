package com.accountpatrol.api.service.pentadata;

import com.accountpatrol.api.pentadata.model.PentadataResponse;

public interface UserService {

    String createUser(String emailId);
    PentadataResponse getUserByEmailId(String emailId);

}
