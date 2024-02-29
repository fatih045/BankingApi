package com.example.banking_app.controller;

import com.example.banking_app.Dto.AccountDto;
import com.example.banking_app.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add account rest api

    @PostMapping
    public ResponseEntity<AccountDto> createAccount( @RequestBody AccountDto accountDto) {

       return  new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
// getAccountRestapi
    @GetMapping("/{id}")
    public  ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id){

        return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.OK);
    }


    @PutMapping("/{id}/amount")
    //deposit
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id, @RequestParam("amount") double amount){

        return new ResponseEntity<>(accountService.deposit(id,amount),HttpStatus.OK);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long id, @RequestParam("amount") double amount){

        return new ResponseEntity<>(accountService.withdraw(id,amount),HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
       return  new ResponseEntity<>(accountService.getAllAccounts(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable("id") Long id){

        accountService.deleteAccountById(id);
        return new ResponseEntity<>("Account deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/totalMoney")
    public double getTotalMoney(){

        return accountService.getTotalMoney();
    }

    @GetMapping("/AllName")
    public List<String> allName() {
        return accountService.getAllName();
    }

    @GetMapping("/AllRich")
    public List<String> allRich(){
        return accountService.allRich();
    }

    @PutMapping("/remittance")
    public void remittance(@RequestParam("id") Long id,@RequestParam("id2")Long id2 , @RequestParam("balance") double balance){
        accountService.remittance(id, id2, balance);
    }

}
