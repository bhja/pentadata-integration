package com.accountpatrol.api.dao.impl;

import com.accountpatrol.api.dao.PentadataUserDao;
import com.accountpatrol.api.dao.entity.PentadataUserEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PentadataUserDaoImpl extends GenericDAOImpl<PentadataUserEntity,String> implements PentadataUserDao {

    public PentadataUserDaoImpl(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public PentadataUserEntity findUserByEmailId(String emailId) {
        Search search = new Search(PentadataUserEntity.class).
        setResultMode(Search.RESULT_SINGLE)
                .addSortDesc(PentadataUserEntity.CREATED)
                .addFilterEqual(PentadataUserEntity.EMAIL_ID, emailId)
                .setMaxResults(1);
        return searchUnique(search);
    }
}
