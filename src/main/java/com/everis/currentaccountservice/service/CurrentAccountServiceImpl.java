package com.everis.currentaccountservice.service;

import com.everis.currentaccountservice.bean.AccountClientBean;
import com.everis.currentaccountservice.domain.model.CurrentAccount;
import com.everis.currentaccountservice.domain.repository.CurrentAccountRepository;
import com.everis.currentaccountservice.domain.service.CurrentAccountService;
import com.everis.currentaccountservice.utils.Utils;
import com.everis.currentaccountservice.utils.Webclients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;


@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {

    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    @Autowired
    private Webclients webclients;


    public Flux<CurrentAccount> findAll() {
        return currentAccountRepository.findAll();
    }

    public Mono<ResponseEntity<CurrentAccount>> getByAccountNumber(String accountNumber)  {
        return currentAccountRepository.findByAccountNumber(accountNumber)
                .map(a -> ResponseEntity.ok().body(a))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<CurrentAccount> create(String documentNumber) {
        CurrentAccount account = CurrentAccount.setNewCurrentAccount();
        AccountClientBean accountClientBean = AccountClientBean.buildAccountClient(account, documentNumber);
        return webclients.sendClientAccount(accountClientBean, documentNumber)
                .flatMap(responseAccountClient -> currentAccountRepository.insert(account))
                .switchIfEmpty(Mono.error(new Exception("Error on create account")))
                .onErrorResume(throwable -> Mono.error(new Exception("Error on create account")));
    }

    public Mono<CurrentAccount> update( CurrentAccount account) {
       return currentAccountRepository.findByAccountNumber(account.getAccountNumber())
               .flatMap( a -> {
                   account.setId(a.getId());
                   return currentAccountRepository.save(account);
               })
               .switchIfEmpty(Mono.empty());
    }

    public Mono<CurrentAccount> disable(String accountNumber) {
        return currentAccountRepository.findByAccountNumber(accountNumber)
                .flatMap( a -> {
                    a.setIsActive(false);
                    return currentAccountRepository.save(a);
                })
                .switchIfEmpty(Mono.empty());
    }



}
