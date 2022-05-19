package com.accountpatrol.api.service.pentadata.impl;

import com.accountpatrol.api.dao.PentadataPersonDao;
import com.accountpatrol.api.dao.PentadataUserDao;
import com.accountpatrol.api.dao.entity.PentadataPersonEntity;
import com.accountpatrol.api.dao.entity.PentadataUserEntity;
import com.accountpatrol.api.pentadata.bean.UriMappings;
import com.accountpatrol.api.pentadata.model.PentadataResponse;
import com.accountpatrol.api.service.pentadata.PersonService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


public class PersonServiceImpl extends AbstractBaseService implements PersonService {

     private PentadataPersonDao personDao;
     private PentadataUserDao userDao;

    public PersonServiceImpl(Credentials pCredentials, UriMappings mappings,PentadataPersonDao pPersonDao,PentadataUserDao pUserDao){
         super(pCredentials,mappings);
        personDao = pPersonDao;
        userDao = pUserDao;
    }


    @Transactional
    @Override
    public PentadataResponse createPerson(String userId){
        Map<String,Object> personResponse = new HashMap<>();
        try {

                PentadataUserEntity userEntity = userDao.find(userId);
                if(userEntity == null){
                    throw new RuntimeException("User not found");
                }
                Map<String,Object> request = new HashMap<>();
                request.put("first_name","mp_user_fn");
                request.put("last_name","mp_user_ln");
                request.put("email",userEntity.getEmailId());

               ResponseEntity<Map> responseEntity = exchange(String.format("%s%s", getMappings().getBaseUrl(), getMappings().getPersons()),
                        HttpMethod.POST, getEntity(request), Map.class);
                if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                    getLogger().debug("Person created [" + responseEntity.getBody() + "]");
                    personResponse = responseEntity.getBody();
                    PentadataPersonEntity entity  = new PentadataPersonEntity();
                    entity.setPersonId(String.valueOf(personResponse.get("person_id")));
                    entity.setUserId(userId);
                    personDao.save(entity);
                }
        }catch(Exception e){
            getLogger().debug(" Error creating pentadata person" + e.getMessage());
            throw new RuntimeException("Person not created [" +e.getMessage() + "]");
        }
        return new PentadataResponse(personResponse);
    }

    @Override
    @Transactional
    public PentadataResponse getPersonDetails(String emailId) {

        PentadataUserEntity userEntity = userDao.findUserByEmailId(emailId);
        if(userEntity == null ){
            return new PentadataResponse(new HashMap<>());
        }
        PentadataPersonEntity entity = personDao.findPersonByUserId(userEntity.getId());
        if(entity == null){
            return new PentadataResponse(new HashMap<>());
        }
        Map<String,Object> response = new HashMap<>();
        response.put("person_id",entity.getPersonId());
        return new PentadataResponse(response);
    }

} //End of Person
