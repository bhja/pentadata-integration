package com.accountpatrol.api.service.pentadata;

import com.accountpatrol.api.pentadata.model.Account;
import com.accountpatrol.api.pentadata.model.PentadataRequest;
import com.accountpatrol.api.pentadata.model.PentadataResponse;
import com.accountpatrol.api.pentadata.model.Transaction;

import java.util.List;

public interface AccountService {

    PentadataResponse createDefaultAccount(String userId);

    PentadataResponse createAccount(PentadataRequest request);

    List<Account> getAccounts(String userId,String personId);

    List<Transaction> getAccountTransactions(String userId,String accountId);

    PentadataResponse getAccountACH(String accountId);

    Integer deleteAccount(String accountId);

    List<Transaction> getAccountTransactions(String userId,String accountId,String fromDate,String toDate,boolean pending);



}
