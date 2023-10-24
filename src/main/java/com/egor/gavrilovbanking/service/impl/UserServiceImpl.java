package com.egor.gavrilovbanking.service.impl;

import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
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
    public String login(LoginDTO login) throws UserNotFound {
        String username = login.getUsername();

        boolean userIsExist = userRepository.existsUserByUsername(username);
        if (!userIsExist) throw new UserNotFound("User not found!");

        User user = userRepository.findUserByUsername(username);
        return jwtUtilities.generateToken(user.getUsername(), Roles.ROLE_USER);
    }

    @Override
    public void reg(UserDTO userDTO) {
        User user = userDtoToUserConverter.convert(userDTO);
        assert user != null;
        userRepository.save(user);
    }
    
}
