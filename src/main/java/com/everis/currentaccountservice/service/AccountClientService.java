package com.everis.currentaccountservice.service;

import com.everis.currentaccountservice.bean.AccountClientBean;
import com.everis.currentaccountservice.bean.ResponseAccountClient;
import com.everis.currentaccountservice.repository.CurrentAccountRepository;
import com.everis.currentaccountservice.utils.Webclients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
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
                                    "Current");

                    return webclients.sendClientAccount(clientBean, documentNumber);
                });
    }
}
