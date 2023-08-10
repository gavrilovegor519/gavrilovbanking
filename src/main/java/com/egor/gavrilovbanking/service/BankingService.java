package com.egor.gavrilovbanking.service;

public interface BankingService {
    void addMoney(long amount, String username);
    void getMoney(long amount, String username);
    void transferMoney(long amount, String sender, String reciver);
    long getBalance(String username);
}
