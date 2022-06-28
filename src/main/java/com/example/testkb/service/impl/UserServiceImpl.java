package com.example.testkb.service.impl;

import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.RoleName;
import com.example.testkb.repository.RoleRepository;
import com.example.testkb.repository.UserRepository;
import com.example.testkb.service.BankService;
import com.example.testkb.service.UserService;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final BankService bankService;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           BankService bankService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.bankService = bankService;
    }

    @Override
    @Transactional
    public User create(@NonNull UserCreateRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new EntityNotFoundException(format("User with username: %s has existed yet", request.getUsername()));
        }

        Bank bank = bankService.getById(request.getBankId());
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBank(bank);

        Role userRole = roleRepository.findByName(RoleName.ROLE_CASHIER)
                .orElseThrow(() -> new EntityNotFoundException(format("Role: %s not found", RoleName.ROLE_CASHIER)));

        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User changePassword(@NonNull PasswordUpdateRequest request) {
        User user = userRepository.getReferenceById(request.getId());

        user.setPassword(request.getNewPassword());

        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return Optional.of(userRepository.getReferenceById(id)).
                orElseThrow(() -> new EntityNotFoundException(format("User with id: %s not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(@NonNull String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", username)
                        )
                );
    }
}
