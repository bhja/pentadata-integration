package com.accountpatrol.api.pentadata.rest;

import com.accountpatrol.api.pentadata.model.Institution;
import com.accountpatrol.api.service.pentadata.InstitutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/pentadata/institutions")
public class InstitutionResource {

    private InstitutionService service;

    public InstitutionResource(InstitutionService pService){
        service = pService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Institution>> getInstitutions(@RequestParam(required = false,defaultValue = "0") int offset  ,
            @RequestParam(required = false,defaultValue = "100")  int limit){

        return ResponseEntity.ok(service.getInstitutions(limit,offset));
    }

    @GetMapping(value="/search")
    public ResponseEntity<List<Institution>> getInstitutions(@RequestParam(required = false) String searchTxt){
        return ResponseEntity.ok(service.getInstitutions(searchTxt));
    }




}
