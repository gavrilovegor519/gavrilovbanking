package com.egor.gavrilovbanking.service.impl;

import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.exceptions.DuplicateUser;
import com.egor.gavrilovbanking.exceptions.IncorrectPassword;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.repo.UserRepo;
import com.egor.gavrilovbanking.security.JwtUtilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private JwtUtilities jwtUtilities;

    @Mock
    private UserRepo userRepository;

    @Spy
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loginWithExistUser() throws UserNotFound, IncorrectPassword {
        User user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password(passwordEncoder.encode("test"))
                .build();

        LoginDTO loginDTO = LoginDTO.builder()
                .username(user.getUsername())
                .password("test")
                .build();

        when(userRepository.findUserByUsername("test1")).thenReturn(Optional.of(user));

        userService.login(loginDTO);
    }

    @Test
    void loginWithExistUserButWithIncorrectPassword() {
        User user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password(passwordEncoder.encode("test1"))
                .build();

        LoginDTO loginDTO = LoginDTO.builder()
                .username(user.getUsername())
                .password("test")
                .build();

        when(userRepository.findUserByUsername("test1")).thenReturn(Optional.of(user));

        assertThrows(IncorrectPassword.class, () -> userService.login(loginDTO));
    }

    @Test
    void loginWithNotExistUser() {
        LoginDTO loginDTO = LoginDTO.builder()
                .username("test1")
                .password("qwerty")
                .build();

        assertThrows(UserNotFound.class, () -> userService.login(loginDTO));
    }

    @Test
    void registrationWithDuplicatedUser() {
        UserDTO user = UserDTO.builder()
                .username("test1")
                .email("1@1.ru")
                .tel(1234)
                .password("test")
                .build();

        when(userRepository.existsUserByUsername(any())).thenReturn(true);

        assertThrows(DuplicateUser.class, () -> userService.reg(user));
    }
}