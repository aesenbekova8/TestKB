package com.example.testkb.service.impl;

import com.example.testkb.entity.Role;
import com.example.testkb.entity.enums.RoleName;
import com.example.testkb.repository.RoleRepository;
import com.example.testkb.service.RoleService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

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

    @Override
    @Transactional(readOnly = true)
    public Role getByName(@NonNull RoleName name) {
        return roleRepository.findByName(RoleName.ROLE_CASHIER)
                .orElseThrow(() -> new EntityNotFoundException(format("Role: %s not found", RoleName.ROLE_CASHIER)));
    }
}
