package com.accountpatrol.api.pentadata.rest;

import com.accountpatrol.api.pentadata.model.PentadataRequest;
import com.accountpatrol.api.pentadata.model.PentadataResponse;
import com.accountpatrol.api.pentadata.model.Person;
import com.accountpatrol.api.service.pentadata.AuthService;
import com.accountpatrol.api.service.pentadata.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/pentadata/person")
public class PersonResource  {


    private final PersonService personService;
    private final AuthService authService;

    public PersonResource(PersonService pPersonService, AuthService pAuthService){

        personService = pPersonService;
        authService = pAuthService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<PentadataResponse> getPerson(@RequestParam String emailId){
           return ResponseEntity.ok(personService.getPersonDetails(emailId));
    }


    @PostMapping(value="/")
    public ResponseEntity<PentadataResponse> createPerson(@RequestParam String userId, HttpServletRequest request){
        if(userId == null){
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(personService.createPerson(userId));
    }


    @GetMapping(value = "/consent/signature/{personId}/{useCase}")
    public ResponseEntity<PentadataResponse> getConsentInformation(@PathVariable("personId") String personId, @PathVariable("useCase") String useCase){
        return ResponseEntity.ok(authService.getConsentInformation(personId,useCase));
    }


    @Deprecated
    @PostMapping(value="/{personId}/authenticate")
    public ResponseEntity<PentadataResponse> authenticatePerson(@RequestBody Person person,@PathVariable("personId")String personId){
      return   ResponseEntity.ok(authService.authenticatePerson(personId,person));
    }

    @Deprecated
    @PostMapping(value = "/{personId}/verify")
    public ResponseEntity<PentadataResponse> verifyPerson(@PathVariable("personId")String personId,@RequestBody PentadataRequest request){
        return ResponseEntity.ok(authService.verifyPerson(personId,request));
    }






}
