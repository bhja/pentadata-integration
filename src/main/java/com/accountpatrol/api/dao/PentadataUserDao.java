package com.accountpatrol.api.dao;

import com.accountpatrol.api.dao.entity.PentadataUserEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface PentadataUserDao extends GenericDAO<PentadataUserEntity,String> {

    PentadataUserEntity findUserByEmailId(String emailId);
}
