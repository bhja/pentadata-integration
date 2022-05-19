package com.accountpatrol.api.pentadata.rest.management;

import com.accountpatrol.api.service.pentadata.InstitutionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Helper to load the institutions to the database.
 */
@RestController
@RequestMapping("/api/pentadata/management/institutions")
public class ManagementEndpoint {

    private InstitutionService service;
    public ManagementEndpoint(InstitutionService pService){
        service = pService;
    }

    @GetMapping("/refresh")
    public void refresh(){
        service.refreshInstitutions();
    }
}
