package com.accountpatrol.api.pentadata.bean;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UriMappings {

    @Value("${pentadata.baseurl}")
    private String baseUrl;

    @Value("${pentadata.subscribers.login}")
    private String subscriberLogin;

    @Value("${pentadata.subscribers.refresh}")
    private String subscriberRefresh;

    @Value("${pentadata.institutions}")
    private String institutions;

    @Value("${pentadata.persons}")
    private String persons;

    @Value("${pentadata.persons.verify}")
    private String personsVerify;

    @Value("${pentadata.persons.authenticate}")
    private String personAuthenticate;

    @Value("${pentadata.accounts}")
    private String accounts;

    @Value("${pentadata.accounts.transactions}")
    private String transactions;

    @Value("${pentadata.consents.signature}")
    private String consentSignature;

    @Value("${pentadata.consents}")
    private String consents;

    @Value("${pentadata.person.accounts}")
    private String personAccounts;

    @Value("${pentadata.person.accounts}")
    private String accountStatements;

    @Value("${pentadata.accounts.transactions}")
    private String accountTransactions;

    @Value("${pentadata.accounts.statement}")
    private String accountStatement;

    @Value("${pentadata.accounts.ach}")
    private String accountACH;
}
