package com.imenez.accountservice.api;

import com.imenez.accountservice.entity.Account;
import com.imenez.accountservice.service.AccountService;
import com.imenez.client.contract.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("account")
@RequiredArgsConstructor
/**
 htto://localhost:8080/account
 GET POST DELETE UPDATE(PUT)
 */
public class AccountApi {

    //AccountService accountService = new AccountService(); -> yerine @Service anostasyonu sayesinde DI yapar

    /*
    Constructor injection ile bu şekilde yapılabilir

    private final AccountService accountService;
    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

     */


    @Autowired
    AccountService accountService;


    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable("id") String id){

        return ResponseEntity.ok(accountService.get(id));//"ok" HTTP 200 ile döner

    }

    @PostMapping
    public ResponseEntity<AccountDto> save(@RequestBody AccountDto account){

        return ResponseEntity.ok(accountService.save(account));

    }

    @PutMapping
    public ResponseEntity<AccountDto> update(@PathVariable("id") String id, @RequestBody AccountDto account){

        return ResponseEntity.ok(accountService.update(id, account));

    }

    @DeleteMapping
    public void delete(String id){

        accountService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> get(){

        return ResponseEntity.ok(accountService.findAll());//"ok" HTTP 200 ile döner

    }

    /*public ResponseEntity<Account> pagenation(){

        return ResponseEntity.ok(accountService.pagenation(account));

    }*/
}
