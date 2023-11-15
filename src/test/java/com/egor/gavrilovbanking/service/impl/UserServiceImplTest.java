package com.egor.gavrilovbanking.service.impl;

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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private JwtUtilities jwtUtilities;

    @Mock
    private UserRepo userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loginWithExistUser() throws UserNotFound {
        User user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password("qwerty")
                .build();

        LoginDTO loginDTO = LoginDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(true);
        when(userRepository.findUserByUsername("test1")).thenReturn(user);

        userService.login(loginDTO);
    }

    @Test
    void loginWithNotExistUser() {
        LoginDTO loginDTO = LoginDTO.builder()
                .username("test1")
                .password("qwerty")
                .build();

        when(userRepository.existsUserByUsername("test1"))
                .thenReturn(false);

        assertThrows(UserNotFound.class, () -> userService.login(loginDTO));
    }
}