package com.accountpatrol.api.dao.impl;

import com.accountpatrol.api.dao.PentadataTransactionDao;
import com.accountpatrol.api.dao.entity.PentadataTransactionEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PentadataTransactionDaoImpl  extends GenericDAOImpl<PentadataTransactionEntity,String>
        implements PentadataTransactionDao {

    private Logger logger = Logger.getLogger(PentadataTransactionDaoImpl.class);

    public PentadataTransactionDaoImpl(SessionFactory factory){
        super.setSessionFactory(factory);
    }


    @Override
    public List<PentadataTransactionEntity> getTransactions(String userId, String accountId) {
        logger.debug(" Retrieve the transactions for the user: "+ userId);
        Search search = new Search(PentadataTransactionEntity.class)
                .addFilterEqual(PentadataTransactionEntity.COL_USER_ID,userId).
                        addFilterEqual(PentadataTransactionEntity.COL_ACCOUNT_ID,Integer.valueOf(accountId))
                .addSortDesc(PentadataTransactionEntity.CREATED)
                .setResultMode(Search.RESULT_AUTO);

        return search(search);
    }

}
