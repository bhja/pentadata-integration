package com.accountpatrol.api.dao;

import com.accountpatrol.api.dao.entity.PentadataPersonEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface PentadataPersonDao extends GenericDAO<PentadataPersonEntity, String> {

    PentadataPersonEntity findPersonByUserId(String userId);

}
