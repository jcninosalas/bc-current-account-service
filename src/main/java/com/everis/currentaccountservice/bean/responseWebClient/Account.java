package com.everis.currentaccountservice.bean.responseWebClient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Account
{
    private String _id;
    private String accountNumber;
    private String type;
}
