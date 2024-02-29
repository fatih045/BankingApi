package com.example.banking_app.Dto;

import lombok.Data;

@Data
public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double balance;
}
