package com.egor.gavrilovbanking.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.entity.User;

@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User user) {

        return UserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }
}
