package com.egor.gavrilovbanking.service.impl;

import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        when(userRepository.findUserByUsername("test1")).thenReturn(Optional.ofNullable(user));

        bankingService.addMoney(30000, "test1");

        assertEquals(30000, user.getAmountOfMoney());
    }

    @Test
    void addMoneyWithNotExistUser() {
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

        when(userRepository.findUserByUsername("test1")).thenReturn(Optional.ofNullable(user));

        bankingService.getMoney(30000, "test1");

        assertEquals(20000, user.getAmountOfMoney());
    }

    @Test
    void getMoneyWithNotExistUser() {
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

        when(userRepository.findUserByUsername("test1")).thenReturn(Optional.ofNullable(user));

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

        when(userRepository.findUserByUsername("test1")).thenReturn(Optional.ofNullable(user));

        assertEquals(30000, bankingService.getBalance("test1"));
    }

    @Test
    void getBalanceWithNotExistUser() {
        assertThrows(UserNotFound.class, () -> bankingService.getBalance("test1"));
    }
}