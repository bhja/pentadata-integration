package com.accountpatrol.api.dao;

import com.accountpatrol.api.dao.entity.PentadataTransactionEntity;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

import java.util.List;

public interface PentadataTransactionDao extends GenericDAO<PentadataTransactionEntity,String> {

    List<PentadataTransactionEntity> getTransactions(String userId,String accountId);


}
