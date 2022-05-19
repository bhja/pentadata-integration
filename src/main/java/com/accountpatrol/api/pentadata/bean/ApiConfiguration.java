package com.accountpatrol.api.pentadata.bean;

import com.accountpatrol.api.dao.*;
import com.accountpatrol.api.pentadata.model.PendataSecrets;
import com.accountpatrol.api.service.pentadata.*;
import com.accountpatrol.api.service.pentadata.impl.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

@Configuration
public class ApiConfiguration {


    @Bean
    public PendataSecrets allocatePentadataConfig(@Value("${pentadata.email_id}") final String emailId,
             @Value("${pentadata.apikey}") final String apiKey){
        return new PendataSecrets(apiKey,emailId);
    }


    @Bean
    public PersonService allocatePersonService(final UriMappings mappings,final Credentials credentials,final PentadataPersonDao personDao,PentadataUserDao userDao){
        return new PersonServiceImpl(credentials,mappings,personDao,userDao);
    }

    @Bean
    public Credentials allocateCredentials(final PendataSecrets secrets,final UriMappings mappings){
        return new Credentials(secrets,mappings);
    }

    @Bean
    public InstitutionService allocateInstitutionService(final UriMappings mappings,final Credentials credentials,
            PentadataInstitutionDao dao, @Qualifier("pentadataThreadPoolExecutor") Executor executor ){
        return new InstitutionServiceImpl(credentials,mappings,dao,executor);
    }

    @Bean
    public AuthService allocateAuthService(final UriMappings mappings,final Credentials credentials){
        return new AuthServiceImpl(credentials,mappings);
    }

    @Bean
    public AccountService allocateAccountService(final UriMappings mappings,final Credentials credentials,
            PentadataAccountDao pAccountDao,
            PentadataTransactionDao pTransactionDao,PentadataAdditionalConfig config){

        return new AccountServiceImpl(credentials,mappings,pAccountDao,pTransactionDao,config);
    }

    @Bean
    public UserService allocateUserService(final PentadataUserDao dao){
        return  new UserServiceImpl(dao);
    }


    @Bean
    public ScheduledService allocateSchedulerService(final AccountService service) {
        return  new ScheduledServiceImpl(service);
    }



}
