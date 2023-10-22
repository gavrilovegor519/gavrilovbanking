package com.egor.gavrilovbanking.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.egor.gavrilovbanking.constants.Roles;
import com.egor.gavrilovbanking.converters.UserDTOToUserConverter;
import com.egor.gavrilovbanking.dto.*;
import com.egor.gavrilovbanking.repo.*;
import com.egor.gavrilovbanking.security.JwtUtilities;
import com.egor.gavrilovbanking.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtilities jwtUtilities;
    private final UserRepo userRepository;
    private final UserDTOToUserConverter userDtoToUserConverter;

    @Override
    public String login(LoginDTO login) {
        var user = userRepository.findUserByUsername(login.getUsername());
        assert user != null;
        return jwtUtilities.generateToken(user.getUsername(), Roles.ROLE_USER);
    }

    @Override
    public void reg(UserDTO userData) {
        var user = userDtoToUserConverter.convert(userData);
        assert user != null;

        userRepository.save(user);
    }
    
}
