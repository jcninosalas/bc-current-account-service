package com.everis.currentaccountservice.bean;

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
    private String status;
    private Map<String, Object> body;
}
