package com.accountpatrol.api.service.pentadata.impl;

import com.accountpatrol.api.pentadata.bean.UriMappings;
import com.accountpatrol.api.pentadata.model.PendataSecrets;
import com.accountpatrol.api.pentadata.model.TokenResponse;

import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread safe class to generate tokens for the Pentadata API calls.
 * If the token expires use the refresh token only if the refresh_expires has also not exceeded
 *
 */

public class Credentials {

        private Logger logger = Logger.getLogger(Credentials.class);
        private TokenResponse tokenResponse;
        private ReentrantLock lock;
        private PendataSecrets secrets;
        private RestTemplate template;
        private String tokenUrl;
        private String refreshTokenUrl;
        private HttpEntity entity ;
        private UriMappings mappings;


        public Credentials(PendataSecrets pPendataSecrets, UriMappings pMappings){
            secrets = pPendataSecrets;
            mappings = pMappings;
            init();
        }

        private void init(){
            template = new RestTemplate();
            this.lock = new ReentrantLock();
            tokenUrl = String.format("%s%s",mappings.getBaseUrl() , mappings.getSubscriberLogin());
            refreshTokenUrl = String.format("%s%s",mappings.getBaseUrl(),mappings.getSubscriberRefresh());
        }

    /**
     * Create the entity to fetch the tokens
     * @return
     */
    protected HttpEntity entity(){
        try {
            this.lock.lock();
            if (this.entity == null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                Map<String, String> parameters = new HashMap<>();
                parameters.put("email", secrets.getEmailId());
                parameters.put("api_key", secrets.getApiKey());
                this.entity = new HttpEntity(parameters, headers);
            }
        }finally {
            this.lock.unlock();
        }
            return entity;
        }

    /**
     * Creates entity using the refresh token as Authorization token
     * to get new tokens
     * @param refreshToken
     * @return
     */
    protected HttpEntity refreshTokenEntity(String refreshToken){
            HttpEntity httpEntity;
            try {
                this.lock.lock();
                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.AUTHORIZATION," Bearer "+refreshToken);
                httpEntity  = new HttpEntity(headers);
            }finally {
                this.lock.unlock();
            }
            return httpEntity;
        }

    /**
     * Method fetches the tokens based on the expiration logic
     * @return
     */
    public String getToken(){
            try{
                this.lock.lock();
            if(this.tokenResponse == null)
                fetchApiToken();
            else {
                  long now = Instant.now().toEpochMilli();
                  if(convertToMilliseconds(this.tokenResponse.getExpires()) <= now) {//Check for refresh token else get the new token
                      refreshToken();
                  }
            }
            }catch(Exception e){
                logger.error("Error fetching token \n. Caused by => [" +e.getMessage() +"]");
                throw new RuntimeException(e.getMessage());
            }finally {
                this.lock.unlock();
            }
            return this.tokenResponse.getToken();
            }


        private void fetchApiToken() {
            try {
                this.lock.lock();
                exchange(tokenUrl,HttpMethod.POST,entity());
            }catch (Exception e) {
                logger.error("Issue generating the token "+ e.getMessage());
                throw new RuntimeException(e.getMessage());
            }finally {
                this.lock.unlock();
            }
        }


        private void refreshToken(){
            try {
                this.lock.lock();
                if(this.tokenResponse.getRefreshToken() != null  && convertToMilliseconds(this.tokenResponse.getRefreshExpires()) > Instant.now().toEpochMilli()){
                    logger.debug("Using refresh token to continue with the login session");
                    exchange(refreshTokenUrl,HttpMethod.GET,refreshTokenEntity(tokenResponse.getRefreshToken()));
                }else{
                    fetchApiToken();
                }

            }catch (Exception e) {
                logger.error("Issue generating the token " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }finally {
                this.lock.unlock();
            }
        }

    private void exchange(String url,HttpMethod method,HttpEntity entity){
        try {
            this.lock.lock();

            ResponseEntity<TokenResponse> responseResponseEntity =
                    template.exchange(url,
                            method,entity,TokenResponse.class);


            if(responseResponseEntity.getStatusCode() == HttpStatus.OK){
                logger.debug(" Response [ " + responseResponseEntity.getBody().toString() + "]");
                setTokenResponse(responseResponseEntity.getBody());
            }
        }catch (Exception e) {
            logger.error("Issue generating the token "+ e.getMessage());
            throw new RuntimeException(e.getMessage());
        }finally {
            this.lock.unlock();
        }
    }

        private void setTokenResponse(TokenResponse response) {
            try {
                this.lock.lock();
                this.tokenResponse = response;
            } finally {
                this.lock.unlock();
            }
        }

        private long convertToMilliseconds(long date){
             DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyyMMddHHmmss" ) ;
             LocalDateTime ldt = LocalDateTime.parse(String.valueOf(date), f ) ;
             Instant instant = ldt.toInstant(ZoneOffset.UTC);
             logger.debug(" Date converted from " + date + " to  " +ldt + " epoch time " +ldt.toInstant(ZoneOffset.UTC).toEpochMilli());
             return instant.toEpochMilli();
        }
}
