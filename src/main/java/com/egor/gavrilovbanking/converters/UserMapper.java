package com.egor.gavrilovbanking.converters;

import com.egor.gavrilovbanking.converters.util.EncodedMapping;
import com.egor.gavrilovbanking.converters.util.PasswordEncoderMapper;
import com.egor.gavrilovbanking.dto.UserDTO;
import com.egor.gavrilovbanking.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserMapper {
    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User map(UserDTO dto);
}
