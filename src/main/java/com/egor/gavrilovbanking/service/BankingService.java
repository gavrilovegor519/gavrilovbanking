package com.egor.gavrilovbanking.service;

import com.egor.gavrilovbanking.entity.User;

public interface BankingService {
    void addMoney(long amount, User user);
    void getMoney(long amount, User user);
    void transferMoney(long amount, User sender, User getter);
}
