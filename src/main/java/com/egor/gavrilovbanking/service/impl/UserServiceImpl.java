package com.egor.gavrilovbanking.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egor.gavrilovbanking.constants.Roles;
import com.egor.gavrilovbanking.converters.UserDTOToUserConverter;
import com.egor.gavrilovbanking.dto.*;
import com.egor.gavrilovbanking.entity.*;
import com.egor.gavrilovbanking.repo.*;
import com.egor.gavrilovbanking.security.JwtUtilities;
import com.egor.gavrilovbanking.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtilities jwtUtilities;
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final UserDTOToUserConverter userDtoToUserConverter;

    @Override
    @Transactional
    public String login(LoginDTO login) {
        User user = userRepository.findUserByUsername(login.getUsername());
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getAuthority()));
        return jwtUtilities.generateToken(user.getUsername(), rolesNames);
    }

    @Override
    @Transactional
    public void reg(UserDTO userData) {
        User user = userDtoToUserConverter.convert(userData);
        assert user != null;
        user.setRoles(new HashSet<>());

        Role role = roleRepository.getRoleByName(Roles.ROLE_USER);
        user.getRoles().add(role);

        userRepository.save(user);
    }
    
}
