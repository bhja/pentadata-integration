package com.accountpatrol.api.service.pentadata.impl;

import com.accountpatrol.api.dao.PentadataAccountDao;
import com.accountpatrol.api.dao.PentadataTransactionDao;
import com.accountpatrol.api.dao.entity.PentadataAccountEntity;
import com.accountpatrol.api.dao.entity.PentadataTransactionEntity;
import com.accountpatrol.api.pentadata.bean.PentadataAdditionalConfig;
import com.accountpatrol.api.pentadata.bean.UriMappings;
import com.accountpatrol.api.pentadata.model.*;
import com.accountpatrol.api.service.pentadata.AccountService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handled account information related with a given person created in Pentadata
 */

public class AccountServiceImpl extends AbstractBaseService implements AccountService {


    private final PentadataAccountDao accountDao;
    private final PentadataTransactionDao transactionDao;


    private final ObjectMapper objectMapper;
    private final PentadataAdditionalConfig config;

    public AccountServiceImpl(Credentials pCredentials, UriMappings mappings,PentadataAccountDao pAccountDao,
            PentadataTransactionDao pTransactionDao,PentadataAdditionalConfig pConfig) {
        super(pCredentials,mappings);

        accountDao = pAccountDao;
        transactionDao = pTransactionDao;
        config = pConfig;
        objectMapper = new ObjectMapper();
    }

    @Override
    public PentadataResponse createDefaultAccount(String userId) {

        //Fetch the email ID of the user  from user Entity.
        return null;
    }

    /**
     * Method that establishes the bank relation.
     * @param body
     * @return
     */
    @Override
    public PentadataResponse createAccount(final PentadataRequest body)
    {
        Map<String,Object> response ;
        try {
            ResponseEntity<Map> responseEntity = exchange(String.format("%s%s", getMappings().getBaseUrl(), getMappings().getAccounts()),
                    HttpMethod.POST, getEntity(body.getRequest()), Map.class);
            response = responseEntity.getBody();
        }catch (Exception e){
            getLogger().error("Could not create the account" + e.getMessage());
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"Could not create account");
        }
        return new PentadataResponse(response);
    }


    @Override
    @Transactional
    public List<Account> getAccounts(final String userId,final String personId){
        List<Account> accounts = new ArrayList<>();

        try{
            List<PentadataAccountEntity> accountEntityList = accountDao.getAccountsByPersonIdAndUserId(personId,userId);
            if(!CollectionUtils.isEmpty(accountEntityList)){
                 for(PentadataAccountEntity entity: accountEntityList) {
                     Account account = new Account();
                     account.setAccountId(entity.getAccountId());
                     account.setBank(entity.getBank());
                     account.setAccountName(entity.getAccountName());
                     account.setAccountType(entity.getAccountType());
                     account.setRouting(entity.getRouting());
                     account.setLastOptIn(entity.getLastOptIn());
                     accounts.add(account);
                 }
            }else{

            ResponseEntity<String> responseEntity = exchange(String.format("%s%s",getMappings().getBaseUrl(),getMappings()
                        .getPersonAccounts().replace(":id",personId)),HttpMethod.GET,
                getEntity(null),String.class);
            if(responseEntity.getStatusCode() == HttpStatus.OK) {
                getLogger().debug("Account details retrieved [ \n " + responseEntity.getBody() + " ]");
                //Persist the data in the database.
                Map<String, List<Account>> response = objectMapper
                        .readValue(responseEntity.getBody(), new TypeReference<Map<String, List<Account>>>() {

                        });
                accounts = response.get("accounts");
                accounts.stream().forEach(account -> {
                    PentadataAccountEntity entity = new PentadataAccountEntity();
                    entity.setPersonId(personId);
                    entity.setUserId(userId);
                    entity.setAccountId(account.getAccountId());
                    entity.setBank(account.getBank());
                    entity.setRouting(account.getRouting());
                    entity.setLastOptIn(account.getLastOptIn());
                    entity.setAccountName(account.getAccountName());
                    entity.setAccountType(account.getAccountType());
                    accountDao.save(entity);
                });
            }
        }
        }catch (Exception e){
            getLogger().error("Could not process the user accounts " + e.getMessage());
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"Could not fetch user account");
        }
       return accounts;

    }

    /**
     * Look up user account transactions from the database based on accountId
     * If there is no data , call the API endpoint
     * and fetch the data and persist to the database.
     * @param userId
     * @param accountId
     * @return
     */

    @Override
    @Transactional
    public List<Transaction> getAccountTransactions(final String userId,String accountId){
        //Lookup user transactions from the database else get it from the service.
        List<PentadataTransactionEntity> entities = transactionDao.getTransactions(userId,accountId);

        List<Transaction> transactions = new ArrayList<>();if(entities.isEmpty()){
            transactions = getAccountTransactions(userId,accountId,computeDate(Integer.valueOf(config.getFromDate())),computeDate(0),config.isPending());
        }else{
            for(PentadataTransactionEntity entity:entities)
                        try {
                            Transaction transaction = new Transaction();
                            transaction.setAmount(entity.getAmount());
                            transaction.setCategory(Arrays.stream(entity.getCategory().split(",")).collect(Collectors.toList()));
                            transaction.setCurrency(entity.getCurrency());
                            transaction.setDescription(entity.getDescription());
                            transaction.setDatetime(entity.getDateTime());
                            //TODO Time crunch See if this can be done in a better way using json type in orm.
                            transaction.setLocation(objectMapper.readValue(entity.getLocation(),Location.class));
                            transaction.setMerchantName(entity.getMerchantName());
                            transaction.setPending(entity.getPending());
                            transactions.add(transaction);
                        }catch (Exception e){
                            getLogger().error("Could not fetch the transaction" + e.getMessage());
                        }
                    }
        return transactions;
    }

    /**
     * Not really used but added support for features of pentadata API for future.
     * @param accountId
     * @return
     */
    @Override
    public PentadataResponse getAccountACH(final String accountId){
        ResponseEntity<Map> responseEntity = exchange(String.format("%s%s",getMappings().getBaseUrl(),getMappings().
                getAccountACH().replace(":id",accountId)),HttpMethod.GET,getEntity(null),Map.class);
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            getLogger().debug("Account ACH details retrieved [ \n " + responseEntity.getBody() + " ]");
        }
        return new PentadataResponse(responseEntity.getBody());
    }

    /**
     * Deletes the account from the database
     * and proceeds to delete from pentadata.
     *
     * @param accountId
     * @return
     */
    @Override
    public Integer deleteAccount(final String accountId){
        //Delete from the database as well.
        ResponseEntity<Integer> responseEntity = null;
        try {

            accountDao.deleteByAccountId(accountId);
            responseEntity = exchange(
                    String.format("%s%s/%s", getMappings().getBaseUrl(), getMappings().getAccounts(), accountId), HttpMethod.DELETE,
                    getEntity(null), Integer.class);
            if (responseEntity.getStatusCode() == HttpStatus.ACCEPTED) {
                getLogger().debug(" Deleted account " + accountId);
            }
        }catch (Exception e){
            getLogger().debug("Could not delete the account Id" + accountId);
        }
        return responseEntity!=null ? responseEntity.getBody() : null;
    }

    /** Fetches the data from the Pentadata API.
     * Uses the accountId to fetch the transaction details for the user
     * based on fromDate and toDate.
     * Pending = true fetches all the pending transactions of the given account id.
     * @param userId
     * @param accountId
     * @param fromDate
     * @param toDate
     * @param pending
     * @return
     */
    @Override
    public List<Transaction> getAccountTransactions(final String userId,final String accountId, final String fromDate,final String toDate,
            boolean pending) {

        List<Transaction> transactions = new ArrayList<>();
        List<Transaction> total = new ArrayList<>();
        try {
            PentadataAccountEntity accountEntity = accountDao.getAccountById(accountId);
            MultiValueMap<String,String> parameters = new LinkedMultiValueMap<>();
            int offset = 0;
            parameters.set("fromDate",fromDate);
            parameters.set("toDate",toDate);
            parameters.set("limit",String.valueOf(100));
            parameters.set("offset",String.valueOf(offset));
            do {
                //Loop through the results and fetch the data. If response
                String url = UriComponentsBuilder.fromHttpUrl(
                        String.format("%s%s", getMappings().getBaseUrl(), getMappings().getAccountTransactions().replace(":id", accountId))).queryParams(parameters).
                        queryParam("pending",pending).toUriString();

                ResponseEntity<Transactions> responseEntity = exchange(
                        url,
                        HttpMethod.GET, getEntity(parameters), Transactions.class);
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    getLogger().debug("Transaction details retrieved [ \n " + responseEntity.getBody() + " ]");
                    transactions = responseEntity.getBody().getTransactions();
                }
                 total.addAll(total.size()==0 ? 0: total.size()-1,transactions);
                 offset = offset + 100;
                 parameters.remove("offset");
                 parameters.set("offset",String.valueOf(offset));
            }while(!transactions.isEmpty());
            for(Transaction transaction : total){
                saveTransaction(userId,accountEntity,transaction);
            }
        }catch (Exception e){
            getLogger().error("Could not process the user transactions " + e.getMessage());
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"Could not fetch user transactions");
        }
        return  total;
    }



    /**
     * Save transactions by accountId.
     * @param userId
     * @param accountEntity
     * @param transaction
     */
    @Transactional
    protected void saveTransaction(String userId, PentadataAccountEntity accountEntity,Transaction transaction){
        try {
            PentadataTransactionEntity entity = new PentadataTransactionEntity();
            entity.setAccountId(accountEntity.getAccountId());
            entity.setUserId(userId);
            entity.setPersonId(accountEntity.getPersonId());
            entity.setDescription(transaction.getDescription());
            entity.setCurrency(transaction.getCurrency());
            entity.setAmount(transaction.getAmount());
            entity.setCId(transaction.getCid());
            entity.setDateTime(transaction.getDatetime());
            entity.setPending(transaction.isPending());
            entity.setMerchantName(transaction.getMerchantName());
            entity.setCategory(String.join(",", transaction.getCategory()));
            entity.setLocation(objectMapper.writeValueAsString(transaction.getLocation()));
            transactionDao.save(entity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Date in format YYYYMMDD (or YYYYMMDDhhmmss), UTC.
     * @param subtractDays
     * @return
     */
    protected String computeDate(int subtractDays){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.now(ZoneId.of("UTC"));
        date = date.minusDays(subtractDays);
        return  date.format(formatter);
    }



}
