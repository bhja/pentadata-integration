package com.accountpatrol.api.service.pentadata;

import com.accountpatrol.api.pentadata.model.Institution;

import java.util.List;

public interface InstitutionService {

    List<Institution> getInstitutions(int offset,int limit);

    List<Institution> getInstitutions(String filter);

    void refreshInstitutions();
}
