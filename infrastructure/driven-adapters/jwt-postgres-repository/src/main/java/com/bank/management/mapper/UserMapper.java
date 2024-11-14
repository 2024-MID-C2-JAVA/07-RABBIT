package com.bank.management.mapper;

import com.bank.management.data.UserEntity;
import com.bank.management.User;
import java.util.Collections;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(Collections.emptyList());
        return userEntity;
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(userEntity.getUsername(), userEntity.getPassword());
    }
}
