package com.egor.gavrilovbanking.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.repo.UserRepo;
import com.egor.gavrilovbanking.service.BankingService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BankingServiceImpl implements BankingService {
    private UserRepo userRepo;

    @Override
    @Transactional
    public void addMoney(long amount, String username) {
        User user = userRepo.findUserByUsername(username);
        user.setAmountOfMoney(user.getAmountOfMoney() + amount);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void getMoney(long amount, String username) {
        User user = userRepo.findUserByUsername(username);
        user.setAmountOfMoney(user.getAmountOfMoney() - amount);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void transferMoney(long amount, String sender, String getter) {
        User user = userRepo.findUserByUsername(sender);
        user.setAmountOfMoney(user.getAmountOfMoney() - amount);
        User user2 = userRepo.findUserByUsername(getter);
        user2.setAmountOfMoney(user.getAmountOfMoney() + amount);
        userRepo.save(user);
        userRepo.save(user2);
    }

    @Override
    @Transactional
    public long getBalance(String username) {
        User user = userRepo.findUserByUsername(username);
        return user.getAmountOfMoney();
    }
    
}
