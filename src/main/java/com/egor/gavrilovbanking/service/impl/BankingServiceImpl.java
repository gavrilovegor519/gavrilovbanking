package com.egor.gavrilovbanking.service.impl;

import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
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
    public void addMoney(long amount, String username) throws UserNotFound {
        boolean userIsExist = userRepo.existsUserByUsername(username);
        if (!userIsExist) throw new UserNotFound("User not found!");

        User user = userRepo.findUserByUsername(username);

        user.setAmountOfMoney(user.getAmountOfMoney() + amount);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void getMoney(long amount, String username) throws UserNotFound {
        boolean userIsExist = userRepo.existsUserByUsername(username);
        if (!userIsExist) throw new UserNotFound("User not found!");

        User user = userRepo.findUserByUsername(username);

        if (user.getAmountOfMoney() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        user.setAmountOfMoney(user.getAmountOfMoney() - amount);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void transferMoney(long amount, String sender, String receiver) throws UserNotFound {
        boolean userIsExist = userRepo.existsUserByUsername(sender);
        if (!userIsExist) throw new UserNotFound("Sender not found!");

        boolean userIsExist2 = userRepo.existsUserByUsername(receiver);
        if (!userIsExist2) throw new UserNotFound("Receiver not found!");

        User user = userRepo.findUserByUsername(sender);
        User user2 = userRepo.findUserByUsername(receiver);

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
    public long getBalance(String username) throws UserNotFound {
        boolean userIsExist = userRepo.existsUserByUsername(username);
        if (!userIsExist) throw new UserNotFound("User not found!");

        User user = userRepo.findUserByUsername(username);
        return user.getAmountOfMoney();
    }
    
}
