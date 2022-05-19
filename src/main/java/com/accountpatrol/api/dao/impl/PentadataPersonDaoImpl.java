package com.accountpatrol.api.dao.impl;

import com.accountpatrol.api.dao.PentadataPersonDao;
import com.accountpatrol.api.dao.entity.PentadataPersonEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository
public class PentadataPersonDaoImpl extends GenericDAOImpl<PentadataPersonEntity,String>
        implements PentadataPersonDao {

    private Logger logger = Logger.getLogger(PentadataPersonDaoImpl.class);

     public PentadataPersonDaoImpl(SessionFactory factory){
         super.setSessionFactory(factory);
     }

    @Override
    public PentadataPersonEntity findPersonByUserId(String userId) {

        logger.debug(" Search person by user Id: " + userId);
        Search search = new Search(PentadataPersonEntity.class)
                .setResultMode(Search.RESULT_SINGLE)
                .addSortDesc(PentadataPersonEntity.CREATED)
                .addFilterEqual(PentadataPersonEntity.COL_USER_ID, userId)
                .setMaxResults(1);
        return searchUnique(search);
    }
}
