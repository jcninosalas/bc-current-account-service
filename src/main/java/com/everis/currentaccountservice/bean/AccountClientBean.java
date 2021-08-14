package com.everis.currentaccountservice.bean;

import com.everis.currentaccountservice.domain.model.CurrentAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AccountClientBean
{
    private String idAccount;
    private String accountNumber;
    private String type;
    private BigDecimal amountTotal;

    static public AccountClientBean buildAccountClient(CurrentAccount currentAccount, String documentNumber) {
        return new AccountClientBean(currentAccount.getId(),
                currentAccount.getAccountNumber(),
                "Current", currentAccount.getAccountBalance());
    }
}
