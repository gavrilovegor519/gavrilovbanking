package com.egor.gavrilovbanking.service.impl;

import com.egor.gavrilovbanking.constants.Roles;
import com.egor.gavrilovbanking.converters.UserDTOToUserConverter;
import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.entity.User;
import com.egor.gavrilovbanking.exceptions.DuplicateUser;
import com.egor.gavrilovbanking.exceptions.IncorrectPassword;
import com.egor.gavrilovbanking.exceptions.UserNotFound;
import com.egor.gavrilovbanking.repo.UserRepo;
import com.egor.gavrilovbanking.security.JwtUtilities;
import com.egor.gavrilovbanking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtilities jwtUtilities;
    private final UserRepo userRepository;
    private final UserDTOToUserConverter userDtoToUserConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDTO login) throws UserNotFound, IncorrectPassword {
        String username = login.getUsername();
        String password = login.getPassword();

        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFound("User not found."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPassword("Incorrect password!");
        }

        return jwtUtilities.generateToken(user.getUsername(), Roles.ROLE_USER);
    }

    @Override
    public void reg(UserDTO userDTO) throws DuplicateUser {
        boolean usernameIsExist =
                userRepository.existsUserByUsername(userDTO.getUsername());

        boolean telIsExist =
                userRepository.existsUserByTel(userDTO.getTel());

        boolean emailIsExist =
                userRepository.existsUserByEmail(userDTO.getEmail());

        if (usernameIsExist || telIsExist || emailIsExist) {
            throw new DuplicateUser("Duplicate registration data.");
        }

        User user = userDtoToUserConverter.convert(userDTO);
        assert user != null;
        userRepository.save(user);
    }
    
}
