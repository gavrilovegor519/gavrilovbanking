package com.egor.gavrilovbanking.service.impl;

import com.egor.gavrilovbanking.constants.Roles;
import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.repo.UserRepo;
import com.egor.gavrilovbanking.security.JwtUtilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private JwtUtilities jwtUtilities;

    @Mock
    private UserRepo userRepository;

    @Mock
    private LoginDTO loginDTO;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void login() throws UserNotFound {
        User user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password("qwerty")
                .build();

        when(loginDTO.getUsername()).thenReturn("test1");
        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(true);
        when(userRepository.findUserByUsername("test1")).thenReturn(user);
        when(jwtUtilities.generateToken("test1", Roles.ROLE_USER)).thenReturn("test_token");

        assertEquals(userService.login(loginDTO), "test_token");
    }
}