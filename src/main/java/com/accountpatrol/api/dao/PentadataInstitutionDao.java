package com.accountpatrol.api.dao;

import com.accountpatrol.api.dao.entity.PentadataInstitutionEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

import java.util.List;

public interface PentadataInstitutionDao extends GenericDAO<PentadataInstitutionEntity,String> {

     List<PentadataInstitutionEntity> findByName(String name);
     void deleteAll();


}
