package com.example.banking_app.mapper;

import com.example.banking_app.Dto.AccountDto;
import com.example.banking_app.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {

        Account account = new Account();
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());
        account.setId(accountDto.getId());

        return account;
    }


    public static  AccountDto mapToAccountDto(Account account) {

        AccountDto accountDto = new AccountDto();
        accountDto.setAccountHolderName(account.getAccountHolderName());
        accountDto.setBalance(account.getBalance());
        accountDto.setId(account.getId());

        return accountDto;
    }
}
