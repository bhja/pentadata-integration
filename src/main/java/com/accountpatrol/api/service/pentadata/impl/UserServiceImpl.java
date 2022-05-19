package com.accountpatrol.api.service.pentadata.impl;

import com.accountpatrol.api.dao.PentadataUserDao;
import com.accountpatrol.api.dao.entity.PentadataUserEntity;
import com.accountpatrol.api.pentadata.model.PentadataResponse;
import com.accountpatrol.api.service.pentadata.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

public class UserServiceImpl  implements UserService {


    private PentadataUserDao userServiceDao;

    public UserServiceImpl(PentadataUserDao pUserServiceDao){
        userServiceDao = pUserServiceDao;
    }

    @Transactional
    @Override
    public String createUser(String emailId){
        PentadataUserEntity entity = new PentadataUserEntity();
        entity.setEmailId(emailId);
        userServiceDao.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public PentadataResponse getUserByEmailId(String emailId) {
        PentadataUserEntity user =  userServiceDao.findUserByEmailId(emailId);
        String userId ;
        if(user == null){
            userId =  createUser(emailId);
        }else{
            userId = user.getId();
        }
        return new PentadataResponse(Collections.singletonMap("userId",userId));
    }




}
