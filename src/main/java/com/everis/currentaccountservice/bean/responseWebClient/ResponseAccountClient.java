package com.everis.currentaccountservice.bean.responseWebClient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ResponseAccountClient
{
    private String message;
    private Integer status;
    private Map<String, Object> body;
}
