package com.egor.gavrilovbanking.service.impl;

import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.repo.UserRepo;
import com.egor.gavrilovbanking.service.BankingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BankingServiceImpl implements BankingService {
    private UserRepo userRepo;

    @Override
    @Transactional
    public void addMoney(long amount, String username) throws UserNotFound {
        User user = userRepo.findUserByUsername(username).orElseThrow(() -> new UserNotFound("User not found."));
        user.setAmountOfMoney(user.getAmountOfMoney() + amount);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void getMoney(long amount, String username) throws UserNotFound {
        User user = userRepo.findUserByUsername(username).orElseThrow(() -> new UserNotFound("User not found."));

        if (user.getAmountOfMoney() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        user.setAmountOfMoney(user.getAmountOfMoney() - amount);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void transferMoney(long amount, String sender, String receiver) throws UserNotFound {
        User user = userRepo.findUserByUsername(sender).orElseThrow(() -> new UserNotFound("User not found."));
        User user2 = userRepo.findUserByUsername(receiver).orElseThrow(() -> new UserNotFound("User not found."));

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
        User user = userRepo.findUserByUsername(username).orElseThrow(() -> new UserNotFound("User not found."));;
        return user.getAmountOfMoney();
    }
    
}
