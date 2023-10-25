package com.egor.gavrilovbanking.service.impl;

import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankingServiceImplTest {

    @Mock
    private UserRepo userRepository;

    @InjectMocks
    private BankingServiceImpl bankingService;

    @Test
    void addMoneyWithExistUser() throws UserNotFound {
        User user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password("qwerty")
                .build();

        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(true);
        when(userRepository.findUserByUsername("test1")).thenReturn(user);

        bankingService.addMoney(30000, "test1");

        assertEquals(30000, user.getAmountOfMoney());
    }

    @Test
    void addMoneyWithNotExistUser() {
        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(false);

        assertThrows(UserNotFound.class, () -> bankingService.addMoney(30000, "test1"));
    }

    @Test
    void getMoneyWithExistUser() throws UserNotFound {
        User user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password("qwerty")
                .amountOfMoney(50000)
                .build();

        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(true);
        when(userRepository.findUserByUsername("test1")).thenReturn(user);

        bankingService.getMoney(30000, "test1");

        assertEquals(20000, user.getAmountOfMoney());
    }

    @Test
    void getMoneyWithNotExistUser() {
        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(false);

        assertThrows(UserNotFound.class, () -> bankingService.getMoney(30000, "test1"));
    }

    @Test
    void getMoneyWithExistUserButWithInsufficientFunds() {
        User user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password("qwerty")
                .build();

        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(true);
        when(userRepository.findUserByUsername("test1")).thenReturn(user);

        assertThrows(IllegalArgumentException.class,
                () -> bankingService.getMoney(30000, "test1"));
    }

    // TODO: Add tests for transfer money.
    @Test
    void transferMoney() {
    }

    @Test
    void getBalanceWithExistUser() throws UserNotFound {
        User user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password("qwerty")
                .amountOfMoney(30000)
                .build();

        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(true);
        when(userRepository.findUserByUsername("test1")).thenReturn(user);

        assertEquals(30000, bankingService.getBalance("test1"));
    }

    @Test
    void getBalanceWithNotExistUser() {
        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(false);

        assertThrows(UserNotFound.class, () -> bankingService.getBalance("test1"));
    }
}