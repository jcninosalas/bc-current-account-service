package com.everis.currentaccountservice.controller;

import com.everis.currentaccountservice.domain.model.CurrentAccount;
import com.everis.currentaccountservice.domain.service.CurrentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RequestMapping("/personal-current-accounts")
@RestController
public class CurrentAccountController {

    @Autowired
    private CurrentAccountService currentAccountService;

    @PostMapping("")
    public Mono<CurrentAccount> createAccount(@RequestParam String documentNumber) {
        return currentAccountService.create(documentNumber);
    }

    @GetMapping("/{accountId}")
    public Mono<ResponseEntity<CurrentAccount>> getAccountById(@PathVariable(name = "accountId") String accountId) {
        return currentAccountService.getByAccountNumber(accountId);
    }

    @GetMapping("")
    public Flux<CurrentAccount> getAllAccounts(){
        return currentAccountService.findAll();
    }

    @PutMapping("/disable")
    public Mono<ResponseEntity<CurrentAccount>> disableAccount(@RequestParam String account) {
        return currentAccountService.disable(account)
                .flatMap( a -> Mono.just(ResponseEntity.ok(a)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("")
    public Mono<ResponseEntity<CurrentAccount>> updateAccount(@RequestBody CurrentAccount account) {
        return currentAccountService.update(account)
                .flatMap(a -> Mono.just(ResponseEntity.ok(a)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
