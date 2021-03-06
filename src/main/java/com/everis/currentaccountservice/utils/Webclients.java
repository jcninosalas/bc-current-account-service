package com.everis.currentaccountservice.utils;

import com.everis.currentaccountservice.bean.AccountClientBean;
import com.everis.currentaccountservice.bean.ResponseAccountClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class Webclients
{
    String endpointCustomerP = "http://172.25.0.2:8080/";
    String pathSendAccount = "personals-customers/accounts/";

    private final WebClient client;

    public Webclients(WebClient.Builder builder) {
        this.client = builder.baseUrl(endpointCustomerP).build();
    }

    public Mono<ResponseAccountClient> sendClientAccount(AccountClientBean clientBean, String documentNumber){
        return this.client.post().uri(pathSendAccount.concat(documentNumber))
                .body(Mono.just(clientBean), AccountClientBean.class)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                            clientResponse -> Mono.empty())
                .bodyToMono(ResponseAccountClient.class);
    }

    public Mono<ResponseAccountClient> getAccountClient(String accountNumber){
        return this.client.get().uri(pathSendAccount.concat(accountNumber))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        clientResponse -> Mono.empty())
                .bodyToMono(ResponseAccountClient.class);
    }
}
