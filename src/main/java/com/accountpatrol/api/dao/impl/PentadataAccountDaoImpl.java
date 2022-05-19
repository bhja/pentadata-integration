package com.accountpatrol.api.dao.impl;

import com.accountpatrol.api.dao.PentadataAccountDao;
import com.accountpatrol.api.dao.entity.PentadataAccountEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PentadataAccountDaoImpl extends GenericDAOImpl<PentadataAccountEntity,String> implements PentadataAccountDao {


    private Logger logger = Logger.getLogger(PentadataAccountDaoImpl.class);


    public PentadataAccountDaoImpl(SessionFactory factory){
        super.setSessionFactory(factory);
    }

    @Override
    public PentadataAccountEntity getAccountById(String accountId) {

        logger.debug(" Search by account Id" + accountId);
        Search search = new Search(PentadataAccountEntity.class)
                .addSortDesc(PentadataAccountEntity.CREATED)
                .addFilterEqual(PentadataAccountEntity.COL_ACCOUNT_ID,accountId)
                .setMaxResults(1)
                .setResultMode(Search.RESULT_SINGLE);

        return searchUnique(search);
    }

    @Override
    public List<PentadataAccountEntity> getAccountsByPersonIdAndUserId(String personId, String userId) {
       Search search = new Search(PentadataAccountEntity.class).addSortDesc(PentadataAccountEntity.CREATED).
               addFilterEqual(PentadataAccountEntity.COL_USER_ID,userId).
               addFilterEqual(PentadataAccountEntity.COL_PERSON_ID,personId).setResultMode(Search.RESULT_AUTO);
       return search(search);
    }

    @Override
    public void deleteAllByPersonIdAndUserId(String personId, String userId) {
        List<PentadataAccountEntity> accounts = getAccountsByPersonIdAndUserId(personId,userId);
        accounts.stream().forEach(account->
                _deleteById(PentadataAccountEntity.class,account.getId()));
    }

    @Override
    public void deleteByAccountId(String accountId) {
        PentadataAccountEntity entity = getAccountById(accountId);
        _deleteById(PentadataAccountEntity.class,entity.getId());
    }

}
