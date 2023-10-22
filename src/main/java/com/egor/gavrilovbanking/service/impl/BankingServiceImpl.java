package com.egor.gavrilovbanking.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egor.gavrilovbanking.repo.UserRepo;
import com.egor.gavrilovbanking.service.BankingService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankingServiceImpl implements BankingService {
    private UserRepo userRepo;

    @Override
    @Transactional
    public void addMoney(long amount, String username) {
        var user = userRepo.findUserByUsername(username);
        user.setAmountOfMoney(user.getAmountOfMoney() + amount);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void getMoney(long amount, String username) {
        var user = userRepo.findUserByUsername(username);

        if (user.getAmountOfMoney() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        user.setAmountOfMoney(user.getAmountOfMoney() - amount);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void transferMoney(long amount, String sender, String reciver) {
        var user = userRepo.findUserByUsername(sender);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        var user2 = userRepo.findUserByUsername(reciver);
        if (user2 == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (user.getAmountOfMoney() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        user.setAmountOfMoney(user.getAmountOfMoney() - amount);
        user2.setAmountOfMoney(user2.getAmountOfMoney() + amount);

        userRepo.save(user);
        userRepo.save(user2);
    }

    @Override
    @Transactional
    public long getBalance(String username) {
        var user = userRepo.findUserByUsername(username);
        assert user != null;
        return user.getAmountOfMoney();
    }
    
}
