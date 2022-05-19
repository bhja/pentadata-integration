package com.accountpatrol.api.service.pentadata.impl;


import com.accountpatrol.api.dao.PentadataInstitutionDao;
import com.accountpatrol.api.dao.entity.PentadataInstitutionEntity;
import com.accountpatrol.api.pentadata.bean.UriMappings;
import com.accountpatrol.api.pentadata.model.Institution;
import com.accountpatrol.api.pentadata.model.Institutions;
import com.accountpatrol.api.service.pentadata.InstitutionService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class InstitutionServiceImpl extends AbstractBaseService implements InstitutionService {

    private final PentadataInstitutionDao institutionDao;
    private final Executor executor;

    public InstitutionServiceImpl(Credentials pCredentials, UriMappings mappings, PentadataInstitutionDao pInstitutionDao, Executor pExecutor) {
        super(pCredentials, mappings);
        institutionDao = pInstitutionDao;
        executor = pExecutor;
    }

    @Override public List<Institution> getInstitutions(int limit, int offset) {

        String url = UriComponentsBuilder.fromHttpUrl(String.format("%s%s", getMappings().getBaseUrl(), getMappings().getInstitutions()))
                .queryParam("limit", limit).queryParam("offset", offset).toUriString();

        ResponseEntity<Institutions> responseEntity = exchange(url, HttpMethod.GET, getEntity(null), Institutions.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            getLogger().debug("Retrieved the institutions for  limit: " + limit + " offset: " + offset);
        }
        Institutions institutionList = responseEntity.getBody();

        return institutionList  != null ? institutionList.getInstitutions() : new ArrayList<>();
    }

    //Look up in the database and return
    @Override @Transactional
    public List<Institution> getInstitutions(String filter) {

        List<Institution> institutionList = new ArrayList<>();
        List<PentadataInstitutionEntity> institutionEntities = institutionDao.findByName(filter);

        for(PentadataInstitutionEntity institutionEntity :institutionEntities) {
        Institution institution = new Institution();
        institution.setLogo(institutionEntity.getLogo());
        institution.setName(institutionEntity.getName());
        institution.setId(institutionEntity.getInstitutionId());
        institutionList.add(institution);
     }
        return institutionList;
    }

    /**
     * Loops through the entire data set and populates the database for institutions.
     */
    @Override
    @Transactional
    @Async
    public void refreshInstitutions() {

        int offset = 0;
        int limit = 1000;

        institutionDao.deleteAll();
        List<Institution> data = new ArrayList<>();
        List<Institution> institutionList;
        do {
            institutionList = getInstitutions(limit, offset);
            data.addAll(data.size()== 0 ? 0 : (data.size()-1),institutionList);
            offset = offset + limit;
        } while (!institutionList.isEmpty());

        saveInstitutions(data);
    }


    @Transactional
    @Async("PT-TXN")
    protected void saveInstitutions(List<Institution> institutions){
        for(Institution institution:institutions) {
            getLogger().debug("Saving institution [ " + institution.toString() + "]");
            PentadataInstitutionEntity entity = new PentadataInstitutionEntity();
            entity.setInstitutionId(institution.getId());
            entity.setLogo(institution.getLogo() != null ? institution.getLogo() : "");
            entity.setName(institution.getName());
            institutionDao.save(entity);
        }
    }


}
