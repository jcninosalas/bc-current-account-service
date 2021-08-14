package com.everis.currentaccountservice.domain.repository;

import com.everis.currentaccountservice.domain.model.CurrentAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CurrentAccountRepository extends ReactiveMongoRepository<CurrentAccount, String> {

    Mono<CurrentAccount> findByAccountNumber(String account);

    //Optional<CurrentAccount> findByAccountNumber(String account);

}
