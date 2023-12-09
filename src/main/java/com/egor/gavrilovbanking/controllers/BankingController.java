package com.egor.gavrilovbanking.controllers;

import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.service.BankingService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                            @RequestParam String receiver,
                            Authentication authentication) throws UserNotFound {
        bankingService.transferMoney(amount, authentication.getName(), receiver);
    }

    @GetMapping("/user/banking/balance")
    public long balance(Authentication authentication) throws UserNotFound {
        return bankingService.getBalance(authentication.getName());
    }
}
