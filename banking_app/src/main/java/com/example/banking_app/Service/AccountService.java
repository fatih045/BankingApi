package com.example.banking_app.Service;

import com.example.banking_app.Dto.AccountDto;

import java.util.List;

public interface AccountService {


    AccountDto createAccount(AccountDto account);


    AccountDto getAccountById(Long id);


    AccountDto deposit(Long id, double amount);


    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccountById(Long id);


    double getTotalMoney();

    List<String> getAllName();


    List<String> allRich();


    void  remittance(Long id,Long id2,double balance);
}
