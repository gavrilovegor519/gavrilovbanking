package com.egor.gavrilovbanking.unittests;

import com.egor.gavrilovbanking.constants.Roles;
import com.egor.gavrilovbanking.converters.UserDTOToUserConverter;
import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.repo.UserRepo;
import com.egor.gavrilovbanking.security.JwtUtilities;
import com.egor.gavrilovbanking.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {
    @Mock
    private JwtUtilities jwtUtilities;

    @Mock
    private UserRepo userRepository;

    @Mock
    private UserDTOToUserConverter userDtoToUserConverter;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void loginTest() {
        var user = User.builder()
                .id(0L)
                .username("test1")
                .email("1@1.ru")
                .password("qwerty")
                .amountOfMoney(30000)
                .tel(88005553535L)
                .build();

        var loginDTO = LoginDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        when(userRepository.findUserByUsername(any())).thenReturn(user);
        when(jwtUtilities.generateToken(any(), any())).thenReturn("test");

        assertEquals("test",
                userService.login(loginDTO));
    }
}
