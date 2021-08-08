package com.everis.currentaccountservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AccountClientBean
{
    private String idAccount;
    private String accountNumber;
    private String type;
}
