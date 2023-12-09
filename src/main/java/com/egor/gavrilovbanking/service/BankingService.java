package com.egor.gavrilovbanking.service;

import com.egor.gavrilovbanking.exceptions.UserNotFound;

public interface BankingService {
    void addMoney(long amount, String username) throws UserNotFound;
    void getMoney(long amount, String username) throws UserNotFound;
    void transferMoney(long amount, String sender, String receiver) throws UserNotFound;
    long getBalance(String username) throws UserNotFound;
}
