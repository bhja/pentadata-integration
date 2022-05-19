package com.accountpatrol.api.service.pentadata.impl;


import com.accountpatrol.api.pentadata.bean.UriMappings;
import com.accountpatrol.api.pentadata.model.PentadataRequest;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Base class to be extended by the implementation classes
 */
public abstract class AbstractBaseService {

    private Logger logger = Logger.getLogger(AbstractBaseService.class);
    private UriMappings mappings;
    private Credentials credentials;
    private RestTemplate template;

    protected Logger getLogger(){
        return logger;
    }
    public AbstractBaseService(Credentials pCredentials,UriMappings pMappings){
        mappings = pMappings;
        credentials = pCredentials;
        setTemplate(configureRestTemplate());
    }

    protected void setTemplate(RestTemplate pTemplate){
        template = pTemplate;
    }



    protected Credentials getCredentials(){
        return credentials;
    }

    protected UriMappings getMappings(){
        return mappings;
    }

    protected ResponseEntity exchange(String url, HttpMethod method,HttpEntity entity, Class response){
        try{
            return template.exchange(url,method,entity,response);
        }catch (Exception e){
            throw new RuntimeException("Error with api call",e);
        }
    }

    protected ResponseEntity exchange(String url, HttpMethod method,HttpEntity entity, Class response, Map variables){
        try{
                return template.exchange(url, method, entity, response, variables);
        }catch (Exception e){
            throw new RuntimeException("Error with api call",e);
        }
    }

    protected RestTemplate configureRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION," Bearer "+ getCredentials().getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }



    protected HttpEntity getEntity(Map request){
        if(request == null) {
            return new HttpEntity(getHeaders());
        }else {
            return new HttpEntity(request,getHeaders());
        }
    }



    protected HttpEntity getRequestEntity(PentadataRequest request){
        return new HttpEntity(request,getHeaders());
    }


}
