package com.example.testkb.service.impl;

import com.example.testkb.entity.Role;
import com.example.testkb.repository.RoleRepository;
import com.example.testkb.service.RoleService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(@NonNull Long id) {
        return roleRepository.getById(id);
    }
}
