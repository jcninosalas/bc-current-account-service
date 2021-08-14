package com.everis.currentaccountservice.domain.model;

import com.everis.currentaccountservice.utils.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Document
public class CurrentAccount {

    @Id
    private String id;
    private String accountNumber;
    private BigDecimal accountBalance;
    private Boolean isActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modifiedAt;
    private int maintenanceFee;


    static public CurrentAccount setNewCurrentAccount() {
        CurrentAccount account = new CurrentAccount();
        account.setAccountNumber(Utils.generateAccountNumber());
        account.setCreatedAt(new Date());
        account.setAccountBalance(new BigDecimal(0));
        account.setIsActive(true);
        account.setMaintenanceFee(3);
        return account;
    }
}
