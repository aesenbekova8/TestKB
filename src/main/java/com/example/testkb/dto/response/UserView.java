package com.example.testkb.dto.response;

import com.example.testkb.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserView {
    private Long id;
    private String username;
    private Set<Role> roles;
}