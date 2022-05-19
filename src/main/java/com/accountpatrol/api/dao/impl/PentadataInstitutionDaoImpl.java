package com.accountpatrol.api.dao.impl;

import com.accountpatrol.api.dao.PentadataInstitutionDao;
import com.accountpatrol.api.dao.entity.PentadataInstitutionEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PentadataInstitutionDaoImpl extends GenericDAOImpl<PentadataInstitutionEntity,String> implements PentadataInstitutionDao {


    public PentadataInstitutionDaoImpl(SessionFactory factory){
        super.setSessionFactory(factory);
    }

    @Override
    public List<PentadataInstitutionEntity> findByName(String name) {
        Search search = new Search().setSearchClass(PentadataInstitutionEntity.class);
           if(!StringUtils.isEmpty(name))
                search.addFilterILike(PentadataInstitutionEntity.NAME,"%"+name+"%");
           search.setResultMode(Search.RESULT_AUTO).setMaxResults(100);
        return search(search);
    }

    @Override
    public void deleteAll() {
        Query query = getSession().createQuery("delete from PentadataInstitutionEntity");
        query.executeUpdate();
    }

}
