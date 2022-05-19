package com.accountpatrol.api.dao;

import com.accountpatrol.api.dao.entity.PentadataAccountEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

import java.util.List;


public interface PentadataAccountDao extends GenericDAO<PentadataAccountEntity,String> {

    PentadataAccountEntity getAccountById(String accountId);
    //Fetches all the accounts of pentadata associated with the user.
    List<PentadataAccountEntity> getAccountsByPersonIdAndUserId(String personId,String userId);

    void deleteAllByPersonIdAndUserId(String personId,String userId);
    void deleteByAccountId(String accountId);


}
