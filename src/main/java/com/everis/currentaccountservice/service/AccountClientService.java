package com.everis.currentaccountservice.service;

import com.everis.currentaccountservice.bean.AccountClientBean;
import com.everis.currentaccountservice.bean.ResponseAccountClient;
import com.everis.currentaccountservice.repository.CurrentAccountRepository;
import com.everis.currentaccountservice.utils.Webclients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class AccountClientService
{
    @Autowired
    Webclients webclients;

    @Autowired
    private CurrentAccountRepository repository;

    public Mono<ResponseAccountClient> sendClientAccount(String documentNumber, String numberAccount){
        return repository.findByAccountNumber(numberAccount)
                .flatMap(currentAccount -> {
                    AccountClientBean clientBean =
                            new AccountClientBean(currentAccount.getId(),
                                    currentAccount.getAccountNumber(),
                                    "Current", currentAccount.getAccountBalance());

                    return webclients.sendClientAccount(clientBean, documentNumber);
                });
    }

    public ResponseAccountClient getObjectToMono(String accountNumber){
        return webclients.getAccountClient(accountNumber)
                .map(responseAccountClient -> {
                    log.info("Obteniendo Objeto : {}", responseAccountClient);

                    return responseAccountClient;
                })
                .block(Duration.of(1000, ChronoUnit.MILLIS));
    }
}
