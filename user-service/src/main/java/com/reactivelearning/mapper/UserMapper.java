package com.reactivelearning.mapper;

import com.reactivelearning.dto.UserDTO;
import com.reactivelearning.entity.User;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class UserMapper {

    public UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();
        copyProperties(entity,dto);
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User entity = new User();
        copyProperties(dto,entity);
        return entity;
    }

}
