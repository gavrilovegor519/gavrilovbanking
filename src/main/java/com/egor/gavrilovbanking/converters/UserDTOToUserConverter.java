package com.egor.gavrilovbanking.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.entity.User;

@Component
@RequiredArgsConstructor
public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User convert(UserDTO userDTO) {
        User.UserBuilder builder = User.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .tel(userDTO.getTel());

        builder.password(passwordEncoder.encode(userDTO.getPassword()));

        return builder.build();
    }
}
