package com.egor.gavrilovbanking.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.egor.gavrilovbanking.service.BankingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BankingController {
    private final BankingService bankingService;

    @PostMapping("/user/banking/getMoney")
    public void getMoney(@RequestParam long amount, Authentication authentication) {
        bankingService.getMoney(amount, authentication.getName());
    }

    @PostMapping("/user/banking/addMoney")
    public void addMoney(@RequestParam long amount, Authentication authentication) {
        bankingService.addMoney(amount, authentication.getName());
    }

    @PostMapping("/user/banking/transferMoney")
    public void transferMoney(@RequestParam long amount,
                            @RequestParam String reciver,
                            Authentication authentication) {
        bankingService.transferMoney(amount, authentication.getName(), reciver);
    }

    @GetMapping("/user/banking/balance")
    public long balance(Authentication authentication) {
        return bankingService.getBalance(authentication.getName());
    }
}
