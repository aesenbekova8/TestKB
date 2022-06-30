package com.example.testkb.service;


import com.example.testkb.entity.Role;
import com.example.testkb.entity.enums.RoleName;

public interface RoleService {
    Role getById(Long id);
    Role getByName(RoleName name);
}
