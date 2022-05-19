package com.accountpatrol.api.pentadata.rest;

import com.accountpatrol.api.pentadata.model.Account;
import com.accountpatrol.api.pentadata.model.PentadataRequest;
import com.accountpatrol.api.pentadata.model.PentadataResponse;
import com.accountpatrol.api.pentadata.model.Transaction;
import com.accountpatrol.api.service.pentadata.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/api/pentadata/accounts")
public class AccountResource  {


    private final AccountService accountService;

    public AccountResource(final AccountService pAccountService){
        accountService = pAccountService;
    }

    @PostMapping(value="/")
    public ResponseEntity<PentadataResponse> createAccount(@RequestBody PentadataRequest body){
        return ResponseEntity.ok(accountService.createAccount(body));
    }

    /**
     * Fetches the accounts for the given personId that was created for the first time.
     * @param personId
     * @return
     */
    @GetMapping(value = "/{personId}")
    public ResponseEntity<List<Account>> getAccounts(@PathVariable("personId") String personId,HttpServletRequest request){
        String userId = request.getHeader("userId");
        if(userId == null){
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(accountService.getAccounts(userId,personId));
    }

    @GetMapping(value = "/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable("accountId") String accountId,
            HttpServletRequest request){
        String userId = request.getHeader("userId");if(userId == null){
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(accountService.getAccountTransactions(userId,accountId));
    }

    @GetMapping(value = "/{accountId}/ach")
    public ResponseEntity<PentadataResponse> getAccountACH(@PathVariable("accountId") String accountId){
        return ResponseEntity.ok(accountService.getAccountACH(accountId));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Integer> deleteAccount(@PathVariable("accountId") String accountId){
        return ResponseEntity.ok(accountService.deleteAccount(accountId));
    }





}
