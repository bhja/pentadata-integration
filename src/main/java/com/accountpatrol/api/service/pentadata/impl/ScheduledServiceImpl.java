package com.accountpatrol.api.service.pentadata.impl;

import com.accountpatrol.api.service.pentadata.AccountService;

public class ScheduledServiceImpl implements ScheduledService{

    private final AccountService accountService;

    public ScheduledServiceImpl(AccountService service){
        accountService = service;
    }

    @Override public void loadTransactions() {

    }
}
