package com.example.testkb.mapper;

import com.example.testkb.dto.response.UserView;
import com.example.testkb.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserViewMapper {
    public UserView toUserView(User user){
        return UserView.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}