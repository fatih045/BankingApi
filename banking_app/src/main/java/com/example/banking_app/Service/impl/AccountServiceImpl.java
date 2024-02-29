package com.example.banking_app.Service.impl;

import com.example.banking_app.Dto.AccountDto;
import com.example.banking_app.Service.AccountService;
import com.example.banking_app.entity.Account;
import com.example.banking_app.mapper.AccountMapper;
import com.example.banking_app.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto account) {

        Account account1= AccountMapper.mapToAccount(account);
      Account SAVED = accountRepository.save(account1);


        return AccountMapper.mapToAccountDto(SAVED);
    }

    @Override
    public AccountDto getAccountById(Long id) {
     Account account=   accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=   accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

         account.setBalance(account.getBalance()+amount);
accountRepository.save(account);

        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account=   accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));


        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        else {
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);

        return AccountMapper.mapToAccountDto(account);
        }
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();

        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccountById(Long id) {
       if ( !accountRepository.existsById(id)) {
           throw new RuntimeException("Account not found");
       }
       accountRepository.deleteById(id);
    }


    @Override
    public double getTotalMoney() {
        return accountRepository.findAll().stream().mapToDouble(Account::getBalance).sum();
    }

    @Override
    public List<String> getAllName() {


        return accountRepository.findAll().stream().map(Account::getAccountHolderName).collect(Collectors.toList());
    }

    @Override
    public List<String> allRich() {

        return   accountRepository.findAll().stream().filter(Account ->Account.getBalance()>=100000).map(Account::getAccountHolderName).collect(Collectors.toList());



    }

    @Override
    public void remittance(Long id, Long id2,double balance) {


        Optional<Account> sourceAccountOpt=accountRepository.findById(id);
        Optional<Account> targetAccountOpt=accountRepository.findById(id2);

        if (sourceAccountOpt.isPresent() && targetAccountOpt.isPresent()) {


            Account sourceAccount=sourceAccountOpt.get();
            Account targetAccount=targetAccountOpt.get();

           if (sourceAccount.getBalance()>balance) {

               double sourceNewBalance=sourceAccount.getBalance()-balance;
               double targetNewBalance=targetAccount.getBalance()+balance;


               sourceAccount.setBalance(sourceNewBalance);
               targetAccount.setBalance(targetNewBalance);


               accountRepository.save(sourceAccount);
               accountRepository.save(targetAccount);
           }
           else {
               throw new RuntimeException("Yetersiz Bakiye");
           }

        }
        else {
            throw new RuntimeException("Kullanıcı hesapları bulunamadı");
        }





    }


}
