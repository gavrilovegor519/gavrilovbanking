package com.egor.gavrilovbanking.controllers;

import com.egor.gavrilovbanking.exceptions.UserNotFound;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.egor.gavrilovbanking.service.BankingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BankingController {
    private final BankingService bankingService;

    @PostMapping("/user/banking/getMoney")
    public void getMoney(@RequestParam long amount, Authentication authentication) throws UserNotFound {
        bankingService.getMoney(amount, authentication.getName());
    }

    @PostMapping("/user/banking/addMoney")
    public void addMoney(@RequestParam long amount, Authentication authentication) throws UserNotFound {
        bankingService.addMoney(amount, authentication.getName());
    }

    @PostMapping("/user/banking/transferMoney")
    public void transferMoney(@RequestParam long amount,
                            @RequestParam String reciver,
                            Authentication authentication) throws UserNotFound {
        bankingService.transferMoney(amount, authentication.getName(), reciver);
    }

    @GetMapping("/user/banking/balance")
    public long balance(Authentication authentication) throws UserNotFound {
        return bankingService.getBalance(authentication.getName());
    }
}
