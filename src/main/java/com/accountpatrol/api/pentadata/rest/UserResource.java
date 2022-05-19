package com.accountpatrol.api.pentadata.rest;

import com.accountpatrol.api.pentadata.model.PentadataResponse;
import com.accountpatrol.api.service.pentadata.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pentadata/user/")
public class UserResource {

    private UserService service;

    public UserResource(UserService  pUserService){
        service = pUserService;
    }


    @GetMapping("")
    public ResponseEntity<PentadataResponse> getUser(@RequestParam String emailId){
        return ResponseEntity.ok(service.getUserByEmailId(emailId));
    }

}
