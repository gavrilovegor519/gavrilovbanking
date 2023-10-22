package com.egor.gavrilovbanking.unittests;

import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.repo.UserRepo;
import com.egor.gavrilovbanking.service.impl.BankingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BankingServiceUnitTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private BankingServiceImpl bankingService;

    @Test
    public void addMoneyTesting() {
        var user = User.builder()
                .username("test1")
                .id(0L)
                .tel(88005553535L)
                .amountOfMoney(30000)
                .password("qwerty")
                .build();

        when(userRepo.findUserByUsername("test1")).thenReturn(user);

        bankingService.addMoney(30000, "test1");

        assertEquals(60000, user.getAmountOfMoney());
    }

    @Test
    public void getMoneyTesting() {
        var user = User.builder()
                .username("test1")
                .id(0L)
                .tel(88005553535L)
                .amountOfMoney(30000)
                .password("qwerty")
                .build();

        when(userRepo.findUserByUsername("test1")).thenReturn(user);

        bankingService.getMoney(15000, "test1");

        assertEquals(15000, user.getAmountOfMoney());
    }

    @Test
    public void transferMoneyTesting() {
        var user = User.builder()
                .username("test1")
                .id(0L)
                .tel(88005553535L)
                .amountOfMoney(30000)
                .password("qwerty")
                .build();

        var user2 = User.builder()
                .username("test2")
                .id(1L)
                .tel(88005556666L)
                .amountOfMoney(5000)
                .password("qwerty")
                .build();

        when(userRepo.findUserByUsername("test1")).thenReturn(user);
        when(userRepo.findUserByUsername("test2")).thenReturn(user2);

        bankingService.transferMoney(15000, "test1", "test2");



        assertEquals(15000, user.getAmountOfMoney());
        assertEquals(20000, user2.getAmountOfMoney());
    }

    @Test
    public void getBalanceTesting() {
        var user = User.builder()
                .username("test1")
                .id(0L)
                .tel(88005553535L)
                .amountOfMoney(30000)
                .password("qwerty")
                .build();

        when(userRepo.findUserByUsername("test1")).thenReturn(user);

        assertEquals(30000, bankingService.getBalance("test1"));
    }
}
