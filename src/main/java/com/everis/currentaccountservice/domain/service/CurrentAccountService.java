package com.everis.currentaccountservice.domain.service;

import com.everis.currentaccountservice.domain.model.CurrentAccount;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountService {
    Flux<CurrentAccount> findAll();
    Mono<ResponseEntity<CurrentAccount>> getByAccountNumber(String accountNumber);
    Mono<CurrentAccount> create(String documentNumber);
    Mono<CurrentAccount> update( CurrentAccount account);
    Mono<CurrentAccount> disable(String accountNumber);
}
